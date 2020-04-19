package stickman.model;

public interface CollisionHandler {
    public boolean checkCollision(Entity hero, Entity enemy);
    public void handleCollision(Entity hero, Entity enemy);
}
