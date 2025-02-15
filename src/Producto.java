public class Producto {

    private int id;
    private boolean fin;


    public Producto(int Id, boolean Fin){
        this.id=Id;
        this.fin=Fin;

      }


    public int getId() {
        return id;
    }

    public boolean isFin() {
        return fin;
    }


    public void setFin(boolean fin) {
        this.fin = fin;
    }


      
}
