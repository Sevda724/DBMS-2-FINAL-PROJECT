import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


final public class Tag extends HBox{
    final private Label n;
    final private Button closeButton;

    public Tag(String n) {
        this.n = new Label(n);
        closeButton = new Button("x");
        this.getChildren().addAll(this.n, closeButton);
    }

    public String getText()
    {
        return n.getText();
    }

    Button getCloseButton()
    {
        return closeButton;
    }
}