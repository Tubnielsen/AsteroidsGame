package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.IAsteroidSplitter;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AsteroidPlugin implements IGamePluginService, IAsteroidSplitter {
    
    private static final int LARGE_SPRITE_WIDTH = 39;
    private static final int LARGE_SPRITE_HEIGHT = 37;
    private static final int MEDIUM_SPRITE_WIDTH = 37;
    private static final int MEDIUM_SPRITE_HEIGHT = 36;
    private static final int SMALL_SPRITE_WIDTH = 26;
    private static final int SMALL_SPRITE_HEIGHT = 28;
    private static final int SCALE = 1;

    private Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        // Create and add asteroids to the world
        for (int i = 0; i < 5; i++) {
            Entity asteroid = createAsteroid(3, gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove asteroids from the world
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }


    private Asteroid createAsteroid(int size, GameData gameData) {
        Asteroid asteroid = new Asteroid();
        Image spriteImage;
        ImageView asteroidView;

        // Set up the asteroid depending on its size (1=small, 2=medium, 3=large)
        switch(size) {
            case 1:
                spriteImage = new Image(getClass().getResource("/Asteroid_SMALL.png").toExternalForm());
                asteroid.setRadius(SMALL_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(1);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, SMALL_SPRITE_WIDTH, SMALL_SPRITE_HEIGHT));
                asteroidView.setFitWidth(SMALL_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(SMALL_SPRITE_HEIGHT * SCALE);
                break;
            case 2:
                spriteImage = new Image(getClass().getResource("/Asteroid_MEDIUM.png").toExternalForm());
                asteroid.setRadius(MEDIUM_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(2);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, MEDIUM_SPRITE_WIDTH, MEDIUM_SPRITE_HEIGHT));
                asteroidView.setFitWidth(MEDIUM_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(MEDIUM_SPRITE_HEIGHT * SCALE);
                break;
            case 3:
                spriteImage = new Image(getClass().getResource("/Asteroid_LARGE.png").toExternalForm());
                asteroid.setRadius(LARGE_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(3);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, SMALL_SPRITE_WIDTH, SMALL_SPRITE_HEIGHT));
                asteroidView.setFitWidth(SMALL_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(SMALL_SPRITE_HEIGHT * SCALE);
                break;
            default:
                spriteImage = new Image(getClass().getResource("/Asteroid_LARGE.png").toExternalForm());
                asteroid.setRadius(LARGE_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(3);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, SMALL_SPRITE_WIDTH, SMALL_SPRITE_HEIGHT));
                asteroidView.setFitWidth(SMALL_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(SMALL_SPRITE_HEIGHT * SCALE);
                break;
        }

        // Random position on screen
        double x = random.nextDouble() * gameData.getDisplayWidth();
        double y = random.nextDouble() * gameData.getDisplayHeight();
        asteroidView.setX(x);
        asteroidView.setY(y);
        asteroid.setX(x);
        asteroid.setY(y);

        // Random direction
        asteroid.setRotation(random.nextDouble() * 100 * Math.PI);

        asteroid.setView(asteroidView);

        return asteroid;
    }

    private Asteroid createAsteroidAtPosition(int size, double x, double y, GameData gameData) {
        Asteroid asteroid = new Asteroid();
        Image spriteImage;
        ImageView asteroidView;

        // Set up the asteroid depending on its size (1=small, 2=medium, 3=large)
        switch(size) {
            case 1:
                spriteImage = new Image(getClass().getResource("/Asteroid_SMALL.png").toExternalForm());
                asteroid.setRadius(SMALL_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(1);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, SMALL_SPRITE_WIDTH, SMALL_SPRITE_HEIGHT));
                asteroidView.setFitWidth(SMALL_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(SMALL_SPRITE_HEIGHT * SCALE);
                break;
            case 2:
                spriteImage = new Image(getClass().getResource("/Asteroid_MEDIUM.png").toExternalForm());
                asteroid.setRadius(MEDIUM_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(2);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, MEDIUM_SPRITE_WIDTH, MEDIUM_SPRITE_HEIGHT));
                asteroidView.setFitWidth(MEDIUM_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(MEDIUM_SPRITE_HEIGHT * SCALE);
                break;
            case 3:
                spriteImage = new Image(getClass().getResource("/Asteroid_LARGE.png").toExternalForm());
                asteroid.setRadius(LARGE_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(3);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, SMALL_SPRITE_WIDTH, SMALL_SPRITE_HEIGHT));
                asteroidView.setFitWidth(SMALL_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(SMALL_SPRITE_HEIGHT * SCALE);
                break;
            default:
                spriteImage = new Image(getClass().getResource("/Asteroid_LARGE.png").toExternalForm());
                asteroid.setRadius(LARGE_SPRITE_WIDTH / 2 * SCALE);
                asteroid.setSize(3);
                asteroidView = new ImageView(spriteImage);
                asteroidView.setViewport(new Rectangle2D(0, 0, SMALL_SPRITE_WIDTH, SMALL_SPRITE_HEIGHT));
                asteroidView.setFitWidth(SMALL_SPRITE_WIDTH * SCALE);
                asteroidView.setFitHeight(SMALL_SPRITE_HEIGHT * SCALE);
                break;
        }

        asteroidView.setX(x);
        asteroidView.setY(y);
        asteroid.setX(x);
        asteroid.setY(y);

        // Random direction
        asteroid.setRotation(random.nextDouble() * 100 * Math.PI);

        asteroid.setView(asteroidView);

        return asteroid;
    }

    @Override
    public List<Asteroid> splitAsteroid(Asteroid asteroid, GameData gameData) {
        List<Asteroid> fragments = new ArrayList<>();

        if (asteroid.getSize() > 1) { // Only split if the new size is valid
            int newSize = (int) asteroid.getSize() - 1;
            // Create two smaller asteroids at the same position
            Asteroid fragment1 = createAsteroidAtPosition(newSize, asteroid.getX(), asteroid.getY(), gameData);
            Asteroid fragment2 = createAsteroidAtPosition(newSize, asteroid.getX(), asteroid.getY(), gameData);
            fragments.add(fragment1);
            fragments.add(fragment2);
        }

        return fragments;
    }
}