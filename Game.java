// Game class
public class Game {
    // attributes
    private String id, title;
    private double price;

    // Normal constructor
    public Game(String id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    // Getters
    public String getID() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public double getPrice() {
        return price;
    }

    // Setters
    public void setID(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    // toString
    public String toString(){
        return "Product ID: " + getID() + "\nTitle: " + getTitle() + "\nPrice: RM" + String.format("%.2f" , getPrice());
    }

    // Formatted display on the sheet invoice
    public String toDisplay(){
        return getTitle();
    }

    // processor
    public double calcDiscountedPrice() {
        return this.getPrice() * 0.8;
    }
    
}