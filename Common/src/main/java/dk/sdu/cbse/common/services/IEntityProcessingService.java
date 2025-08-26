package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * Service for processing entities during the main game loop.
 */
public interface IEntityProcessingService {
    
    /**
     * Processes entities and updates game state for the current frame.
     * 
     * <p>Called once per game frame to perform entity updates, movement, AI,
     * and other real-time processing operations. Called before post-processing services.</p>
     * 
     * <h3>Pre-conditions:</h3>
     * <ul>
     *   <li>gameData and world must not be null</li>
     *   <li>gameData must contain valid display dimensions</li>
     *   <li>world must be in a consistent state</li>
     * </ul>
     * 
     * <h3>Post-conditions:</h3>
     * <ul>
     *   <li>All relevant entities are processed for this frame</li>
     *   <li>Entity positions, states, and properties are updated</li>
     *   <li>World remains in a consistent state</li>
     * </ul>
     * 
     * @param gameData the current game configuration and state data
     * @param world the game world containing entities to process
     */
    void process(GameData gameData, World world);
}
