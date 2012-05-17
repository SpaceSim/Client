package net.spacesim.client;

import net.spacesim.util.Vector3;

public abstract class Body {

	public double mass;

	public double radius;

	public Vector3 pos;
	public Vector3 vel;

	public Space space;

	public int position;

	public Body(Space space) {
		this.space = space;
		position = space.bodyCount;
		space.add(this);
		pos = new Vector3();
		vel = new Vector3();
	}

	public abstract void render();

	public void tick() {
		Body[] bodies = space.getBodies();

		for (Body body : bodies) {
			if (body == this || body == null)
				continue;
			double distanceSquared = pos.distanceSquared(body.pos);

			//if(distanceSquared > Math.pow(body.radius + radius, 2)) {
			Vector3 diff = pos.clone().sub(body.pos);
			vel.add((diff.div(-100000).div(distanceSquared).mul(body.mass)));
			//
			//  } else if(mass > body.mass) { space.remove(body); mass +=
			//  body.mass/8; radius = (float) Math.cbrt(mass/4/3/Math.PI); }
		}
		pos.add(vel);
	}

}