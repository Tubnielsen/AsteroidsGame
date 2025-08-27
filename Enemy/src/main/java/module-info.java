import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    exports dk.sdu.cbse.enemy;

    requires Common;
    requires javafx.graphics;

    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemy.EnemyControlSystem;
    
    uses dk.sdu.cbse.common.util.BulletSPI;
}
