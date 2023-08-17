package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana_Plasticine extends Entity {

    GamePanel gp;

    public OBJ_Mana_Plasticine(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        value = 2;
        name = "Mana Plasticine";
        down1 = setup("/objects/plasticineblue", gp.tileSize, gp.tileSize);
        image = setup("/objects/plasticineblue", gp.tileSize - 10, gp.tileSize - 10);
        image2 = setup("/objects/plasticineblue_blank", gp.tileSize - 10, gp.tileSize - 10);
    }

    public void use(Entity entity) {
        gp.playSE(6);
        gp.ui.addMessage(value + " plasticines are flowing out!");
        entity.mana += value;
    }
}
