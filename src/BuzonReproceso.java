public class BuzonReproceso {


    private Producto[] reproceso;
    private int indice=0;


    public BuzonReproceso(int cantidad){
        this.reproceso=new Producto[cantidad];
    }

    public synchronized void agregar(Producto producto){

        if (indice>=reproceso.length) {

            try{
                wait();
            }catch(InterruptedException e){
                System.out.println("ERROR");
            }
            
        }

        reproceso[indice]=producto;
        indice++;
        notifyAll();
    }




    public synchronized Producto extraer(){

        if(indice==0){return null;}

        Producto producto =reproceso[--indice];
        notifyAll();
        return producto;
    }



    

}
