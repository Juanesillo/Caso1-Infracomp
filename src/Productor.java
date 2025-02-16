public class Productor extends Thread{



    private BuzonReproceso buzonReproceso;
    private BuzonRevision buzonRevision;
    private boolean ejecutando=true;
    private int idProductos=0;

    public Productor( BuzonReproceso buzonReproceso, BuzonRevision buzonRevision){

        this.buzonReproceso=buzonReproceso;
        this.buzonRevision=buzonRevision;
    }


    public void run(){

        // si buzon reproceso esta lleno hay que darle prelación 

        while (ejecutando) {

            //validar que podemos o no obtener un producto para reprocesar

            try{
                synchronized(buzonReproceso){
                    Producto producto= buzonReproceso.extraer();
                    if (producto!=null){

                        if (producto.toString().contains("FIN")) {
                            System.out.println("🔴 Productor recibe mensaje FIN y finaliza.");
                            ejecutando = false;
                            return;
                        }
                        Thread.sleep(20000);
                        synchronized(buzonRevision){
                        buzonRevision.agregarbuzon(producto);
                        }
                    continue;                        
                    }

                }
                // En caso de no tener productos en reproceso Generar productos
                synchronized(buzonRevision){
                    Producto producto= new Producto(idProductos++, "NuevoP");
                    Thread.sleep(3000);
                    buzonRevision.agregarbuzon(producto);
                }
   
            }catch(InterruptedException e){}
            System.out.println("ERROR");

        
            
        }

        
    }

}
