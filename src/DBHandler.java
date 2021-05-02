import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DBHandler {
    String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";

    public DBHandler()
    {
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            Connection con = DriverManager.getConnection(oracleUrl, "hr", "hr");
            System.out.println("connected to db!");
        } catch (SQLException ex)
        {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }


    public ObservableList<Movie> getDBMovies()
    {
        String query = "SELECT star, genre, country, name, score FROM movies";
        ObservableList<Movie> movies = FXCollections.observableArrayList();

        try {
            Locale.setDefault(Locale.ENGLISH);
            Connection connection = DriverManager.getConnection(oracleUrl, "hr", "hr");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next())
            {
                Movie movie = new Movie(result.getString("star"), result.getString("genre"), result.getString("country"), result.getString("name"), result.getInt("score"));
                movies.add(movie);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }

        return movies;
    }

}
