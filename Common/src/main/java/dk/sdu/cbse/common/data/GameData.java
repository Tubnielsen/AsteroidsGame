package dk.sdu.cbse.common.data;

//import javafx.scene.layout.Pane;

public class GameData {
    
    private int displayWidth = 1280;
    private int displayHeight = 720;
    private final GameKeys keys = new GameKeys();
    //private Pane gameWindow;

    /*public Pane getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(Pane gameWindow) {
        this.gameWindow = gameWindow;
    }*/
    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }
}
