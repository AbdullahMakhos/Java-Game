package tiles;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage tileImage; //image holder
	boolean collision; //is there a collision
	public Tile(BufferedImage tileImage) {
		this.tileImage = tileImage;
	}
}
