package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    KeyHandler keyH;
    EventRect eventRect[][][];
    int previousEventX, previousEventY;
    //int tempMap, tempCol, tempRow;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {

        //kiem tra xem nhan vat co cach xa vi tri 1 tile so voi noi nhan damage hay khong
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            if (hit(0, 23, 22, "down") == true) {
                damagePit(gp.dialogueState);
            } else if (hit(0, 46, 20, "any") == true) {
                healingPool(gp.dialogueState);
            } else if (hit(1, 55, 45, "any") == true) {
                damagePitStraw(gp.dialogueState);
            } else if (hit(0, 67, 49, "any") == true) {
                healingPool(gp.dialogueState);
            } else if (hit(0, 47, 60, "up") == true) {
                teleport(1, 45, 62);
            } else if (hit(1, 45, 62, "any") == true) {
                teleport(0, 47, 60);
                gp.stopMusic();
                gp.playMusic(0);
            } else if (hit(1, 29, 29, "left") == true) {
                teleport(2, 40, 40);
                gp.stopMusic();
                gp.playSE(1);
                gp.playMusic(12);
            } else if (hit(2, 40, 40, "right") == true) {
                teleport(1, 29, 29);
                gp.stopMusic();
                gp.playMusic(11);
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "Above us only sky..";
            } else if (hit(2, 24, 18, "left") == true) {
                teleport(0, 46, 20);
                gp.stopMusic();
                gp.playSE(6);
                gp.playMusic(0);
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "Imagine the cloud dripping\nDig a hole in your garden to\nput them in..";
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }
        return hit;
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Dripping from a dead dog's eye!";
        gp.player.life -= 1;
        canTouchEvent = false;
    }

    public void damagePitStraw(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Happiness is a warm gun\nGet to the telephone booth.";
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {

        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.stopMusic();
            gp.playSE(2);
            gp.ui.currentDialogue = "When your prized possession\nStarts to weigh you down\nLook in my direction\nI'll be round.";
            gp.player.restoreLifeAndMana();
            gp.aSetter.setMonster();
            gp.aSetter.setObject();
            gp.playMusic(0);
        }
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;

        canTouchEvent = false;
        gp.stopMusic();
        gp.playSE(6);
        gp.playMusic(11);
    }
}
