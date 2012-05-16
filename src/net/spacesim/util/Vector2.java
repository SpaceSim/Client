package net.spacesim.util;

public class Vector2 {

	public double x, y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	public double distanceSquared(Vector2 v) {
		return Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2);
	}

	public double distance(Vector2 v) {
		return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2));
	}

	public double length() {
		return distance(new Vector2(0, 0));
	}
	
	public Vector2 add(Vector2 v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}

	public Vector2 sub(Vector2 v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}

	public Vector2 mul(Vector2 v) {
		this.x *= v.x;
		this.y *= v.y;
		return this;
	}

	public Vector2 mul(double n) {
		this.x *= n;
		this.y *= n;
		return this;
	}

	public Vector2 div(Vector2 v) {
		this.x /= v.x;
		this.y /= v.y;
		return this;
	}

	public Vector2 div(double n) {
		this.x /= n;
		this.y /= n;
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public Vector2 clone() {
		return new Vector2(this);
	}

}
