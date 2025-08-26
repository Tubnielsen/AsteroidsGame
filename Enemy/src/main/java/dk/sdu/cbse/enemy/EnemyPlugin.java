package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyPlugin {
    private Enemy enemy;
    private static final int SPRITE_WIDTH = 64;
    private static final int SPRITE_HEIGHT = 64;
    private static final double SCALE = 2;

    public EnemyPlugin() {
    }

    public void start(GameData gameData, World world) {
        // Add enemy entity to the game world
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    public void stop(World world) {
        // Remove enemy entity from the game world
        world.removeEntity(enemy);
    }

    private Enemy createEnemy(GameData gameData) {
        Enemy ship = new Enemy();
        
        // Set up the enemy's sprite
        Image spriteImage = new Image(getClass().getResource("/Enemy.png").toExternalForm());
        ImageView enemyView = new ImageView(spriteImage);
        enemyView.setViewport(new Rectangle2D(0, 0, SPRITE_WIDTH, SPRITE_HEIGHT));
        enemyView.setFitWidth(SPRITE_WIDTH * SCALE);
        enemyView.setFitHeight(SPRITE_HEIGHT * SCALE);
        enemyView.setX(gameData.getDisplayWidth() / 2);
        enemyView.setY(gameData.getDisplayHeight() / 2);
        
        // Configure the enemy entity
        ship.setRadius(8);
        ship.setX(gameData.getDisplayWidth() * 0.8);
        ship.setY(gameData.getDisplayHeight() / 2);
        ship.setRotation(3.1415f / 2);
        ship.setView(enemyView);

        return ship;
    }

}