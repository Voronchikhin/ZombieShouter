package NEOfr.GameLogic.View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewElement {
    public int x;
    public int y;

    public ViewElement(String sprite, int width, int height, int x, int y) {
        this.sprite = new ImageIcon(ViewElement.class.getResource(sprite) ).getImage();
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public ViewElement(BufferedImage sprite, int width, int height, int x, int y){
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.y = y;
        this.x = x;
    }
    public Image sprite;
    public int width;
    public int height;

}
