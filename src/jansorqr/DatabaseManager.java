/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jansorqr;

import java.sql.*;

/**
 *
 * @author jansoriano acer
 */
public class DatabaseManager {

    public DatabaseManager() {

    }

    public DatabaseManager(DatabaseConfig config) {
        this.config = config;
    }

    //Variable declarations
    private DatabaseConfig config;
    private String column[];        //the column names from left to right beginning zero.
    private final String myDriver = "com.mysql.jdbc.Driver";    //the driver to be used. 
    public static final int CONFIG_SUCCESS = 1;
    public static final int INSERT_SUCCESS = 2;
    public static final int CHANGES_SAVED = 3;
    public static final int CHANGES_UNDONE = 4;
    public static final int FAIL = 0;
    public String tableName;

    //End of variable declarations
    /**
     * Sets the database details to be used by the DatabaseManager.
     *
     * @param config - an object of DatabaseConfig containing the details of the
     * database to be used.
     * @return
     */
    public void setDatabaseConfig(DatabaseConfig config) {
        Main.report("DatabaseConfig set to connect to " + config.getIP() + ".");
        this.config = config;
    }

    public void setTableToUse(DatabaseTable table) {
        tableName = table.getTableName();
        Main.report("Using table \'" + tableName + "\'.");
    }

    /**
     * Tests if the current database configuration is valid.
     *
     * @return returns true if the connection can be established.
     */
    public boolean testConnection() {
        try {
            // create our mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Main.report(ex.getMessage());;
        }
        return false;
    }

    /**
     * Determines if the specified database configuration is accessible.
     *
     * @param config - database configuration which contains host address,
     * username, password, etc.
     * @return returns true if the specified database configuration is
     * accessible.
     */
    public static boolean testConnection(DatabaseConfig config) {
        Main.report("Testing connection to " + config.getIP() + "...");
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            // create our mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());
            conn.close();
            Main.report("Connection to " + config.getIP() + " is working.");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Main.report(ex.getMessage());
            Main.report("Connection to " + config.getIP() + " is NOT working!");
        }
        return false;
    }

    /**
     * Fetches the database table specified by the DatabaseConfig object.
     *
     * @param columnName - The name of the column where the row can be
     * identified. For ex. a column with a PRIMARY_KEY constraint.
     * @param columnKey - The value within the columnName where the row is
     * identified.
     * @return String[] - where String[0] to String[String.length] spans from
     * left to right of the table.
     */
    public String[] fetchRow(String columnName, String columnKey) {
        Main.report("Fetching row for " + columnKey + "...");
        try {
            // create our mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM " + tableName
                    + " WHERE " + columnName + " = " + columnKey;

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            int columnCount = rs.getMetaData().getColumnCount();
            column = new String[columnCount];

            // iterate through the java resultset
            while (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    System.out.println(rs.getString(i + 1));
                    column[i] = rs.getString(i + 1);
                }
            }
            st.close();
            Main.report("Done fetching row for " + columnKey + ".");
            return column;

        } catch (ClassNotFoundException | SQLException e) {
            Main.report(e.getMessage());
            Main.report("Failed to fetch row for " + columnKey + "!");
        }
        return null;
    }

    /**
     * This method returns the raw ResultSet obtained from the database.
     *
     * @return returns the ResultSet
     */
    public ResultSet fetchAll() {
        Main.report("Fetching all columns:");
        try {
            // create our mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM " + tableName;

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            Main.report("ResultSet successfully fetched.");
            return rs;

        } catch (ClassNotFoundException | SQLException e) {
            Main.report(e.getMessage());
            Main.report("Failed fetching ResultSet.");
        }
        return null;
    }

    /**
     * This method inserts a row into a database table.
     *
     * @param columnNames - the names of each column where the new data is to be
     * inserted.
     * @param values - the values corresponding to the columns.
     * @return returns INSERT_SUCCESS if the method successfully inserts the
     * given values into the table.
     */
    public int insertRow(String columnNames[], String values[], boolean commit) {
        Main.report("Startig to insert row...");
        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());

            // the mysql insert statement
            String query = "INSERT INTO " + tableName + "(";
            for (int i = 0; i < columnNames.length; i++) {
                if (i == columnNames.length - 1) {
                    query = query + columnNames[i] + ") ";
                    break;
                }
                query = query + columnNames[i] + ", ";
            }
            query = query + " VALUES (";
            for (int i = 0; i < columnNames.length; i++) {
                if (i == columnNames.length - 1) {
                    query = query + "?);";
                    break;
                }
                query = query + "?, ";
            }
            System.out.println(query);
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                System.out.println("values: " + values[i]);
            }
            for (int i = 0; i < values.length; i++) {
                preparedStmt.setString(i + 1, values[i]);
            }
            System.out.println(preparedStmt.toString());
            // execute the preparedstatement
            preparedStmt.execute();

            if (commit) {
                Main.report("Saving Changes...");
                // the mysql insert statement
                query = "COMMIT";

                // create the mysql insert preparedstatement
                preparedStmt = conn.prepareStatement(query);

                // execute the preparedstatement
                preparedStmt.execute();
                Main.report("Changes saved.");
            }
            conn.close();
            Main.report("Row successfully inserted");
            return INSERT_SUCCESS;
        } catch (ClassNotFoundException | SQLException e) {
            Main.report(e.getMessage());
            Main.report("Failed to insert row!.");
        }
        return 0;
    }

    /**
     * Calls the commit function of the database to save any changes
     * permanently.
     *
     * @return returns CHANGES_SAVED when the changes are successfully saved to
     * the database.
     */
    public int saveChanges() {
        Main.report("Saving changes...");
        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());

            // the mysql insert statement
            String query = "COMMIT";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
            Main.report("Changes saved.");
            return CHANGES_SAVED;
        } catch (ClassNotFoundException | SQLException e) {
            Main.report(e.getMessage());
            Main.report("Failed to save changes!");
        }
        return 0;
    }

    /**
     * Calls the rollback function of the database to restore to the last commit
     * state.
     *
     * @return returns CHANGES_UNDONE if this method is successful.
     */
    public int undoChanges() {
        Main.report("Rolling back changes...");
        try {
            // create a mysql database connection
            String myUrl = "jdbc:mysql://" + config.getIP() + "/" + config.getDatabaseName();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, config.getUsername(), config.getPassword());

            // the mysql insert statement
            String query = "ROLLBACK";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
            Main.report("Changes successfully rolledback.");
            return CHANGES_UNDONE;
        } catch (ClassNotFoundException | SQLException e) {
            Main.report(e.getMessage());
            Main.report("Failed to rollback!");
        }
        return 0;

    }
}
