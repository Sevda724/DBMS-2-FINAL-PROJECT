import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class movdustry extends Application {
    public static int[] arrint;
    public static String[] arrstr;
    public static List<String> list;
    private final TableView<Movie> table = new TableView<>();

    public movdustry(){}
    public static void main(String[] args) {
        launch(args);
    }
    public Connection getConnection() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con= DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
            return con;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    private void executeQuery(String query) {
        Connection conn = this.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    public void count(){
        try{
        Connection con= this.getConnection();
        Statement stmt=con.createStatement();
        arrint = new int[20];
        arrstr = new String[20];
        int i = 0;
        ResultSet rs=stmt.executeQuery("Select Count(*), genre FROM movies Group by genre");
        while(rs.next()) {
            arrint[i] = rs.getInt(1);
            arrstr[i] = rs.getString(2);
            //System.out.println(arrint[i]);
            i++;
        }{
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void imgname(){
        Connection con= this.getConnection();
        try {
            Statement stmt=con.createStatement();
            PreparedStatement ps=con.prepareStatement("select name, image from movies where image is not null");
            list = new ArrayList<>();
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1).replace(":",""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.shuffle(list);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setPrefWidth(1500);
        pane.setPrefHeight(1800);
        Text mainText1 = new Text("Movdustry");
        mainText1.setTranslateY(100);
        mainText1.setTranslateX(420);
        mainText1.setFont(Font.font("Jokerman", 100));
        pane.getChildren().add(mainText1);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ImageView icon = new ImageView("https://www.flaticon.com/premium-icon/icons/svg/2997/2997933.svg");
        icon.setFitHeight(18.0D);
        icon.setFitWidth(18.0D);
        Button insertButton = new Button("Add", icon);
        insertButton.setPrefHeight(18.0D);
        insertButton.setPrefWidth(100.0D);
        pane.getChildren().add(insertButton);
        insertButton.setOnAction(e -> {
            Insert insertDialog = new Insert(primaryStage);
            insertDialog.showStage();
        });

        ImageView icon2 = new ImageView("https://www.flaticon.com/premium-icon/icons/svg/3024/3024020.svg");
        icon2.setFitHeight(18.0D);
        icon2.setFitWidth(18.0D);
        Button updateButton = new Button("Update", icon2);
        updateButton.setPrefHeight(18.0D);
        updateButton.setPrefWidth(100.0D);
        pane.getChildren().add(updateButton);
        updateButton.setOnAction(e -> {
            Update updateDialog = new Update(primaryStage);
            updateDialog.showStage();
        });

        ImageView icon3 = new ImageView("https://www.clipartmax.com/png/small/120-1209780_delete-circle-icon-png-target-logo.png");
        icon3.setFitHeight(18.0D);
        icon3.setFitWidth(18.0D);
        Button deleteButton = new Button("Delete", icon3);
        deleteButton.setPrefHeight(18.0D);
        deleteButton.setPrefWidth(100.0D);
        pane.getChildren().add(deleteButton);
        deleteButton.setOnAction(e -> {
            Delete deleteStage = new Delete(primaryStage);
            deleteStage.showStage();
        });

        HBox hBoxButton = new HBox(insertButton,updateButton, deleteButton);
        hBoxButton.setTranslateX(1000);
        hBoxButton.setTranslateY(5);
        hBoxButton.setSpacing(20);
        pane.getChildren().add(hBoxButton);

        TableColumn star = new TableColumn("Star");
        star.setPrefWidth(125);
        star.setCellValueFactory(new PropertyValueFactory<>("star"));

        TableColumn genre = new TableColumn("Genre");
        genre.setPrefWidth(100);
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn country = new TableColumn("Country");
        country.setPrefWidth(100);
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn name = new TableColumn("Name");
        name.setPrefWidth(290);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn score = new TableColumn("Score");
        score.setPrefWidth(40);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        DBHandler dbhandler = new DBHandler();
        FilteredList<Movie> filteredList = new FilteredList(dbhandler.getDBMovies());
        table.setItems(filteredList);
        table.getColumns().addAll(star, genre, country, name, score);

        Text label = new Text("Star:     ");
        label.setFont(Font.font("Verdana", 14));
        FlowPane flowPane = new FlowPane();
        TextField searchField = new TextField();
        searchField.setOnAction(event -> {
            List<String> searchingFor = new ArrayList();

            Tag tempTag = new Tag(searchField.getText());
            tempTag.getCloseButton().setOnAction((actionEvent) -> {
                flowPane.getChildren().remove(tempTag);
                if(flowPane.getChildren().isEmpty())
                {
                    filteredList.setPredicate(null);
                }
                else{
                    filteredList.setPredicate((t) -> {
                        return !Collections.disjoint(Movie.NamesToList(t.getStar()), searchingFor);
                    });
                }
            });
            flowPane.getChildren().add(tempTag);

            for(Node node : flowPane.getChildren())
            {
                searchingFor.add(((Tag)node).getText());
            }
            filteredList.setPredicate((t) -> {
                return !Collections.disjoint(Movie.NamesToList(t.getStar()), searchingFor);
            });

            searchField.clear();
        });

        Text label2 = new Text("Genre:  ");
        label2.setFont(Font.font("Verdana", 14));
        FlowPane floPane = new FlowPane();
        TextField searcField = new TextField();
        searcField.setOnAction(event -> {
            List<String> searchingFor = new ArrayList();

            Tag tempTag = new Tag(searcField.getText());
            tempTag.getCloseButton().setOnAction((actionEvent) -> {
                floPane.getChildren().remove(tempTag);
                if(floPane.getChildren().isEmpty())
                {
                    filteredList.setPredicate(null);
                }
                else{
                    filteredList.setPredicate((t) -> {
                        return !Collections.disjoint(Movie.NamesToList(t.getGenre()), searchingFor);
                    });
                }
            });
            floPane.getChildren().add(tempTag);

            for(Node node : floPane.getChildren())
            {
                searchingFor.add(((Tag)node).getText());
            }
            filteredList.setPredicate((t) -> {
                return !Collections.disjoint(Movie.NamesToList(t.getGenre()), searchingFor);
            });

            searcField.clear();
        });
        SortedList<Movie> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

        HBox hbox = new HBox(label, searchField, flowPane);
        HBox hb = new HBox(label2, searcField, floPane);
        final VBox vbox = new VBox(table, hbox, hb);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.setTranslateY(200);
        vbox.setTranslateX(340);
        pane.getChildren().add(vbox);

        count();
        PieChart.Data data[] = new PieChart.Data[16];
        for (int i = 0; i < 16; i++) {
            data[i] = new PieChart.Data(arrstr[i], arrint[i]);
        }
        PieChart pie_chart = new
                PieChart(FXCollections.observableArrayList(data));

        Group group0 = new Group(pie_chart);
        group0.setTranslateY(800);
        group0.setTranslateX(150);
        pane.getChildren().add(group0);

        Label textlab2 = new Label("On this chart you can see all possible genres: comedy, animation, musical,thriller," +
                "western, action, Sci-Fi, drama, fantasy, biography, horror, war,family, adventure, mystery, romance. " +
                "The most popular: comedy, drama and action.");
        textlab2.setWrapText(true);
        textlab2.setMaxWidth(550);
        textlab2.setTranslateY(950);
        textlab2.setTranslateX(750);
        textlab2.setFont(Font.font("Verdana", 18.0D));
        pane.getChildren().addAll(textlab2);
        //pane.setStyle("-fx-background-color: lightblue;");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Number of released movies");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Number of released movies by current month");
        //populating the series with data
        OrcHandler dbHandler = new OrcHandler();

        List<List<Integer>> dataHolder = dbHandler.getDataHolder();
        for(int i = 0; i < dataHolder.size(); i++)
        {
            series.getData().add(new XYChart.Data(dataHolder.get(i).get(0), dataHolder.get(i).get(1)));
        }
        lineChart.setTranslateY(1300);
        lineChart.setTranslateX(750);
        pane.getChildren().add(lineChart);
        lineChart.getData().add(series);
        Label textlab1 = new Label("One Canadian scientist spent a long time conducting research on various" +
                " topics and in one day he found a pattern in all things.  In one of his studies, he wrote that " +
                "the month of a person's birth directly affects who he can become in the future in Canada.  " +
                "As a result, as it turned out, those who were born at the beginning of the year mainly became hockey" +
                " players for the reason that the selection to the teams was carried out at the beginning of the year and " +
                "therefore those who were born later in other months were automatically eliminated to be selected in some " +
                "season due to  their age.  After reading this story, we also wondered what month many films were released.  " +
                "Is it possible that some factors can influence their output?  As we can see most of the films were released " +
                "in October.  In our opinion, this could be influenced by such factors as the shooting of the film is easier " +
                "in the summer and so on.  Therefore, we have collected the data in this linear table and we hope " +
                "that you are also interested in the regularity of the release of films by month!");
        textlab1.setWrapText(true);
        textlab1.setMaxWidth(550);
        textlab1.setTranslateY(1300);
        textlab1.setTranslateX(130);
        textlab1.setFont(Font.font("Verdana", 18.0D));
        pane.getChildren().addAll(textlab1);
        imgname();
        System.out.println(list.get(0));
        Image image = new Image(new FileInputStream("C:\\Users\\Админ\\Desktop\\DBPROJECt\\src\\img\\" + list.get(0) + ".jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(1150);
        imageView.setY(150);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        Group group = new Group(imageView);
        Label lab1 = new Label();
        lab1.setMaxWidth(110);
        lab1.setText(list.get(0));
        lab1.setWrapText(true);
        lab1.setTranslateY(350);
        lab1.setTranslateX(1150);
        lab1.setFont(Font.font("Verdana", 14.0D));
        pane.getChildren().addAll(group, lab1);

        Image image2 = new Image(new FileInputStream("C:\\Users\\Админ\\Desktop\\DBPROJECt\\src\\img\\" + list.get(1) + ".jpg"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(1150);
        imageView2.setY(430);
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(200);
        imageView2.setPreserveRatio(true);
        Group group2 = new Group(imageView2);
        Label lab2 = new Label();
        lab2.setMaxWidth(110);
        lab2.setText(list.get(1));
        lab2.setWrapText(true);
        lab2.setTranslateY(630);
        lab2.setTranslateX(1150);
        lab2.setFont(Font.font("Verdana", 14.0D));
        pane.getChildren().addAll(group2,lab2);

        Image image3 = new Image(new FileInputStream("C:\\Users\\Админ\\Desktop\\DBPROJECt\\src\\img\\" + list.get(3) + ".jpg"));
        ImageView imageView3 = new ImageView(image3);
        imageView3.setX(60);
        imageView3.setY(150);
        imageView3.setFitHeight(200);
        imageView3.setFitWidth(200);
        imageView3.setPreserveRatio(true);
        Group group3 = new Group(imageView3);
        Label lab3 = new Label();
        lab3.setMaxWidth(110);
        lab3.setText(list.get(3));
        lab3.setWrapText(true);
        lab3.setTranslateY(350);
        lab3.setTranslateX(60);
        lab3.setFont(Font.font("Verdana", 14.0D));
        pane.getChildren().addAll(group3, lab3);

        Image image4 = new Image(new FileInputStream("C:\\Users\\Админ\\Desktop\\DBPROJECt\\src\\img\\" + list.get(4) + ".jpg"));
        ImageView imageView4 = new ImageView(image4);
        imageView4.setX(60);
        imageView4.setY(430);
        imageView4.setFitHeight(200);
        imageView4.setFitWidth(200);
        imageView4.setPreserveRatio(true);
        Group group4 = new Group(imageView4);
        Label lab4 = new Label();
        lab4.setMaxWidth(110);
        lab4.setText(list.get(4));
        lab4.setWrapText(true);
        lab4.setTranslateY(630);
        lab4.setTranslateX(60);
        lab4.setFont(Font.font("Verdana", 14.0D));
        pane.getChildren().addAll(group4,lab4);

        /*
        Image image1 = new Image("C:\\Users\\Админ\\Downloads\\img\\" + list.get(0) + ".jpg");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setTranslateY(1000);
        imageView1.setTranslateX(100);
        pane.getChildren().add(imageView1);
        imageView1.setFitHeight(300.0D);
        imageView1.setFitWidth(300.0D);
*/
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pane.setStyle("-fx-background-color: peru;");
        Scene scene2 = new Scene(scrollPane);
        scene2.setFill(Color.BLACK);
        primaryStage.setTitle("Movdustry");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
}
