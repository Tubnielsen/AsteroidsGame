// Service provided interface, used for communication between the bullet service and the game engine.

package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public interface IBullet {
    Entity createBullet(Entity e, GameData gameData);
}
