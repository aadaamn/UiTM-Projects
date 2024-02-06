// Gadget class (subclass of Game)
class Gadget extends Game {
    // attributes
    private String item;

    // Normal constructor
    public Gadget(String id, String title, double price, String item) {
        super(id, title, price);
        this.item = item;
    }

    // getter
    public String getItem() {
        return item;
    }

    // setter
    public void setitem(String item) {
        this.item = item;
    }
    
    // toString
    public String toString(){
        return super.toString() + "\nItem: " + getItem();
    }

    // Formatted display on the sheet invoice
    @Override
    public String toDisplay(){
        return getTitle() + " (" + getItem() + ")";
    }

    // processor
    @Override
    public double calcDiscountedPrice() {
        return this.getPrice() * 0.9;
    }
}