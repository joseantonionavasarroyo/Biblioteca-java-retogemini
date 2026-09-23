import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {

    private String id;
    private String nombre;
    private TipoUsuario tipo;
    private ArrayList<Material> materialesPrestados;

    public Usuario(String id, String nombre, TipoUsuario tipo){
        this.id=id;
        this.nombre=nombre;
        this.tipo=tipo;
        this.materialesPrestados=new ArrayList<Material>();
    }

    public boolean puedePedirPrestado(){
        if(materialesPrestados.size()<tipo.getLimitePrestamos()){
            return true;
        }
        return false;
    }

    public void añadirMaterial(Material m){
        if(puedePedirPrestado()){
            m.fechaPrestamo=LocalDate.now();
            materialesPrestados.add(m);
        }else{
            throw new MaterialNoDisponibleException("el usuario "+nombre+ "ha llegado a su limite de libros");
        }
    }

    public void quitarMaterial(String codigo, LocalDate fechaDevolucion){
        boolean encontrado=false;
        for (int i = 0; i < materialesPrestados.size(); i++) {
            if (materialesPrestados.get(i).codigo.equalsIgnoreCase(codigo)) {
                Material material=materialesPrestados.get(i);
                double multa=material.calcularMulta(fechaDevolucion, tipo.getDiasMaximosPrestamo());
                if(multa>0.0){
                    System.out.println("Material "+codigo+" retirado del usuario "+nombre+" con multa de "+material.calcularMulta(fechaDevolucion,tipo.getDiasMaximosPrestamo()));
                }else{
                    System.out.println("Material " + codigo + " retirado con éxito del usuario " + nombre);
                }
                material.devolver();
                materialesPrestados.remove(i);
                encontrado=true;
                break;
            }
        }
        if(!encontrado) {
            throw new MaterialNoDisponibleException("El usuario " + nombre + " no tiene prestado el material con código " + codigo);
        }
    }
}
