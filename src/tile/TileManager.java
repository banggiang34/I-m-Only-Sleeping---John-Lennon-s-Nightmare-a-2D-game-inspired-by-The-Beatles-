package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.awt.Graphics2D;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    //public String path;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map02.txt", 0);
        loadMap("maps/grave.txt", 1);
        loadMap("/maps/sky.txt", 2);
    }

    public void getTileImage() {

        setup(0, "tile1", false);
        setup(1, "tile2", false);
        setup(2, "tile3", false);
        setup(3, "tile4", true);
        setup(4, "tile5", true);
        setup(5, "tile6", true);
        setup(6, "tile7", false);
        setup(7, "tile8", false);
        setup(8, "tile9", true);
        setup(9, "tile10", true);
        setup(10, "tile11", true);
        setup(11, "tile12", false);
        setup(12, "tile13", true);
        setup(13, "tile14", true);
        setup(14, "tile15", true);
        setup(15, "tile16", true);
        setup(16, "tile17", true);
        setup(17, "tile18", true);
        setup(18, "tile19", true);
        setup(19, "tile20", false);
        setup(20, "tile21", false);
        setup(21, "tile22", false);
        setup(22, "tile23", false);
        setup(23, "tile24", false);
        setup(24, "tile25", false);
        setup(25, "tile26", true);
        setup(26, "tile27", true);
        setup(27, "tile28", true);
        setup(28, "tile29", true);
        setup(29, "tile30", false);
        setup(30, "tile31", false);
        setup(31, "tile32", false);
        setup(32, "tile33", false);
        setup(33, "tile34", true);
        setup(34, "tile35", true);
        setup(35, "tile36", true);
        setup(36, "tile37", true);
        setup(37, "tile38", false);
        setup(38, "tile39", true);
        setup(39, "tile40", true);
        setup(40, "tile41", true);
        setup(41, "tile42", true);
        setup(42, "tile43", true);
        setup(43, "tile44", false);
        setup(44, "tile45", false);
        setup(45, "tile46", false);
        setup(46, "tile47", false);
        setup(47, "tile48", false);
        setup(48, "tile49", false);
        setup(49, "tile50", false);
        setup(50, "tile51", false);
        setup(51, "tile52", false);
        setup(52, "tile53", false);
        setup(53, "tile54", false);
        setup(54, "tile55", false);
        setup(55, "tile56", false);
        setup(56, "tile57", false);
        setup(57, "tile58", false);
        setup(58, "tile59", false);
        setup(59, "tile60", true);
        setup(60, "tile61", true);
        setup(61, "tile62", true);
        setup(62, "tile63", false);
        setup(63, "tile64", false);
        setup(64, "tile65", false);
        setup(65, "tile66", false);
        setup(66, "tile67", false);
        setup(67, "tile68", false);
        setup(68, "tile69", false);
        setup(69, "tile70", false);
        setup(70, "tile71", false);
        setup(71, "tile72", true);
        setup(72, "tile73", true);
        setup(73, "tile74", true);
        setup(74, "tile75", false);
        setup(75, "tile76", false);
        setup(76, "tile77", false);
        setup(77, "tile78", false);
        setup(78, "tile79", false);
        setup(79, "tile80", false);
        setup(80, "tile81", false);
        setup(81, "tile82", false);
        setup(82, "tile83", false);
        setup(83, "tile84", false);
        setup(84, "tile85", false);
        setup(85, "tile86", false);
        setup(86, "tile87", false);
        setup(87, "tile88", false);
        setup(88, "tile89", false);
        setup(89, "tile90", false);
        setup(90, "tile91", false);
        setup(91, "tile92", false);
        setup(92, "tile93", false);
        setup(93, "tile94", false);
        setup(94, "tile95", false);
        setup(95, "tile96", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldcol][worldrow];

            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            worldcol++;

            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;
                worldrow++;
            }
        }
    }
}
