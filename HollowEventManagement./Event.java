public class Event {
    private int eventID; // ex: 1001
    private String eventName; // ex: "Joji Live in KL"
    private char eventCategory; // ex: A - Concert,B - Fashion Show, C - Exhibition 
    private String date; // dd/mm/yyyy
    private String venue; // ex: One utama / KLCC
    private String artists; // ex: JOJI
    private double ticketPrice; // ex: 80.0
    private int ticketQuantity; // ex: 1000
    private boolean paymentStatus; // true = paid, false = unpaid
    private String manager; // ex: Mahadzir Bin Slatan 

    public Event(int id, String name, char category, String date, String venue, String artists,
                 double price, int quantity, boolean paymentStatus, String manager) {
        this.eventID = id;
        this.eventName = name;
        this.eventCategory = category;
        this.date = date;
        this.venue = venue;
        this.artists = artists;
        this.ticketPrice = price;
        this.ticketQuantity = quantity;
        this.paymentStatus = paymentStatus;
        this.manager = manager;
    }

    // Getters
    public int getEventID() {
        return eventID;
    }
    public String getEventName() {
        return eventName;
    }
    public char getEventCategory() {
        return eventCategory;
    }
    public String getDate() {
        return date;
    }
    public String getVenue() {
        return venue;
    }
    public String getartists() {
        return artists;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    public int getTicketQuantity() {
        return ticketQuantity;
    }
    public boolean getPaymentStatus() {
        return paymentStatus;
    }
    public String getManager() {
        return manager;
    }
    // setters
    public void setDate(String date) {
        this.date = date;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }
    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    // for display purposes (in toString method)
    public String paid(boolean payment)
    {
        String paid = null;
        if(payment == true)
            paid = "Paid";
        else
            paid = "Unpaid";
        return paid;
    }
    // A function to calculate totalSales
    public double totalSales() {
        return this.ticketPrice * this.ticketQuantity;
    }

    @Override
    public String toString() {
        return  "Event ID: " + eventID + "\nEvent Name: " + eventName + "\nCategory: " + eventCategory + "\nDate: " + date + "\nVenue: " + venue +
                "\nArtists: " + artists + "\nTicket Price: RM" + ticketPrice + "\nTicket Quantity: " + ticketQuantity +
                "\nPayment Status: " + paid(paymentStatus) + "\nManager: " + manager ;
    }
}
