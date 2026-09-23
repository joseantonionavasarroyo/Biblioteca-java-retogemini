public class Revista extends Material{
    private int numEdicion;

    public Revista(String titulo, String autor, String codigo, int numEdicion){
        super(titulo,autor,codigo);
        this.numEdicion=numEdicion;
    }

    public String getTipo(){
        return "Revista";
    }
    public int getNumEdicion(){return numEdicion;}

    @Override
    public void informacion(){
        System.out.println("Autor: "+autor);
        System.out.println("Numero de edicion: "+numEdicion);
        if (tomado){
            System.out.println("Estado: prestado");
        }else{
            System.out.println("Estado: disponible");
        }
    }
}
