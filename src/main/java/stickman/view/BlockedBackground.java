package stickman.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import stickman.model.GameEngine;
import javafx.scene.image.*;


import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.geometry.Bounds;
import stickman.model.GameEngineImpl;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BlockedBackground implements BackgroundDrawer {
    private Rectangle sky;
    private Rectangle floor;
    private Pane pane;
    private GameEngine model;


    @Override
    public void draw(GameEngine model, Pane pane) {
        ///I draw my
        this.model=model;

        ImageView cloud = new ImageView();
        Image c = new Image("cloud_1.png");
        cloud.relocate(50.0,20.0);
        cloud.setFitHeight(30.0);
        cloud.setFitWidth(100.0);
        cloud.setImage(c);

        ImageView cloud_2 = new ImageView();
        Image c2 = new Image("cloud_1.png");
        cloud_2.relocate(0,100);
        cloud_2.setFitHeight(30.0);
        cloud_2.setFitWidth(100.0);
        cloud_2.setImage(c2);


        Bounds bounds = pane.getBoundsInLocal();
        KeyValue onevalue = new KeyValue(cloud.layoutXProperty(), bounds.getMaxX()-cloud.getX());
        KeyFrame oneframe = new KeyFrame(Duration.seconds(model.getCloud_speed()), onevalue);
        Timeline timeline = new Timeline(oneframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Bounds bounds_2 = pane.getBoundsInLocal();
        KeyValue twovalue = new KeyValue(cloud_2.layoutXProperty(), bounds_2.getMaxX()-cloud_2.getX());
        KeyFrame twoframe = new KeyFrame(Duration.seconds(model.getCloud_speed()), twovalue);
        Timeline timeline_2 = new Timeline(twoframe);
        timeline_2.setCycleCount(Timeline.INDEFINITE);
        timeline_2.play();

        //end of my code


       // this.model = model;
        this.pane = pane;


        double width = pane.getWidth();
        double height = pane.getHeight();
        //double floorHeight = model.getCurrentLevel().getFloorHeight();
        double floorHeight = model.getCurrentLevel().getFloorHeight();

       this.sky = new Rectangle(0, 0, width, floorHeight);
        sky.setFill(Paint.valueOf("LIGHTBLUE"));
        sky.setViewOrder(1000.0);

        this.floor = new Rectangle(0, floorHeight, width, height - floorHeight);
        //this.floor = new Rectangle(0, 10, width, height - 1.1);
        floor.setFill(Paint.valueOf("GREEN"));
        floor.setViewOrder(1000.0);



        pane.getChildren().addAll(sky, floor, cloud, cloud_2);
    }

    @Override
    public void update(double xViewportOffset) {
        // do nothing since this is a static bg
    }
}
