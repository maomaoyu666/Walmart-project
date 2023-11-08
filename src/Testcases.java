import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Testcases {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-DD-YYYY");

        // Test Cases for createProduct:
        System.out.println("Test Cases for createProduct:");
        // I
        manager.createProduct(20231031L, "Apple", 4, 10, 5);
        manager.createProduct(20231031L, "Apple", 6, 10,5);
        // II
        manager.createProduct(20231032L, "Orange", 5, 20, 2);
        manager.displayProduct("Apple" ,20231031L);
        manager.displayProduct();
        manager.displayAllProductRefill();
        manager.displayProductRefill(20231031L);
        manager.displayProductCount();
        manager.displayProductCountById(20231032L);

    }
}
    // III
//        manager.createProduct();
        // IV - Expiry Date and Markdown Date should be checked from the result.
//        manager.displayProduct("Apple", "101");
//
//        System.out.println("\n--------------------------------------");
//
//        // Test Cases for displayProduct:
//        System.out.println("Test Cases for displayProduct:");
//        // I
//        manager.displayProduct("Apple", "101");
//        // II
//        manager.displayProduct("Mango", "105");
//        // III
//        manager.displayProduct();
//
//        System.out.println("\n--------------------------------------");
//
//        // Test Cases for Section A:
//        System.out.println("Test Cases for Section A:");
//        // Adding some products for testing. Assume `weight` parameter has been added to the Product class.
//        manager.addProduct(new Product("102", "Oranges", LocalDate.now().plusDays(10), LocalDate.now().plusDays(5), 20));
//        manager.addProduct(new Product("103", "Bananas", LocalDate.now().plusDays(8), LocalDate.now().plusDays(4), 10));
//        // Calling the displayProductToRefill functions
//        manager.displayProductToRefill();
//        manager.displayProductToRefill("102");
//        manager.displayProductCount();
//        manager.displayProductCount("101");
//
//        System.out.println("\n--------------------------------------");
//
//        // Test Cases for Section B:
//        System.out.println("Test Cases for Section B:");
//        manager.displayProductsExpiryDate("101");
//        manager.displayProductsExpiryDate();
//        manager.displayExpiredProducts();
//
//        System.out.println("\n--------------------------------------");
//
//        // Test Cases for Section C:
//        System.out.println("Test Cases for Section C:");
//        manager.displayProductsInMarkDown();
//        manager.displayProductsForMarkDown();
//    }
//}
