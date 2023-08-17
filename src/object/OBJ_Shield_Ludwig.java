package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Ludwig extends Entity {
    public OBJ_Shield_Ludwig(GamePanel gp) {
        super(gp);

        type = type_shield;

        name = "Ludwig Shield";
        down1 = setup("/objects/shieldludwig", gp.tileSize, gp.tileSize);
        defenseValue = 3;
        description = "[" + name + "]\nThe vintage drum-kit appeared\non the Ed Sullivan Show.";
    }
}
