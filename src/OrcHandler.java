import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

public class OrcHandler
{
    List<List<Integer>> dataHolder;
    String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";

    //There is probably a better way to structure this DBHandler Class
    public OrcHandler()
    {
        dataHolder = new ArrayList();

        try
        {
            Connection connection = DriverManager.getConnection(dbURL, "hr", "hr");
            Statement stmt = connection.createStatement();//Create Statement
            ResultSet rs = stmt.executeQuery("select  extract(month from released) \"Month\", count(*) from movies\n" +
                    "group by extract(month from released)\n" +
                    "order by extract(month from released)");//Query DB and get results.

            //Iterate through results.
            while(rs.next())
            {
                List<Integer> tempDataHolder = new ArrayList();
                tempDataHolder.add(rs.getInt(1));
                tempDataHolder.add(Integer.parseInt(rs.getString(2)));
                dataHolder.add(tempDataHolder);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<List<Integer>> getDataHolder()
    {
        return dataHolder;
    }

}