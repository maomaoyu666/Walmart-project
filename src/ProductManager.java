import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        }
        Product targetProduct = productInventory.get(productId);
        System.out.println(targetProduct.getProductName() + " need to be replenished by " + (targetProduct.getMinDisplayNum() - targetProduct.getQuantity()) + ".");
    }

    public void displayProductCount() {
        Integer countOfAllProduct = Integer.valueOf(0);
        if (products.isEmpty()) {
            System.out.println("There is no product.");
        } else {
            for (Product product : products) {
                countOfAllProduct =+ product.getQuantity();
            }
            if (countOfAllProduct != 0) {
                System.out.println("The amount of all the products on the shelf is " + countOfAllProduct);
            } else {
                System.out.println("There is no product on the shelf. ");
            }
        }
    }
    public void displayProductCountById(Long productId) {
        if (!searchByProductId(productId)) {
            System.out.println("Product doesn't exist.");
        }
        Product targetProduct = productInventory.get(productId);
        System.out.println(targetProduct.getProductName() + " on the shelf is " + targetProduct.getQuantity());
    }
}


//
//    public String displayProductsExpiryDate(String productID) throws Exception {
//        if (productID == null) {
//            throw new IllegalArgumentException("ProductID is mandatory for this function.");
//        }
//
//        Optional<Product> optionalProduct = products.stream().filter(p -> p.productID.equals(productID)).findFirst();
//
//        if (!optionalProduct.isPresent()) {
//            throw new Exception("Invalid ProductID: " + productID);
//        }
//
//        Product product = optionalProduct.get();
//        return "ProductID: " + product.productID + ", ProductName: " + product.productName + ", ExpiryDate: " + product.expiryDate;
//    }
//
//    public List<String> displayProductsExpiryDate() {
//        List<String> messages = new ArrayList<>();
//
//        if (products.isEmpty()) {
//            messages.add("No products on shelf.");
//            return messages;
//        }
//
//        for (Product product : products) {
//            messages.add("ProductID: " + product.productID + ", ProductName: " + product.productName + ", ExpiryDate: " + product.expiryDate);
//        }
//
//        return messages;
//    }
//
//    public List<String> displayExpiredProducts() {
//        List<String> messages = new ArrayList<>();
//
//        LocalDate currentDate = LocalDate.now();
//
//        for (Product product : products) {
//            if (product.expiryDate.isBefore(currentDate)) {
//                messages.add("!!! EXPIRED !!! ProductID: " + product.productID + ", ProductName: " + product.productName + ", ExpiredDate: " + product.expiryDate);
//            }
//        }
//
//        if (messages.isEmpty()) {
//            messages.add("No expired products on the shelf.");
//        }
//
//        return messages;
//    }
//
//    public List<String> displayProductsInMarkDown() {
//        List<String> messages = new ArrayList<>();
//        LocalDate currentDate = LocalDate.now();
//
//        for (Product product : products) {
//            if (product.timeDurationForMarkDown.isBefore(currentDate)) {
//                messages.add("ProductID: " + product.productID + ", ProductName: " + product.productName + ", MarkdownDate: " + product.timeDurationForMarkDown);
//            }
//        }
//
//        if (messages.isEmpty()) {
//            messages.add("No products are past the MarkdownDate.");
//        }
//
//        return messages;
//    }
//
//    public List<String> displayProductsForMarkDown() {
//        List<String> messages = new ArrayList<>();
//        LocalDate currentDate = LocalDate.now();
//        LocalDate oneWeekFromNow = currentDate.plusWeeks(1);
//
//        for (Product product : products) {
//            if (!product.timeDurationForMarkDown.isBefore(currentDate) && product.timeDurationForMarkDown.isBefore(oneWeekFromNow)) {
//                messages.add("ProductID: " + product.productID + ", ProductName: " + product.productName + ", MarkdownDate: " + product.timeDurationForMarkDown);
//            }
//        }
//
//        if (messages.isEmpty()) {
//            messages.add("No products need to be marked down a week from now.");
//        }
//
//        return messages;
//    }
//}
