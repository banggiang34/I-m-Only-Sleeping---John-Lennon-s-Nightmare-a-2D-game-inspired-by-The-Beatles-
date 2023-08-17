package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Pepper extends Entity {

    public OBJ_Shield_Pepper(GamePanel gp) {
        super(gp);

        type = type_shield;

        name = "Pepper Shield";
        down1 = setup("/objects/shieldpep", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nThe Sgt. Pepper's \ndrum-kit shield.";
    }
}
