import java.sql.*;

public class CheckUsers {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1522/orclpdb",
                "amina",
                "1234"
            );
            
            System.out.println("Listing all users:");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": [" + columnValue + "]");
                }
                System.out.println("");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
