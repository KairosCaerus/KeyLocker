package application;

import java.io.File;
import java.sql.*;

public class UserDatabaseHandler {

    // singleton reference
    private static UserDatabaseHandler instance = new UserDatabaseHandler();

    // gets the current singleton instance
    static UserDatabaseHandler getDBInstance(){
        return instance;
    }

    // stores the database connection
    Connection userDBConnection;

    // constructs the dbHandler object
    private UserDatabaseHandler(){
        // create database directory
        createDatabaseDirectory();
    }

    // initializes the database if it does not already exists. Should not break database if this is run while already initialized
    void createUserCredsDatabase(String dbName){
        String url = "jdbc:sqlite:databases/" + dbName;
        try{
            // creates db connection, creates new db file if none already exists
            userDBConnection = DriverManager.getConnection(url);
            System.out.println("Currently connected to database: " + dbName);

            // creates table if not exists
            String sqlCommand = "CREATE TABLE IF NOT EXISTS clients (clientname text PRIMARY KEY, password text NOT NULL)";

            Statement stmt = userDBConnection.createStatement();
            stmt.execute(sqlCommand);

        }catch(SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    // creates the database folder, if the database folder exists assumes that the database has already been set up
    void createDatabaseDirectory(){
        // get filepath
        File dir = new File("databases/");

        //check if directory exists. if directory does not exist create directory and database, else connect to the database
        if(!dir.exists()) {
            System.out.println("Database directory was created");
            dir.mkdir();
            createUserCredsDatabase("loginCredsUserDB");
        }else{
            System.out.println("Database directory already exists");
            createDatabaseConnection("loginCredsUserDB");
        }
    }

    // connects to an already existing database
    void createDatabaseConnection(String dbName){
        String url = "jdbc:sqlite:databases/" + dbName;
        try{
            userDBConnection = DriverManager.getConnection(url);
            System.out.println("Currently connected to database: " + dbName);

        }catch(SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    // adds a user to the database, returns true if successful, false if not. NOTE: Returns false if ANY error occurs
    boolean addClientToDatabase(String clientname, String password){
        try{
            String sqlCommand = "INSERT INTO clients (clientname, password) VALUES(?, ?)";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, clientname);
            preparedSQLCommand.setString(2, password);

            preparedSQLCommand.executeUpdate();

            System.out.println(String.format("%s has been added to the client database.", clientname));
            return true;
        }catch(SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();
            return false;
        }
    }

    // verifies if the given arguements lead to a valid client. If it does, returns true else returns false. NOTE: Also returns false for any error that occurs.
    boolean verifyClientLogin(String clientname, String password){
        try{
            String sqlCommand = "SELECT * FROM clients WHERE clientname = ? AND password = ?";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, clientname);
            preparedSQLCommand.setString(2, password);

            ResultSet resultSet = preparedSQLCommand.executeQuery();

            if (resultSet.next() == false){
                System.out.println(String.format("Login failed for %s", clientname));
                return false;
            }

            System.out.println(String.format("%s has successfully logged in.", clientname));
            return true;
        }catch (SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();
            return false;
        }
    }
}
