package org.ess.app.common;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@Getter
public abstract class Database<T> {


    protected File file;

    /**
     * The default constructor
     */
    public Database() {

        file = new File("storage/sqlite.database");

        initialize();
    }

    /**
     * The constructor
     *
     * @param name {@link String}
     */
    public Database(String name) {

        file = new File(name);

        initialize();
    }

    /**
     * Initialize the database file
     */
    private void initialize() {

        try {

            if (!file.exists() && file.createNewFile())
                System.out.println("Model Created At: " + file.getAbsolutePath());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Retrieve the database connection
     *
     * @return {@link Connection}
     * @throws SQLException exception
     */
    public Connection getConnection() throws SQLException {

        // db parameters
        String url = String.format("%s%s", "jdbc:sqlite:", file.getAbsolutePath());

        // create a connection to the database
        return DriverManager.getConnection(url);
    }

    /**
     * Execute a query on the database
     *
     * @param statement {@link String}
     * @return {@link Statement}
     * @throws SQLException exception
     */
    protected ResultSet query(String statement) throws SQLException {

        try (Statement s = getConnection().createStatement()) {

            return s.executeQuery(statement);
        }
    }

    /**
     * Prepared the sql statement
     *
     * @param statement {@link String}
     * @return {@link PreparedStatement}
     * @throws SQLException exception
     */
    protected PreparedStatement preparedStatement(String statement) throws SQLException {

        return getConnection().prepareStatement(statement);
    }

    /**
     * Connect to a sample database
     *
     * @param statement {@link String}
     */
    protected boolean execute(String statement) throws SQLException {

        Connection conn = getConnection();

        return conn.createStatement().execute(statement);
    }

    /**
     * Retrieve all entities from the database
     *
     * @return {@link List}
     */
    public abstract List<T> query();

    /**
     * Store a given entity in the database
     *
     * @return {@link List}
     */
    public abstract int insert(T entity);

    /**
     * Update a given entity in the database
     *
     * @return {@link List}
     */
    public abstract int update(T entity);

    /**
     * Remove the entity with the given id from the database
     *
     * @param entity {@link T}
     * @return {@link Boolean}
     */
    public abstract boolean delete(T entity);
}
