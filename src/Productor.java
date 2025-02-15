public class Productor extends Thread{


    private int id;
    private BuzonReproceso buzonReproceso;
    private BuzonRevision buzonRevision;

    public Productor(int Id, BuzonReproceso buzonReproceso, BuzonRevision buzonRevision){
        this.id=Id;
        this.buzonReproceso=buzonReproceso;
        this.buzonRevision=buzonRevision;
    }


    public void run(){

        // si buzon reproceso esta lleno hay que darle prelación 

        while (true) {

            //validar que podemos o no obtener un producto para reprocesar

            try{
                synchronized(buzonReproceso){
                    Producto producto= buzonReproceso.extraer();
                    if (producto!=null){
                        Thread.sleep(20000);
                        synchronized(buzonRevision){
                            buzonRevision.agregarbuzon(producto);
                        }
                    continue;                        
                    }

                }
                // En caso de no tener productos en reproceso Generar productos
                synchronized(buzonRevision){
                    Producto producto= new Producto(id, false);
                    Thread.sleep(3000);
                    buzonRevision.agregarbuzon(producto);
                }
   
            }catch(InterruptedException e){}
            System.out.println("ERROR");

        
            
        }

        
    }

}
