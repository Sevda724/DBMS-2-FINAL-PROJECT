import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private static final String INFORMATION_SEPARATOR_THREE = "\u001D";

    private final StringProperty star = new SimpleStringProperty();
    private final StringProperty genre = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty score = new SimpleIntegerProperty();

    public Movie( String star, String genre, String country, String name, int score)
    {
        this.star.set(star);
        this.genre.set(genre);
        this.country.set(country);
        this.name.set(name);
        this.score.set(score);
    }



    public void setStar(String star)
    {
        this.star.set(star);
    }

    public String getStar()
    {
        return this.star.get();
    }
    public void setGenre(String genre)
    {
        this.genre.set(genre);
    }

    public String getGenre()
    {
        return this.genre.get();
    }

    public void setCountry(String country)
    {
        this.country.set(country);
    }

    public String getCountry()
    {
        return this.country.get();
    }


    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getName()
    {
        return this.name.get();
    }

    public void setScore(int score)
    {
        this.score.set(score);
    }

    public int getScore()
    {
        return this.score.get();
    }


    public static List<String> NamesToList(String star)
    {
        String[] nameArray = star.split(INFORMATION_SEPARATOR_THREE);

        List<String> namesList = new ArrayList();
        for(String entry : nameArray)
        {
            namesList.add(entry);
        }

        return namesList;
    }

    public static String namesListToString(List<String> star)
    {
        return String.join(INFORMATION_SEPARATOR_THREE, star);
    }
}
