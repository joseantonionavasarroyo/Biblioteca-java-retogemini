import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Material implements Prestable, Comparable<Material> {

    protected String titulo;
    protected String autor;
    protected String codigo;
    protected boolean tomado;
    protected LocalDate fechaPrestamo;

    public Material(String titulo, String autor, String codigo){
        this.titulo=titulo;
        this.autor=autor;
        this.codigo=codigo;
        this.tomado=false;
    }

    public void informacion(){
        System.out.println("Titulo: "+titulo);
        System.out.println("Codigo: "+codigo);
        if (tomado){
            System.out.println("Estado: prestado");
        }else{
            System.out.println("Estado: disponible");
        }

    }

    public void prestar(){
        if(!this.tomado){
            System.out.println("Material "+codigo+" prestado con exito");
            this.tomado=true;
        }else{
            System.out.println("Error: material "+codigo+" ya prestado");
        }
    }

    public void devolver(){
        if(this.tomado){
            System.out.println("Material "+codigo+" devuelto con exito");
            this.tomado=false;
        }else{
            System.out.println("Error: material "+codigo+" actualmente en la biblioteca");
        }
    }

    public double calcularMulta(LocalDate fechaDevolucion, int diasPermitidos){
        if(fechaPrestamo==null){
            return 0.0;
        }

        LocalDate fechaLimite=fechaPrestamo.plusDays(diasPermitidos);

        if(fechaDevolucion.isAfter(fechaLimite)){
            return ChronoUnit.DAYS.between(fechaLimite,fechaDevolucion)*1.50;
        }
        return 0.0;
    }

    public boolean isPrestado(){
        return tomado;
    }

    public String getTitulo(){return titulo;}

    public String getAutor(){return autor;}

    public abstract String getTipo();

    @Override
    public int compareTo(Material otro){
        return this.titulo.compareToIgnoreCase(otro.titulo);
    }
}
