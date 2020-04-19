package stickman.model;

public class EnemyCollsionHandler implements CollisionHandler {

    public boolean checkCollision(Entity hero, Entity enemy){
        if(hero.getXPos()==enemy.getXPos()){
            return false;
        }
        if((enemy.getImagePath().equals("finishLine.png"))) {
            return false;
        }
        if((Math.abs(hero.getXPos()-enemy.getXPos())!=0) &&(hero.getImagePath().equals("ch_stand2.png")) &&(enemy.getImagePath().startsWith("slime"))){
            return (hero.getXPos() < (enemy.getXPos() + enemy.getWidth()/4)) &&
                    ((hero.getXPos() + hero.getWidth()/4) > enemy.getXPos()) &&
                    (hero.getYPos() < (enemy.getYPos() + enemy.getHeight())) &&
                    ((hero.getYPos() + hero.getHeight()) > enemy.getYPos());

        }
        return false;

    }
    public void handleCollision(Entity hero, Entity enemy){
        if((hero.getImagePath().equals("ch_stand2.png"))&&(!(enemy.equals("finishLine")))) {
                hero.set_x(0);
        }
    }
}
