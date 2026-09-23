public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos la biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // 2. Creamos e insertamos materiales
        Libro libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "LIB-001", 471);
        Revista revista1 = new Revista("National Geographic", "Varios", "REV-001", 302);

        biblioteca.agregarMaterial(libro1);
        biblioteca.agregarMaterial(revista1);

        // Comprobamos por consola que la colección contenga elementos
        int cantidadAntes = biblioteca.obtenerCatalogoOrdenado().size();
        System.out.println("Cantidad de materiales en memoria antes de guardar: " + cantidadAntes);

        // 3. Guardamos en el archivo
        System.out.println("\n=== 1. GUARDANDO EN ARCHIVO ===");
        biblioteca.guardarEnArchivo("catalogo.txt");

        // 4. Creamos una segunda biblioteca independiente
        Biblioteca bibliotecaNueva = new Biblioteca();

        // 5. Cargamos los datos desde el archivo generado
        System.out.println("\n=== 2. CARGANDO DESDE ARCHIVO ===");
        bibliotecaNueva.cargarArchivo("catalogo.txt");

        // 6. Mostramos el contenido recuperado
        System.out.println("\n=== 3. CONTENIDO RECUPERADO ===");
        bibliotecaNueva.mostrarCatalogo();
    }
}
