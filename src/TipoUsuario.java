public enum TipoUsuario {
    ESTUDIANTE(2,7),
    PROFESOR(5,14);

    private final int limitePrestamos;
    private final int diasMaximosPrestamo;

    // El constructor de un enum es privado por defecto en Java
    TipoUsuario(int limitePrestamos, int diasMaximosPrestamo) {
        this.limitePrestamos = limitePrestamos;
        this.diasMaximosPrestamo=diasMaximosPrestamo;
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }
    public int getDiasMaximosPrestamo(){return diasMaximosPrestamo;}
}
