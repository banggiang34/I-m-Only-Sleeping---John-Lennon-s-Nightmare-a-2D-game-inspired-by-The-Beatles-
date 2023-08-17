package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Savoy_Truffle extends Entity {

    GamePanel gp;

    int value = 5;

    public OBJ_Savoy_Truffle(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Savoy Truffle";
        down1 = setup("/objects/savoytruffle", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nBut you'll have to have " + value + "\nlives pulled out when being hurt..";
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You know that what you eat you are..\n " + value + " lives recovered!";

        entity.life += value;
        gp.playSE(7);
    }
}
