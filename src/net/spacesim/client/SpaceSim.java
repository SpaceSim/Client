package net.spacesim.client;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.nio.FloatBuffer;

import net.spacesim.client.views.SpaceView;
import net.spacesim.util.DrawUtils;
import net.spacesim.util.Vector2;
import net.spacesim.util.Vector3;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class SpaceSim {

	public final static SpaceSim instance = new SpaceSim();

	public View view = new SpaceView();

	public Vector3 cameraPosition = new Vector3(0, 0, 0.1);
	public Vector3 cameraOrientation = new Vector3(0, 0, 0);

	public int fpsTarget = -1;
	public int tpsTarget = 24;

	private boolean running;

	private Vector2 displaySize = new Vector2(1280, 720);
	private double aspectRatio;

	private int fps = 0;
	private int tps = 0;

	// Lighting test START
	private FloatBuffer matSpecular;
	private FloatBuffer lightPosition;
	private FloatBuffer whiteLight;
	private FloatBuffer lModelAmbient;

	@SuppressWarnings("unused")
	private void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(0.0f).put(0.0f).put(0.0f).put(0.0f).flip();

		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
	}
	// Lighting test END

	public void run() throws Exception {
		createWindow();
		initGL();

		long lastFPS = System.currentTimeMillis();
		int frames = 0;

		long lastTPS = System.currentTimeMillis();
		int ticks = 0;

		long lastLogic = System.currentTimeMillis();
		int logicClock = 0;

		running = true;
		while(running) {
			render();
			input();
			frames++;

			do
				if(System.currentTimeMillis() - lastLogic > logicClock) {
					long renderTimeFix = System.currentTimeMillis() - lastLogic - logicClock;

					long beginLogic = System.currentTimeMillis();
					update();
					long logicTime = System.currentTimeMillis() - beginLogic;


					logicClock = (int) (1000d / (double)tpsTarget - logicTime - renderTimeFix);

					lastLogic = System.currentTimeMillis();
					ticks++;
				}
			while(logicClock < 0);

			if(System.currentTimeMillis() - lastFPS > 1000) {
				fps = frames;
				frames = 0;
				lastFPS = System.currentTimeMillis();
			}


			if(System.currentTimeMillis() - lastTPS > 1000) {
				tps = ticks;
				ticks = 0;
				lastTPS = System.currentTimeMillis();
			}

			Thread.yield();
		}
	}

	private void createWindow() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode((int)displaySize.x, (int)displaySize.y));
		Display.setTitle("SpaceSim v1.0 - Prototype");
		Display.setResizable(true);
		Display.create();

		Mouse.setGrabbed(true);
	}

	private void input() {
		//Handle mouse
		while(Mouse.next()) {
			if(Mouse.getEventButton() == -1 && Mouse.getEventDWheel() == 0) {
				continue;
			} else if(Mouse.getEventDWheel() != 0) {
				view.onMouseScroll(Mouse.getEventDWheel());
				continue;
			}

			if(Mouse.getEventButtonState()) {
				view.onMouseDown(Mouse.getEventButton());
			}

			if(!Mouse.getEventButtonState()) {
				view.onMouseUp(Mouse.getEventButton());
			}
			
			if(Mouse.getDX() != 0 || Mouse.getDY() != 0) {
				view.onMouseMove();
			}
		}

		//Handle keys
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				view.onKeyDown(Keyboard.getEventCharacter(), Keyboard.getEventKey());
			} else {
				view.onKeyUp(Keyboard.getEventCharacter(), Keyboard.getEventKey());
			}
		}
	}
	
	private void initGL() {
		DrawUtils.setup();
		DrawUtils.enableFrustumViewport();
	}

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

		displaySize.x = Display.getWidth();
		displaySize.y = Display.getHeight();

		glViewport(0, 0, (int)displaySize.x, (int)displaySize.y);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		aspectRatio = displaySize.x / displaySize.y;
		gluPerspective(45.0f, (float)aspectRatio, 0.1f, 10000.0f);

		cameraOrientation.toAngle();
		//System.out.println(cameraOrientation);

		glRotated(-cameraOrientation.x, 1, 0, 0);
		glRotated(-cameraOrientation.y, 0, 1, 0);
		glRotated(-cameraOrientation.z, 0, 0, 1);
		glTranslated(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		view.render();

		Display.update();
		if(fpsTarget != -1) Display.sync(fpsTarget);

		if (Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) running = false;

		while(Mouse.next()) {
			if(Mouse.getEventDWheel() != 0) {
				view.onMouseScroll(Mouse.getDWheel());
			}
		}
	}

	public void update() {
		view.tick();
	}

	/*public void renderScene() {
		glTranslatef(-aspectRatio / 2f, -0.5f, 0);
		//glScalef(0.5f, 0.5f, 1);
        //glRotatef(rtri, 0.0f, 1.0f, 0.0f);

        glBegin(GL_QUADS);
        {
            glColor3f(1.0f, 0.0f, 0.0f);
            glVertex3f(aspectRatio, 0.0f, 0.0f);
            glVertex3f(aspectRatio, 1.0f, 0.0f);
            glVertex3f(0.0f, 1.0f, 0.0f);
            glVertex3f(0.0f, 0.0f, 0.0f);
        }
        glEnd();
	}*/

	public boolean isRunning() {
		return running;
	}

	public Vector2 getDisplaySize() {
		return displaySize.clone();
	}

	public double getAspectRatio() {
		return aspectRatio;
	}

	public int getFPS() {
		return fps;
	}

	public int getTPS() {
		return tps;
	}

	public static void main(String[] args) throws Exception {
		instance.run();
	}

}
