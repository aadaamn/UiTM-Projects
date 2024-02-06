import java.util.*;
import java.io.*;

class Store {
    // Attributes
    private Game[] inventory;
    private int count;

    // Normal constructor
    public Store(int capacity) {
        this.inventory = new Game[capacity];
        this.count = 0;
    }
    // getters
    public Game[] getInventory() {
        return inventory;
    }
    public int getCount(){
        return count;
    }
   
    // setters
    public void setInventory(Game game) {
        if (count < inventory.length) {
            inventory[count] = game;
            count++;
        } 
        else {
            // Increase capacity by creating a new array with double the size
            int newCapacity = count * 2;
            Game[] newInventory = new Game[newCapacity];
            
            // Copy existing games to the new array
            for (int i = 0; i < count; i++) {
                newInventory[i] = inventory[i];
            }
            
            // Addjng the new game to the new array
            newInventory[count] = game;
            count++;
            
            // Update inventory and capacity references
            inventory = newInventory;
            count = newCapacity;

            System.out.println("Inventory capacity has been increased. Game added successfully.");
        }
    }
    public void setCount(int count){
        this.count = count;
    }

    // method to calculate Total Inventory Value
    public static double calcTotalInventoryValue(Game[] games) {
        double totalValue = 0.0;
        for (int i = 0; i < games.length; i++) {
            if (games[i] != null) {
                totalValue += games[i].getPrice();
            }
        }
        return totalValue;
    }

    // method to add product manually
    public static Game[] addItem(int numGames){
        Scanner scan = new Scanner(System.in);
        Game[] gameList = new Game[numGames];
        for (int i = 0; i < numGames; i++) {
            System.out.println("\n#Product [" + (i+1) + "]");
            System.out.print("Enter game type (Game/Console/Gadget): ");
            String gameType = scan.nextLine();
            System.out.print("Enter product ID: ");
            String id = scan.nextLine();
            System.out.print("Enter title: ");
            String title = scan.nextLine();
            System.out.print("Enter price: ");
            double price = scan.nextDouble();
            scan.nextLine();
            if (gameType.equalsIgnoreCase("Game")) {
                gameList[i] = new Game(id,title, price);
            } 
            else if (gameType.equalsIgnoreCase("Console")) {
                System.out.print("Enter brand: ");
                String brand = scan.nextLine();
                gameList[i] = new Console(id,title, price, brand);
            }
            else if (gameType.equalsIgnoreCase("Gadget")) {
                System.out.print("Enter name: ");
                String name = scan.nextLine();
                gameList[i] = new Gadget(id,title, price, name);
            }
        }
        return gameList;
    }

