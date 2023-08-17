package main;

import entity.NPC_GeorgeHarrison;
import entity.NPC_PaulMcCartney;
import monster.MON_Blackbird;
import monster.MON_Blue_Meanies;
import monster.MON_Octopus;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_GuitarJohn(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
        i++;

        gp.obj[mapNum][i] = new OBJ_LucyLesPaul(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 28;
        i++;

        gp.obj[mapNum][i] = new OBJ_Shield_Pepper(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        gp.obj[mapNum][i] = new OBJ_Shield_Ludwig(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.obj[mapNum][i] = new OBJ_Savoy_Truffle(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 46;
        gp.obj[mapNum][i].worldY = gp.tileSize * 56;
        i++;
        gp.obj[1][i] = new OBJ_Strawberry(gp);
        gp.obj[1][i].worldX = gp.tileSize * 55;
        gp.obj[1][i].worldY = gp.tileSize * 45;
        i++;
        gp.obj[1][i] = new OBJ_Door(gp);
        gp.obj[1][i].worldX = gp.tileSize * 30;
        gp.obj[1][i].worldY = gp.tileSize * 28;
        i++;
        gp.obj[1][i] = new OBJ_Door(gp);
        gp.obj[1][i].worldX = gp.tileSize * 30;
        gp.obj[1][i].worldY = gp.tileSize * 29;
        i++;

    }

    public void setNPC() {
        int mapNum = 0;

        gp.npc[mapNum][0] = new NPC_GeorgeHarrison(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 23;
        gp.npc[mapNum][0].worldY = gp.tileSize * 26;

        gp.npc[mapNum][1] = new NPC_PaulMcCartney(gp);
        gp.npc[mapNum][1].worldX = gp.tileSize * 25;
        gp.npc[mapNum][1].worldY = gp.tileSize * 20;
    }

    public void setMonster() {
        int i = 0;
        int mapNum = 0;

        gp.monster[mapNum][i] = new MON_Octopus(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new MON_Octopus(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
        i++;
        gp.monster[mapNum][i] = new MON_Octopus(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        gp.monster[mapNum][i] = new MON_Octopus(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[mapNum][i] = new MON_Octopus(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[mapNum][i] = new MON_Blue_Meanies(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 26;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[mapNum][i] = new MON_Blue_Meanies(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 26;
        gp.monster[mapNum][i].worldY = gp.tileSize * 40;
        i++;
        gp.monster[mapNum][i] = new MON_Blue_Meanies(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 40;
        i++;
        gp.monster[mapNum][i] = new MON_Blue_Meanies(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 50;
        i++;
        gp.monster[1][i] = new MON_Blackbird(gp);
        gp.monster[1][i].worldX = gp.tileSize * 35;
        gp.monster[1][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[1][i] = new MON_Blackbird(gp);
        gp.monster[1][i].worldX = gp.tileSize * 40;
        gp.monster[1][i].worldY = gp.tileSize * 50;
        i++;
        gp.monster[1][i] = new MON_Blackbird(gp);
        gp.monster[1][i].worldX = gp.tileSize * 35;
        gp.monster[1][i].worldY = gp.tileSize * 51;
        i++;
        gp.monster[1][i] = new MON_Blackbird(gp);
        gp.monster[1][i].worldX = gp.tileSize * 36;
        gp.monster[1][i].worldY = gp.tileSize * 30;
        i++;
        gp.monster[1][i] = new MON_Blackbird(gp);
        gp.monster[1][i].worldX = gp.tileSize * 56;
        gp.monster[1][i].worldY = gp.tileSize * 30;
        i++;

    }
}
