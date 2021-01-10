import java.sql.*;

public class Test {

    static Connection connection;

    public static void main(String[] args) throws SQLException {

        doConnection();

        System.out.println("Selecting data from DB:");
        select();
        System.out.println();

        insert();
        System.out.println("Inserting data to DB:");
        select();
        System.out.println();

        update();
        System.out.println("Updating data from DB:");
        select();
        System.out.println();

        delete();
        System.out.println("Deleting data from DB:");
        select();
    }

    private static void doConnection(){

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/example", "postgres", "2m3410lt");

            System.out.println("Java JDBC PostgreSQL Example");

            System.out.println("Connected to PostgreSQL database!");
            System.out.println();

        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void select() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person_one");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id") + " " + resultSet.getString("name") + " " + resultSet.getString("surname") + " " + resultSet.getString("age") + " " + resultSet.getString("height"));
        }
    }

    private static void insert() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO person_one(id, name, surname, age, height) VALUES(3, 'Israel', 'Adesanya', 31, 193)");
    }

    private static void update() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE person_one SET age = 87 WHERE id = 2");
    }

    private static void delete() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM person_one WHERE id = 3");
    }
}
