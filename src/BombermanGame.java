import entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import graphics.Sprite;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static final int WIDTH_MAP = 31;
    public static final int HEIGHT_MAP = 13;


    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public char map[][];


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        createMap(new String("/levels/map.txt"));

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        entities.add(bomberman);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                scene.setOnKeyPressed(event -> {
                    update(event);
                });
            }
        };
        timer.start();

    }

    public void createMap(String path) {
        map = new char[HEIGHT][WIDTH_MAP];
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("/home/teag/demo/bomberman-starter/res/levels/map.txt"),
                            Charset.forName("UTF-8")));
            int c;
            for (int i = 0; i < HEIGHT_MAP; i++) {
                for (int j = 0; j < WIDTH_MAP; j++) {
                    c = reader.read();
                    if (c == '\n') {
                        c = reader.read();
                    }
                    char character = (char) c;
                    map[i][j] = character;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                System.out.printf(map[i][j] + "");
            }
            System.out.println();
        }

        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                Entity object;
//                if (j == 0 || j == WIDTH_MAP - 1 || i == 0 || i == HEIGHT_MAP - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
                if (map[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                }
                else if (map[i][j] == '*') {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                }
                else if (map[i][j] == 'x') {
                    object = new Portal(j, i, Sprite.portal.getFxImage());
                }
                else object = new Grass(j, i, Sprite.grass.getFxImage());
                stillObjects.add(object);
            }
        }
    }

    public void update(KeyEvent event) {
//        entities.forEach(Entity::update);
        for (Entity entity : entities) {
            entity.update(event);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
