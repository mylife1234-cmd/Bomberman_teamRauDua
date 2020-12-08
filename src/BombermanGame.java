import entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import graphics.Sprite;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int WIDTH_MAP = 31;
    public static final int HEIGHT_MAP = 13;


    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private Entity bomberman;

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

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        ((Bomber)bomberman).setEntities(entities);
//        entities.add(bomberman);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                bomberman.update();
                entities.forEach(Entity::update);
                scene.setOnKeyPressed(event -> {
//                    ((Bomber)bomberman).setCheck();
                    setEventScreen(event);
                });
//
//                scene.setOnKeyReleased(event -> {
//
//                });

//                bomberman.setX(bomberman.getX() + 10);
//                bomberman.setY(bomberman.getY() + 10);
            }
        };
        timer.start();

    }

    public void createMap(String path) {
        map = new char[HEIGHT][WIDTH_MAP];
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("res/levels/map.txt"),
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

        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                System.out.printf(map[i][j] + "");
            }
            System.out.println();
        }

        for (int i = 0; i < HEIGHT_MAP; i++) {
            for (int j = 0; j < WIDTH_MAP; j++) {
                Entity object;

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

                if (map[i][j] == 'q') {
                    object = new EnermySimple(j, i, Sprite.balloom_left1.getFxImage());
                    ((EntityMove)object).setEntities(stillObjects);
                    entities.add(object);
                }
            }
        }
    }

    public void setEventScreen(KeyEvent event) {

        KeyCombination keyCombinationS = new KeyCodeCombination(KeyCode.S);
        KeyCombination keyCombinationD = new KeyCodeCombination(KeyCode.D);
        KeyCombination keyCombinationW = new KeyCodeCombination(KeyCode.W);
        KeyCombination keyCombinationA = new KeyCodeCombination(KeyCode.A);
        KeyCombination keyCombinationSpace = new KeyCodeCombination(KeyCode.SPACE);

        if (keyCombinationS.match(event)) {
            ((Bomber)bomberman).setStatus(Bomber.DOWN);
        }
        else if (keyCombinationD.match(event)) {
            ((Bomber)bomberman).setStatus(Bomber.RIGHT);
        }
        else if (keyCombinationW.match(event)) {
            ((Bomber)bomberman).setStatus(Bomber.UP);
        }
        else if (keyCombinationA.match(event)) {
            ((Bomber)bomberman).setStatus(Bomber.LEFT);
        }
        else bomberman.setStop(true);

        if (keyCombinationSpace.match(event)) {
//            ((Bomber)bomberman)
            ((Bomber)bomberman).plantBoom();
        }
        stillObjects.forEach(entity -> {
            if (!Entity.checkToUnmove((Bomber)bomberman, (EntityUnmove) entity)) {
                bomberman.setStop(true);
            }
            entities.forEach(entity1 -> {
                if (!entity.equals(entity1) && !Entity.checkToUnmove((EntityMove) entity1, entity)) {
                    entity1.setStop(true);
                }
            });
        });
        entities.forEach(entity -> {
            entity.move(((EntityMove)entity).getStatus());
        });

        entities.forEach(entity -> {
            if (!(entity instanceof Bomber) && !Entity.checkToUnmove((Bomber)bomberman, entity)) {
                bomberman.setStop(true);
//                Platform.exit();
                entities.remove(entity);
            }
        });


        if (!bomberman.isStop()) {
            bomberman.move(((Bomber)bomberman).getStatus());
        }
        ((Bomber) bomberman).nextFrame(((Bomber)bomberman).getStatus());


        bomberman.setStop(false);


    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }
}
