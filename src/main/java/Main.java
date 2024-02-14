


/*

This program is a book store management application.
Admin will be able to add, delete and update information about books in the store.

 */


import Management.storeManagement;

import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws Exception{

        //change the _data_ to your own to use this program

        String URL = "jdbc:postgresql://localhost:5432/_database_";
        String USER = "_user_";
        String PASS = "_password_";

        Connection connection = DriverManager.getConnection(URL , USER , PASS);
        storeManagement admin = new storeManagement(connection);
        Scanner scanner = new Scanner(System.in);
        admin.createTable();

        while(true) {
            System.out.println("_₊✩‧₊˚౨ৎ˚₊✩‧₊_Book Store Manager_₊✩‧₊˚౨ৎ˚₊✩‧₊_");
            System.out.println("_₊✩‧₊˚౨ৎ˚₊✩‧₊_1. View all books_₊✩‧₊˚౨ৎ˚₊✩‧₊_");
            System.out.println("___₊✩‧₊˚౨ৎ˚₊✩‧₊__2. Add book__₊✩‧₊˚౨ৎ˚₊✩‧₊___");
            System.out.println("__₊✩‧₊˚౨ৎ˚₊✩‧₊__3. Delete Book__₊✩‧₊˚౨ৎ˚₊✩‧₊__");
            System.out.println("__₊✩‧₊˚౨ৎ˚₊✩‧₊__4. Update book__₊✩‧₊˚౨ৎ˚₊✩‧₊__");
            System.out.println("____₊✩‧₊˚౨ৎ˚₊✩‧₊___5. EXIT___₊✩‧₊˚౨ৎ˚₊✩‧₊____");

            int choose = scanner.nextInt();
            scanner.nextLine();

            if(choose == 1) admin.viewAllBooks();

            else if(choose == 2) {
                System.out.println("Enter book title: ");
                String bookname = scanner.nextLine();

                System.out.println("Enter author's name: ");
                String authorname = scanner.nextLine();

                System.out.println("Enter book's price: ");
                float price = (float) scanner.nextFloat();

                System.out.println("Enter number of books: ");
                int quantity = (int) scanner.nextInt();

                System.out.println("Adding a new book to store...");
                TimeUnit.MILLISECONDS.sleep(1500);
                if(admin.addBook(bookname, authorname, price, quantity)){
                    System.out.println("New book added successfully!");
                }else{
                    System.out.println("Book you entered already exists");
                    System.out.println("Try updating");
                }
            }

            else if(choose == 3) {
                System.out.println("Select book ID: ");
                admin.viewAllBooks();
                int id = scanner.nextInt();

                System.out.println("Deleting a selected book...");
                TimeUnit.SECONDS.sleep(1);

                if(admin.doesExist(id)){
                    admin.delBook(id);
                    System.out.println("Book deleted successfully!");
                }else{
                    System.out.println("The book doesn't exist");
                }
            }
            else if(choose == 4) {
                System.out.println("Select book ID: ");
                admin.viewAllBooks();
                int id = scanner.nextInt();

                if(admin.doesExist(id)){
                    System.out.println("1. Update book title");
                    System.out.println("2. Update book's author");
                    System.out.println("3. Update price");
                    System.out.println("4. Update quantity");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 1){
                        String bookname = scanner.nextLine();
                        admin.updBook(id, choice, bookname);
                    }else if(choice == 2){
                        String authorname = scanner.nextLine();
                        admin.updBook(id, choice, authorname);
                    }else if(choice == 3){
                        float price = scanner.nextFloat();
                        admin.updBook(id, price);
                    }else if(choice == 4){
                        int quantity = scanner.nextInt();
                        admin.updBook(id, quantity);
                    }

                }else{
                    System.out.println("The book doesn't exist");
                }
            }
            else if(choose == 5) {
                break;
            }

        }

    }












}