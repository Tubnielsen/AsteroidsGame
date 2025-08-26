package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class EnemyControlSystem {
    
    private static final double SHOOT_INTERVAL = 2.0; // Shoot every 2 seconds
    private static final double MOVE_CHANGE_INTERVAL = 3.0; // Do not move before 3 seconds
    
    private double shootTimer = 0;
    private double moveTimer = 0;

    private Random random = new Random();
    
    public void process(GameData gameData, World world) {
        // Process enemy entity
        for (Entity enemy : world.getEntities(Enemy.class)) {
            // Update timers
            shootTimer += 1.0/60.0;
            moveTimer += 1.0/60.0;
            
            // Random movement behavior
            handleMovement(enemy);
            
            // Keep enemy on screen with wrapping
            if (enemy.getX() < -32) {
                enemy.setX(gameData.getDisplayWidth() + 32);
            } else if (enemy.getX() > gameData.getDisplayWidth() + 32) {
                enemy.setX(-32);
            }
            
            if (enemy.getY() < -32) {
                enemy.setY(gameData.getDisplayHeight() + 32);
            } else if (enemy.getY() > gameData.getDisplayHeight() + 32) {
                enemy.setY(-32);
            }
            
            // Shooting behavior
            handleShooting(enemy, gameData);
        }
    }
        
    private void handleMovement(Entity enemy) {
        // Change direction and move at random times but not before set interval.
        if (moveTimer >= MOVE_CHANGE_INTERVAL || random.nextDouble() < 0.008) {
            // Generate random direction
            double angle = random.nextDouble() * 2;
            enemy.setRotation(Math.toRadians(angle));
            double dx = Math.cos(Math.toRadians(enemy.getRotation()));
            double dy = Math.sin(Math.toRadians(enemy.getRotation()));
            // Move the enemy
            enemy.setX(enemy.getX() + dx);
            enemy.setY(enemy.getY() + dy);
            moveTimer = 0;;
        }
    }
    
    private void handleShooting(Entity enemy, GameData gameData) {
        // Shoot at specific intervals.
        if (shootTimer >= SHOOT_INTERVAL) {
            // Create and add bullet to world
            /*getBulletSPIs().stream().findFirst().ifPresent(
                spi -> world.addEntity(spi.createBullet(enemy, gameData))
            );*/
            shootTimer = 0;
        }
    }
}