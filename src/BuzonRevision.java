public class BuzonRevision {


    private Producto[] buzon;
    private int indice=0;

    

    public BuzonRevision(int capacidad){

        this.buzon= new Producto[capacidad];
    }


    public synchronized void agregarbuzon(Producto producto){

        //Condición 1: Como se comparten el buzon entre productor y operadores calidad 
        // se debe verificar si el buzon esta lleno o no para continuar con la ejecución

        while (indice>=buzon.length) {
            try{
                System.out.println("El buzon esta lleno");
                wait();
            }
            catch(InterruptedException e){
                System.out.println("Error");
            }
            
        }
        // Se agrega el producto al array si el buzon no esta lleno notificando al resto de threads 


            buzon[indice] = producto;
            System.out.println("Producto agregado en posición " + indice);
            indice++;
            notifyAll();
    }




    public synchronized Producto extraerbuzon() {
        while (indice == 0) { // 🛑 Esperar correctamente si el buzón está vacío
            try {
                System.out.println("⏳ Esperando productos en el buzón...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("🚨 Error en extraerbuzon()");
            }
        }

        Producto producto = buzon[--indice]; // 🔹 Reducir el índice solo cuando haya producto disponible
        System.out.println("📤 Producto extraído: " + producto);
        notifyAll(); // 🔹 Despertar a los productores si había espacio ocupado
        return producto;
    }


    public synchronized boolean estaVacio() {
        return indice == 0;
    }



}
