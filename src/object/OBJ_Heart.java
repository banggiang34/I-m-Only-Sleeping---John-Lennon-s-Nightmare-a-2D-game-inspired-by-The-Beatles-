package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/objects/vinyl", gp.tileSize, gp.tileSize);
        image = setup("/objects/vinyl", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/halfvinyl", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/nonevinyl", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {
        gp.playSE(6);
        gp.ui.addMessage(value + " lives are flowing out!");
        entity.life += value;
    }
}
