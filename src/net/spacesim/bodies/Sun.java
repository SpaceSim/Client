package net.spacesim.bodies;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslated;

import org.lwjgl.util.glu.Sphere;

import net.spacesim.client.Body;
import net.spacesim.client.Space;
import net.spacesim.client.SpaceSim;

import net.spacesim.util.Math;

public class Sun extends Body {

	private Sphere sphere = new Sphere();
	
	public Sun(Space space) {
		super(space);
		mass = 1.98892d * Math.pow(10, 33);
		radius = Math.cbrt(mass/4/3/Math.PI);
		System.out.println(radius);
	}

	@Override
	public void render() {
		glPushMatrix();
		{
			glTranslated(pos.x, pos.y, pos.z);

			int quality = (int) ((50000.0 - SpaceSim.instance.cameraPosition.clone().distanceSquared(pos)) / 50000 * 60);
			if(quality < 12) quality = 12;
			if(quality > 100) quality = 100;
			
			quality = (int)((float)quality / 20.0 * 20);
			
			glColor3f(255, 255, 0);
			sphere.draw((float) radius, quality, quality);
		}
		glPopMatrix();
	}

}
