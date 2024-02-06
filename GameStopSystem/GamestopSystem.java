import java.util.*;
import java.io.*;

public class GamestopSystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Create a GameStop store
        Store gamestopStore = new Store(100);

        int option  = 0;
        while (option != 4){
            gamestopStore.displayMenu();
            System.out.println("\n[1] Add data automatically with a file" + "\n[2] Add data manually" + "\n[3] Change any data regarding the products" + "\n[4] Exit");
            System.out.print("\nEnter option: ");
            option = scan.nextInt();
            scan.nextLine();
            switch(option){
                case 1:
                    // to add data automatically using file input
                    boolean found = false;
                    System.out.print("Enter filename (in .txt format): ");
                    String filename = scan.nextLine();
                    Game[] fileGames = gamestopStore.readData(filename);
                    for (int i = 0; i < fileGames.length; i++){
                        gamestopStore.setInventory(fileGames[i]);
                        found = true;
                    }
                    if (found)
                        System.out.println("File input successfully loaded into the system!");
                    else
                        System.out.println("Error! Cannot find the specific file");
                    break;
                case 2:
                    // to add data manually
                    System.out.print("Enter how many products: ");
                    int quantity = scan.nextInt();
                    scan.nextLine();
                    Game[] manualGames = Store.addItem(quantity);
                    for (int i = 0; i < manualGames.length; i++) 
                        gamestopStore.setInventory(manualGames[i]);
                    System.out.println("Product successfully loaded into the system!");
                    break;
                case 3:
                    // to update data's attributes
                    gamestopStore.updateData();
            }
        }

        // Generate file output
        gamestopStore.fileOutput(gamestopStore.getInventory());

    }

}
