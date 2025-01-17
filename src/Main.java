import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Controlador controlador = new Controlador();

    // Almacena los usuarios registrados con sus contraseñas
    private static final Map<String, String> userDatabase = new HashMap<>();
    // Almacena la información de los productos
    private static final Map<String, Product> productCatalog = new HashMap<>();

    public static void main(String[] args) {

        // Inicializa el catálogo de productos
        initializeProducts();

        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("=== Registro / Login ===");
            System.out.print("Correo electrónico: ");
            String email = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            if (userDatabase.containsKey(email)) {
                // Usuario existente, intentar iniciar sesión
                if (userDatabase.get(email).equals(password)) {
                    loggedIn = true;
                } else {
                    System.out.println("Contraseña incorrecta. Inténtelo de nuevo.");
                }
            } else {
                // Usuario nuevo, registrarse
                userDatabase.put(email, password);
                loggedIn = true;
            }
        }

        // Mostrar menú de productos
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Comprar comida");
            System.out.println("2. Comprar ropa");
            System.out.println("3. Comprar electrodomesticos");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            if (option == 4) {
                System.out.println("Sesión cerrada.");
                break;
            }

            Product selectedProduct = null;
            String productName = "";

            switch (option) {
                case 1:
                    productName = "Comida";
                    break;
                case 2:
                    productName = "Ropa";
                    break;
                case 3:
                    productName = "Electrodomesticos";
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
                    continue;
            }

            selectedProduct = productCatalog.get(productName);
            if (selectedProduct == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.println("\nEl " +selectedProduct.getName()+ " tiene un precio de: " +selectedProduct.getPrice());
            System.out.println(selectedProduct.getDescription());
            System.out.print("\nIngrese la cantidad: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            double totalPrice = selectedProduct.getPrice() * quantity;
            System.out.printf("Precio total: %.2f\n", totalPrice);

            System.out.println("\n¿Desea comprar el prodcuto?");
            String confirmacion = scanner.nextLine();

            if(confirmacion.equals("s")) {

                controlador.setProductDetails(
                        selectedProduct.getName(),
                        selectedProduct.getDescription(),
                        quantity,
                        totalPrice
                );
                controlador.ejecutar();
                System.out.println("Email enviado");
            } else {
                System.out.println("Compra cancelada");
            }
        }
        scanner.close();
    }

    private static void initializeProducts() {
        productCatalog.put("Comida", new Product("Comida", 60.0, "Comida perfecta para cualquier ocasión."));
        productCatalog.put("Ropa", new Product("Ropa", 80.0, "Ropa resistente y flexible para cualquier ocasión."));
        productCatalog.put("Electrodomesticos", new Product("Electrodomesticos", 90.0, "Pocillo de cerámica para disfrutar tu café."));
    }

    private static class Product {
        private final String name;
        private final double price;
        private final String description;

        public Product(String name, double price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }
    }
}
