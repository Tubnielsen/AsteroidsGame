import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Bullet {
    exports dk.sdu.cbse.bullet;

    requires Common;
    requires javafx.graphics;

    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletControlSystem;
}

