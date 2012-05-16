package net.spacesim.client;

public abstract class View {

	public abstract void render();
	public abstract void tick();
	public abstract void onMouseDown();
	public abstract void onMouseUp();
	public abstract void onMouseScroll(int scrollAmount);
	public abstract void onMouseMove();
	public abstract void onKeyDown();
	public abstract void onKeyUp();

}