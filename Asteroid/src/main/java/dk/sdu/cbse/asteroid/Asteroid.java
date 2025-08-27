package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;

public class Asteroid extends Entity{
    
    public Asteroid() {
    }
    
    private int size; // 1 = small, 2 = medium, 3 = large

    public void setSize(int size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}

