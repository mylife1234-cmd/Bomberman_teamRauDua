package uet.oop.bomberman.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.EntityAnimate;

public abstract class BommerCharacter extends EntityAnimate {


    public BommerCharacter(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void render() {

    }

    @Override
    public void update() {

    }

}
