import java.time.LocalDate;

class Product {
    private long productID;
    private String productName;
    private Integer quantity;
    private Integer maxDisplayNum;
    private Integer minDisplayNum;
    private LocalDate expiryDate; //  3 months from the day by default
    private LocalDate markDownDate; // 6 days before the ExpiryDate by default




    public Product(Long productID, String productName, Integer quantity, Integer maxDisplayNum, Integer minDisplayNum) {

        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.maxDisplayNum = maxDisplayNum;
        this.minDisplayNum = minDisplayNum;
        this.expiryDate = LocalDate.now().plusMonths(3);
        this.markDownDate = expiryDate.minusDays(6);
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getMaxDisplayNum() {
        return maxDisplayNum;
    }

    public void setMaxDisplayNum(int maxDisplayNum) {
        this.maxDisplayNum = maxDisplayNum;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getMarkDownDate() {
        return markDownDate;
    }

    public void setgetMarkDownDate(LocalDate timeDurationForMarkDown) {
        this.markDownDate = timeDurationForMarkDown;
    }

    public int getMinDisplayNum() {
        return minDisplayNum;
    }

    public void setMinDisplayNum(int minDisplayNum) {
        this.minDisplayNum = minDisplayNum;
    }

    @Override
    public String toString() {
        return "Product name: " + this.productName + "; Product id: " + this.productID + "; Current quantity: " + this.quantity + "; Maximum display number: " + this.maxDisplayNum + "; Minimum display number: " + this.minDisplayNum + "; Expired date: " + this.expiryDate + "; Mark down date: " + this.markDownDate + ".";
    }

}
