import java.util.Scanner;

/**
 * Clase principal que gestiona la interacción por consola con el usuario.
 * Permite probar de forma interactiva la adición de materiales y la persistencia en archivos.
 */
public class Main {

    public static void main(String[] args) {
        // Instanciamos la biblioteca y el escáner para leer teclado
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        // Bucle que mantiene la aplicación activa hasta elegir la opción 6
        while (opcion != 6) {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE BIBLIOTECA");
            System.out.println("========================================");
            System.out.println("1. Agregar un Libro");
            System.out.println("2. Agregar una Revista");
            System.out.println("3. Mostrar Catálogo");
            System.out.println("4. Guardar Catálogo en Archivo (.txt)");
            System.out.println("5. Cargar Catálogo desde Archivo (.txt)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción (1-6): ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese título: ");
                        String tituloL = scanner.nextLine();
                        System.out.print("Ingrese autor: ");
                        String autorL = scanner.nextLine();
                        System.out.print("Ingrese código (ej. LIB-001): ");
                        String codigoL = scanner.nextLine();
                        System.out.print("Ingrese número de páginas: ");
                        int paginas = Integer.parseInt(scanner.nextLine());

                        Libro nuevoLibro = new Libro(tituloL, autorL, codigoL, paginas);
                        biblioteca.agregarMaterial(nuevoLibro);
                        System.out.println("¡Libro agregado con éxito a la memoria!");
                        break;

                    case 2:
                        System.out.print("Ingrese título: ");
                        String tituloR = scanner.nextLine();
                        System.out.print("Ingrese autor/editorial: ");
                        String autorR = scanner.nextLine();
                        System.out.print("Ingrese código (ej. REV-001): ");
                        String codigoR = scanner.nextLine();
                        System.out.print("Ingrese número de edición: ");
                        int edicion = Integer.parseInt(scanner.nextLine());

                        Revista nuevaRevista = new Revista(tituloR, autorR, codigoR, edicion);
                        biblioteca.agregarMaterial(nuevaRevista);
                        System.out.println("¡Revista agregada con éxito a la memoria!");
                        break;

                    case 3:
                        System.out.println("\n--- CATÁLOGO ACTUAL ---");
                        biblioteca.mostrarCatalogo();
                        break;

                    case 4:
                        // Llama a la escritura del archivo
                        biblioteca.guardarEnArchivo("catalogo.txt");
                        break;

                    case 5:
                        // Llama a la lectura del archivo
                        biblioteca.cargarArchivo("catalogo.txt");
                        break;

                    case 6:
                        System.out.println("Saliendo del sistema... ¡Hasta pronto!");
                        break;

                    default:
                        System.out.println("Opción no válida. Ingrese un número del 1 al 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }

        scanner.close(); // Cerramos el recurso del teclado al finalizar
    }
}
