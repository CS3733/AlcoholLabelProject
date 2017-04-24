package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.LogManager;
import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.*;

/**
 * Dan, Kyle, Elijah on 3/31/17
 * This class does stuff for databases
 */
public class Database {

    private String dbName;
    private Connection connection;
    private boolean connected = false;
    private Statement statement;

    private static final String TAG = "DATABASE";

    /**
     * A class representing a database with the given name.
     *
     * @param dbName The name of the database.
     */
    public Database(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Connect to the database.
     *
     * @return True if the database was successfully connected to.
     */
    public boolean connect() {
        try {
            DriverManager.registerDriver(new EmbeddedDriver());
            connection = DriverManager.getConnection("jdbc:derby:" + dbName + ";create=true;characterEncoding=utf8");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            connected = true;
            LogManager.getInstance().logAction(TAG, "Connected to database: " + dbName);
        } catch (SQLException e) {
            LogManager.getInstance().logAction(TAG, "Could not connect to databaase: " + dbName + "\n" +
                    e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Drop the table specified.
     *
     * @param tableName The table name
     * @return True if it was able to drop the table without error.
     */
    public boolean dropTable(String tableName) throws SQLException {
        if (!connected)
            return false;

        statement.execute("DROP TABLE " + tableName);

        connection.commit();

        return true;
    }


    /**
     * Create a table with the columns given.
     *
     * @param tableName The name of the table.
     * @param entities  The entities of the table (columns)
     * @return True if the table was created without error.
     */
    public boolean createTable(String tableName, TableField... entities) throws SQLException {
        if (!connected)
            return false;

        String entityString = "";

        for (TableField entity : entities) {
            entityString += entity.getName() + " " + entity.getType() + ",\n";
        }

        entityString = entityString.substring(0, entityString.length() - 2);
        statement.execute("CREATE TABLE " + tableName + "(\n" + entityString + "\n)");
        connection.commit();
        return true;
    }


    /**
     * Insert values into the table.
     *
     * @param values    The values in order of their respective columns where strings have '' around them.
     * @param tableName The name of the table.
     * @return True if the values were inserted without error.
     */
    public boolean insert(String values, String tableName) {
//        if (!connected)
//            return false;
        try {
            statement.execute("INSERT INTO " + tableName + " VALUES ( " + values + " )");
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean runStatement(String sql) {
        if (!connected)
            return false;
        try {
            statement.execute(sql);
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Select values from the table.
     *
     * @param values The SQL statement for which values to select. Ex: "*"
     * @param table  The SQL table to run the select on.
     * @return The {@link ResultSet} from the select query.
     */
    public ResultSet select(String values, String table) {
        if (!connected) {
            System.err.println("Database was not connected to.");
            return null;
        }
        try {
            return statement.executeQuery("SELECT " + values + " FROM " + table);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select values from the table.
     *
     * @param values  The SQL statement for which values to select. Ex: "*"
     * @param table   The SQL table to run the select on.
     * @param orderBy The column to order by.
     * @return The {@link ResultSet} from the select query.
     */
    public ResultSet selectOrdered(String values, String table, String orderBy) {
        if (!connected) {
            System.err.println("Database was not connected to.");
            return null;
        }
        try {
            return statement.executeQuery("SELECT " + values + " FROM " + table + " ORDER BY " + orderBy);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select values from the table.
     *
     * @param values The SQL statement for which values to select. Ex: "*"
     * @param table  The SQL table to run the select on.
     * @param where  The where clause to the SQL select query.
     * @return The {@link ResultSet} from the select query.
     */
    public ResultSet select(String values, String table, String where) {
        if (!connected) {
            System.err.println("Database was not connected to.");
            return null;
        }
        try {
            return statement.executeQuery("SELECT " + values + " FROM " + table + " WHERE " + where);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select values from the table.
     *
     * @param values  The SQL statement for which values to select. Ex: "*"
     * @param table   The SQL table to run the select on.
     * @param where   The where clause to the SQL select query.
     * @param orderBy The column to order by.
     * @return The {@link ResultSet} from the select query.
     */
    public ResultSet selectOrdered(String values, String table, String where, String orderBy) {
        if (!connected) {
            System.err.println("Database was not connected to.");
            return null;
        }
        try {
            return statement.executeQuery("SELECT " + values + " FROM " + table + " WHERE " + where + " ORDER BY " + orderBy);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(String tableName, String values, String where) {
        if (!connected)
            return false;
        try {
            statement.execute("UPDATE " + tableName + " SET " + values + " WHERE " + where);
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String tableName, String where) {
        if (!connected)
            return false;
        try {
            statement.execute("DELETE FROM " + tableName + " WHERE " + where);
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Close the database.
     *
     * @return True if the database was successfully closed.
     */
    public boolean close() {
        try {
            statement.close();
            connection.close();
            connected = false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static class TableField {
        private String name;
        private String type;

        public TableField(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }

}