    // method to read file input and store into inventory[] array
    public static Game[] readData(String filename) {
        Game[] games = new Game[0];
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int capacity = countLines(filename);
            games = new Game[capacity];
            String inData = null;
            int count = 0;
            while ((inData = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inData, ",");
                if (st.hasMoreTokens()) {
                    String itemType = st.nextToken().trim();
                    if (itemType.equalsIgnoreCase("Game")) {
                            String id = st.nextToken().trim();
                            String title = st.nextToken().trim();
                            double price = Double.parseDouble(st.nextToken().trim());
                            games[count] = new Game(id,title, price);
                            count++;
                    } 
                    else if (itemType.equalsIgnoreCase("Console")) {
                            String id = st.nextToken().trim();
                            String title = st.nextToken().trim();
                            double price = Double.parseDouble(st.nextToken().trim());
                            String brand = st.nextToken().trim();
                            games[count] = new Console(id,title, price, brand);
                            count++;
                    } 
                    else if (itemType.equalsIgnoreCase("Gadget")) {
                            String id = st.nextToken().trim();
                            String title = st.nextToken().trim();
                            double price = Double.parseDouble(st.nextToken().trim());
                            String item = st.nextToken().trim();
                            games[count] = new Gadget(id, title, price, item);
                            count++;
                    }
                }
            }
            in.close();
        } 
        catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } 
        catch (IOException iox) {
            System.out.println(iox.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Problem: " + e.getMessage());
        }
        return games;
    }
    
    // method to scan how many lines in the file input
    public static int countLines(String filename) {
        int count = 0;
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                scan.nextLine();
                count++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    // method to generate output file/Invoice
    public static void fileOutput(Game[] temporary){
        double totalValue = calcTotalInventoryValue(temporary);
        try {
        PrintWriter outInventory = new PrintWriter (new BufferedWriter(new FileWriter("StoreInventory")));
        outInventory.println("====================================================================================");
        outInventory.println();
        outInventory.println("_|_|_|                                        _|_|_|    _|");                          
        outInventory.println("_|          _|_|_|  _|_|_|  _|_|      _|_|    _|        _|_|_|_|    _|_|    _|_|_|");    
        outInventory.println("_|  _|_|  _|    _|  _|    _|    _|  _|_|_|_|    _|_|      _|      _|    _|  _|    _|");  
        outInventory.println("_|    _|  _|    _|  _|    _|    _|  _|              _|    _|      _|    _|  _|    _|");  
        outInventory.println("  _|_|_|    _|_|_|  _|    _|    _|    _|_|_|  _|_|_|        _|_|    _|_|    _|_|_|    ");
        outInventory.println("                                                                            _|        ");
        outInventory.println("                                                                            _|     ");
        outInventory.println("====================================================================================\n");
        outInventory.println("");
        outInventory.println("\t--------------------- GameStop Inventory -------------------\n");
        outInventory.println("\n\tAll Item");
        outInventory.println("\t---------");
        for (int i = 0; i < temporary.length; i++){
            if (temporary[i] != null){
                // Will output all objects
                outInventory.println("\t" + String.format("%-50s" , temporary[i].toDisplay()) + "| RM" +temporary[i].getPrice());
            }
        }
        outInventory.println("\n\tGames");
        outInventory.println("\t-----");
        for (int i = 0; i < temporary.length; i++){
            if (isGame(temporary[i])){
                // Will output games objects
                outInventory.println("\t" + String.format("%-50s" , temporary[i].toDisplay()) + "| RM" + String.format("%.2f", temporary[i].getPrice()));
            }
        }
        outInventory.println("\n\tConsoles");
        outInventory.println("\t--------");
        for (int i = 0; i < temporary.length; i++){
            if (isConsole(temporary[i])){
                // Will output console objects
                Console c = (Console) temporary[i];
                outInventory.println("\t" + String.format("%-50s" , c.toDisplay()) + "| RM" + String.format("%.2f", c.getPrice()));
            }
        }
        outInventory.println("\n\tGadgets");
        outInventory.println("\t-------");
        for (int i = 0; i < temporary.length; i++){
            if (isGadget(temporary[i])){
                // Will output gadget objects
                Gadget g = (Gadget) temporary[i];
                outInventory.println("\t" + String.format("%-50s" , g.toDisplay()) + "| RM" + String.format("%.2f", g.getPrice()));
            }
        }
        outInventory.println("\n\n\t" + String.format("%-50s" , "Total Value for GameStop Inventory: ") + "| RM" + String.format("%.2f" , totalValue));


        outInventory.close();
        }
        catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } 
        catch (IOException iox) {
            System.out.println(iox.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Problem: " + e.getMessage());
        } 
    }
    

    // to test condition whether the array is occupied and the object type - Game,Console or Gadget
    public static boolean isGame(Game game) {
        return game != null && game.getClass().equals(Game.class);
    }
    public static boolean isConsole(Game game) {
        return game != null && game.getClass().equals(Console.class);
    }
    public static boolean isGadget(Game game) {
        return game != null && game.getClass().equals(Gadget.class);
    }

    // Display Menu General
    public void displayMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("====================================================================================");
        System.out.println();
        System.out.println("_|_|_|                                        _|_|_|    _|");                          
        System.out.println("_|          _|_|_|  _|_|_|  _|_|      _|_|    _|        _|_|_|_|    _|_|    _|_|_|");    
        System.out.println("_|  _|_|  _|    _|  _|    _|    _|  _|_|_|_|    _|_|      _|      _|    _|  _|    _|");  
        System.out.println("_|    _|  _|    _|  _|    _|    _|  _|              _|    _|      _|    _|  _|    _|");  
        System.out.println("  _|_|_|    _|_|_|  _|    _|    _|    _|_|_|  _|_|_|        _|_|    _|_|    _|_|_|    ");
        System.out.println("                                                                            _|        ");
        System.out.println("                                                                            _|     ");
        System.out.println("====================================================================================\n");
    }

    // updating data using product IDs
    public void updateData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n----- Updating Products Data -----");
        System.out.print("Enter product ID: ");
        String id = scan.nextLine();

        boolean found = false;
        for (int i = 0; i < inventory.length; i++){
            if (inventory[i] != null && inventory[i].getID().equalsIgnoreCase(id)){
                found = true;
                // random value so can enter the loop
                int option = 1;
                // to change attributes on GAME item
                if (isGame(inventory[i])){
                    while (option != 3){
                        System.out.println("\n" + inventory[i].toString());
                        System.out.println("[1] Update title\n[2] Update price\n[3] Done");
                        System.out.print("Enter option: ");
                        option = scan.nextInt();
                        scan.nextLine();
                        if (option == 1){
                            System.out.print("Enter new title: ");
                            String newTitle = scan.nextLine();
                            inventory[i].setTitle(newTitle);
                        }
                        else if (option == 2){
                            System.out.print("Enter price: ");
                            double newPrice = scan.nextDouble();
                            scan.nextLine();
                            inventory[i].setPrice(newPrice);
                            System.out.print("Is the game is on summer sale?\nEnter (Y - Yes/ N - No): ");
                            char sale = scan.nextLine().charAt(0);
                            sale = Character.toUpperCase(sale);
                            if (sale == 'Y')
                                inventory[i].setPrice(inventory[i].calcDiscountedPrice());
                            else
                                break;     
                        }
                    }
                }
                       
                // to change attributes on CONSOLE item
                else if (isConsole(inventory[i])){
                    Console x = (Console) inventory[i];
                    while (option != 4){
                        System.out.println("\n" + inventory[i].toString());
                        System.out.println("[1] Update title\n[2] Update price\n[3] Update brand\n[4] Done");
                        System.out.print("Enter option: ");
                        option = scan.nextInt();
                        scan.nextLine();
                        if (option == 1){
                            System.out.print("Enter new title: ");
                            String newTitle = scan.nextLine();
                            inventory[i].setTitle(newTitle);
                        }
                        else if (option == 2){
                            System.out.print("Enter price: ");
                            double newPrice = scan.nextDouble();
                            scan.nextLine();
                            inventory[i].setPrice(newPrice);
                            System.out.print("Is the console is on summer sale?\nEnter (Y - Yes/ N - No): ");
                            char sale = scan.nextLine().charAt(0);
                            sale = Character.toUpperCase(sale);
                            if (sale == 'Y')
                                inventory[i].setPrice(inventory[i].calcDiscountedPrice());
                            else
                                break;     
                        }
                        else if (option == 3){
                            System.out.print("Enter new brand: ");
                            String newBrand = scan.nextLine();
                            x.setBrand(newBrand);
                            inventory[i] = x;
                            break;
                        }
                    }
                }
                // to change attributes on GADGET item
                else if (isGadget(inventory[i])){
                    Gadget x = (Gadget) inventory[i];
                    while (option != 4){
                        System.out.println("\n" + inventory[i].toString());
                        System.out.println("[1] Update title\n[2] Update price\n[3] Update item\n[4] Done");
                        System.out.print("Enter option: ");
                        option = scan.nextInt();
                        scan.nextLine();
                        if (option == 1){
                            System.out.print("Enter new title: ");
                            String newTitle = scan.nextLine();
                            inventory[i].setTitle(newTitle);
                        }
                        else if (option == 2){
                            System.out.print("Enter price: ");
                            double newPrice = scan.nextDouble();
                            scan.nextLine();
                            inventory[i].setPrice(newPrice);
                            System.out.print("Is the console is on summer sale?\nEnter (Y - Yes/ N - No): ");
                            char sale = scan.nextLine().charAt(0);
                            sale = Character.toUpperCase(sale);
                            if (sale == 'Y')
                                inventory[i].setPrice(inventory[i].calcDiscountedPrice());
                            else
                                break;     
                        }
                        else if (option == 3){
                            System.out.print("Enter new item: ");
                            String newItem = scan.nextLine();
                            x.setitem(newItem);
                            inventory[i] = x;
                            break;
                        }
                    }
                }
            }
        }
        if (found)
                System.out.println("Product successfully updated!");
        else if(found != true)   
                System.out.println("Product is not found!");
    }
}