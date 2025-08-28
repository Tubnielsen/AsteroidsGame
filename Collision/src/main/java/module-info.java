import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    exports dk.sdu.cbse.collision;

    requires Common;

    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionSystem;
}

