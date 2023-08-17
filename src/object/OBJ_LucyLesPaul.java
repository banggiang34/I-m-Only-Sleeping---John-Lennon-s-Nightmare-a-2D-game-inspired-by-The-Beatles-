package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_LucyLesPaul extends Entity {

    public OBJ_LucyLesPaul(GamePanel gp) {
        super(gp);

        type = type_axe;

        name = "Lucy";
        down1 = setup("/objects/lucylespaul",gp.tileSize,gp.tileSize);
        attackValue = 3;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nThe unique red Gibson Les Paul\ngiven by Eric Clapton.";
    }
}
