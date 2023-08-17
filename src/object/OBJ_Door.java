package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        super(gp);

        type = type_pickUpOnly;
        name = "Iron Gate";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
