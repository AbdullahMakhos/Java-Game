package main;

public class Level {
	private int[][] tileMatrix;
	private int[][] objectMatrix;
	
	public Level(int[][] tileMatrix,int[][] objectMatrix) {
		this.tileMatrix = tileMatrix;
		this.objectMatrix = objectMatrix;
	}
	
	public int[][] getTileMatrix() {
		return tileMatrix;
	}
	
	public int[][] getObjectMatrix() {
		return objectMatrix;
	}
	
}
