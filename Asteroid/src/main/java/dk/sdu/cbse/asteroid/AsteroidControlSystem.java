package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {

    private static final float SPEED = 0.3f;

    @Override
    public void process(GameData gameData, World world) {
        
        // Process each asteroid in the world
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            // Move the asteroids
            double dx = Math.cos(Math.toRadians(asteroid.getRotation()));
            double dy = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + dx * SPEED);
            asteroid.setY(asteroid.getY() + dy * SPEED);

            // Handle screen wrapping mechanic
            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth() - 1);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight() - 1);
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(1);
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(1);
            }
        }
    }
    
}