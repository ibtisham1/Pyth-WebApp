package stickman.model;

public class FastMovingEnemy implements EnemyMovementStrategy {
    public void movement(Entity en){
        en.enemyLeft();

    }
}
