// Service provided interface, used for communication between the bullet service and the game engine.

package dk.sdu.cbse.common.util;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
