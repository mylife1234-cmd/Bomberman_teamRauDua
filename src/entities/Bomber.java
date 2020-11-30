package entities;

import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.Reader;

public class Bomber extends Entity {
    public final int UP = 0;
    public final int DOWN = 1;
    public final int LEFT = 2;
    public final int RIGHT = 3;
    private int status;
    private int frame;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        status = DOWN;
        frame = 0;
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
            setX(getX() + 8);
        }
        else if (event.getCode() == KeyCode.A) {
            setX(getX() - 8);
        }
        else if (event.getCode() == KeyCode.S) {
            setY(getY() + 8);
        }
        else if (event.getCode() == KeyCode.W) {
            setY(getY() - 8);
        }
    }
}
