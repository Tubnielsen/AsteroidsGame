package dk.sdu.cbse.common.data;

import java.util.List;

public interface IAsteroidSplitter {
    List<Asteroid> splitAsteroid(Asteroid asteroid, GameData gameData);
}
