package dk.sdu.cbse.common.data;

public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 5;
    public static final int UP = 0; // KeyCode.W
    public static final int LEFT = 1; // KeyCode.A
    public static final int RIGHT = 2; // KeyCode.D
    public static final int SPACE = 3; // KeyCode.SPACE
    public static final int ROLL = 4; // KeyCode.R

    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public boolean isDown(int k) {
        return keys[k];
    }

    public boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

}