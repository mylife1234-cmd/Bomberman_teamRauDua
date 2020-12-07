package entities;

import java.util.List;

public interface Moveable {

    public boolean isMove(int status, List<Entity> entityList);

    public void move(int status);

    public static boolean checkToEntity(Entity entity1, Entity entity2) {
        if (entity2 instanceof Grass) {
            return true;
        }
        if (((entity1.getX() + entity1.img.getWidth()) <= entity2.getX() || (entity1.getX() > (entity2.getX() + entity2.img.getWidth()))) &&
                ((entity1.getY() + entity1.img.getHeight()) < entity2.getY() || entity1.img.getHeight() > (entity2.getY() + entity2.img.getHeight()))) {
            return true;
        }
        return false;
    }
}
