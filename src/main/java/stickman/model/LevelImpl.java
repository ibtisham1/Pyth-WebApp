package stickman.model;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {
    private double anotherX;
    private double anotherY;
    private double lheight;
    private double lwidth;
    private double fheight;
    private double cloud_speed;
    private double finishingLiney=300;
    private int lifeCount=3;
    private double time=0;
    private int timeDisplayer=0;
    private final List<Entity> lis= new ArrayList<Entity>();

    public LevelImpl(double another_x, double anotherY, double lheight, double lwidth, double fheight, double cloud_speed, JSONObject ene, JSONObject plat){
        this.anotherX =another_x;
        this.anotherY = anotherY;
        this.lheight=lheight;
        this.lwidth=lwidth;
        this.fheight=fheight;
        this.cloud_speed= cloud_speed;
        Entity sm= new EntityImpl("ch_stand2.png", another_x, anotherY, null);
        Entity cl_4 =new EntityImpl("GameOver.jpg",300,300, null);
        Entity cl_5= new EntityImpl("finishLine.png",lwidth,finishingLiney, null);
        Entity cl_6 = new EntityImpl("Win.png",lwidth,200, null);
        try {
            for (int i = 1; i <= ene.size(); i++) {
                JSONObject ene_2 = (JSONObject) ene.get("enemy_" + i);
                String image = (String) ene_2.get("image");
                double pos_x = (double) ene_2.get("x");
                double pos_y = (double) ene_2.get("y");
                String movement= (String)ene_2.get("strategy");
                EnemyMovementStrategy strat;
                if(movement.equals("move")){
                    strat=new FastMovingEnemy();
                }
                else{
                    strat= new NoMovementStrategy();
                }
                Entity r = new EntityImpl(image, pos_x, pos_y, strat);
                lis.add(r);

            }
            for (int i = 1; i <= plat.size(); i++) {

                JSONObject plat_2 = (JSONObject) plat.get("platform_" + i);
                String image = (String) plat_2.get("image");
                double pos_x = (double) plat_2.get("x");
                double pos_y = (double) plat_2.get("y");

                Entity r = new EntityImpl(image, pos_x, pos_y, null);
                lis.add(r);

            }

        }catch (NullPointerException e){

        }
        lis.add(sm);
        lis.add(cl_4);
        lis.add(cl_5);
        lis.add(cl_6);
        System.out.println("You have 3 lives");
    }
    @Override
    public List<Entity> getEntities() {
        return lis;
    }

    @Override
    public double getHeight() {
        return lheight;
    }

    @Override
    public double getWidth() {
        return lwidth;
    }

    @Override
    public void tick(boolean lefty, boolean righty, boolean jumpy, double tickcount) {
        time=tickcount;
        for (Entity e : getEntities()){
           for(Entity f: getEntities()) {

               CollisionHandler platformCol = new PlatformCollisionHandler();
               if(platformCol.checkCollision(e,f)){
                   platformCol.handleCollision(e,f);
                   jumpy=true;
               }
              CollisionHandler finishLineCol = new FinishLineCollisionHandler();
               if(finishLineCol.checkCollision(e,f)){
                   timeDisplayer++;
                   if(timeDisplayer==1){
                       System.out.println("It took you "+(time/100)+" seconds to finish the game");
                   }
                   finishLineCol.handleCollision(e,f);
                   for(Entity t:  getEntities()){
                       t.setwinner();
                  }
               }
               CollisionHandler enemyCol= new EnemyCollsionHandler();
               if(enemyCol.checkCollision(e,f)){
                   System.out.println("Enemy killed you");
                   lifeCount--;
                   if(lifeOver()){
                       System.out.println("Game Over");
                       for(Entity g: getEntities()) {
                           if (g.getImagePath().equals("GameOver.jpg")) {
                               g.set_x(0);
                               g.set_y(100);
                               g.setgameover();
                           }
                       }
                   }
                   System.out.println("You have "+lifeCount+ " lives left");
                   enemyCol.handleCollision(e,f);
               }
           }
           if(e.getImagePath().startsWith("slime")){
               e.move(e);
           }
           if(e.getImagePath().equals("ch_stand2.png")){
               if (jumpy==true){
                   jump(e);
               }
               if(jumpy==false){
                   noJump(jumpy, e);
               }
               if (lefty==true){
                   moveLeft(e);
               }
               if (righty==true){
                   moveRight(e);
               }
           }
        }
    }

    @Override
    public double getFloorHeight() {
        return fheight;
    }

    @Override
    public double getHeroX() {
       for(Entity e: getEntities()){
           if(e.getImagePath().equals("ch_stand2.png")){
               return e.getXPos();
           }
       }
       return anotherX;
    }

    @Override
    public void jump(Entity e) {
        e.setup();

    }
    public void noJump(boolean ans, Entity e){
        if(ans==false) {
            e.setdown();
        }
    }

    @Override
    public void moveLeft(Entity e) {
        e.setleft();
    }

    @Override
    public void moveRight(Entity e) {
        e.setright();
    }

    @Override
    public boolean stopMoving() {
        return true;
    }

    public boolean lifeOver(){
        if (lifeCount==0){
            return true;
        }
        return false;
    }


}

