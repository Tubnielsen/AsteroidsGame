package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.Bullet;
import dk.sdu.cbse.common.data.Entity;

public class BulletPlugin implements IGamePluginService {
    private Bullet bullet;

    @Override
    public void start(GameData gameData, World world) {
    
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Bullet.class)) {
            world.removeEntity(e);
        }
    }
}
