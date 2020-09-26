package buttons;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Play extends Button{
    
    public Play(String text){
        setText(text);
        setLayoutX(250-96);
        setLayoutY(200-48);
        setMinSize(144, 48);
        setFont(Font.font("Arial Black", 25));
    }
    
    public void setButtonFont(){
        setFont(Font.font("Arial Black", 80));
    }
    
    public void setButtonStylePressed(){
        setLayoutX(400-56);
        setLayoutY(200-48);
        setMinSize(80, 48);
    }
    
    

}

