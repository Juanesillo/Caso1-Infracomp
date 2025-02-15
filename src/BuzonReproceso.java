public class BuzonReproceso {


    private Producto[] reproceso;
    private int indice=0;


    public BuzonReproceso(int cantidad){
        this.reproceso=new Producto[cantidad];
    }

    public synchronized void agregar(Producto producto){

        while (indice>=reproceso.length) {

            try{
                wait();
            }catch(InterruptedException e){
                System.out.println("ERROR");
            }
            
        }

        reproceso[indice]=producto;
        indice++;
        notify();
    }




    public synchronized Producto extraer(){

        while (indice==0) {
            try{
                wait();
            }catch(InterruptedException e){
                System.out.println("Error");
            }
            
        }

        Producto producto= reproceso[--indice];
        notify();
        return producto;
    }



    

}
