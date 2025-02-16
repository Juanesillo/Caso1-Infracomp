public class Productor extends Thread {

    private final BuzonReproceso buzonReproceso;
    private final BuzonRevision buzonRevision;
    private boolean ejecutando = true;
    private int idProductos = 0;

    public Productor(BuzonReproceso buzonReproceso, BuzonRevision buzonRevision) {
        this.buzonReproceso = buzonReproceso;
        this.buzonRevision = buzonRevision;
    }

    public void run() {
        while (ejecutando) {
            try {
                Producto productoReprocesado;

                synchronized (buzonReproceso) {
                    productoReprocesado = buzonReproceso.extraer();
                }

                if (productoReprocesado != null) {
                    if (productoReprocesado.toString().contains("FIN")) {
                        System.out.println("Mensaje Fin recibido");
                        ejecutando = false;
                        return;
                    }

                    System.out.println("Volviendo a procesar producto: " + productoReprocesado);
                    Thread.sleep(5000); 

                    synchronized (buzonRevision) {
                        buzonRevision.agregarbuzon(productoReprocesado);
                    }
                    continue; 
                }

     
                Producto nuevoProducto = new Producto(idProductos++, "NuevoP");
                System.out.println("🛠 Generando: " + nuevoProducto);
                Thread.sleep(3000); 

                synchronized (buzonRevision) {
                    buzonRevision.agregarbuzon(nuevoProducto);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Productor interrumpido.");
            } catch (Exception e) {
                System.out.println("Error en el Productor: " + e.getMessage());
            }
        }
    }
}
