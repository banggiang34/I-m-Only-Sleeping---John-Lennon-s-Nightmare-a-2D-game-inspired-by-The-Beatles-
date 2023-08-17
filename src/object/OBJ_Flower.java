package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Flower extends Projectile {

    GamePanel gp;
    public OBJ_Flower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Flower Power";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 1;
        useCost = 1;
        alive = false;

        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/flower", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/flower2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/flower2", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/flower", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/flower3", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/flower4", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/flower4", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/flower3", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user){
        boolean haveResource=false;
        if(user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user){
        user.mana -= useCost;
    }

    public Color getParticleColor(){
        Color color = Color.pink;
        return color;
    }
    public int getParticleSize(){
        int size = 6; //pixel
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }

}
