// Console class (subclass of Game)
class Console extends Game {
    // attributes
    private String brand;
    
    // Normal constructor
    public Console(String id, String title, double price, String brand) {
        super(id, title, price);
        this.brand = brand;
    }

    // getter
    public String getBrand() {
        return brand;
    }

    // setter
    public void setBrand(String brand) {
        this.brand = brand;
    }
    // toString
    public String toString(){
        return super.toString() + "\nBrand: " + getBrand();
    }

    // Formatted display on the sheet invoice
    @Override
    public String toDisplay(){
        return getBrand() + " " + getTitle() ;
    }
    // processor
    @Override
    public double calcDiscountedPrice() {
        return this.getPrice() * 0.95;
    }
}