// Service provided interface, used for communication between the bullet service and the game engine.

package dk.sdu.cbse.bullet;

public interface IBullet {
    Entity createBullet(Entity e, GameData gameData);
}
