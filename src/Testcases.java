public class Testcases {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        System.out.println("Create product:");
        // I
        manager.createProduct(20231031L, "Apple", 4, 10, 5);
        manager.createProduct(20231031L, "Apple", 6, 10,5); // Throw exception: product should with unique productId
        // II
        manager.createProduct(20231032L, "Orange", 5, 20, 2);
        System.out.println("Test Cases for displayProduct:");
        manager.displayProduct("Apple" ,20231031L); // display by specific product info.
        manager.displayProduct();

        System.out.println();
        System.out.println("Test Cases for Section A: ");
        manager.displayAllProductRefill();
        manager.displayProductRefill(20231031L);
        manager.displayProductCount();
        manager.displayProductCountById(20231032L);

        System.out.println();
        System.out.println("Test Cases for Section B: ");
        manager.displayProductsExpiryDateByProductId(20231031L);
        manager.displayAllProductsExpiryDate();
        manager.displayExpiredProducts();

        System.out.println();
        System.out.println("Test Cases for Section C: ");
        manager.displayProductsInMarkDown();
        manager.displayProductsForMarkDown();
    }
}