package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Mana_Plasticine;

import java.awt.Graphics2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font cooper_black;
    BufferedImage heart_full, heart_half, heart_blank, plasticine_full, plasticine_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    //public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;
    int counter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        //font chu
        cooper_black = new Font("Cooper Black", Font.PLAIN, 35);

        //hud
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity plasticine = new OBJ_Mana_Plasticine(gp);
        plasticine_full = plasticine.image;
        plasticine_blank = plasticine.image2;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(cooper_black);
        g2.setColor(Color.white);

        //man hinh main menu
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //trang thai tro choi dang choi
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();

        }
        //tam dung
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();

        }
        //hoi thoai
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
            gp.keyH.downPressed = false;
            gp.keyH.upPressed = false;
            gp.keyH.leftPressed=false;
            gp.keyH.rightPressed=false;
        }
        //stat nhan vat
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
        //cai dat game
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        //game over
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        //loading khi chuyen map
        //if (gp.gameState == gp.transitionState) {
        //    drawTransition();
        //}
        if (gp.gameState == gp.creditState){
            drawCredit();
        }
    }

    public void drawCredit(){
        g2.setColor(new Color(7, 7, 7));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));

        text = "I'm Only Sleeping";
        //ve bong
        g2.setColor(Color.pink);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        //chu
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        g2.setFont(g2.getFont().deriveFont(20f));
        text = "Developed by Giang";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);

        text = "Assets and Sounds by Giang";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);

        g2.setColor(Color.pink);
        text = "Get Back";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        commandNum =0;

        if (commandNum == 0) {
            g2.drawImage(gp.player.submarine, x - gp.tileSize * 4 / 3, y - gp.tileSize * 3 / 4, null);
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));

        text = "'I know what it's like to be dead..'";
        //ve bong
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        //chu
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        //choi lai
        g2.setFont(g2.getFont().deriveFont(20f));
        text = "Get back";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawImage(gp.player.submarine, x - gp.tileSize * 4 / 3, y - gp.tileSize * 3 / 4, null);
        }

        //ve menu
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);

        if (commandNum == 1) {
            g2.drawImage(gp.player.submarine, x - gp.tileSize * 4 / 3, y - gp.tileSize * 3 / 4, null);
        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //khong co health, day du mang
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //health hien co
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        //ve day du mana
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(plasticine_blank, x, y, null);
            i++;
            x += 35;
        }

        //ve mana hien co
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(plasticine_full, x, y, null);
            i++;
            x += 35;
        }
    }

    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.pink);
                g2.drawString(message.get(i), messageX, messageY);

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX - 2, messageY - 2);

                int counter = messageCounter.get(i) + 1; //them 1 vao bo dem tin nhan
                messageCounter.set(i, counter);// bo dem them vao array
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen() {
        g2.setColor(new Color(7, 7, 7));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //ten tua de
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
        String text = "I'M ONLY SLEEPING";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //bong duoi chu
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y);
        //mau chu
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //them hinh
        x = gp.screenWidth / 2 - (gp.tileSize * 7) / 2;
        y += gp.tileSize; //* 2;
        g2.drawImage(gp.player.menutitle, x, y, gp.tileSize * 7, gp.tileSize * 4, null);

        //bang menu
        g2.setFont((g2.getFont().deriveFont(Font.BOLD, 30F)));

        text = "START GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4.5;
        //them bong
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawImage(gp.player.submarine, x - gp.tileSize * 4 / 3, y - gp.tileSize * 3 / 4, null);
        }

        text = "CREDIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawImage(gp.player.submarine, x - gp.tileSize * 4 / 3, y - gp.tileSize * 3 / 4, null);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x + 4, y);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawImage(gp.player.submarine, x - gp.tileSize * 4 / 3, y - gp.tileSize * 3 / 4, null);
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);

        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        //cua so hoi thoai
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterScreen() {
        //tao frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //them chu
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //ten
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next level", textX, textY);
        textY += lineHeight;
        g2.drawString("Strawberry", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //gia tri stat
        int tailX = (frameX + frameWidth) - 30;
        //khoi phuc gia tri textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 25, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 25, null);
    }

    public void drawInventory() {

        //ve khung vat dung nhan vat
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //cac slot do vat
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //ve do dung nguoi choi
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            //equip
            if (gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentShield) {
                g2.setColor(new Color(240, 190, 190));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //o chon
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // ve con tro
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //cua so mo ta do vat
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;

        //chu mo ta do vat
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(15F));

        int itemIndex = getItemIndexOnSlot();

        if (itemIndex < gp.player.inventory.size()) {

            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }


    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));
        //cua so
        int frameX = gp.tileSize * 3;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 10;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                option_top(frameX, frameY);
                break;
            case 1:
                options_control(frameX, frameY);
                break;
            case 2:
                options_endGameConfirmation(frameX, frameY);
                break;
        }
        gp.keyH.enterPressed = false;
    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Everybody loves you\nwhen you're six-foot in the ground";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize * 2;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm / Attack", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Shoot", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Inventory", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        //back
        textX = frameX + gp.tileSize * 2;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
            if (gp.keyH.enterPressed == true) {
                gp.playSE(3);
                subState = 0;
                commandNum = 2;
            }
        }
    }

    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;

        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;

        g2.drawString(text, textX, textY);

        //nhac nen
        textX = frameX + gp.tileSize * 2;
        textY += gp.tileSize * 3 / 2;
        g2.drawString("Music", textX, textY);
        if (commandNum == 0) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
        }
        //am thanh hieu ung
        textX = frameX + gp.tileSize * 2;
        textY += gp.tileSize * 3 / 2;
        g2.drawString("Sound FX", textX, textY);
        if (commandNum == 1) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
        }
        //dieu khien
        textY += gp.tileSize * 3 / 2;
        g2.drawString("Control", textX, textY);
        if (commandNum == 2) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
            if (gp.keyH.enterPressed == true) {
                subState = 1;
                commandNum = 0;
            }
        }
        //quit
        textY += gp.tileSize * 3 / 2;
        g2.drawString("Quit", textX, textY);
        if (commandNum == 3) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        //back
        textY += gp.tileSize * 3 / 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 4) {
            g2.drawImage(gp.player.submarine, textX - gp.tileSize * 4 / 3, textY - gp.tileSize * 3 / 4, null);
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //slider cho music
        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, frameY + gp.tileSize * 2, volumeWidth, 24);
        //soundfx
        textY += gp.tileSize * 3 / 2;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 3, 5, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(254, 253, 252);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
