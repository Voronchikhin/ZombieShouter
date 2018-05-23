package NEOfr.GameLogic.Level;

public class Player {

    private int bulletCount;
    private int bulletPower;
    private int barrierCount;
    private int width = 300;
    private int height =200;
    private int bulletHolder = 3;

    public Player() {
        this(1,1 ,0 ,295 ,105 , 0, 0,"player" );
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
}
