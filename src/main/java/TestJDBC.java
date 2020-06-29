import java.sql.Connection;
import java.sql.DriverManager;

/*
Requirements - MySQL Workbench
             - MySQL 80 Windows service running (Task Manager or MySQL Notifier)
 */

public class TestJDBC {
    public static void main(String args[]) {
        String username = "hbstudent";
        String password = "hbstudent";
        String url = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false&serverTimezone=UTC";

        try {
            System.out.println("Connecting to database URL " + url);

            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connection successful");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
