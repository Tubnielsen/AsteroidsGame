package dk.sdu.cbse.player;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.IBullet;
import dk.sdu.cbse.common.data.Player;

public class PlayerControlSystem implements IEntityProcessingService {
    private long lastFiringTime = 0;
    private static final long FIRE_COOLDOWN = 200;

    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            
            // Handle key inputs for player control
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 2);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 2);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double dx = Math.cos(Math.toRadians(player.getRotation()));
                double dy = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + dx);
                player.setY(player.getY() + dy);
            }

            long currentTime = System.currentTimeMillis();
            if (gameData.getKeys().isDown(GameKeys.SPACE) && currentTime - lastFiringTime > FIRE_COOLDOWN) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(player, gameData));}
                );
                lastFiringTime = currentTime;
            }

            // Handle screen wrapping mechanic
            if (player.getX() < 0) {
                player.setX(gameData.getDisplayWidth() - 1);
            }

            if (player.getY() < 0) {
                player.setY(gameData.getDisplayHeight() - 1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(1);
            }
        }
    }

    private Collection<? extends IBullet> getBulletSPIs() {
        return ServiceLoader.load(IBullet.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
