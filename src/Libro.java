public class Libro extends Material {
    private int paginas;


    public Libro(String titulo, String autor, String codigo, int paginas){
        super(titulo,autor,codigo);
        this.paginas=paginas;
    }
    @Override
    public void informacion(){
        System.out.println("Autor: "+autor);
        System.out.println("Numero de paginas: "+paginas);
        if (tomado){
            System.out.println("Estado: prestado");
        }else{
            System.out.println("Estado: disponible");
        }
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public int getPaginas(){
        return paginas;
    }

    public void setTitulo(String _titulo){
        this.titulo=_titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPaginas(int paginas) {
        if (paginas<=0){
            System.out.println("Error: El número de páginas debe ser mayor a 0. El valor no ha sido modificado.");
        }else{
            this.paginas=paginas;
        }
    }

    public String getTipo(){
        return "Libro";
    }
}
