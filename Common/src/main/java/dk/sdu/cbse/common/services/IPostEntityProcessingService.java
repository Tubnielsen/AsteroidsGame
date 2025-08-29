package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Service for post-processing operations after main entity processing.
 */
public interface IPostEntityProcessingService {

    /**
     * Performs post-processing operations after main entity processing is complete.
     * 
     * <p>Called once per game frame after all entity processing services have
     * completed. Handles cleanup, collision detection, entity removal, and other
     * finalization tasks that depend on all entities being in their final state.</p>
     * 
     * <h3>Pre-conditions:</h3>
     * <ul>
     *   <li>gameData and world must not be null</li>
     *   <li>All IEntityProcessingService implementations must have completed</li>
     *   <li>All entities must be in their post-processed state</li>
     * </ul>
     * 
     * <h3>Post-conditions:</h3>
     * <ul>
     *   <li>Invalid or destroyed entities are removed from the world</li>
     *   <li>World state is finalized and ready for rendering</li>
     * </ul>
     * 
     * @param gameData the current game configuration and state data
     * @param world the game world containing all entities in their processed state
     */
    void process(GameData gameData, World world);
}
