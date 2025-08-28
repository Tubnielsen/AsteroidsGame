package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.data.Bullet;
import dk.sdu.cbse.common.data.Enemy;
import dk.sdu.cbse.common.data.Player;
import dk.sdu.cbse.common.data.Asteroid;

public class CollisionSystem implements IPostEntityProcessingService {
    
    public CollisionSystem() {
    }

    @Override
    public void process(GameData gameData, World world) {
        
        // Check for collisions between entities
        for (Entity entityA : world.getEntities()) {
            for (Entity entityB : world.getEntities()) {
                
                // Edge case: don't check collision with itself
                if (entityA.getID() == entityB.getID()) {
                    continue; 
                }

                // Check if entities are colliding (using pythagorean theorem) and handle it if they are
                if (checkCollision(entityA, entityB)) handleCollision(entityA, entityB, world);
            }
        }
    }


    private boolean checkCollision(Entity entityA, Entity entityB) {
        // Calculate the distance between the two entities from their middle points
        double x = entityA.getX() - entityB.getX();
        double y = entityA.getY() - entityB.getY();
        double distance = Math.sqrt(x * x + y * y);

        // Calculate the allowed distance for a collision to occur
        float collisionDistance = entityA.getRadius() + entityB.getRadius();

        // Check if the distance is less than the sum of the allowed distance for collision
        return distance < collisionDistance;
    }

    private void handleCollision(Entity entityA, Entity entityB, World world) {
        // If collision between a bullet and asteorid. remove bullet and split asteroid
        if (entityA instanceof Bullet && entityB instanceof Asteroid) {
        
        // If collision between bullet and enemy ship, remove bullet and damage enemy ship
        } else if (entityA instanceof Bullet && entityB instanceof Enemy) {

        }
        // If collision between bullet and player ship, remove bullet and damage player ship
        else if (entityA instanceof Bullet && entityB instanceof Player) {
        
        }
        // If collision between player ship and enemy ship, both ships gets destroyed
        else if (entityA instanceof Player && entityB instanceof Enemy) {
        
        }
        // If collision between player ship and asteroid, player ship gets damaged and asteroid is destroyed
        else if (entityA instanceof Player && entityB instanceof Asteroid) {
        
        }
        // If collision between enemy ship and asteroid, enemy ship gets damaged and asteroid is destroyed
        else if (entityA instanceof Enemy && entityB instanceof Asteroid) {
        
        }
    }
}