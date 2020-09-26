package objetos;

import javafx.scene.image.Image;

public class Fruta {

    private final Image image;

    private int posX;
    private int posY;

    private int frames;
    public static int maxFramesFruta = 60;

    public Fruta(int posX, int posY) {
        this.image = new Image(this.getClass().getResource("/img/maçã.png").toString());
        this.posX = posX;
        this.posY = posY;
    }

    public Image getImage() {
        return image;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean colide(int posX,int posY,int width, int height) {
        boolean colidiu = false;
        if((this.posX + 25) >= posX && (this.posX + 25) <= (posX + width) && 
                (this.posY + image.getHeight()) >= posY && (this.posY + image.getHeight()) <= (posY + height)){
            colidiu = true;
        }
        return colidiu;
    }

}
