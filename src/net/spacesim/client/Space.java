package net.spacesim.client;

public class Space {

	private Body[] bodies;
	public int position;

	public Space() {
		bodies = new Body[10000];
	}

	public void render() {
		for(int i = 0; i < position; i++) {
			Body body = bodies[i];
			if(body == null) continue;
			body.render();
		}
	}

	public void update() {
		for(int i = 0; i < position; i++) {
			Body body = bodies[i];
			if(body == null) continue;
			body.tick();
		}
	}

	public Body[] getBodies() {
		return bodies;
	}

	public void add(Body body) {
		bodies[position] = body;
		position++;
	}

	public void remove(Body body) {
		bodies[body.position] = null;
	}

}