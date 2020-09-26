package urso;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import objetos.Fruta;
import objetos.FrutaPodre;

public class Jogo extends AnimationTimer {

    private Media musicFile;
    private MediaPlayer mediaplayer;
    private Group root;
    private int posX, posY;
    private Scene theScene;
    private GraphicsContext gc;
    private Canvas canvas;
    private long score;
    private int frames;
    private int index;
    private int framesFruta;
    private Image framesUrso[];
    private ArrayList<Fruta> frutas;
    private Image background;
    private ArrayList<FrutaPodre> frutasPodres;

    public Jogo() {

        this.musicFile = new Media(this.getClass().getResource("/music/URSO.mp3").toExternalForm());
        this.mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setVolume(1);
        mediaplayer.play();
        this.root = new Group();
        this.posX = 100;
        this.posY = 480;
        this.theScene = new Scene(root);
        this.canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        this.framesUrso = new Image[2];
        this.index = (index + 1) % framesUrso.length;
        this.frutas = new ArrayList();
        this.background = new Image("/img/bg.png");
        Image urso3 = new Image("/img/urso3.png");
        Image urso4 = new Image("/img/urso4.png");
        framesUrso[0] = urso3;
        framesUrso[1] = urso4;
        this.frutasPodres = new ArrayList();
    }

    @Override
    public void handle(long now) {
        frames++;
        theScene.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.LEFT && !(posX - 10 <= 0)) {
                posX = posX - 30;

            } else if (e.getCode() == KeyCode.RIGHT && !(posX + 100 >= 800)) {
                posX = posX + 30;
            }
        });
        if (frames >= 30) {
            frames = 0;
            index = (index + 1) % framesUrso.length;
        }
        framesFruta++;
        if (framesFruta == 120 || framesFruta >= 240) {
            frutas.add(SpawnFrutas.spawnFruta());
        }

        if (framesFruta >= 240) {
            frutasPodres.add(SpawnFrutas.spawnFrutaPodre());
            framesFruta = 0;
        }

            gc.clearRect(0, 0, 800, 600);
            gc.drawImage(background, 0, 0, 800, 600);
            gc.drawImage(framesUrso[index], posX, posY, 80, 111);

            for (int i = 0; i < frutasPodres.size(); i++) {
                FrutaPodre fp = frutasPodres.get(i);

                if (frames >= 29) {
                    fp.setPosY(fp.getPosY() + 20);

                }
                if (fp.colide(posX, posY, 80, 111)) {
                    frutasPodres.remove(i);
                    i--;
                    Urso.trocaTela("GameOver");
                } else if (fp.colide(0, 620, 800, 21)) {
                    frutasPodres.remove(i);
                    i--;
                } else {
                    gc.drawImage(fp.getImage(), fp.getPosX(), fp.getPosY());
                }

            }

            for (int i = 0; i < frutas.size(); i++) {
                Fruta f = frutas.get(i);

                if (frames >= 29) {
                    f.setPosY(f.getPosY() + 20);

                }
                if (f.colide(posX, posY, 80, 111)) {
                    frutas.remove(i);
                    i--;
                    score++;
                } else if (f.colide(0, 620, 800, 21)) {
                    frutas.remove(i);
                    i--;
                } else {
                    gc.drawImage(f.getImage(), f.getPosX(), f.getPosY());
                }

            }

            String pointsText = "Pontuação: " + score + "00";
            gc.fillText(pointsText, 320, 36);
            gc.strokeText(pointsText, 320, 36);
            gc.setFont(Font.font("arial", 20));
        }

    

    public Scene getScene() {
        return this.theScene;
    }
    
    public long getPoints(){
        return this.score;
    }
    
    public void stopMusic(){
        this.mediaplayer.stop();
    }

}
