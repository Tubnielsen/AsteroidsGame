module Core {
    requires javafx.graphics;
    requires javafx.controls;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    requires Common;
    
    opens dk.sdu.cbse.core to javafx.graphics, spring.core;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;

    exports dk.sdu.cbse.core;
}