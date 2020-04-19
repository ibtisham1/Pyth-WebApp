package stickman.model;

public interface Entity{
    String getImagePath();
    double getXPos();
    double getYPos();
    double getHeight();
    double getWidth();
    void setright();
    void setleft();
    void setup();
    void setgameover();
    void setwinner();
    void enemyLeft();
    void setdown();
    void set_y(double val);
    void set_x(double val);
    void move(Entity en);
    Layer getLayer();
    enum Layer{
        BACKGROUND, FOREGROUND, EFFECT
    }

}
