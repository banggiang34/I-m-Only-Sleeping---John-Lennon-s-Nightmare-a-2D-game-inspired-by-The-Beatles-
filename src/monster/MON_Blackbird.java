package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Mana_Plasticine;
import object.OBJ_Strawberry;

import java.util.Random;

public class MON_Blackbird extends Entity {

    GamePanel gp;

    public MON_Blackbird(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Blackbird";
        speed = 15;
        maxLife = 1;
        life = maxLife;
        attack = 10;
        defense = 1 / 2;
        exp = 5;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/birdback1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/birdback2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/birdfront1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/birdfront2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/birdleft1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/birdleft2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/birdright1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/birdright2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //chon so bat ky

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {
        //ngau nhien so
        int i = new Random().nextInt(100) + 1;

        //dat vat tha cua quai vat
        if (i < 50) {
            dropItem(new OBJ_Strawberry(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Mana_Plasticine(gp));
        }
    }
}
