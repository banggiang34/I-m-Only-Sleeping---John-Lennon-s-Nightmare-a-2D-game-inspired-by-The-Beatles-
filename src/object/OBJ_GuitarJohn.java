package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GuitarJohn extends Entity {
    public OBJ_GuitarJohn(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Epiphone Casino";
        down1 = setup("/objects/lennonguitar", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn essential part of\nLennon’s signature sound.";
    }
}
