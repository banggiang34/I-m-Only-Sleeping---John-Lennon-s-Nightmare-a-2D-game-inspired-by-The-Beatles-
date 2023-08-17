package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_PaulMcCartney extends Entity{
    public NPC_PaulMcCartney(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        type = type_npc;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/npc/paulback1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/paulback2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/paulfront1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/paulfront2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/paulleft1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/paulleft2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/paulright1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/paulright2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {

        dialogues[0] = "Yesterday,\nAll my troubles seem so far away..";
        dialogues[1] = "Welcome to your own nightmarish experience,\nJohn.";
        dialogues[2] = "Find the way to Eleanor Rigby's graveyard..";
        dialogues[3] = "Wearing the face that she keeps in a jar\nby the door\nWho is it for?";
        dialogues[4] = "The Strawberry Field has been surrounded\nby Blue Meanies and Octopuses.";
        dialogues[5] = "You may find Strawberries by defeating them.";
        dialogues[6] = "In the end, the love you take\nIs equal to the love you make.";
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