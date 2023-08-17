package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    int doorCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    //vi tri bat dau
    public void setDefaultValues() {

        worldX = gp.tileSize * 39;
        worldY = gp.tileSize * 42;
        speed = 3 / 2;
        direction = "down";

        //stat nhan vat chinh
        level = 1;
        maxLife = 12;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_GuitarJohn(gp);
        currentShield = new OBJ_Shield_Pepper(gp);
        projectile = new OBJ_Flower(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setDefaultPosition() {
        worldX = gp.tileSize * 39;
        worldY = gp.tileSize * 42;
        direction = "down";
    }

    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {

        //anh nhan vat
        up1 = setup("/player/johnup1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/johnup2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/johndown1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/johndown2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/johnleft", gp.tileSize, gp.tileSize);
        left2 = setup("/player/johnlefty", gp.tileSize, gp.tileSize);
        right1 = setup("/player/johnright2", gp.tileSize, gp.tileSize);
        right2 = setup("/player/johnright", gp.tileSize, gp.tileSize);
        //anh menu
        menutitle = setup("/objects/titlemenu", gp.tileSize * 4, gp.tileSize * 4);
        submarine = setup("/objects/yellowsub", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == type_sword) {
            attackUp1 = setup("/player/johnattackup1", gp.tileSize, gp.tileSize);
            attackUp1 = setup("/player/johnattackup2", gp.tileSize, gp.tileSize);
            attackDown1 = setup("/player/johnattackdown1", gp.tileSize, gp.tileSize);
            attackDown2 = setup("/player/johnattackdown2", gp.tileSize, gp.tileSize);
            attackLeft1 = setup("/player/johnattackleft1", gp.tileSize, gp.tileSize);
            attackLeft2 = setup("/player/johnattackleft2", gp.tileSize, gp.tileSize);
            attackRight1 = setup("/player/johnattackright1", gp.tileSize, gp.tileSize);
            attackRight2 = setup("/player/johnattackright2", gp.tileSize, gp.tileSize);
        }

        if (currentWeapon.type == type_axe) {
            attackUp1 = setup("/player/johnattackup1 - Copy", gp.tileSize, gp.tileSize);
            attackUp1 = setup("/player/johnattackup2 - Copy", gp.tileSize, gp.tileSize);
            attackDown1 = setup("/player/johnattackdown1 - Copy", gp.tileSize, gp.tileSize);
            attackDown2 = setup("/player/johnattackdown2 - Copy", gp.tileSize, gp.tileSize);
            attackLeft1 = setup("/player/johnattackleft1 - Copy", gp.tileSize, gp.tileSize);
            attackLeft2 = setup("/player/johnattackleft2 - Copy", gp.tileSize, gp.tileSize);
            attackRight1 = setup("/player/johnattackright1 - Copy", gp.tileSize, gp.tileSize);
            attackRight2 = setup("/player/johnattackright2 - Copy", gp.tileSize, gp.tileSize);
        }
    }

    public void update() {

        if (attacking == true) {
            attacking();
        } else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //kiem tra phan tile co phai collision hay khong
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //kiem tra object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //kiem tra collision cua npc
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //kiem tra collision cua quai vat
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //kiem tra event
            gp.eHandler.checkEvent();

            //neu collision false thi di chuyen
            if (collisionOn == false && gp.keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            if (keyH.enterPressed == true && attackCanceled == false) {
                gp.playSE(5);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true) {

            //huong mac dinh, toa do va nhan vat
            projectile.set(worldX, worldY, direction, true, this);

            //tru di mana
            projectile.subtractResource(this);
            // them vao array
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;

            gp.playSE(4);
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
            gp.playSE(10);
            gp.keyH.upPressed = false;
            gp.keyH.downPressed = false;
            gp.keyH.leftPressed = false;
            gp.keyH.rightPressed = false;
        }
    }

    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            //luu toa do worldX va Y va solidArea hien tai
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //dieu chinh toa do trong vung tan cong
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            //vung tan cong tro thanh vung solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //kiem tra collision quai vat voi toa do va solidArea moi
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            //sau khi kiem tra collision thi khoi phuc toa do ban dau
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {

        if (i != 999) {
            String text;
            String doorName = "Iron Gate";

            //nhat vat dung
            if (gp.obj[gp.currentMap][i].type == type_pickUpOnly) {
                if (gp.currentMap == 0) {
                    if (gp.obj[gp.currentMap][i].name == doorName) {
                        if (coin >= 3) {
                            text = "Opened up the door";
                            gp.ui.addMessage(text);
                            gp.obj[gp.currentMap][5] = null;
                            coin -= 3;
                        } else {
                            doorCounter++;
                            text = "The long and winding road.. Get " + (3 - coin) + " strawberries";
                            gp.gameState = gp.dialogueState;
                            gp.ui.currentDialogue = text;

                            if (doorCounter > 120) {
                                gp.gameState = gp.playState;
                            }
                            standCounter = 0;
                        }
                    } else {
                        gp.obj[gp.currentMap][i].use(this);
                        gp.obj[gp.currentMap][i] = null;
                    }

                } else if (gp.currentMap == 1) {
                    if (gp.obj[1][i].name == doorName) {
                        if (coin >= 3) {
                            text = "Opened up the door";
                            gp.ui.addMessage(text);
                            gp.obj[1][7] = null;
                            gp.obj[1][8] = null;
                            coin -= 1;
                        } else {
                            doorCounter++;
                            text = "The long and winding road.. Get " + (3 - coin) + " strawberries";
                            gp.gameState = gp.dialogueState;
                            gp.ui.currentDialogue = text;
                        }
                    } else {
                        gp.obj[gp.currentMap][i].use(this);
                        gp.obj[gp.currentMap][i] = null;
                    }
                }
            }
            //inventory
            else {
                if (inventory.size() != maxInventorySize && i != 5) {
                    inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(6);
                    text = "Got to get " + gp.obj[gp.currentMap][i].name + " in to my life!";
                } else {
                    text = "She's so heavy..";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }

        }

    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                attackCanceled = true;
                gp.playSE(4);
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                gp.playSE(9);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {

                gp.playSE(5);

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " CLANG CLANG!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Make sure that " + gp.monster[gp.currentMap][i].name + " was dead..");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp + " with diamonds");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(1);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "I'VE GOT A FEELING!!!\nLevel: Revolution " + level;
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {

                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);

        //reset do trong suot
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
