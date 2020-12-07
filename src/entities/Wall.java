package entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Wall extends EntityUnmove {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(KeyEvent event) {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean isMove(int status, List<Entity> entityList) {
        return false;
    }

    @Override
    public void move(int status) {

    }
}
