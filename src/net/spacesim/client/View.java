package net.spacesim.client;

public abstract class View {

	public abstract void render();
	public abstract void tick();
	public abstract void onMouseDown(int button);
	public abstract void onMouseUp(int button);
	public abstract void onMouseScroll(int scrollAmount);
	public abstract void onMouseMove();
	public abstract void onKeyDown(char c, int key);
	public abstract void onKeyUp(char c, int key);

}