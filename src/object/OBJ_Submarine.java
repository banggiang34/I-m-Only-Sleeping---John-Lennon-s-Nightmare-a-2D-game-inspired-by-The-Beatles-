package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Submarine extends Entity {
    public OBJ_Submarine(GamePanel gp) {
        super(gp);
        name = "Submarine";
        down1 = setup("/objects/yellowsub",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nWe all live in a\nYellow Submarine.";
    }
}
