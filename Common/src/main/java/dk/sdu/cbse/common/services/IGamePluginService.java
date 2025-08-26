package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Service for managing game plugin lifecycle events.
 */
public interface IGamePluginService {
    
    /**
     * Initializes the plugin and adds any necessary entities to the game world.
     * 
     * <p>Called once during game initialization to set up initial state,
     * load resources, and add entities to the world.</p>
     * 
     * <h3>Pre-conditions:</h3>
     * <ul>
     *   <li>gameData and world must not be null</li>
     *   <li>gameData display dimensions must be positive</li>
     *   <li>world must be ready for entity addition</li>
     * </ul>
     * 
     * <h3>Post-conditions:</h3>
     * <ul>
     *   <li>Plugin is fully initialized and ready for processing</li>
     *   <li>Initial entities are added to the world</li>
     *   <li>Resources are loaded and available</li>
     * </ul>
     * 
     * @param gameData the game configuration and state data
     * @param world the game world containing all entities
     */
    void start(GameData gameData, World world);

    /**
     * Cleans up the plugin and removes managed entities from the game world.
     * 
     * <p>Called during game shutdown to perform cleanup operations,
     * release resources, and remove entities from the world.</p>
     * 
     * <h3>Pre-conditions:</h3>
     * <ul>
     *   <li>gameData and world must not be null</li>
     *   <li>Plugin must have been previously started</li>
     * </ul>
     * 
     * <h3>Post-conditions:</h3>
     * <ul>
     *   <li>All plugin-managed entities are removed from the world</li>
     *   <li>All resources are properly released</li>
     *   <li>Plugin is in a clean, uninitialized state</li>
     * </ul>
     * 
     * @param gameData the game configuration and state data
     * @param world the game world containing all entities
     */
    void stop(GameData gameData, World world);
}