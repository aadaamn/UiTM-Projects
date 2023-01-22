#include <iostream>
#include <array>
#include <vector>
using namespace std;

// Function declaration
void MainMenu();
void showtimes();
void AccountMenu();
bool signIn();
void view_show();
void buy_ticket(string show, string date);
void display_ticket(string title, string date, float total);

bool signIn() { // Function for sign in into account
    // Variables
    string username, password;
    
    cout << "Enter your username: ";
    cin >> username;
    cout << "Enter your password: ";
    cin >> password;

    if (password == "12345")
        return true;
    else 
        return false;
}

void view_show() {
    int show;
    string shows[100] = {"Travis Scott - 29 March 2024" , "Tyler The Creator - 1 April 2024", "Post Malone - 3 April 2024", 
    "The Weekend - 7 April 2024"};

    cout << "\n\n";
    for (int i = 0; i < 4; i ++) {
        cout << "[" << i + 1 << "] " << shows[i];
        cout << endl;
    } 
    cout << "\n\nEnter show you would like to purchase: ";
    cin >> show;

    if (show == 1)
        buy_ticket("Travis Scott Astroworld ", "29 March 2024");
    else if (show == 2)
        buy_ticket("Tyler the Creator - Live", "1 April 2024");
    else if (show == 3)
        buy_ticket("Post Malone - All Tryie ", "3 April 2024");
    else if (show == 4)
        buy_ticket("The Weekend - The Lights", "7 April 2024");
    
}

void showtimes() {  // Function show apa yang available
    int option;
    string shows[100] = {"Travis Scott - 29 March 2024" , "Tyler The Creator - 1 April 2024","Post Malone - 3 April 2024", 
    "The Weekend - 7 April 2024"};

    cout << "\n\n";
    for (int i = 0; i < 5; i ++) {
        cout << shows[i];
        cout << endl;
    } 
    cout << "\n\n[1] Buy Tickets\n";
    cout << "[0] Back\n";
    cout << "\nEnter your command: ";
    cin >> option;

    if (option == 0)
        AccountMenu();
    else if (option == 1)
        view_show();
}

void display_ticket(string title, string date, float total){
    cout << " _________________________________________________________________________" << endl;
    cout << "|                                                                         |" << endl;
    cout << "|     Show Name: " << title << "                                 |" << endl;
    cout << "|                                                                         |" << endl;
    cout << "|     Date : " << date << "                                                 |" << endl;
    cout << "|                                                                         |" << endl;
    cout << "|     Price    : RM" << total << " For " << total / 50 << " Persons                                       |" << endl;
    cout << "|_________________________________________________________________________|" << endl;
}




// Ticket menu for everything, everything start here - adam
void buy_ticket(string show, string date) {
    // Variable
    int ticket, choice;
    float total_price = 0;
    cout << "\n\t\t" << show;
    string seat[100] = {"[A1]","[A2]", "[A3]", "[A4]" , "[A5]", "[A6]", "[A7]", "[A8]" , "[A9]", "[A10]",
    "[A11]", "[A12]" , "[A13]" , "[A14]", "[A15]", "[A16]", "[A16]" , "[A17]" , "[A18]" , "[A19]" , "[A20]",
    "[A21]", "[A22]", "[A23]", "[A24]", "[A25]", "[A26]", "[A27]", "[A28]", "[A29]", "[A30]"};
    cout << "\n\n";
    for (int i = 0; i < 32; i++){
        cout << "  " << seat[i];
        if (i % 10 == 0)
            cout << endl;
    }
    
    cout <<"\n\n[1] Buy Ticket";
    cout << "\n[0] Main Menu";
    cout << "\nPlease enter command: ";
    cin >> choice;
    if (choice == 1) {
        char choice = 'Y';
        while(choice == 'Y'){
            cout << "Enter the seat you want (number): ";
            cin >> ticket;
            if (ticket <= 10)
                seat[ticket - 1] = "[ X ]";
            else
                seat[ticket] = "[ X ]";
            cout << "\n\t\t" << show;
            cout << "\n\n";
            for (int i = 0; i < 32; i++){
                cout << "  " << seat[i];
                if (i % 10 == 0)
                cout << endl;
            }
            cout << "\n\nWould you like to continue? (Y/N): ";
            cin >> choice;
            choice = toupper(choice);
        }
        for (int i = 0; i < 32; i++){
            if(seat[i] == "[ X ]")
                total_price += 50;
            }
        display_ticket(show , date , total_price);
    }
    else    
        MainMenu();
    
}
        


void AccountMenu() {    // Main menu if dah sign in into account
    // Variables
    int option;
    cout << " ________________________" << endl;
    cout << "|                        |" << endl;
    cout << "| Welcome to Astroworld! |" << endl;
    cout << "|________________________|" << endl;

    cout << "\nPoints available: 0.00\n\n"; 
    cout << "\n[1] Available Shows & Showtimes" << endl;
    cout << "[2] Buy Tickets" << endl;
    cout << "[3] Sign out" << endl;
    cout << "Enter your command: ";
    cin >> option;

    if (option == 1) 
        showtimes();
    else if (option == 2)
        view_show();
    
}


void MainMenu () {  // Main menu general
    // Variables
    int option;
    cout << " ________________________" << endl;
    cout << "|                        |" << endl;
    cout << "| Welcome to Astroworld! |" << endl;
    cout << "|________________________|" << endl;

    cout << "\n[1] Available Shows & Showtimes" << endl;
    cout << "[2] Buy Tickets" << endl;
    cout << "[3] Sign in" << endl;
    cout << "Enter your command: ";
    cin >> option;

    if (option == 1) 
        showtimes();
    else if(option == 2){
        view_show();
    }
    else if (option == 3){
        if (signIn() == true)
            AccountMenu();
        else 
            MainMenu();
    }
    
}

int main () {
    MainMenu();
}

