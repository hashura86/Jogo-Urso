package urso;

import buttons.MaxDownButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GameOver {

    private Group gameOverPane;
    private Scene gameOverScene;
    private Label texto;
    private TextField campoNome;
    private long score;
    private static MediaPlayer mediaplayer;
    private boolean bateuRecord;
    private int index;
    
    public GameOver(long score) {
        initializeScene();
        this.score = score;
    }

    private void initializeScene() {
        Media musicFile = new Media(this.getClass().getResource("/music/gameOverMusic.mp3").toExternalForm());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setVolume(1);
        mediaplayer.play();
        this.gameOverPane = new Group();
        gameOverPane.setTranslateX(0);
        gameOverPane.setTranslateY(0);
        gameOverScene = new Scene(gameOverPane, 800, 600);
        

        Image background = new Image(getClass().getResourceAsStream("/img/gameOverkk.png"));
        ImageView bg = new ImageView(background);
        bg.toBack();
        bg.setX(0);
        bg.setY(0);

        texto = new Label("pontos: " + Urso.jogo.getPoints() + "00");
        texto.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
        texto.setTranslateX(320 - 200);
        texto.setTranslateY(300 - 200);
        texto.setTextFill(Paint.valueOf("green"));
        
        
        for(index = 0; index < 10; index++){
            if(Urso.records.getRecord(index).equals("") || Urso.jogo.getPoints() >= Urso.records.getRecordInt(index)){
                this.bateuRecord = true;
                break;
            }
        }
        
         
        if (bateuRecord == true) {

            this.campoNome = new TextField("NAME");
            this.campoNome.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
            this.campoNome.setTranslateX(400 - 200);
            this.campoNome.setTranslateY(300 + 100);
            this.campoNome.setTooltip(new Tooltip("Digite apenas letras Maiúsculas, e no máximo 4 letras (exemplo: URSO)."));

        }



        MaxDownButton back = new MaxDownButton("BACK");
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mediaplayer.play();
                if (bateuRecord == true) {
                    if (!validarNome(campoNome.getText())) {
                        campoNome.setText("");
                    } else {
                        for (int i = 9; i > index; i--) {
                            Urso.records.setRecord(i, Urso.records.getRecord(i - 1));
                        }
                        Urso.records.setRecord(index, campoNome.getText() + "-" + Urso.jogo.getPoints());
                        try {
                            Urso.records.Save();
                        } catch (IOException ex) {
                            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
                        }                     
                        Urso.trocaTela("Menu");
                        mediaplayer.stop();
                    }

                } else {
                    Urso.trocaTela("Menu");
                    mediaplayer.stop();
                }
            }
        });

        this.gameOverPane.getChildren().add(bg);
        this.gameOverPane.getChildren().add(texto);
        this.gameOverPane.getChildren().add(back);
        this.texto.toFront();
        back.toFront();

        if (bateuRecord == true) {
            this.gameOverPane.getChildren().add(this.campoNome);
            this.campoNome.toFront();
        }


    }


    public Scene getScene() {
        return this.gameOverScene;
    }

    public void setPoints(long pontos) {
        this.score = pontos;
    }

    protected boolean validarNome(String nome) {
        return nome.matches("[A-Z]{1}|[A-Z]{2}|[A-Z]{3}|[A-Z]{4}");
    }

    public static void stop() {
        mediaplayer.stop();
    }

    
}



