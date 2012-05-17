package net.spacesim.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.spacesim.util.Font;

import static org.lwjgl.opengl.ARBVertexBlend.GL_MODELVIEW0_ARB;
import static org.lwjgl.opengl.GL11.*;

public class DrawUtils {

	private static Map<String, Integer> textures = new HashMap<String, Integer>();
	private static Map<String, Font> fonts = new HashMap<String, Font>();

	public static void setup() {
		setupFonts();
	}

	public static boolean inrange(int x1, int y1, int x2, int y2, int x3, int y3) {
		return x1 >= x2 && x1 <= x3 && y1 >= y2 && y1 <= y3;
	}

	public static void drawTexturedQuad(int x, int y, int w, int h, float tx, float ty, float tw, float th) {
		glPushMatrix();
		{
			glTranslatef(x, y, 0);
			glScalef(w, h, 1);
			glBegin(GL_QUADS);
			{
				glTexCoord2f(tx, ty);
				glVertex2f(0, 0);

				glTexCoord2f(tx + tw, ty);
				glVertex2f(1, 0);

				glTexCoord2f(tx + tw, ty + th);
				glVertex2f(1, 1);

				glTexCoord2f(tx, ty + th);
				glVertex2f(0, 1);
			}
			glEnd();
		}
		glPopMatrix();
	}

	public static int getTexture(String textureName) {
		textureName = textureName.toLowerCase();

		if(textures.containsKey(textureName)) {
			return textures.get(textureName);
		} else {
			int texture = loadTexture(textureName);
			textures.put(textureName, texture);

			return texture;
		}
	}

	public static int loadTexture(String textureName) {
		BufferedImage image = getResourceAsImage(textureName);
		return setupTexture(image);
	}

	public static int setupTexture(BufferedImage image) {
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		int textureWidth = image.getWidth();
		int textureHeight = image.getHeight();

		byte[] pixelBuffer = new byte[textureWidth * textureHeight * 4];
		int[] colorBuffer = new int[textureWidth * textureHeight];

		image.getRGB(0, 0, textureWidth, textureHeight, colorBuffer, 0, textureWidth);

		int pixel;
		int a, r, g, b;
		for(int i = 0; i < colorBuffer.length; i++) {
			pixel = colorBuffer[i];
			a = pixel >> 24 & 255;
		r = pixel >> 16 & 255;
		g = pixel >> 8 & 255;
		b = pixel & 255;

		pixelBuffer[i * 4 + 0] = (byte)r;
		pixelBuffer[i * 4 + 1] = (byte)g;
		pixelBuffer[i * 4 + 2] = (byte)b;
		pixelBuffer[i * 4 + 3] = (byte)a;
		}

		ByteBuffer buffer = ByteBuffer.allocateDirect(pixelBuffer.length).order(ByteOrder.nativeOrder());
		buffer.put(pixelBuffer);
		buffer.position(0);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, textureWidth, textureHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		return id;
	}

	private static BufferedImage getResourceAsImage(String textureName) {
		try {
			InputStream imageInputStream = DrawUtils.class.getResourceAsStream("textures/" + textureName);
			BufferedImage image = ImageIO.read(imageInputStream);
			imageInputStream.close();
			return image;
		} catch (Exception e) {
			// TODO: log exception
			e.printStackTrace();
			return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		}
	}

	public static void enableOrthoViewport(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glViewport(0, 0, width, height);
		glOrtho(0, width, height, 0, -1, 1);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glMatrixMode(GL_MODELVIEW0_ARB);
		glLoadIdentity();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glAlphaFunc(GL_ALWAYS, 0);

		glShadeModel(GL_SMOOTH);


		glDisable(GL_COLOR_MATERIAL);
		glDisable(GL_LIGHTING);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_CULL_FACE);
		glDisable(GL_FOG);
	}
	
	public static void enableFrustumViewport() {
		//glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearDepth(1.0);

		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		/*initLightArrays();


		glMaterial(GL_FRONT, GL_SPECULAR, matSpecular); // sets specular
														// material color

		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f); // sets shininess

		glLight(GL_LIGHT0, GL_POSITION, lightPosition); // sets light position

		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight); // sets specular light to
														// white

		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight); // sets diffuse light to
													// white

		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient); // global ambient
																// light

		glEnable(GL_LIGHTING); // enables lighting

		glEnable(GL_LIGHT0); // enables light0

		glEnable(GL_COLOR_MATERIAL); // enables opengl to use glColor3f to
										// define material color

		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);*/
	}

	public static void glColor(int color) {
		float r = (float)(color >> 24 & 0xff) / 255F;
		float g = (float)(color >> 16 & 0xff) / 255F;
		float b = (float)(color >> 8 & 0xff) / 255F;
		float a = (float)(color & 0xff) / 255F;
		glColor4f(r, g, b, a);
	}

	public static void glRectWH(int x, int y, int w, int h, int color) {
		glBegin(GL_QUADS);
		{
			glColor(color);
			glVertex2f(x, y);
			glVertex2f(x + w, y);
			glVertex2f(x + w, y + h);
			glVertex2f(x, y + h);
		}
		glEnd();
	}

	public static Font getFont(String fontName) {
		return fonts.get(fontName.toLowerCase());
	}

	private static java.awt.Font getResourceAsFont(String fontName) {
		try {
			InputStream fontInputStream = DrawUtils.class.getResourceAsStream("fonts/" + fontName);
			java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontInputStream);
			fontInputStream.close();
			return font;
		} catch (Exception e) {
			// TODO: log exception
			e.printStackTrace();
			return new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 11);
		}
	}

	private static void setupFonts() {
		fonts.put("Calibri Regular".toLowerCase(), new Font(getResourceAsFont("Calibri Regular.ttf").deriveFont(12f)));
		fonts.put("Calibri Bold".toLowerCase(), new Font(getResourceAsFont("Calibri Bold.ttf").deriveFont(12f)));
	}

}
