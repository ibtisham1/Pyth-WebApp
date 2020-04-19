package stickman.model;

public class FinishLineCollisionHandler implements CollisionHandler {
    @Override
    public boolean checkCollision(Entity hero, Entity finishLine) {
        if(hero.getXPos()==finishLine.getXPos()){
            return false;
        }

        if((Math.abs(hero.getXPos()-finishLine.getXPos())!=0) &&(finishLine.getImagePath().equals("finishLine.png")) &&(hero.getImagePath().equals("ch_stand2.png"))){
            return (hero.getXPos() < (finishLine.getXPos()+finishLine.getWidth()/4)) &&
                    ((hero.getXPos()+hero.getWidth()/4) > finishLine.getXPos());

        }
        return false;
    }

    @Override
    public void handleCollision(Entity hero, Entity enemy) {

    }
}
