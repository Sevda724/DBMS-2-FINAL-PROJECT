import javafx.scene.Scene;
import javafx.scene.control.Button;
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

import java.awt.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;

public class Delete {
    private Stage owner;

    public Delete(Stage owner) {
        this.owner = owner;
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
        Stage deleteStage = new Stage();
        Pane pane = new Pane();
        Text mainText1 = new Text("Delete");
        mainText1.setTranslateY(100);
        mainText1.setTranslateX(520);
        mainText1.setFont(Font.font("Jokerman", 100));
        pane.getChildren().add(mainText1);
        ImageView icon = new ImageView("https://www.flaticon.com/premium-icon/icons/svg/2081/2081479.svg");
        icon.setFitHeight(30);
        icon.setFitWidth(30);

        Button exitButton = new Button("cancel", icon);
        exitButton.setTranslateX(1270);
        exitButton.setTranslateY(10);
        exitButton.setCancelButton(true);
        exitButton.setStyle("-fx-background-color: bisque");

        // registering handlers
        exitButton.setOnAction(e -> {
            deleteStage.close();
        });
        pane.getChildren().add(exitButton);
        Text text1 = new Text("Name: ");
        text1.setFont(Font.font("Verdana", 18.0D));
        TextField textField1 = new TextField();
        HBox hBox1 = new HBox(text1, textField1);
        hBox1.setTranslateY(230.0D);
        hBox1.setTranslateX(150.0D);
        hBox1.setSpacing(39.0D);


        Button btn = new Button("Add");
        btn.setTranslateY(300);
        btn.setTranslateX(300);
        btn.setPrefWidth(100);
        btn.setStyle("-fx-background-color: red");
        btn.setTextFill(Color.WHITE);
        pane.getChildren().add(btn);

        btn.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            String query = "BEGIN\n" +
                    "p_delete('"+textField1.getText()+"');\n" +
                    "END;";
            this.executeQuery(query);
            textField1.clear();
            System.out.println(query);
            System.out.println("clicked");
        });

        pane.getChildren().addAll(hBox1);
        pane.setStyle("-fx-background-color: peru;");
        Scene scene = new Scene(pane);
        deleteStage.setTitle("Movdustry: DELETE");
        deleteStage.setMaximized(true);
        deleteStage.setScene(scene);
        deleteStage.show();
    }
}
