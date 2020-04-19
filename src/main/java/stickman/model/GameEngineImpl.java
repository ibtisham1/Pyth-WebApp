package stickman.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class GameEngineImpl implements GameEngine{

    private double another_x;
    private double another_y;
    private double cloud_speed;
    private double lheight;
    private double lwidth;
    private double fheight;
    private double tickcount=0;
    private boolean lefty=false;
    private boolean righty=false;
    private boolean jumpy=false;
    private final List<Level> ls= new ArrayList<>();
    private JSONObject ene;
    private JSONObject plat;

    public GameEngineImpl(String s){

        try {
            FileReader f = new FileReader(s);
            JSONParser p= new JSONParser();
            Object o_file=p.parse(f);
            JSONObject arra= (JSONObject) o_file;
            JSONObject stickpos =(JSONObject) arra.get("stickmanPos");
            another_x=(Double)stickpos.get("x");
            JSONObject enemy_list=(JSONObject)arra.get("enemies");
            ene=enemy_list;
            JSONObject platform_list=(JSONObject)arra.get("platforms");
            plat=platform_list;
            String stickmansize =(String)arra.get("stickmanSize");
            if (stickmansize.equalsIgnoreCase("tiny")){
                another_y=350.0;
            }
            else if (stickmansize.equalsIgnoreCase("normal")){
                another_y= 300.0;
            }
            else if (stickmansize.equalsIgnoreCase("large")){
                another_y= 250.0;
            }
            else if (stickmansize.equalsIgnoreCase("giant")){
                another_y= 150.0;
            }
            else{
                another_y=300.0;
            }
            cloud_speed =(Double)arra.get("cloudVelocity");
            lheight=(Double) arra.get("levelHeight");
            lwidth =(Double) arra.get("levelWidth");
            fheight=(Double) arra.get("floorHeight");

        }catch (IOException | ParseException | NullPointerException e) {
            System.out.println("no file");
        }
        Level l=new LevelImpl(another_x,another_y,lheight,lwidth,fheight,cloud_speed, ene, plat);
        ls.add(l);
    }

    @Override
    public double getCloud_speed(){
        return cloud_speed;
    }

    @Override
    public Level getCurrentLevel() {
        return ls.get(0);
    }

    @Override
    public void startLevel() {
    }

    @Override
    public boolean jump(boolean ans) {
        jumpy=ans;
        return ans;
    }

    @Override
    public boolean moveLeft(boolean ans) {
       lefty=ans;
       return ans;
    }

    @Override
    public boolean moveRight(boolean ans) {
        righty=ans;
        return ans;
    }

    @Override
    public boolean stopMoving() {
        return true;
    }

    @Override
    public void tick() {
        tickcount++;
        for(Level l2:ls){
            l2.tick(moveLeft(lefty),moveRight(righty),jump(jumpy), tickcount);
        }

    }
}
