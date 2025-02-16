public class BuzonRevision {
    private final Producto[] buzon;
    private int indice = 0;

    public BuzonRevision(int capacidad) {
        this.buzon = new Producto[capacidad];
    }

    public synchronized void agregarbuzon(Producto producto) {
        while (indice >= buzon.length) {
            try {
                System.out.println(" El buzón de revisión está lleno. Esperando espacio...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(" Error en agregarbuzon()");
            }
        }

        buzon[indice] = producto;
        System.out.println(" Producto agregado con exito para revisión");
        indice++;
        notifyAll(); 
    }

    public synchronized Producto extraerbuzon() {
        while (indice == 0) { 
            try {
                System.out.println(" Esperando productos en el buzón de revisión...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(" Error en extraerbuzon()");
            }
        }

        Producto producto = buzon[--indice]; 
        System.out.println("📤 Producto extraído de revisión: " + producto);
        notifyAll();
        return producto;
    }

    public synchronized boolean estaVacio() {
        return indice == 0;
    }
}
