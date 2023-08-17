package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Strawberry extends Entity {
    GamePanel gp;

    public OBJ_Strawberry(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Strawberry";
        value = 1;
        down1 = setup("/objects/strawberry", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nStrawberry Field\nNothing is real..";
    }

    public void use(Entity entity) {
        gp.playSE(7);
        gp.ui.addMessage(value + " strawberry added!");
        gp.player.coin += 1;
    }
}
