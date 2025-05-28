package tiles;

import java.awt.image.BufferedImage;

//objects like box fire...etc not monsters , monsters will be entities 

public class Object extends Tile {
    
    public Object(BufferedImage image) {
        super(image); 
        this.setCrossable(false);
    }

    public void behavior() {
        // Implement specific behavior here
    }
}
