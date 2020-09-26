package urso;

import buttons.Play;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;


public class Menu {

    private static AnchorPane menuPane;
    private final int HEIGHT = 600;
    private final int WIDTH = 800;
    public Scene menuScene;
    boolean mutedm = false;
    boolean mbutton = false;
    MediaPlayer mediaplayer;
    private Label textoRecord;
    private Label records;

    public Menu() {
        initializeScene();
    }

    public void initializeScene() {
        menuPane = new AnchorPane();
        menuScene = new Scene(menuPane, WIDTH, HEIGHT);
        Image background = new Image(getClass().getResourceAsStream("/img/URSO.png"));
        ImageView bg = new ImageView(background);
        Media musicFile = new Media(this.getClass().getResource("/music/menuMusic.mp3").toExternalForm());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setVolume(1);
        mediaplayer.play();
        bg.toBack();
        bg.setX(0);
        bg.setY(0);
        
        textoRecord = new Label("-RECORDS-");
        textoRecord.setFont(Font.font("Arial Black", 30));
        textoRecord.setLayoutX(50);
        textoRecord.setLayoutY(220);
        textoRecord.setTextFill(Paint.valueOf("red"));
        
        records = new Label(Urso.records.toString());
        records.setFont(Font.font("Arial Black", 20));
        records.setLayoutX(50);
        records.setLayoutY(260);
        records.setTextFill(Paint.valueOf("red"));
        
        Play playButton = new Play("Jogar");
        playButton.setOnMousePressed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                mediaplayer.stop();
                Urso.trocaTela("Jogo");
                
            }
        });
        menuPane.getChildren().add(bg);
        menuPane.getChildren().add(playButton);
        menuPane.getChildren().add(textoRecord);
        playButton.toFront();
        menuPane.getChildren().add(records);
        records.toFront();
        textoRecord.toFront();
    }

    public Scene getScene() {
        return menuScene;
    }

}



