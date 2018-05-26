package NEOfr.GameLogic.Level;


import NEOfr.GameLogic.Unit.Barrier;
import NEOfr.GameLogic.Unit.Spike;
import NEOfr.GameLogic.Unit.Unit;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Player {

    private int bulletCount;
    private int bulletPower;
    private int barrierCount = 2;
    private int width = 300;
    private int height =200;
    private int bulletHolder = 3;

    public Player(int bulletCount , int bulletPower, int barrierCount, int rifleX, int rifleY, int xPos, int yPos, String id) {
        this.bulletCount = bulletCount;
        this.bulletPower = bulletPower;
        this.barrierCount = barrierCount;
        this.rifleX = rifleX;
        this.rifleY = rifleY;
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
    }

    public Player() {
        this(1,1 ,0 ,295 ,105 , 0, 0,"player" );
    }
    public Player(String configFile){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = properties.getProperty("id");
        this.xPos = Integer.parseInt(properties.getProperty("xPos"));
        this.yPos = Integer.parseInt(properties.getProperty("yPos"));
        this.bulletHolder = Integer.parseInt(properties.getProperty("bulletHolder"));
        this.bulletCount = this.bulletHolder;
        this.bulletPower = Integer.parseInt(properties.getProperty("bulletPower"));
        this.rifleX = Integer.parseInt(properties.getProperty("rifleX"));
        this.rifleY = Integer.parseInt(properties.getProperty("rifleY"));
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public void addBullet () {
        if (bulletCount < bulletHolder){
            bulletCount++;
        }
    }

    public int getBulletPower() {
        return bulletPower;
    }

    public void setBulletPower(int bulletPower) {
        this.bulletPower = bulletPower;
    }

    public int getRifleX() {
        return rifleX;
    }

    public void setRifleX(int rifleX) {
        this.rifleX = rifleX;
    }

    public int getRifleY() {
        return rifleY;
    }

    public void setRifleY(int rifleY) {
        this.rifleY = rifleY;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private int rifleX;     // relativeyPos
    private int rifleY;   // relative xPos


    private int xPos;

    public String getId() {
        return id;
    }

    private int yPos;
    private String id = "player";

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    public void deleteBullet() {
        if( bulletCount > 0 ){
            bulletCount--;

        }
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {
        return height;
    }

    public void handleAction(int x, int y, List<Unit> units) {
        if(x<xPos+width)
            addBullet();
        else if(barrierCount-- >=0)
            units.add(new Spike(x,500));
    }
}
