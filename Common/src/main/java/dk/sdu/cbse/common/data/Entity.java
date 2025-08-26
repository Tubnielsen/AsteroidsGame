package dk.sdu.cbse.common.data;

import java.util.UUID;
import javafx.scene.image.ImageView;
import java.io.Serializable;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private float radius; // Used for collision detection
    private double rotation; // Used for direction
    private ImageView entityImage;
    private double x;
    private double y;
    
    public String getID() {
        return ID.toString();
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setView(ImageView entityImage) {
        this.entityImage = entityImage;
    }

    public ImageView getView() {
        return entityImage;
    }
}
