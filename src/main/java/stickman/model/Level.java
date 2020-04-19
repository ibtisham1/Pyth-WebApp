
package stickman.model;

import java.util.List;

public interface Level {
    List<Entity> getEntities();
    double getHeight();
    double getWidth();
    void tick(boolean lefty, boolean righty, boolean jumpy, double tickcount);
    double getFloorHeight();
    double getHeroX();
    void jump(Entity e);
    void moveLeft(Entity e);
    void moveRight(Entity e);
    boolean stopMoving();
}

