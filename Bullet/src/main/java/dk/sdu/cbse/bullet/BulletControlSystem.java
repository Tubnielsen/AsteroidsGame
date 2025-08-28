package dk.sdu.cbse.bullet;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import dk.sdu.cbse.common.data.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.util.BulletSPI;
import dk.sdu.cbse.common.data.Bullet;

public class BulletControlSystem implements BulletSPI, IEntityProcessingService {
    
    private static final float SPEED = 6; // Speed of the bullet
    
    private static final int BULLET_SPRITE_WIDTH = 9;
    private static final int BULLET_SPRITE_HEIGHT = 27;
    private static final double SCALE = 2;

    private static final double BULLET_SPAWN_OFFSET = 32;
    private static final double PLAYER_SPRITE_X_OFFSET = 64;
    private static final double PLAYER_SPRITE_Y_OFFSET = 64;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double dx = Math.cos(Math.toRadians(bullet.getRotation()));
            double dy = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + dx * SPEED);
            bullet.setY(bullet.getY() + dy * SPEED);


            // Remove if bullet is out of bounds
            if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() ||
                bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }
        }
    }

    public Bullet createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();

        // Set up the bullet's sprite
        Image spriteImage = new Image(getClass().getResource("/bullet.png").toExternalForm());
        ImageView bulletView = new ImageView(spriteImage);
        bulletView.setViewport(new Rectangle2D(0, 0, BULLET_SPRITE_WIDTH, BULLET_SPRITE_HEIGHT));
        bulletView.setFitWidth(BULLET_SPRITE_WIDTH * SCALE);
        bulletView.setFitHeight(BULLET_SPRITE_HEIGHT * SCALE);
        bullet.setView(bulletView);

        // Set bullet position and properties
        double dx = Math.cos(Math.toRadians(shooter.getRotation()));
        double dy = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + dx + BULLET_SPAWN_OFFSET + PLAYER_SPRITE_X_OFFSET);
        bullet.setY(shooter.getY() + dy + BULLET_SPAWN_OFFSET + PLAYER_SPRITE_Y_OFFSET);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        
        return bullet;
    }
}
