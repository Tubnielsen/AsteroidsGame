import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Collision {
    exports dk.sdu.cbse.collision;

    requires Common;

    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionSystem;
}

