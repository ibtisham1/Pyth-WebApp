package stickman.model;

public class PlatformCollisionHandler implements CollisionHandler {

    @Override
    public boolean checkCollision(Entity hero, Entity platform) {
        if(hero.getXPos()==platform.getXPos()){
            return false;
        }

        if((Math.abs(hero.getXPos()-platform.getXPos())!=0) &&(platform.getImagePath().startsWith("foot_tile")) &&(hero.getImagePath().equals("ch_stand2.png"))){
            return (hero.getXPos() < (platform.getXPos()+platform.getWidth()/4)) &&
                    ((hero.getXPos()+hero.getWidth()/4) > platform.getXPos());
        }
        return false;
    }

    @Override
    public void handleCollision(Entity hero, Entity enemy) {
        if(hero.getImagePath().equals("ch_stand2.png")){
            hero.set_y(200.0);
        }
    }
}
