package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {
    
    private PlayerControlSystem playerControlSystem;
    private GameData gameData;
    private World world;
    private Player player;
    
    @BeforeEach
    void setUp() {
        playerControlSystem = new PlayerControlSystem();
        gameData = new GameData();
        world = new World();
        player = createTestPlayer();
        world.addEntity(player);
    }
    
    @Test
    @DisplayName("Player should rotate left when LEFT key is pressed")
    void testPlayerRotateLeft() {
        double initialRotation = 0.0;
        player.setRotation(initialRotation);
        gameData.getKeys().setKey(GameKeys.LEFT, true);
        
        playerControlSystem.process(gameData, world);
        
        assertEquals(initialRotation - 2, player.getRotation(), 0.001);
    }
    
    @Test
    @DisplayName("Player should rotate right when RIGHT key is pressed")
    void testPlayerRotateRight() {
        double initialRotation = 0.0;
        player.setRotation(initialRotation);
        gameData.getKeys().setKey(GameKeys.RIGHT, true);
        
        playerControlSystem.process(gameData, world);
        
        assertEquals(initialRotation + 2, player.getRotation(), 0.001);
    }
    
    @Test
    @DisplayName("Player should move forward when UP key is pressed")
    void testPlayerMoveForward() {
        double initialX = 100.0;
        double initialY = 100.0;
        double rotation = 0.0; // Facing right
        
        player.setX(100);
        player.setY(100);
        player.setRotation(0.0);
        gameData.getKeys().setKey(GameKeys.UP, true);
        
        playerControlSystem.process(gameData, world);
        
        double expectedX = initialX + Math.cos(Math.toRadians(rotation));
        double expectedY = initialY + Math.sin(Math.toRadians(rotation));
        
        assertEquals(expectedX, player.getX(), 0.001);
        assertEquals(expectedY, player.getY(), 0.001);
    }
    
    @Test
    @DisplayName("Player movement should be based on rotation direction")
    void testPlayerMovementDirection() {
        // Given - Player pointing north (270 degrees or -90 degrees)
        double initialX = 100.0;
        double initialY = 100.0;
        double rotation = -90.0;
        
        player.setX(initialX);
        player.setY(initialY);
        player.setRotation(rotation);
        gameData.getKeys().setKey(GameKeys.UP, true);
        
        playerControlSystem.process(gameData, world);
        
        double expectedX = initialX + Math.cos(Math.toRadians(rotation));
        double expectedY = initialY + Math.sin(Math.toRadians(rotation));
        
        assertEquals(expectedX, player.getX(), 0.001);
        assertEquals(expectedY, player.getY(), 0.001);
        
        // assert: Y should decrease (moving up)
        assertTrue(player.getY() < initialY);
    }
    
    @Test
    @DisplayName("Player should not move when no keys are pressed")
    void testPlayerNoMovement() {
        double initialX = 100.0;
        double initialY = 100.0;
        double initialRotation = 45.0;
        
        player.setX(initialX);
        player.setY(initialY);
        player.setRotation(initialRotation);
        
        // No keys pressed
        
        playerControlSystem.process(gameData, world);
        
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());
        assertEquals(initialRotation, player.getRotation());
    }
        
    private Player createTestPlayer() {
        Player player = new Player();
        player.setX(400);
        player.setY(300);
        player.setRotation(0);
        player.setRadius(8);
        return player;
    }
}
