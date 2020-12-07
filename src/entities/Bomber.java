package entities;

import graphics.Sprite;
import graphics.SpriteSheet;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.Reader;
import java.net.URL;
import java.util.List;

import static graphics.Sprite.DEFAULT_SIZE;

public class Bomber extends EntityMove implements Moveable{
    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 1;
    private int step = 4;
    private int status;
    private int frame;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        status = DOWN;
        frame = 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStep() {
        return step;
    }

    @Override
    public void update(KeyEvent event) {

        if (event.getCode() == KeyCode.D) {
            if (status != RIGHT || frame == 2) {
                status = RIGHT;
                frame = 0;
                setImg(Sprite.player_right.getFxImage());
            }
            else if (frame == 0) {
                frame = 1;
                setImg(Sprite.player_right_1.getFxImage());
            }
            else if (frame == 1) {
                frame = 2;
                setImg(Sprite.player_right_2.getFxImage());
            }
            move(status);
        }
        else if (event.getCode() == KeyCode.A) {
            if (frame == 2) {
                status = LEFT;
                frame = 0;
                setImg(Sprite.player_left.getFxImage());
            }
            else if (frame == 0) {
                frame = 1;
                setImg(Sprite.player_left_1.getFxImage());
            }
            else if (frame == 1) {
                frame = 2;
                setImg(Sprite.player_left_2.getFxImage());
            }
            status = LEFT;
            move(status);
        }
        else if (event.getCode() == KeyCode.S) {
            if (status != DOWN || frame == 2) {
                status = DOWN;
                frame = 0;
                setImg(Sprite.player_down.getFxImage());
            }
            else if (frame == 0) {
                frame = 1;
                setImg(Sprite.player_down_1.getFxImage());
            }
            else if (frame == 1) {
                frame = 2;
                setImg(Sprite.player_down_2.getFxImage());
            }
            move(status);
        }
        else if (event.getCode() == KeyCode.W) {
            if (status != UP || frame == 2) {
                status = UP;
                frame = 0;
                setImg(Sprite.player_up.getFxImage());
            }
            else if (frame == 0) {
                frame = 1;
                setImg(Sprite.player_up_1.getFxImage());
            }
            else if (frame == 1) {
                frame = 2;
                setImg(Sprite.player_up_2.getFxImage());
            }
            move(status);
        }

    }

    @Override
    public void update() {
        setImg(Sprite.player_down.getFxImage());
    }

    @Override
    public void move(int status) {
//        if (stop) {
//            stop = false;
//            return;
//        }
        switch (status) {
            case LEFT: {
                setX(getX() - step);
                break;
            }
            case RIGHT: {
                setX(getX() + step);
                break;
            }
            case UP: {
                setY(getY() - step);
                break;
            }
            case DOWN: {
                setY(getY() + step);
                break;
            }
            default: {
            }
        }
    }

    @Override
    public boolean isMove(int status, List<Entity> entityList) {
        int x = getX();
        int y = getY();
        switch (status) {
            case UP: {
                y -= step;
            }
            case DOWN: {
                y += step;
            }
            case RIGHT: {
                x += step;
            }
            case LEFT: {
                x -= step;
            }
        }

        int i = 0;
        while (i < entityList.size()) {
            if (!Moveable.checkToEntity(this, entityList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void nextFrame(int direction) {
        if (direction != status) {
            status = direction;
        }
        frame++;
        setImg(new Sprite(DEFAULT_SIZE, status % 4, frame % 3, SpriteSheet.tiles, 10, 16).getFxImage());
    }

}
