import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Locale;

public class InsertImageToOracleDB {
    public static void main(String args[]) throws Exception{
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        try{
            Locale.setDefault(Locale.ENGLISH);
            Connection con = DriverManager.getConnection(oracleUrl, "hr", "hr");
            PreparedStatement pstmt = con.prepareStatement("update movies set image = ? where name = ?");
            pstmt.setString(2, "Dangal");
            InputStream in = new FileInputStream("C:\\Users\\Админ\\Downloads\\img\\Dangal.jpg");
            pstmt.setBlob(1, in);
            pstmt.execute();
            System.out.println("Record inserted");
        }
        catch(
                SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}