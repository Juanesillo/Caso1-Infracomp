public class Producto {

    private int id;
    private String nombre;


    public Producto(int Id, String nombre){
        this.id=Id;
        this.nombre=nombre;

      }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + id + " - " + nombre + "]";
    }


      
}
