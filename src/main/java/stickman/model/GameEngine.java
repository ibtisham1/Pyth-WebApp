package stickman.model;

public interface GameEngine {
    Level getCurrentLevel();
    void startLevel();
    double getCloud_speed();
    boolean jump(boolean ans);
    boolean moveLeft(boolean ans);
    boolean moveRight(boolean ans);
    boolean stopMoving();
    void tick();
}
