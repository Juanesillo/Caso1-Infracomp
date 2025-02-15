import java.util.ArrayList;

public class Deposito {

    private static ArrayList<Producto> deposito= new ArrayList<Producto>();

    public synchronized void agregar(Producto producto){

        deposito.add(producto);

    }

}
