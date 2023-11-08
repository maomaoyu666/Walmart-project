import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    private HashMap<Long, Product> productInventory = new HashMap<>();

    public boolean searchByProductId(Long productId) {
        if (productInventory.get(productId) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createProduct(Long productID, String productName, Integer quantity, Integer maxDisplay, Integer minDisplay) {
        if (productID == null || productName == null) {
            System.out.println("productID and productName are required.");
            return false;
        }

        boolean exists = products.stream().anyMatch(product -> product.getProductID() == productID);

        if (exists) {
            System.out.println("Product should have a uniqueID");
            return false;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyy");
            Integer maxDisplayNum = maxDisplay == null ? 10 : maxDisplay;
            Integer minDisplayNum = minDisplay == null ? 5 : minDisplay;
            Product newProduct = new Product(productID, productName, quantity, maxDisplayNum, minDisplayNum);
            products.add(newProduct);
            productInventory.put(newProduct.getProductID(), newProduct);
            System.out.println("Created successfully!");
            System.out.println("Product Name: " + productName + ", Product ID: " + productID);
            System.out.println("-----------------------------------------------------------");
            return true;
        }

    }

    public void displayProduct(String productName, Long productID) {
        List<Product> filteredProducts = new ArrayList<>();
        if (productID != null) {
            filteredProducts.addAll(products.stream().filter(p -> p.getProductID() == (productID)).toList());
        } else if (productName != null) {
            filteredProducts.addAll(products.stream().filter(p -> p.getProductName().equals(productName)).toList());
        } else if (productName == null && productID == null) {
            filteredProducts = products;
        }

        if (filteredProducts.isEmpty()) {
            System.out.println("Product doesn't exist or please ensure you have provided correct product information.");
            return;
        } else {
            System.out.println("Product found: ");
            for (Product product : filteredProducts) {
                System.out.println(product.toString());
            }
        }
    }

    public void displayProduct() {
        System.out.println("Show all products: ");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void displayAllProductRefill() {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < product.getMinDisplayNum()) {
                filteredProducts.add(product);
            }
        }
        if (filteredProducts.isEmpty()) {
            System.out.println("Nothing need to be refilled for now.");
        } else {
            System.out.println("Products need to be refilled:");
            for (Product product : filteredProducts) {
                System.out.println(product.getProductName() + " need to be replenished by " + (product.getMinDisplayNum() - product.getQuantity()) + ".");
            }
        }
    }

    public void displayProductRefill(Long productId) {
        if (!searchByProductId(productId)) {
            System.out.println("Product doesn't exist.");
            return;
        }
        Product targetProduct = productInventory.get(productId);
        System.out.println(targetProduct.getProductName() + " need to be replenished by " + (targetProduct.getMinDisplayNum() - targetProduct.getQuantity()) + ".");
    }

    public boolean displayProductCount() {
        Integer countOfAllProduct = Integer.valueOf(0);
        if (products.isEmpty()) {
            System.out.println("There is no product.");
            return false;
        } else {
            for (Product product : products) {
                countOfAllProduct = +product.getQuantity();
            }
            if (countOfAllProduct != 0) {
                System.out.println("The amount of all the products on the shelf is " + countOfAllProduct + ".");
                return true;
            } else {
                System.out.println("There is no product on the shelf.");
                return false;
            }
        }
    }

    public void displayProductCountById(Long productId) {
        if (!searchByProductId(productId)) {
            System.out.println("Product doesn't exist.");
            return;
        }
        Product targetProduct = productInventory.get(productId);
        System.out.println(targetProduct.getProductName() + " on the shelf is " + targetProduct.getQuantity() + ".");
    }

    public void displayAllProductsExpiryDate() {
        System.out.println("Show all products' expiry date: ");
        for (Product product : products) {
            System.out.println(product.getProductName() + " will be expiry on " + product.getExpiryDate() + ".");
        }
    }

    public void displayProductsExpiryDateByProductId(Long productID) {
        if (!searchByProductId(productID)) {
            System.out.println("This product doesn't exist!");
        } else {
            Product targetProduct = productInventory.get(productID);
            System.out.println(targetProduct.getProductName() + "'s expiry date is " + targetProduct.getExpiryDate() + ".");
        }
    }

    public void displayExpiredProducts() {
        List<Product> filteredProduct = new ArrayList<>();
        if (products.isEmpty()) {
            System.out.println("No product on the shelf.");
        } else {
            for (Product product : products) {
                if (LocalDate.now().isAfter(product.getExpiryDate())) {
                    filteredProduct.add(product);
                    System.out.println("Attention: " + product.getProductName() + " ," + product.getProductID() + "(" + product.getExpiryDate() + ") has expired. Remove it as soon as possible!");
                }
            }
            if (filteredProduct.isEmpty()) {
                System.out.println("No product is expired.");
            };
        }
    }

    public void displayProductsForMarkDown() {
        List<Product> filteredProduct = new ArrayList<>();
        if (products.isEmpty()) {
            System.out.println("There is no product on the shelf.");
        } else {
            for (Product product : products) {
                if (LocalDate.now().plusDays(7).isAfter(product.getMarkDownDate())) {
                    System.out.println("A list of product need to be marked down a week after now.");
                    filteredProduct.add(product);
                    System.out.println(product.getProductName() + " ," + product.getProductID() + ".");
                }
            }
            if (filteredProduct.isEmpty()) {
            System.out.println("No product need to be marked down in the coming week!");
            }
        }
    }

    public void displayProductsInMarkDown() {
        List<Product> filteredProduct = new ArrayList<>();
        if (products.isEmpty()) {
            System.out.println("There is no product on the shelf.");
        } else {
            for (Product product : products) {
                if (LocalDate.now().isAfter(product.getMarkDownDate())) {
                    filteredProduct.add(product);
                    System.out.println("A list of product which is past the Markdown Date.");
                    System.out.println(product.getProductName() + " ," + product.getProductID() + ".");
                }
            }
            if (filteredProduct.isEmpty()) {
                System.out.println("No product in markdown list.");
            }
        }
    }
}