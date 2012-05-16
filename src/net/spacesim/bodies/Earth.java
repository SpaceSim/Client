package net.spacesim.bodies;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.Sphere;

import net.spacesim.client.Body;
import net.spacesim.client.Space;
import net.spacesim.client.SpaceSim;
import net.spacesim.client.views.SpaceView;

public class Earth extends Body {

	private float r, g, b;
	private Sphere sphere = new Sphere();

	public Earth(Space space, int position) {
		super(space, position);

		float color = SpaceView.random.nextFloat();
		if (SpaceView.random.nextBoolean()) {
			r = 1f;
			g = color;
			b = color;
		} else {
			r = color;
			g = color;
			b = 1f;
		}

		mass = 50000f + SpaceView.random.nextFloat() * 50000f;
		radius = (float) Math.cbrt(mass/4/3/Math.PI);
	}

	@Override
	public void render() {
		glPushMatrix();
		{
			glTranslated(pos.x, pos.y, pos.z);

			int quality = (int) ((50000.0 - SpaceSim.instance.cameraPosition.clone().distanceSquared(pos)) / 50000 * 60);
			if(quality < 12) quality = 12;
			if(quality > 100) quality = 100;
			
			quality = (int)((float)quality / 20.0 * radius);
			
			glColor3f(r, g, b);
			sphere.draw(radius, quality, quality);
		}
		glPopMatrix();
	}

}