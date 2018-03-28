/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jansorqr;

import java.sql.*;

/**
 * This class contains some details about a table on a database server.
 *
 * @author jansoriano
 */
public class DatabaseTable extends DatabaseConfig {

    public DatabaseTable() {
        tableName = "document";
        columnCount = 12;
        columnNames = new String[columnCount];
        columnNames[0] = "pr_number";
        columnNames[1] = "title";
        columnNames[2] = "src_office";
        columnNames[3] = "author";
        columnNames[4] = "dest_office";
        columnNames[5] = "target_person";
        columnNames[6] = "date";
        columnNames[7] = "time";
        columnNames[8] = "add_text";
        columnNames[9] = "remarks";
        columnNames[10] = "act_receiver";
        columnNames[11] = "receiver_mac";
    }

    //Variable declarations
    private String tableName;
    private int columnCount;
    private String[] columnNames;

    /**
     * Returns the name of the table.
     *
     * @return name of the table.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Returns the number of columns contained in the table.
     *
     * @return the number of columns.
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * Returns an array of String containing the names of the columns found in
     * the table. The leftmost column would be found in the String[0] of the
     * returned value. Next column names would be in String[1]... and so on.
     *
     * @return
     */
    public String[] getColumnNames() {
        return this.columnNames;
    }

    /**
     * Sets the new name for the table.
     *
     * @param tableName - the new table name.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Sets the new number of columns contained in the table.
     *
     * @param columnCount - the number of columns in the table.
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * Sets the column names of of the table.
     *
     * @param columnNames - an array containing the names of each column.
     * columnNames[0] should be the leftmost column -- increasing to the right.
     */
    public void setColumnNames(String columnNames[]) {
        this.columnNames = columnNames;
    }

    /**
     * Sets the column name of the table specified by the index.
     *
     * @param columnName - the name of the column in the table.
     * @param index - the index specifying the location of the column in the
     * array.
     */
    public void setColumnName(String columnName, int index) {
        columnNames[index] = columnName;
    }

    /**
     * Reconfigure the databaseTable using values from the database define by
     * the database config.
     *
     * @param config - the configuration to be used to access the database.
     * @return returns true if the method is successfully done. Returns false
     * otherwise.
     */
    public boolean setNewTable(DatabaseConfig config) {
        Main.report("Setting new databaseTable...");
        super.ipAddress = config.ipAddress;
        super.database = config.database;
        super.username = config.username;
        super.password = config.password;
        DatabaseManager db = new DatabaseManager(config);
        db.setTableToUse(this);
        ResultSet rs = db.fetchAll();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            columnCount = metaData.getColumnCount();
            columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
            }
            rs.close();
            Main.report("New table successfully set.");
            return true;
        } catch (SQLException ex) {
            Main.report(ex.getMessage());
            Main.report("Failed to set new databaseTable!");
        }
        return false;
    }

    public void setConfig(DatabaseConfig config) {
        super.ipAddress = config.ipAddress;
        super.database = config.database;
        super.username = config.username;
        super.password = config.password;
    }
}
