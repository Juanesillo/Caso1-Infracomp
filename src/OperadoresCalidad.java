public class OperadoresCalidad extends Thread {
    private final Deposito deposito;
    private final BuzonRevision buzonRevision;
    private final BuzonReproceso buzonReproceso;
    private final int maxProductos;
    private final int maxFallos;
    private int fallos = 0;
    private int productosAprobados = 0;

    public OperadoresCalidad(BuzonRevision buzonRevision, BuzonReproceso buzonReproceso,
                             int maxProductos, Deposito deposito) {
        this.deposito = deposito;
        this.buzonRevision = buzonRevision;
        this.buzonReproceso = buzonReproceso;
        this.maxProductos = maxProductos;
        this.maxFallos = (int) Math.floor(0.1 * maxProductos);
    }


    public void run() {
        while (productosAprobados < maxProductos || !buzonRevision.estaVacio()) {
            try {
                Producto producto = buzonRevision.extraerbuzon();

                Thread.sleep(1000);
                int resultado = (int) (Math.random() * 100 + 1);
                if (resultado % 7 == 0 && fallos < maxFallos) {
                    System.out.println("Producto rechazado: " + producto);
                    buzonReproceso.agregar(producto);
                    fallos++;
                } else {
                    System.out.println("Aprobado para ingresar al deposito: " + producto);
                    deposito.agregar(producto);
                    productosAprobados++;
                }

              
                if (productosAprobados >= maxProductos && buzonRevision.estaVacio()) {
                    System.out.println("Ultimo producto");
                    buzonReproceso.agregar(new Producto(-1, "FIN"));
                    return;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
