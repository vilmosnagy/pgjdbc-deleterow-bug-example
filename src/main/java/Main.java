import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "mysecretpassword";

        try (
                Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT id as _id, text as _text from foo");
        ) {
            while (rs.next()) {
                System.out.println(String.format("%s\t\t%s", rs.getString(1), rs.getString(2)));
                rs.deleteRow();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

