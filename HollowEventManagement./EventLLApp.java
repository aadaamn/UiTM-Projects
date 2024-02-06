import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EventLLApp {
    
    // Function to display menu
    private static void displayMenu() {
        System.out.println("\n\t██╗  ██╗     ██████╗     ██╗         ██╗          ██████╗     ██╗    ██╗" +
                "\n\t██║  ██║    ██╔═══██╗    ██║         ██║         ██╔═══██╗    ██║    ██║" +
                "\n\t███████║    ██║   ██║    ██║         ██║         ██║   ██║    ██║ █╗ ██║" +
                "\n\t██╔══██║    ██║   ██║    ██║         ██║         ██║   ██║    ██║███╗██║" +
                "\n\t██║  ██║    ╚██████╔╝    ███████╗    ███████╗    ╚██████╔╝    ╚███╔███╔╝" +
                "\n\t╚═╝  ╚═╝     ╚═════╝     ╚══════╝    ╚══════╝     ╚═════╝      ╚══╝╚══╝");
    }
    // Function to diplay certain category - based on user selection (case 8)
    public static void displayCategory (LinkedList list, char category) {
        String categoryName = "";
        Event a = list.getFirst();
        if (category == 'A') 
            categoryName = "Concert Events";
        else if (category == 'B')
            categoryName = "Fashion Show Events";
        else if (category == 'C')
            categoryName = "Exhibition Events";
        System.out.println("\n<<<<<<<<<<<<<<<<< " + categoryName + " >>>>>>>>>>>>>>>>>>\n");
        while (a != null) {
            if (a.getEventCategory() == category) {
                System.out.println(a.toString());
                System.out.println();
            }
            a = list.getNext();
        } 
    }

    // Main Menu (For Linked List)
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList EventList = new LinkedList();

        int option = 100;
        while (option != 10) {
            System.out.println(
                    "========================================================================================");
            displayMenu();
            System.out.println(
                    "\n========================================================================================");
            System.out.println(
                    "   1) Store data automatically (using .txt files)\n   2) Store data manually\n   3) Display the data in sorted manner (according to dates)\n   4) Display all data \n   5) Remove records using id");
            System.out.println("   6) Search & display event details that have not paid full settlement payment");
            System.out.println("   7) Update event details");
            System.out.println("   8) Split the record based on categories");
            System.out.println("   9) Calculate & display total revenue based on category");
            System.out.println("   0) Exit !");
            System.out.println(
                    "========================================================================================");
            System.out.print("Enter choice: ");
            option = scan.nextInt();
            scan.nextLine();
            System.out.println();
            switch (option) {
                case 1:
                    System.out.println("\t<<<<<<<<<<<<<<<<< Adding Data Automatically >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter number of files: ");
                    int num = scan.nextInt(); scan.nextLine();
                    for (int i = 0; i < num; i++) {
                        System.out.print("Enter textfile " + (i+1) + " name (in .txt file): ");
                        String txtfile = scan.nextLine();
                        try (BufferedReader inFile = new BufferedReader(new FileReader(txtfile))) {
                            String line;
                            while ((line = inFile.readLine()) != null) {
                                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                                // Skip this line & proceed to the next line (if ada data corrupted & not enough data)
                                if (tokenizer.countTokens() != 10) {
                                    continue; 
                                }

                                // Extract data 
                                int eventID = Integer.parseInt(tokenizer.nextToken());
                                String eventName = tokenizer.nextToken();
                                char eventCategory = tokenizer.nextToken().charAt(0);
                                String date = tokenizer.nextToken();
                                String venue = tokenizer.nextToken();
                                String artists = tokenizer.nextToken();
                                double ticketPrice = Double.parseDouble(tokenizer.nextToken());
                                int ticketQuantity = Integer.parseInt(tokenizer.nextToken());
                                boolean paymentStatus = Boolean.parseBoolean(tokenizer.nextToken());
                                String manager = tokenizer.nextToken();

                                // Create an Event object
                                Event event = new Event(eventID, eventName, eventCategory, date, venue, artists,
                                        ticketPrice, ticketQuantity, paymentStatus, manager);

                                // insert data into the LL
                                EventList.insertAtBack(event); 
                            }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                    System.out.println("\n\t\t   Successfully inserted into the list !");
                    break;

                case 2:
                    System.out.println("\t<<<<<<<<<<<<<<<<< Adding Data Manually >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter quantity of data needed to inserted: ");
                    int quantity = scan.nextInt();scan.nextLine();
                    for (int i = 0; i < quantity; i++){
                        System.out.println("# Event " + (i+1));
                        System.out.print("Enter ID: ");
                        int eventID = scan.nextInt(); scan.nextLine();
                        // checking if there is duplicate id, every eventID is unique, cannot be the same -
                        boolean isDuplicate = false;
                        Event dup = EventList.getFirst();
                        while (dup != null) {
                            // if duplicate, will keluar error and repeat the loop (i--)
                            if (dup.getEventID() == eventID) {
                                System.out.println("This ID already exists. Please enter a different ID.");
                                isDuplicate = true;
                                i--;
                                break;
                            }
                            dup = EventList.getNext();
                        }
                        // if not duplicate, it will continue get all the other attributes
                        if (!isDuplicate) {
                            System.out.print("Enter Event Name: ");
                            String eventName = scan.nextLine();
                            System.out.println("A - Concert\nB - Fashion Show\nC - Exhibition");
                            System.out.print("Enter category (A/B/C): ");
                            char eventCategory = scan.nextLine().charAt(0);
                            eventCategory = Character.toUpperCase(eventCategory);
                            System.out.print("Enter date (dd/mm/yyyy): ");
                            String date = scan.nextLine();
                            System.out.print("Enter venue: ");
                            String venue = scan.nextLine();
                            System.out.print("Enter artists: ");
                            String artists = scan.nextLine();
                            System.out.print("Enter price per ticket: RM");
                            double ticketPrice = scan.nextDouble();scan.nextLine();
                            System.out.print("Enter ticket quantity: ");
                            int ticketQuantity = scan.nextInt(); scan.nextLine();
                            System.out.print("Enter payment status (P - Paid/U - Unpaid): ");
                            char ps = scan.nextLine().charAt(0);
                            boolean paymentStatus;
                            if (ps == 'P')
                                paymentStatus = true;
                            else 
                                paymentStatus = false;
                            System.out.print("Enter manager name: ");
                            String manager = scan.nextLine();
                
                            Event a = new Event(eventID, eventName, eventCategory, date, venue, artists, ticketPrice, ticketQuantity, paymentStatus, manager);
                            EventList.insertAtBack(a);
                        }
                    }
                    System.out.println("\n\t\t   Successfully insert into the list !");
                    break;

                case 3:
                    System.out.println("\t<<<<<<<<<<<<<<<<<   Sorted !     >>>>>>>>>>>>>>>>>>");
                    EventList.sortEventsByDate(EventList);
                    break;

                case 4:
                    System.out.println("\t<<<<<<<<<<<<<<<<< All Data >>>>>>>>>>>>>>>>>>");
                    EventList.displayAll();
                    break;

                case 5:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Removing data >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter EventID to be removed: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    Event temp = EventList.removeEvent(id);
                    if (temp != null)
                        System.out.println("Event with ID " + id + " has been succesfully removed.");
                    else
                        System.out.println("Event is not found!");
                    break;

                case 6:
                    System.out.println(
                            "\n\t<<<<<<<<<<<<<<<<< Events that have not paid full settlement >>>>>>>>>>>>>>>>>>\n");
                    Event a = EventList.getFirst();
                    while (a != null) {
                        if (a.getPaymentStatus() == false) {
                            System.out.println();
                            System.out.println(a.toString());
                        }
                        a = EventList.getNext();
                    }
                    break;

                case 7:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Update Event Details >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter Event ID: ");
                    int targetid = scan.nextInt();
                    scan.nextLine();
                    boolean flag = false;
                    Event b = EventList.getFirst();
                    while (b != null) {
                        if (b.getEventID() == targetid) {
                            System.out.println("-------------- Event: " + b.getEventName() + " -----------------");
                            System.out.println("D - Date\nV - Venue\nT - Ticket Quantity\nP - Payment status");
                            System.out.print("Enter data to be changed: ");
                            char change = scan.nextLine().charAt(0);
                            change = Character.toUpperCase(change);
                            // if user wants to change date
                            if (change == 'D') {
                                System.out.print("Enter new date: ");
                                String newdate = scan.nextLine();
                                b.setDate(newdate);
                            } 
                            // if user wants to change venue
                            else if (change == 'V') {
                                System.out.print("Enter new venue: ");
                                String newVenue = scan.nextLine();
                                b.setVenue(newVenue);
                            } 
                            // if the user wants to change ticket quantity
                            else if (change == 'T') {
                                System.out.print("Enter new ticket quantity(in digit): ");
                                int q = scan.nextInt(); scan.nextLine();
                                b.setTicketQuantity(q);  
                            }
                            // if the user paid, so need to update the payment status
                            else if (change == 'P') {
                                System.out.println("P - Paid, U - Unpaid");
                                System.out.print("Enter updated payment status: ");
                                char ps = scan.nextLine().charAt(0);
                                ps = Character.toUpperCase(ps);
                                boolean pstatus;
                                if (ps == 'P')
                                    pstatus = true;
                                else
                                    pstatus = false;
                                b.setPaymentStatus(pstatus);
                            }
                            flag = true;
                        }
                        b = EventList.getNext();
                    }
                    if (!flag)
                        System.out.println("\nWe did not find the event!");
                    else
                        System.out.println("\nSuccessfully updated!");
                    break;

                case 8:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Split the records >>>>>>>>>>>>>>>>>>\n");
                    System.out.println("A - Concert Event\nB - Fashion Show\nC - Exhibition Event\nD - All Category");
                    System.out.print("Enter category (A/B/C/D): ");
                    char category = scan.nextLine().charAt(0);
                    category = Character.toUpperCase(category);
                    if (category == 'A' || category == 'B' || category == 'C')
                        displayCategory(EventList, category);
                    else if (category == 'D') {
                        displayCategory(EventList, 'A');
                        displayCategory(EventList, 'B');
                        displayCategory(EventList, 'C');
                    }
                    break;

                case 9:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Calculate revenue based on category >>>>>>>>>>>>>>>>>>\n");
                    System.out.println("A - Concert Event\nB - Fashion Show\nC - Exhibition Event");
                    System.out.print("Enter category (A/B/C): ");
                    char ctg = scan.nextLine().charAt(0);
                    ctg = Character.toUpperCase(ctg);
                    double revenue = 0;
                    int count = 0;
                    String categoryName = "";
                    Event d = EventList.getFirst();
                    while (d != null) {
                        if (d.getEventCategory() == ctg) {
                            revenue += d.totalSales();
                            count++;
                            if (d.getEventCategory() == 'A')
                                categoryName = "Concert Events";
                            else if (d.getEventCategory() == 'B')
                                categoryName = "Fashion Show Events";
                            else if (d.getEventCategory() == 'C')
                                categoryName = "Exhibition Events";
                        }
                        d = EventList.getNext();
                    }
                    if (count > 0) {
                        System.out.println("\n<<<<<<<<<<<<<<<<< " + categoryName + " >>>>>>>>>>>>>>>>>>\n");
                        System.out.println("Total Sales: RM" + String.format("%.2f", revenue));
                        System.out.println("Total Events: " + count);
                    }
                    else
                        System.out.println("No event is happening in this category");
                    
                    break;
                
                case 0:
                    System.out.println("\n========================================================================================");
                    System.out.println("                           Thank You For Using Our Service! ");
                    System.out.println("                               by Adam, Najmi, Mahadzir");
                    System.out.println("========================================================================================");
                    System.exit(0);
                 
            }
        }
    }
}
