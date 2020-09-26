package buttons;

import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class MaxDownButton extends Button{
    public final String BUTTON_STYLE = "-fx-background-color: transparent;"
            + " -fx-background-image: url('/img/botão.png');"
            + " -fx-background-size: 100% 100%";
    
    public MaxDownButton (String text){
        setText(text);
        setStyle(BUTTON_STYLE);
        setMinSize(256, 128);
        setLayoutX(400 - this.getMinWidth()/2);
        setLayoutY(300 + 256 - this.getMinHeight()/2);
        setFont(Font.font("Arial Black", 25));
        this.setTextFill(Paint.valueOf("green"));
        
    }
}

