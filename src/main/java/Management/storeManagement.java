package Management;

import java.sql.*;

public class storeManagement {

    static Connection connection;
    public storeManagement(Connection connect){
        connection = connect;
    }

    public static void createTable() throws Exception {
        Statement stmt = connection.createStatement();
        String q = "create table if not exists bookstore (id serial primary key,name varchar(20),sname varchar(20),gender varchar(20));";
        try (ResultSet results = stmt.executeQuery(q)) {

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public static void viewAllBooks() throws Exception {
        Statement stmt = connection.createStatement();
        String q = "select * from bookstore order by id asc";
        ResultSet results = stmt.executeQuery(q);

        while (results.next()) {
            System.out.println("Id: " + results.getInt(1));
            System.out.println("Title: " + results.getString(2));
            System.out.println("Author: " + results.getString(3));
            System.out.println("Price: " + results.getFloat(4));
            System.out.println("Quantity: " + results.getInt(5));

            System.out.println();

        }
    }

    public static boolean addBook(String bookname, String authorname, float price, int quantity) throws Exception {
        // Check if the book already exists
        PreparedStatement checkStmt = connection.prepareStatement("select count(*) from bookstore where book_name = ? and book_author = ?");
        checkStmt.setString(1, bookname);
        checkStmt.setString(2, authorname);
        ResultSet resultSet = checkStmt.executeQuery();
        resultSet.next();


        int count = resultSet.getInt(1);

        if (count > 0) {
            // Book exists, let user know that it already exists
            return false;
        } else {
            // Book doesn't exist, insert a new record
            PreparedStatement insertStmt = connection.prepareStatement("insert into bookstore (book_name, book_author, price, quantity) values (?, ?, ?, ?)");
            insertStmt.setString(1, bookname);
            insertStmt.setString(2, authorname);
            insertStmt.setFloat(3, price);
            insertStmt.setInt(4, quantity);
            insertStmt.executeUpdate();

            return true;
        }
    }


    public static void delBook(int id) throws Exception{
        PreparedStatement pst = connection.prepareStatement("delete from bookstore where id = ?");
        pst.setInt(1, id);
        pst.executeUpdate();
    }

    public static void updBook(int id, int choice, String name) throws Exception{
        PreparedStatement updStm;
        if(choice == 1){
            updStm = connection.prepareStatement("update bookstore set book_name=? where id=?");
            updStm.setString(1,name);
            updStm.setInt(2,id);
            updStm.executeUpdate();
        }else{
            updStm = connection.prepareStatement("update bookstore set author_name=? where id=?");
            updStm.setString(1,name);
            updStm.setInt(2,id);
            updStm.executeUpdate();
        }

    }
    public static void updBook(int id, float price) throws Exception{
        PreparedStatement updStm = connection.prepareStatement("update bookstore set price=? where id=?");
        updStm.setFloat(1, price);
        updStm.setInt(2,id);
        updStm.executeUpdate();
    }
    public static void updBook(int id, int quantity) throws Exception{
        PreparedStatement updStm = connection.prepareStatement("update bookstore set quantity=? where id=?");
        updStm.setInt(1,quantity);
        updStm.setInt(2,id);
        updStm.executeUpdate();
    }

    public static boolean doesExist(int id) throws Exception{
        PreparedStatement checkId = connection.prepareStatement("select count(*) from bookstore where id=?");
        checkId.setInt(1, id);
        ResultSet resultSet = checkId.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

}
