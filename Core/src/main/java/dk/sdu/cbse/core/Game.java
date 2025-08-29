package dk.sdu.cbse.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;

import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.util.ServiceLocator;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameKeys;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();
    private final Map<Entity, ImageView> entities = new ConcurrentHashMap<>();
    private Stage primaryStage;
    private AnimationTimer gameLoop;

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;

    private final Text fpsCounter = new Text(1200, 20, "FPS: 0");
    
    // For FPS Counter
    private int frames = 0;
    private long lastFpsTime = 0;


    Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServices, List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }
    
    public void start(Stage window) throws Exception {
        this.primaryStage = window;
        
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(fpsCounter);

        fpsCounter.setStyle("-fx-font-size: 20px; -fx-fill: green;");

        Scene scene = new Scene(gameWindow);

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        // Lookup all Game Plugins using service loader
        System.out.println("GamePluginServices: " + gamePluginServices);
        for (IGamePluginService plugin : gamePluginServices) {
            plugin.start(gameData, world);
        }
        // Lookup all Entities in world object
        for (Entity entity : world.getEntities()) {
            gameWindow.getChildren().add(entity.getView());
        }
        fpsCounter.toFront();

        // Set up the stage
        window.setTitle("Asteroids");
        window.setScene(scene);
        window.setResizable(false);
        gameData.setDisplayWidth(gameData.getDisplayWidth());
        gameData.setDisplayHeight(gameData.getDisplayHeight());
        
        window.show();
    }


    public void render() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                update();
                draw();
                gameData.getKeys().update();

                // FPS Counter
                frames++;
                if (lastFpsTime == 0) {
                    lastFpsTime = now;
                }
                if (now - lastFpsTime >= 1_000_000_000) { // 1 second}
                    fpsCounter.setText("FPS: " + frames);
                    frames = 0;
                    lastFpsTime = now;
                }
            }
        };
        gameLoop.start();
    }


    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        
        for (Entity entity : entities.keySet()) {
            if (!world.getEntities().contains(entity)) {
                ImageView removedEntity = entities.get(entity);
                entities.remove(entity);
                gameWindow.getChildren().remove(removedEntity);
            }
        }

        for (Entity entity : world.getEntities()) {
            ImageView view = entities.get(entity);

            if (view == null) {
                view = entity.getView();
                if (view != null) {
                    entities.put(entity, view);
                    if (!gameWindow.getChildren().contains(view)) { // Prevent duplicate addition
                        gameWindow.getChildren().add(view);
                    }
                }
            }

            if (view != null) {
                view.setX(entity.getX());
                view.setY(entity.getY());
                view.setRotate(entity.getRotation());
            }
        }
    }

    public List<IGamePluginService> getGamePluginServices() {
        System.out.println("GamePluginServices: " + gamePluginServices);
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServices;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }
    
    private void restartGame() {
        // Ensure display dimensions are properly set
        if (primaryStage != null) {
            gameData.setDisplayWidth((int) primaryStage.getWidth());
            gameData.setDisplayHeight((int) primaryStage.getHeight());
        }
        
        // If dimensions are still invalid, set default values
        if (gameData.getDisplayWidth() <= 0) {
            gameData.setDisplayWidth(1280);
        }
        if (gameData.getDisplayHeight() <= 0) {
            gameData.setDisplayHeight(720);
        }
        
        // Clear all entities from world
        for (Entity entity : world.getEntities()) {
            world.removeEntity(entity);
        }
        
        // Clear entities from game window
        entities.clear();
        gameWindow.getChildren().clear();
        
        // Restart all game plugins
        for (IGamePluginService plugin : gamePluginServices) {
            plugin.start(gameData, world);
        }
        
        // Add all entities back to game window
        for (Entity entity : world.getEntities()) {
            if (entity.getView() != null) {
                gameWindow.getChildren().add(entity.getView());
            }
        }
        
        // Restart the game loop
        render();
    }
    
    private void exitGame() {
        // Stop the game loop if running
        if (gameLoop != null) {
            gameLoop.stop();
        }
        
        // Close the primary stage (exit application)
        if (primaryStage != null) {
            primaryStage.close();
        }
        
        // Exit the JavaFX application
        javafx.application.Platform.exit();
    }
}
