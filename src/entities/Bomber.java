package entities;

import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.Reader;
import java.net.URL;

public class Bomber extends Entity {
    public final int UP = 0;
    public final int DOWN = 1;
    public final int LEFT = 2;
    public final int RIGHT = 3;
    private int step = 4;
    private int status;
    private int frame;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        status = DOWN;
        frame = 0;
    }

    @Override
    public void update(KeyEvent event) {
        if (status == UP) {
            setImg(Sprite.player_up.getFxImage());
        }
        else if (status == RIGHT) {
            setImg(Sprite.player_right.getFxImage());
        }
        else if (status == DOWN) {
            setImg(Sprite.player_down.getFxImage());
        }
        else setImg(Sprite.player_left.getFxImage());

        ////
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
            setX(getX() + step);
        }
        else if (event.getCode() == KeyCode.A) {
            if (status != LEFT || frame == 2) {
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
            setX(getX() - step);
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
            setY(getY() + step);
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
            setY(getY() - step);
        }

    }
}
