package application;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class UserDatabaseController {

    // singleton reference
    private static UserDatabaseController instance = new UserDatabaseController();

    // gets the current singleton instance
    public static UserDatabaseController getDBInstance(){
        return instance;
    }

    // stores the database connection
    Connection userDBConnection;

    // constructs the dbHandler object
    private UserDatabaseController(){
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
            String sqlCommand1 = "CREATE TABLE IF NOT EXISTS clients (clientname text PRIMARY KEY, password text NOT NULL)";
            String sqlCommand2 = "CREATE TABLE IF NOT EXISTS accounts (owner text, service text, username text NOT NULL, password text, notes text)";
            String sqlCommand3 = "CREATE UNIQUE INDEX IF NOT EXISTS idx_accountid ON accounts (owner, service, username)";

            Statement stmt = userDBConnection.createStatement();
            stmt.execute(sqlCommand1);
            stmt.execute(sqlCommand2);
            stmt.execute(sqlCommand3);

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

            System.out.printf("%s has been added to the client database.%n", clientname);
            return true;
        }catch(SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();
            return false;
        }
    }

    // verifies if the given arguments lead to a valid client. If it does, returns true else returns false. NOTE: Also returns false for any error that occurs.
    boolean verifyClientLogin(String clientname, String password){
        try{
            String sqlCommand = "SELECT * FROM clients WHERE clientname = ? AND password = ?";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, clientname);
            preparedSQLCommand.setString(2, password);

            ResultSet resultSet = preparedSQLCommand.executeQuery();

            if (!resultSet.next()){
                System.out.printf("Login failed for %s%n", clientname);
                return false;
            }

            System.out.printf("%s has successfully logged in.%n", clientname);
            return true;
        }catch (SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();
            return false;
        }
    }

    boolean updateClientPassword(String clientname, String newPassword){
        try{
            String sqlCommand1 = "SELECT * FROM clients WHERE clientname = ?";
            PreparedStatement preparedSQLCommand1 = userDBConnection.prepareStatement(sqlCommand1);

            ResultSet resultSet = preparedSQLCommand1.executeQuery();

            if(resultSet.next()){
                String sqlCommand2 = "UPDATE clients SET password = ? WHERE clientname = ?";
                PreparedStatement preparedSQLCommand2 = userDBConnection.prepareStatement(sqlCommand2);

                preparedSQLCommand2.setString(1, newPassword);
                preparedSQLCommand2.setString(2, clientname);

                preparedSQLCommand2.executeUpdate();

                return true;
            }

            return false;
        }catch (SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();

            return false;
        }
    }

    boolean addClientAccount(String owner, String service,  String username, String password){
        return addClientAccount(owner, service, username, password, "");
    }

    boolean addClientAccount(String owner, String service, String username, String password, String notes){
        try{
            String sqlCommand = "INSERT INTO accounts (owner, service, username, password, notes) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, owner);
            preparedSQLCommand.setString(2, service);
            preparedSQLCommand.setString(3, username);
            preparedSQLCommand.setString(4, password);
            preparedSQLCommand.setString(5, notes);

            preparedSQLCommand.executeUpdate();

            System.out.printf("%s added to client %s%n", username, owner);

            return true;

        }catch(SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();

            return false;
        }
    }

    public ArrayList<ArrayList<String>> getClientAccounts(String owner){
        try{
            String sqlCommand = "SELECT * FROM accounts WHERE owner = ?";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, owner);

            ResultSet resultSet = preparedSQLCommand.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            ArrayList<ArrayList<String>> results = new ArrayList<>();

            while(resultSet.next()){
                ArrayList<String> curResult = new ArrayList<String>();
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    curResult.add(resultSet.getString(i));
                }
                results.add(curResult);
            }

            return results;

        }catch (SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();

            return new ArrayList<>();
        }
    }

    ArrayList<String> getClientAccount(String owner, String service, String username){
        try{
            String sqlCommand = "SELECT * FROM accounts WHERE owner = ? AND service = ? AND username = ?";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, owner);
            preparedSQLCommand.setString(2, service);
            preparedSQLCommand.setString(3, username);

            ResultSet resultSet = preparedSQLCommand.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            ArrayList<String> result = new ArrayList<String>();

            if (resultSet.next()){
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
                    result.add(resultSet.getString(i));
                }
            }

            return result;
        }catch (SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();

            return new ArrayList<String>();
        }
    }

    boolean changeClientAccountPassword(String owner, String service, String username, String newPassword){
        try{
            ArrayList<String> account = getClientAccount(owner, service, username);

            if(account.size() > 0){
                String sqlCommand = "UPDATE accounts SET password = ? WHERE owner = ? AND service = ? AND username = ?";
                PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

                preparedSQLCommand.setString(1, newPassword);
                preparedSQLCommand.setString(2, owner);
                preparedSQLCommand.setString(3, service);
                preparedSQLCommand.setString(4, username);

                preparedSQLCommand.executeUpdate();
            }

            return true;

        }catch (SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();

            return false;
        }
    }

    boolean deleteClientAccount(String owner, String service, String username, String password){
        try{
            String sqlCommand = "DELETE FROM accounts WHERE owner = ? AND service = ? AND username = ? AND password = ?";
            PreparedStatement preparedSQLCommand = userDBConnection.prepareStatement(sqlCommand);

            preparedSQLCommand.setString(1, owner);
            preparedSQLCommand.setString(2, service);
            preparedSQLCommand.setString(3, username);
            preparedSQLCommand.setString(4, password);

            preparedSQLCommand.executeUpdate();

            System.out.printf("%s was deleted from client %s%n", username, owner);

            return true;

        }catch(SQLException e){
            System.out.println("An error has occurred:");
            System.out.println(e.getMessage());
            System.out.println();

            return false;
        }
    }

    boolean deleteClient(String clientname, String password){
        if (verifyClientLogin(clientname, password)){
            try{
                String sqlCommand1 = "DELETE FROM clients WHERE clientname = ?";
                String sqlCommand2 = "DELETE FROM accounts WHERE owner = ?";

                PreparedStatement preparedSQLCommand1 = userDBConnection.prepareStatement(sqlCommand1);
                PreparedStatement preparedSQLCommand2 = userDBConnection.prepareStatement(sqlCommand2);

                preparedSQLCommand1.setString(1, clientname);
                preparedSQLCommand2.setString(1, clientname);

                preparedSQLCommand1.executeUpdate();
                preparedSQLCommand2.executeUpdate();

                System.out.printf("%s and all associated accounts were deleted", clientname);

                return true;

            }catch (SQLException e){
                System.out.println("An error has occurred:");
                System.out.println(e.getMessage());
                System.out.println();

                return false;
            }
        }
        
        return false;
    }
}
