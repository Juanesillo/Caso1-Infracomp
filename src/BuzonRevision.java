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
            notify();
            
        
        


    }



}
