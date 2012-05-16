package net.spacesim.util;

import static net.spacesim.util.Math.*;

public class Vector3 {

	public double x, y, z;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(Vector3 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public Vector3() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public double distance(Vector3 v) {
		return sqrt(pow(x - v.x, 2) + pow(y - v.y, 2) + pow(z - v.z, 2));
	}

	public double distanceSquared(Vector3 v) {
		return pow(x - v.x, 2) + pow(y - v.y, 2) + pow(z - v.z, 2);
	}

	public double length() {
		return distance(new Vector3(0, 0, 0));
	}

	public Vector3 normalize() {
		double length = length();
		x /= length;
		y /= length;
		z /= length;
		return this;
	}

	public Vector3 cross(Vector3 vec) {
		double a = x, b = y, c = z;
		double p = vec.x, q = vec.y, r = vec.z;
		x = b*r-c*q;
		y = c*p-a*r;
		z = a*q-b*p;
		return this;
	}

	public Vector3 right()  {
		Vector3 up = new Vector3(0, -1, 0);
		return normalize().cross(up.normalize());
	}
	
	public Vector3 up()  {
		Vector3 up = new Vector3(1, 0, 0);
		return normalize().cross(up.normalize());
	}

	public Vector3 add(Vector3 v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3 sub(Vector3 v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3 mul(Vector3 v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
	}

	public Vector3 mul(double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}

	public Vector3 div(Vector3 v) {
		this.x /= v.x;
		this.y /= v.y;
		this.z /= v.z;
		return this;
	}

	public Vector3 div(double n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}

	public Vector3 toVel() {
		double nx = sin(toRadians(y)) * cos(toRadians(x));
		double ny = -sin(toRadians(x));
		double nz = cos(toRadians(y)) * cos(toRadians(x));

		this.x = nx;
		this.y = ny;
		this.z = nz;
		return this;
	}

	public Vector3 toAngle() {
		while(x < 0) x += 360;
		while(x >= 360) x -= 360;
		while(y < 0) y += 360;
		while(y >= 360) y -= 360;
		while(z < 0) z += 360;
		while(z >= 360) z -= 360;	
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	@Override
	public Vector3 clone() {
		return new Vector3(this);
	}

}