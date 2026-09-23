import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Biblioteca {
    private HashMap<String,Material> coleccion;

    public Biblioteca(){
        this.coleccion=new HashMap<String,Material>();
    }

    public void agregarMaterial(Material m) {
        this.coleccion.put(m.codigo,m);
    }

    public void mostrarCatalogo(){
        if(!coleccion.isEmpty()){
            //se hace asi el for ya que un hash no se puede recorrer mediante i, ya que no devuelve los valores que buscamos como tal
            for(Material m: coleccion.values()){
                System.out.println("========"+m.getTipo()+"========");
                m.informacion();
            }
        }else{
            System.out.println("Biblioteca vacia");
        }
    }

    public List<Material> obtenerCatalogoOrdenado(){
        List<Material> resultado=coleccion.values().stream()
                .sorted()
                .collect(Collectors.toList());
        return resultado;
    }

    public List<Material> filtrarPorTipo(String tipo){
        List<Material> resultado=coleccion.values().stream()
                .filter(entry->entry.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
        return resultado;
    }

    public List<Material> obtenerMaterialesPrestados(){
        List<Material> resultado=coleccion.values().stream()
                .filter(entry->entry.isPrestado())
                .collect(Collectors.toList());
        return resultado;
    }

    public Material buscarPorCodigo(String codigo){
        return coleccion.get(codigo);
    }

    public void prestarMaterial(String codigo, Usuario u){
        Material m=buscarPorCodigo(codigo);
        if(m!=null){
            if(!m.isPrestado() && u.puedePedirPrestado()) {
                m.prestar();
                u.añadirMaterial(m);
            }else{
                throw new MaterialNoDisponibleException("Material "+codigo+" actualmente en propiedad de otro usuario o limite de libros alcanzado");
            }
        }else{
            throw new MaterialNoDisponibleException("Material no existente");
        }
    }

    public void guardarEnArchivo(String rutaArchivo){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(rutaArchivo))){
            List<Material> lista=obtenerCatalogoOrdenado();
            for(Material m:lista){
                if(m.getTipo().equalsIgnoreCase("Libro")){
                    Libro l=(Libro) m;
                    writer.write("Libro;"+l.codigo+";"+l.titulo+";"+l.autor+";"+l.getPaginas());
                }else if(m.getTipo().equalsIgnoreCase("Revista")){
                    Revista r=(Revista) m;
                    writer.write("Revista;"+r.codigo+";"+r.titulo+";"+r.autor+";"+r.getNumEdicion());
                }
                writer.newLine();
            }
            System.out.println("Catalogo guardado con exito en: "+rutaArchivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarArchivo(String rutaArchivo){
        try(BufferedReader reader=new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while((linea=reader.readLine())!=null){
                String[] datos=linea.split(";");

                if(datos.length>=5){
                    String tipo=datos[0];
                    String codigo=datos[1];
                    String titulo=datos[2];
                    String autor=datos[3];

                    if(tipo.equalsIgnoreCase("Libro")){
                        int paginas=Integer.parseInt(datos[4]);
                        Libro l=new Libro(titulo,autor,codigo,paginas);
                        agregarMaterial(l);
                    } else if (tipo.equalsIgnoreCase("Revista")) {
                        int numEdicion=Integer.parseInt(datos[4]);
                        Revista r=new Revista(titulo,autor,codigo,numEdicion);
                        agregarMaterial(r);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
