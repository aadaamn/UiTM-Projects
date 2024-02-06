import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EventQueueApp {

    // Function to display main menu
    private static void displayMenu() {

        System.out.println("\n\t██╗  ██╗     ██████╗     ██╗         ██╗          ██████╗     ██╗    ██╗" +
                "\n\t██║  ██║    ██╔═══██╗    ██║         ██║         ██╔═══██╗    ██║    ██║" +
                "\n\t███████║    ██║   ██║    ██║         ██║         ██║   ██║    ██║ █╗ ██║" +
                "\n\t██╔══██║    ██║   ██║    ██║         ██║         ██║   ██║    ██║███╗██║" +
                "\n\t██║  ██║    ╚██████╔╝    ███████╗    ███████╗    ╚██████╔╝    ╚███╔███╔╝" +
                "\n\t╚═╝  ╚═╝     ╚═════╝     ╚══════╝    ╚══════╝     ╚═════╝      ╚══╝╚══╝");

    }
    // Function to compare date (will be used in sorting)
    private static int compareDates(String dateone, String datetwo) {
        String[] date1 = dateone.split("/");
        String[] date2 = datetwo.split("/");

        int day1 = Integer.parseInt(date1[0]);
        int month1 = Integer.parseInt(date1[1]);
        int year1 = Integer.parseInt(date1[2]);

        int day2 = Integer.parseInt(date2[0]);
        int month2 = Integer.parseInt(date2[1]);
        int year2 = Integer.parseInt(date2[2]);

        if (year1 != year2) 
            return year1 - year2;
        else if (month1 != month2) 
            return month1 - month2;
        else 
            return day1 - day2;
    }

    // Function to sort - using bubble sort using ArrayList, sorting based on event dates
    private static ArrayList<Event> bubbleSort(ArrayList<Event> eventList) {
        int n = eventList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare events based on date
                if (compareDates(eventList.get(j).getDate(), eventList.get(j + 1).getDate()) > 0) {
                    // Swap events
                    Event temp = eventList.get(j);
                    eventList.set(j, eventList.get(j + 1));
                    eventList.set(j + 1, temp);
                }
            }
        }
        return eventList;
    }

    // Function to display (in case 8)
    private static Queue displayCategory(Queue q,char category) {
        Queue tempQQ = new Queue();
        String catName = "";
        if (category == 'A') 
            catName = "Concert Events";
        else if (category == 'B')
            catName = "Fashion Show Events";
        else if (category == 'C')
            catName = "Exhibition Events";
        System.out.println("\n<<<<<<<<<<<<<<<<< " + catName + " >>>>>>>>>>>>>>>>>>\n");
        while (!q.isEmpty()) {
            Event currentEvent = q.dequeue();
            if (currentEvent.getEventCategory() == category) {
                System.out.println(currentEvent.toString());
                System.out.println();
            }
            // save back original data for later use
            tempQQ.enqueue(currentEvent);
        }
        return tempQQ;
    }

    // Main Menu for Queue App
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Queue EventQueue = new Queue();
    
        int option = 100;
        while (option != 10) {
            System.out.println(
                    "========================================================================================");
            displayMenu();
            System.out.println(
                    "\n========================================================================================");
            System.out.println(
                    "   1) Store data automatically (using .txt files)\n   2) Store data manually\n   3) Display the data in sorted manner (according to dates)\n   4) Display all data \n   5) Remove records using id");
            System.out.println("   6) Search and display event details that have not paid full settlement payment");
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
                                // Skip this line & proceed to the next line (if ada data corrupted & tak cukup ciri - ciri)
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
                                EventQueue.enqueue(event); 
                            }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    System.out.println("\n\t\t   Successfully inserted into the list !");
                    break;
  
                case 2:
                    System.out.println("\t<<<<<<<<<<<<<<<<< Adding Data Manually >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter quantity of data needed to be inserted: ");
                    int quantity = scan.nextInt();
                    scan.nextLine();
                    // Loop based on quantity needed
                    for (int i = 0; i < quantity; i++) {
                        System.out.println("# Event " + (i + 1));
                        System.out.print("Enter ID: ");
                        int eventID = scan.nextInt();
                        scan.nextLine();
            
                        // checking if there is a duplicate id, every eventID is unique, cannot be the same
                        boolean isDuplicate = false;
            
                        Queue tempQueue = new Queue();
                        while (!EventQueue.isEmpty()) {
                            Event existingEvent = EventQueue.dequeue();
                            if (existingEvent.getEventID() == eventID) {
                                System.out.println("This ID already exists. Please enter a different ID.");
                                isDuplicate = true;
                                i--;
                                continue;
                            }
                            tempQueue.enqueue(existingEvent);
                        }
                        // restore the original data
                        while (!tempQueue.isEmpty()) {
                            EventQueue.enqueue(tempQueue.dequeue());
                        }

                        if (isDuplicate == false) {
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
                            System.out.print("Enter ticket per price: RM");
                            double ticketPrice = scan.nextDouble();scan.nextLine();
                            System.out.print("Enter ticket quantity: ");
                            int ticketQuantity = scan.nextInt(); scan.nextLine();
                            System.out.print("Enter payment status (P - Paid/U - Unpaid): ");
                            char ps = scan.nextLine().charAt(0);
                            ps = Character.toUpperCase(ps);
                            boolean paymentStatus;
                            if (ps == 'P')
                                paymentStatus = true;
                            else 
                                paymentStatus = false;
                            System.out.print("Enter manager name: ");
                            String manager = scan.nextLine();
                
                            Event a = new Event(eventID, eventName, eventCategory, date, venue, artists, ticketPrice, ticketQuantity, paymentStatus, manager);
                            EventQueue.enqueue(a);
                        }
                    }
                    System.out.println("\n\t\t   Successfully inserted into the queue !");
                    break;

                
                case 3:
                    System.out.println("\t<<<<<<<<<<<<<<<<<   Sorted !     >>>>>>>>>>>>>>>>>>\n");
                    
                    // a temporary ArrayList to store events for sorting
                    ArrayList<Event> sortedEvent = new ArrayList<Event>();
                    // temporary queue to save the original order (data)
                    Queue tempQ = new Queue();

                    // Copy events from EventQueue to the list for sorting
                    while (!EventQueue.isEmpty()) {
                        Event a = EventQueue.dequeue();
                        sortedEvent.add(a);
                        tempQ.enqueue(a);
                    }
                    
                    // Sort events based on date
                    bubbleSort(sortedEvent);
                    
                    // Display sorted events
                    for (Event a : sortedEvent) {
                        System.out.println(a.toString() + "\n");
                    }
                    
                    // restore the original data
                    while (!tempQ.isEmpty()) {
                        EventQueue.enqueue(tempQ.dequeue());
                    }
                    break;
            
                case 4:
                    System.out.println("\t<<<<<<<<<<<<<<<<< All Data >>>>>>>>>>>>>>>>>>\n");
                
                    // Temporary queue for store original queue events
                    Queue displayQueue = new Queue();
                    
                    // Displaying
                    while (!EventQueue.isEmpty()) {
                        Event currentEvent = EventQueue.dequeue();
                        System.out.println(currentEvent.toString() + "\n"); 
                        displayQueue.enqueue(currentEvent); 
                    }
                    
                    // restore the original data
                    while (!displayQueue.isEmpty()) {
                        EventQueue.enqueue(displayQueue.dequeue());
                    }
                    break;
            
                case 5:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Removing data >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter EventID to be removed: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    boolean found = false;
                    
                    // Temporary queue for removing events
                    Queue tempQueue = new Queue();
                    
                    while (!EventQueue.isEmpty()) {
                        Event currentEvent = EventQueue.dequeue();
                        if (currentEvent.getEventID() == id) {
                            System.out.println("Event with ID " + id + " has been successfully removed.");
                            found = true;
                        }
                        // if not matching, just add back into the queue  
                        else {
                            tempQueue.enqueue(currentEvent); 
                        }
                    }
                    
                    // restore the original data
                    while (!tempQueue.isEmpty()) {
                        EventQueue.enqueue(tempQueue.dequeue());
                    }
                    
                    if (!found) {
                        System.out.println("Event is not found!");
                    }
                    break;
                
                case 6:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Events that have not paid full settlement >>>>>>>>>>>>>>>>>>\n");
                    boolean unpaidFound = false;
                    // temporary queue so the original queue can remain the same
                    Queue tempUnpaid = new Queue();
                
                    // Iterating through EventQueue without altering it
                    while (!EventQueue.isEmpty()) {
                        Event currentEvent = EventQueue.dequeue();
                        if (!currentEvent.getPaymentStatus()) {
                            System.out.println(currentEvent.toString()); // Print details of unpaid events
                            System.out.println();
                            unpaidFound = true;
                        }
                        tempUnpaid.enqueue(currentEvent); // Enqueue all events (paid and unpaid) to tempQueue
                    }
                
                    // restore the original data
                    while (!tempUnpaid.isEmpty()) {
                        EventQueue.enqueue(tempUnpaid.dequeue());
                    }
                
                    if (!unpaidFound) {
                        System.out.println("No events found with unpaid settlements.");
                    }
                    break;
                

                case 7:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Update Event Details >>>>>>>>>>>>>>>>>>\n");
                    System.out.print("Enter Event ID: ");
                    int targetid = scan.nextInt();
                    scan.nextLine();
                    boolean foundEvent = false;
                
                    // Temporary queue for updating event details
                    Queue updatedEvents = new Queue();
                
                    while (!EventQueue.isEmpty()) {
                        Event currentEvent = EventQueue.dequeue();
                        if (currentEvent.getEventID() == targetid) {
                            System.out.println("-------------- Event: " + currentEvent.getEventName() + " -----------------");
                            System.out.println("D - Date\nV - Venue\nT - Ticket Quantity\nP - Payment status");
                            System.out.print("Enter data to be changed: ");
                            char change = scan.nextLine().charAt(0);
                            change = Character.toUpperCase(change);
                            if (change == 'D') {
                                System.out.print("Enter new date: ");
                                String newdate = scan.nextLine();
                                currentEvent.setDate(newdate);
                            } 
                            else if (change == 'V') {
                                System.out.print("Enter new venue: ");
                                String newVenue = scan.nextLine();
                                currentEvent.setVenue(newVenue);
                            } 
                            // if the user wants to change ticket quantity
                            else if (change == 'T') {
                                System.out.print("Enter new ticket quantity(in digit): ");
                                int q = scan.nextInt(); scan.nextLine();
                                currentEvent.setTicketQuantity(q);  
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
                                currentEvent.setPaymentStatus(pstatus);
                            }
                            foundEvent = true;
                        }
                        // Enqueue updated events
                        updatedEvents.enqueue(currentEvent); 
                    }
                
                    // restore the original data
                    while (!updatedEvents.isEmpty()) {
                        EventQueue.enqueue(updatedEvents.dequeue());
                    }
                
                    if (!foundEvent) {
                        System.out.println("\nEvent with ID " + targetid + " not found.");
                    } else {
                        System.out.println("\nSuccessfully updated!");
                    }
                    break;
                

                case 8:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Split the records >>>>>>>>>>>>>>>>>>\n");
                    System.out.println("A - Concert Event\nB - Fashion Show\nC - Exhibition Event\nD - All Categories");
                    System.out.print("Enter category (A/B/C/D): ");
                    char category = scan.nextLine().charAt(0);
                    category = Character.toUpperCase(category);
                    
                    Queue tempQQ = new Queue();
                    if (category == 'A' || category == 'B' || category == 'C')
                        tempQQ = displayCategory(EventQueue, category);
                    else if (category == 'D') {
                        tempQQ= displayCategory(EventQueue, 'A');
                        tempQQ = displayCategory(tempQQ, 'B');
                        tempQQ = displayCategory(tempQQ, 'C');
                    }
                    // restore the original data
                    while (!tempQQ.isEmpty())
                        EventQueue.enqueue(tempQQ.dequeue());
                    break;
                    

                case 9:
                    System.out.println("\n\t<<<<<<<<<<<<<<<<< Calculate revenue based on category >>>>>>>>>>>>>>>>>>\n");
                    System.out.println("A - Concert Event\nB - Fashion Show\nC - Exhibition Event");
                    System.out.print("Enter category (A/B/C): ");
                    char ctg = scan.nextLine().charAt(0);
                    ctg = Character.toUpperCase(ctg);
                    
                    double totalRevenue = 0;
                    int totalEvents = 0;
                    
                    Queue tempRevenue = new Queue();
                    
                    while (!EventQueue.isEmpty()) {
                        Event currentEvent = EventQueue.dequeue();
                        if (currentEvent.getEventCategory() == ctg) {
                            totalRevenue += currentEvent.totalSales();
                            totalEvents++;
                        }
                        tempRevenue.enqueue(currentEvent); // Store events temporarily
                    }
                    
                    // restore the original data
                    while (!tempRevenue.isEmpty()) {
                        EventQueue.enqueue(tempRevenue.dequeue());
                    }
                    
                    if (totalEvents > 0) {
                        String categoryName;
                        switch (ctg) {
                            case 'A':
                                categoryName = "Concert Events";
                                break;
                            case 'B':
                                categoryName = "Fashion Show Events";
                                break;
                            case 'C':
                                categoryName = "Exhibition Events";
                                break;
                            default:
                                categoryName = "Invalid Category";
                                break;
                        }
                     
                        System.out.println("\n<<<<<<<<<<<<<<<<< " + categoryName + " >>>>>>>>>>>>>>>>>>\n");
                        System.out.println("Total Sales: RM" + String.format("%.2f", totalRevenue));
                        System.out.println("Total Events: " + totalEvents);
                    } else {
                        System.out.println("\nNo events found for the chosen category.");
                    }
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

