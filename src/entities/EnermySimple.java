package entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.Random;

public class EnermySimple extends EntityMove {
    public EnermySimple(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setStep(16);
    }

    @Override
    public void update(KeyEvent event) {

    }

    @Override
    public void update() {
        Random random = new Random();
        status = random.nextInt(4) + 0;
        checkToMap();
        while (stop == true) {
            status = random.nextInt(4) + 0;
            checkToMap();
        }


        move(status);
    }

    @Override
    public boolean isMove(int status, List<Entity> entityList) {
        return false;
    }

    @Override
    public void move(int status) {
        switch (status) {
            case UP: {
                setY(getY() - step);
                break;
            }
            case RIGHT: {
                setX(getX() + step);
                break;
            }
            case LEFT: {
                setX(getX() - step);
                break;
            }
            case DOWN: {
                setY(getY() + step);
                break;
            }
        }
    }

    public void checkToMap() {
        int i = 0;
        while (i < entities.size()) {
            if (!checkToUnmove(this, entities.get(i))){
                this.stop = true;
                break;
            }
            stop = false;
            i++;
        }

    }
}
