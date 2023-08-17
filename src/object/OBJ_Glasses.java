package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Glasses extends Entity {
    public OBJ_Glasses(GamePanel gp) {

        super(gp);
        name = "Glasses";
        down1 = setup("/objects/glass",gp.tileSize,gp.tileSize);
        //type = type_light;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        description = "[" + name + "]\nThe iconic rounded glasses.";
    }
}
