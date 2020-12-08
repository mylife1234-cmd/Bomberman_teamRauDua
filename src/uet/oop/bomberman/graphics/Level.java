package uet.oop.bomberman.graphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uet.oop.bomberman.entities.Tile.Bomb;
import uet.oop.bomberman.entities.Enermy.Balloon;
import uet.oop.bomberman.entities.Tile.Flame;
import uet.oop.bomberman.entities.Tile.UnmovedTile;

public class Level {

    private String path;
    protected char[] tiles;
    protected int width_map, height_map;
    public int balloon;
    public static List<Bomb> listBomb;
    public static List<Flame> listFlames;
    public static List<Balloon> balloonList;
    public static List<Integer> wallX;
    public static List<Integer> wallY;
    private Random random;
    private boolean completed;

    public Level(String path, int balloon) {
        listBomb = new ArrayList<>();
        listFlames = new ArrayList<>();
        balloonList = new ArrayList<>();
        wallX = new ArrayList<>();
        wallY = new ArrayList<>();
        this.balloon = balloon;
        this.path = path;
        random = new Random();
        completed = false;
        loadLevel();
    }


    public void update() {
        if(balloonList.size() == 0 && listFlames.size() == 0){
            completed = true;
        }
    }

    public void loadLevel() {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" ");
        height_map = Integer.parseInt(arrays[1]);
        width_map = Integer.parseInt(arrays[2]);
        tiles = new char[height_map * width_map];
        for(int i = 0; i< height_map; i++){
            for(int j = 0; j< width_map; j++){
                tiles[i*j] = list.get(i+1).charAt(j);
            }
        }
    }

    public void render(Screeen screeen) {
        int x1 = (screeen.width + 64) >> 6;
        int y1 = (screeen.height + 64) >> 6;
        for (int y = 0; y < y1; y++) {
            for (int x = 0; x < x1; x++) {
                if (x >= 15 || y > 8)break;
                getUnmoveTile(x, y).render(x, y, screeen);
            }
        }

        for (int i = 0; i < listBomb.size(); i++) {
            listBomb.get(i).update();
            listBomb.get(i).render(screeen);
        }

        for (int i = 0; i < Level.listFlames.size(); i++) {
            Level.listFlames.get(i).update();
            Level.listFlames.get(i).render(screeen);
        }

        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).update();
            balloonList.get(i).render(screeen);
            if (balloonList.get(i).removed)
                balloonList.remove(i);
        }
    }

    public UnmovedTile getUnmoveTile(int xOffset, int yOffset) {
        UnmovedTile unmovedTile =UnmovedTile.wall;
        if (xOffset >= width_map || xOffset < 0 || yOffset < 0 || yOffset >= height_map) unmovedTile = UnmovedTile.wall;
        else if (tiles[xOffset + yOffset * width_map] == '#') unmovedTile = UnmovedTile.wall;
        else if (tiles[xOffset + yOffset * width_map] == '*') unmovedTile = UnmovedTile.brick;
        else if (tiles[xOffset + yOffset * width_map] == ' ') unmovedTile = UnmovedTile.grass;

        for(int yy = 0; yy < wallY.size(); yy++) {
            if(wallX.get(yy) == yOffset && wallY.get(yy) == yOffset) {
                return UnmovedTile.grass;
            }
        }

        return unmovedTile;
    }

    public void addBomb(Bomb bomb) {
        this.listBomb.add(bomb);
    }

    public void addFlame(Flame flame) {
        this.listFlames.add(flame);
    }

    public void addBalloon() {
        for (int i = 0; i < balloon; i++) {
            int xx = 0;
            int yy = 0;

            while (getUnmoveTile(xx, yy).solid() || (xx == 1 && yy == 1) || (xx == 2 && yy == 1) || (xx == 1 && yy == 2)) {
                xx = getRandomX();
                yy = getRandomY();
            }

            balloonList.add(new Balloon(xx, yy, Sprite.creep_forward_1, this));
        }
    }

    private int getRandomX() {
        int num = 0;

        while (num % 2 == 0) {
            num = random.nextInt(11) + 1;
        }

        return num;
    }

    private int getRandomY() {
        int num = 0;

        while (num % 2 == 0) {
            num = random.nextInt(5) + 1;
        }

        return num;
    }

    public void addBrick(int x, int y) {
        wallX.add(x);
        wallY.add(y);
    }

    public boolean isCompleted() {
        return completed;
    }

}
