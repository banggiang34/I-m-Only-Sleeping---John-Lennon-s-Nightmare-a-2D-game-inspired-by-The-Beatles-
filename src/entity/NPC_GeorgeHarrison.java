package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_GeorgeHarrison extends Entity {
    public NPC_GeorgeHarrison(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        type = type_npc;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/npc/georgeback1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/georgeback2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/georgefront1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/georgefront2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/georgeleft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/georgeleft2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/georgeright1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/georgeright2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {

        dialogues[0] = "Still my guitar gently weeps..";
        dialogues[1] = "Ah.. Look at all the lonely people..";
        dialogues[2] = "I think Paul wants you to find out something\nwithin the graveyard of Eleanor Rigby,\nstay alert to the blackbird under there.";
        dialogues[3] = "I think the octopuses has mistaken this as their\n happy garden.";
        dialogues[4] = "Eleanor Rigby's graveyard is on the South\nof this Strawberry Field.";
        dialogues[5] = "We were talking about the space between us all\nAnd the people who hide themselves\nbehind a wall\nOf illusion.";
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

    public void speak() {
        super.speak();
    }
}
