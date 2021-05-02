import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;

public class Insert {
    private Stage second;

    public Insert(Stage second) {
        this.second = second;
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

    public void showStage() {
        Stage insertStage = new Stage();
        Pane pane = new Pane();
        Text mainText1 = new Text("Insert");
        mainText1.setTranslateY(100);
        mainText1.setTranslateX(535);
        mainText1.setFont(Font.font("Jokerman", 100));
        pane.getChildren().add(mainText1);
        ImageView icon = new ImageView("https://www.flaticon.com/premium-icon/icons/svg/2081/2081479.svg");
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Button exitButton = new Button("cancel", icon);
        exitButton.setTranslateX(1270);
        exitButton.setTranslateY(10);
        exitButton.setStyle("-fx-background-color: bisque");
        exitButton.setCancelButton(true);

        // registering handlers
        exitButton.setOnAction(e -> {
            insertStage.close();
        });
        pane.getChildren().add(exitButton);
        Text text1 = new Text("Name: ");
        text1.setFont(Font.font("Verdana", 18.0D));
        TextField textField1 = new TextField();
        HBox hBox1 = new HBox(text1, textField1);
        hBox1.setTranslateY(230.0D);
        hBox1.setTranslateX(150.0D);
        hBox1.setSpacing(39.0D);

        Text text2 = new Text("Country: ");
        text2.setFont(Font.font("Verdana", 18.0D));
        TextField textField2 = new TextField();
        HBox hBox2 = new HBox(text2, textField2);
        hBox2.setTranslateY(280.0D);
        hBox2.setTranslateX(150.0D);
        hBox2.setSpacing(20.0D);

        Text text3 = new Text("Company: ");
        text3.setFont(Font.font("Verdana", 18.0D));
        TextField textField3 = new TextField();
        HBox hBox3 = new HBox(text3, textField3);
        hBox3.setTranslateY(330.0D);
        hBox3.setTranslateX(150.0D);
        hBox3.setSpacing(8.0D);

        Text text4 = new Text("Director: ");
        text4.setFont(Font.font("Verdana", 18.0D));
        TextField textField4 = new TextField();
        HBox hBox4 = new HBox(text4, textField4);
        hBox4.setTranslateY(380.0D);
        hBox4.setTranslateX(150.0D);
        hBox4.setSpacing(21.0D);

        Text text5 = new Text("Genre: ");
        text5.setFont(Font.font("Verdana", 18.0D));
        TextField textField5 = new TextField();
        HBox hBox5 = new HBox(text5, textField5);
        hBox5.setTranslateY(430.0D);
        hBox5.setTranslateX(150.0D);
        hBox5.setSpacing(39.0D);

        Text text6 = new Text("Rating: ");
        text6.setFont(Font.font("Verdana", 18.0D));
        TextField textField6 = new TextField();
        HBox hBox6 = new HBox(text6, textField6);
        hBox6.setTranslateY(230.0D);
        hBox6.setTranslateX(550.0D);
        hBox6.setSpacing(34.0D);

        Text text7 = new Text("Released: ");
        text7.setFont(Font.font("Verdana", 18.0D));
        TextField textField7 = new TextField();
        HBox hBox7 = new HBox(text7, textField7);
        hBox7.setTranslateY(280.0D);
        hBox7.setTranslateX(550.0D);
        hBox7.setSpacing(11.0D);

        Text text8 = new Text("Runtime: ");
        text8.setFont(Font.font("Verdana", 18.0D));
        TextField textField8 = new TextField();
        HBox hBox8 = new HBox(text8, textField8);
        hBox8.setTranslateY(330.0D);
        hBox8.setTranslateX(550.0D);
        hBox8.setSpacing(14.0D);

        Text text9 = new Text("Writer: ");
        text9.setFont(Font.font("Verdana", 18.0D));
        TextField textField9 = new TextField();
        HBox hBox9 = new HBox(text9, textField9);
        hBox9.setTranslateY(380.0D);
        hBox9.setTranslateX(550.0D);
        hBox9.setSpacing(34.0D);

        Text text10 = new Text("Gross: ");
        text10.setFont(Font.font("Verdana", 18.0D));
        TextField textField10 = new TextField();
        HBox hBox10 = new HBox(text10, textField10);
        hBox10.setTranslateY(430.0D);
        hBox10.setTranslateX(550.0D);
        hBox10.setSpacing(39.0D);

        Text text11 = new Text("Star: ");
        text11.setFont(Font.font("Verdana", 18.0D));
        TextField textField11 = new TextField();
        HBox hBox11 = new HBox(text11, textField11);
        hBox11.setTranslateY(230.0D);
        hBox11.setTranslateX(950.0D);
        hBox11.setSpacing(49.0D);

        Text text12 = new Text("Votes: ");
        text12.setFont(Font.font("Verdana", 18.0D));
        TextField textField12 = new TextField();
        HBox hBox12 = new HBox(text12, textField12);
        hBox12.setTranslateY(280.0D);
        hBox12.setTranslateX(950.0D);
        hBox12.setSpacing(37.0D);

        Text text13 = new Text("Budget: ");
        text13.setFont(Font.font("Verdana", 18.0D));
        TextField textField13 = new TextField();
        HBox hBox13 = new HBox(text13, textField13);
        hBox13.setTranslateY(330.0D);
        hBox13.setTranslateX(950.0D);
        hBox13.setSpacing(23.0D);

        Text text14 = new Text("Year: ");
        text14.setFont(Font.font("Verdana", 18.0D));
        TextField textField14 = new TextField();
        HBox hBox14 = new HBox(text14, textField14);
        hBox14.setTranslateY(380.0D);
        hBox14.setTranslateX(950.0D);
        hBox14.setSpacing(47.0D);

        Text text15 = new Text("Score: ");
        text15.setFont(Font.font("Verdana", 18.0D));
        TextField textField15 = new TextField();
        HBox hBox15 = new HBox(text15, textField15);
        hBox15.setTranslateY(430.0D);
        hBox15.setTranslateX(950.0D);
        hBox15.setSpacing(37.0D);

        pane.getChildren().addAll(hBox1, hBox2, hBox3,hBox4,hBox5,hBox7,hBox6,hBox8,hBox9,hBox10,hBox11,hBox12,hBox13,hBox14,hBox15);
        Button btn = new Button("Add");
        btn.setTranslateY(600);
        btn.setTranslateX(630);
        btn.setPrefWidth(100);
        btn.setStyle("-fx-background-color: red");
        btn.setTextFill(Color.WHITE);
        pane.getChildren().add(btn);
        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            String query = "BEGIN\n" +
                    "p_insert(" + Integer.valueOf(textField13.getText())+",'"+textField3.getText()+"','"+
                    textField2.getText()+"','"+textField4.getText()
                    +"','"+textField5.getText()+"',"+Integer.valueOf(textField10.getText())+",'"+textField1.getText()+"','"
                    +textField6.getText()
                    +"',To_date('"+textField7.getText()+"', 'dd/mm/yyyy'),"+Integer.valueOf(textField8.getText())+","+Double.valueOf(textField15.getText())+
                    ",'"+textField11.getText()+
                    "',"+Integer.valueOf(textField12.getText())+",'"+textField9.getText()+"',To_date('"+textField14.getText()+
                    "', 'dd/mm/yyyy'));\n" +
                    "END;";
//            String query = "BEGIN\n" +
//            "p_insert(400,'mokjhm','mom','mom','mom',400, 'mom', 'mom',To_date('01/11/2010', 'dd/mm/yyyy'),90,7,'mom',400," +
//                    "'mom',To_date('01/11/2010', 'dd/mm/yyyy'));\n" +
//                    "END;";
            this.executeQuery(query);
            System.out.println(query);
            System.out.println("clicked");
        });
        pane.setStyle("-fx-background-color: peru;");
//        InputStream iconStream = getClass().getResourceAsStream("C:\\Users\\Админ\\Desktop\\DBPROJECt\\src\\img\\disney.png");
//        javafx.scene.image.Image image1 = new Image(iconStream);
//        insertStage.getIcons().add(image1);

        Scene scene = new Scene(pane);
        //scene.setFill(Color.B);

        insertStage.setTitle("Movdustry: INSERT");
        insertStage.setMaximized(true);
        insertStage.setScene(scene);
        insertStage.show();
    }
}