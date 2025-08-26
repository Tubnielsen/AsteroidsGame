package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;

public class BulletPlugin {
    private Bullet bullet;

    //@Override
    public void start(GameData gameData, World world) {
    
    }

    //@Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Bullet.class)) {
            world.removeEntity(e);
        }
    }
}
