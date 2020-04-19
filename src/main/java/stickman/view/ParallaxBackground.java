package stickman.view;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import stickman.model.GameEngine;

public class ParallaxBackground implements BackgroundDrawer {

    private double width;
    private Image[] images;
    private ImageView[] imageViews;
    private double[] parallaxEffect;

    @Override
    public void draw(GameEngine model, Pane pane) {
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

        this.width = pane.getWidth();
        double height = pane.getHeight();
        double floorHeight = model.getCurrentLevel().getFloorHeight();

        this.images = new Image[6];

        images[0] = new Image("landscape_0000_1_trees.png");
        images[1] = new Image("landscape_0001_2_trees.png");
        images[2] = new Image("landscape_0002_3_trees.png");
        images[3] = new Image("landscape_0003_4_mountain.png");
        images[4] = new Image("landscape_0004_5_clouds.png");
        images[5] = new Image("landscape_0005_6_background.png");

        this.parallaxEffect = new double[6];

        parallaxEffect[0] = 0.5;
        parallaxEffect[1] = 0.4;
        parallaxEffect[2] = 0.2;
        parallaxEffect[3] = 0.05;
        parallaxEffect[4] = 0.05;
        parallaxEffect[5] = 0.0;

        this.imageViews = new ImageView[6];

        for (int i = 0;i < 6; i++) {
            imageViews[i] = new ImageView(images[i]);
            double rawHeight = images[i].getHeight();
            double rawWidth = images[i].getWidth() / 2; // images are all double stitched

            imageViews[i].setViewOrder(1001.0 + i);
            imageViews[i].setFitHeight(height);
            imageViews[i].setFitWidth(width);
            imageViews[i].setX(0);
            imageViews[i].setY(0);
            imageViews[i].setViewport(new Rectangle2D(0, 0, rawWidth, rawHeight));

            pane.getChildren().add(imageViews[i]);
        }

        Rectangle floor = new Rectangle(0, floorHeight, width, height - floorHeight);
        floor.setFill(Paint.valueOf("#1d2b38"));
        floor.setViewOrder(1000.0);

        pane.getChildren().addAll(floor,cloud,cloud_2);
    }

    @Override
    public void update(double xViewportOffset) {


        for (int i = 0;i < 6; i++) {
            double rawHeight = images[i].getHeight();
            double rawWidth = images[i].getWidth() / 2; // images are all double stitched
            double widthScale = rawWidth / width;
            double translation = (xViewportOffset * widthScale * parallaxEffect[i]) % rawWidth;
            imageViews[i].setViewport(new Rectangle2D(translation, 0, rawWidth, rawHeight));
        }
    }
}