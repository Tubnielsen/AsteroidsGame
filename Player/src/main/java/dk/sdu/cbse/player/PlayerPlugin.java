package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerPlugin implements IGamePluginService {
    private Player player;
    private static final int PLAYER_SPRITE_WIDTH = 64;
    private static final int PLAYER_SPRITE_HEIGHT = 64;
    private static final double SCALE = 2;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add player entity to the game world
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove player entity from the game world
        world.removeEntity(player);
    }

    private Player createPlayer(GameData gameData) {
        Player ship = new Player();
        
        // Set up the player's sprite
        Image spriteImage = new Image(getClass().getResource("/Player.png").toExternalForm());
        ImageView playerView = new ImageView(spriteImage);
        playerView.setViewport(new Rectangle2D(0, 0, PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT));
        playerView.setFitWidth(PLAYER_SPRITE_WIDTH * SCALE);
        playerView.setFitHeight(PLAYER_SPRITE_HEIGHT * SCALE);
        playerView.setX(gameData.getDisplayWidth() / 2);
        playerView.setY(gameData.getDisplayHeight() / 2);
        
        // Configure the player entity
        ship.setRadius(8);
        ship.setX(gameData.getDisplayWidth() / 2);
        ship.setY(gameData.getDisplayHeight() / 2);
        ship.setRotation(3.1415f / 2);
        ship.setView(playerView);

        return ship;
    }

}