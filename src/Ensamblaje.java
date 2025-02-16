import java.util.Scanner;

public class Ensamblaje {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Ingrese el número de operarios en producción y calidad: ");
        int numOperarios = scanner.nextInt();

        System.out.print("Ingrese el número total de productos a producir: ");
        int numProductos = scanner.nextInt();

        System.out.print("Ingrese la capacidad máxima del buzón de revisión: ");
        int capacidadBuzon = scanner.nextInt();

        scanner.close(); 


        BuzonRevision buzonRevision = new BuzonRevision(capacidadBuzon);
        BuzonReproceso buzonReproceso = new BuzonReproceso(numProductos); 
        Deposito deposito = new Deposito(); 


        Productor[] productores = new Productor[numOperarios];
        for (int i = 0; i < numOperarios; i++) {
            productores[i] = new Productor(buzonReproceso, buzonRevision);
            productores[i].start();
        }


        OperadoresCalidad[] revisores = new OperadoresCalidad[numOperarios];
        for (int i = 0; i < numOperarios; i++) {
            revisores[i] = new OperadoresCalidad(buzonRevision, buzonReproceso, numProductos, deposito);
            revisores[i].start();
        }


        try {
            for (OperadoresCalidad revisor : revisores) {
                revisor.join();
            }


            System.out.println("✅ Todos los productos han sido evaluados. Enviando mensaje FIN...");
            buzonReproceso.agregar(new Producto(-1, "FIN"));

            for (Productor productor : productores) {
                productor.join();
            }

        } catch (InterruptedException e) {
            System.out.println("🚨 Interrupción en la ejecución.");
            Thread.currentThread().interrupt();
        }

        

        System.out.println("Proceso finalizado.");
    }

   
    

  
    














}
