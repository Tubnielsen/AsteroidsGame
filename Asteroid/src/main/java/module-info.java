import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    exports dk.sdu.cbse.asteroid;

    requires Common;
    requires javafx.graphics;

    provides IEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidControlSystem;
    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides dk.sdu.cbse.common.data.IAsteroidSplitter with dk.sdu.cbse.asteroid.AsteroidPlugin;
}