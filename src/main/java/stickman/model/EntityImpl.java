package stickman.model;

import org.json.simple.JSONObject;

public class EntityImpl implements Entity {
    private double another_x;
    private double another_y;
    private double GameOver_x=5;
    private double GameOver_y=5;
    private double winner_x=5;
    private double winner_y=5;
    private EnemyMovementStrategy strategy;


    private  String img;
    public EntityImpl(String image, double another_x, double another_y, EnemyMovementStrategy strategy){
        this.img=image;
        this.another_x=another_x;
        this.another_y=another_y;
        this.strategy=strategy;
    }

    @Override
    public String getImagePath() {
       return img;
    }

    @Override
    public  double getXPos() {
        return another_x;
    }

    @Override
    public double getYPos() {
        return another_y;
    }
    @Override
    public void enemyLeft(){
        another_x-=0.5;
    }
    @Override
    public void setleft(){
        another_x-=1;
    }
    @Override
    public void setright(){
        another_x+=1;
    }
    @Override
    public void setup(){
        another_y-=1;
    }
    @Override
    public void setdown(){
        another_y=300;
    }

    @Override
    public double getHeight() {
        if(this.img.startsWith("slime")){
            return 50;
        }
        if(this.img.equals("GameOver.jpg")){
            return GameOver_y;
        }
        if(this.img.equals("Win.png")){
            return winner_x ;
        }
      return 100.0;
    }

    @Override
    public double getWidth() {
        if(this.img.startsWith("slime")){
            return 50;
        }
        if(this.img.equals("GameOver.jpg")){
            return GameOver_x;
        }
        if(this.img.equals("Win.png")){
            return winner_y;
        }
        return 200.0;
    }
    @Override
    public void set_y(double val){
        another_y=val;
    }
    @Override
    public void set_x(double val){
        another_x=val;
    }
    @Override
    public void setgameover(){
        GameOver_x=400;
        GameOver_y=400;

    }
    public void setwinner(){
        winner_x=400;
        winner_y=400;

    }
    public void move(Entity en){
        strategy.movement(en);
    }

    @Override
    public Layer getLayer() {

        return Layer.BACKGROUND;
    }
}
