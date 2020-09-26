package urso;

import javafx.application.Application;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.media.MediaPlayer;

public class Urso extends Application {

    MediaPlayer mediaplayer;
    private static Stage screen;
    public static Jogo jogo;
    public static Menu menu;
    public static GameOver go;
    public static ListaRecord record;
    public static final ListaRecord records = new ListaRecord();

    public static void main(String[] args) {
        launch(args);
    }

    public static void trocaTela(String newScene) {
        switch (newScene) {
            case "Menu":
                menu = new Menu();
                screen.setScene(menu.getScene());
                
                
                break;
            case "Jogo":
                jogo = new Jogo();
                jogo.start();
                screen.setScene(jogo.getScene());
                screen.setTitle("URSO");
                break;
            case "GameOver":
                jogo.stopMusic();
                jogo.stop();   
                go = new GameOver(jogo.getPoints());
                screen.setScene(go.getScene());
               
                break;
        }

    }

    @Override
    public void start(Stage theStage) {       
        Urso.screen = theStage;
        theStage.setMinWidth(800 + 16);
        theStage.setMaxWidth(800 + 16);
        theStage.setMinHeight(600 + 38);
        theStage.setMaxHeight(600 + 38);
        theStage.setTitle("URSO");
        Urso.trocaTela("Menu");

        
        theStage.show();

    }

}
