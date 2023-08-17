package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Onion extends Entity {
    GamePanel gp;

    public OBJ_Onion(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickUpOnly;
        name = "Glass Onion";
        value = 1;
        down1 = setup("/objects/strawberry", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nLooking through the bent-backed tulips\nTo see how the other half live\nLooking through a glass onion.";
    }
}
