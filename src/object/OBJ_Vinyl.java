package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Vinyl extends Entity {

    public OBJ_Vinyl(GamePanel gp) {
        super(gp);

        type = type_shield;

        name = "Vinyl";
        down1 = setup("/objects/vinyl",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nVintage analog technology.";
    }
}
