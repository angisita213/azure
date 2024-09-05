class Controlador {
    private Servicio servicio;

    public Controlador() {
        this.servicio = new Servicio();
    }

    public void ejecutar() {
        System.out.println("Iniciando la llamada HTTP...");
        String respuesta = servicio.realizarLlamadaHttp();
        System.out.println("Respuesta recibida: " + respuesta);
    }
}