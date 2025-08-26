package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.GameKeys;

public class PlayerControlSystem {
    
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            
            // Handle key inputs for player control
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 0.1f);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 0.1f);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double dx = Math.cos(Math.toRadians(player.getRotation()));
                double dy = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + dx);
                player.setY(player.getY() + dy);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                /*getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(player, gameData));}
                );*/
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

        /*private Collection<? extends BulletSPI> getBulletSPIs() {
            return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        } */
    }
}
