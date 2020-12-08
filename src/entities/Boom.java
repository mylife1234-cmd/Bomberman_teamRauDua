package entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Boom  extends Entity{

    private boolean explored;
    private List<Flame> flameList;
    int count = 0;


    public Boom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        explored = false;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setFlameList(List<Flame> flameList) {
        this.flameList = flameList;
    }

    public List<Flame> getFlameList() {
        return flameList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void update(KeyEvent event) {

    }

    @Override
    public void update() {
        if (count > 0) {
            count--;
        }
        else setExplored(false);
    }

    @Override
    public boolean isMove(int status, List<Entity> entityList) {
        return false;
    }

    @Override
    public void move(int status) {

    }
}
