public class Controlador {

    public static String productName;
    public static String productDescription;
    public static int quantity;
    public static double totalPrice;

    public static void setProductDetails(String name, String description, int quantity, double price) {
        Controlador.productName = name;
        Controlador.productDescription = description;
        Controlador.quantity = quantity;
        Controlador.totalPrice = price;
    }

    public void ejecutar() {
        Servicio servicio = new Servicio();
        System.out.println("Realizando llamada http");
        String response = servicio.realizarLlamadaHttp();
        System.out.println(response);
    }
}