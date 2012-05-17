package net.spacesim.util;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class Font {

	private static final int charCount = 0xff;

	private int fontTexture;
	private int[] xPos, yPos;
	private FontMetrics metrics;

	public Font(java.awt.Font font) {
		xPos = new int[charCount];
		yPos = new int[charCount];

		BufferedImage image = new BufferedImage(2048, 2048, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setFont(font);

		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, 2048, 2048);
		g.setColor(Color.white);
		metrics = g.getFontMetrics();

		int x = 2, y = 2;
		for(int i = 0; i < charCount; i++) {
			g.drawString("" + (char)i, x, y + metrics.getAscent());
			xPos[i] = x;
			yPos[i] = y - metrics.getMaxDescent();

			x += metrics.stringWidth("" + (char)i) + 2;
			if(x >= 2048 - metrics.getMaxAdvance()) {
				x = 2;
				y += metrics.getMaxAscent() + metrics.getMaxDescent() + (float)font.getSize() / 2f;
			}
		}

		fontTexture = DrawUtils.setupTexture(image);
	}

	public void drawString(String text, int x, int y, int color) {
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, fontTexture);
		DrawUtils.glColor(color);

		int startX = x;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == '\n') {
				y += metrics.getAscent() + 2;
				x = startX;
				continue;
			}
			drawChar(c, x, y);
			x += metrics.getStringBounds("" + c, null).getWidth();
		}
	}

	public void drawStringWithShadow(String string, int x, int y, int color) {
		drawString(string, x + 1, y + 1, 0x000000ff);
		drawString(string, x, y, color);
	}

	public void drawCenteredString(String text, int x, int y, int color) {
		drawString(text, x - getStringWidth(text) / 2, y, color); 
	}

	public void drawCenteredStringWithShadow(String text, int x, int y, int color) {
		drawStringWithShadow(text, x - getStringWidth(text) / 2, y, color); 
	}

	public FontMetrics getMetrics() {
		return metrics;
	}

	public int getStringWidth(String text) {
		return (int)getBounds(text).getWidth();
	}

	public int getStringHeight(String text) {
		return (int)getBounds(text).getHeight();
	}


	private Rectangle getBounds(String text) {
		int w = 0;
		int h = 0;
		int tw = 0;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == '\n') {
				h += metrics.getAscent() + 2;
				if (tw > w) w = tw;
				tw = 0;
				continue;
			}
			tw += metrics.stringWidth("" + c);
		}
		if (tw > w) w = tw;
		h += metrics.getAscent();

		return new Rectangle(0, 0, w, h);
	}

	private void drawChar(char c, int x, int y) {
		Rectangle2D bounds = metrics.getStringBounds(""+c, null);
		float w = (float)bounds.getWidth();
		float h = (float)bounds.getHeight() + metrics.getMaxDescent() + 2;

		float tx = (float)xPos[c] / 2048f;
		float ty = (float)yPos[c] / 2048f;

		float tw = w / 2048f;
		float th = h / 2048f;
		glBegin(GL_QUADS);
		{
			glTexCoord2f(tx, ty);
			glVertex2f(x, y);

			glTexCoord2f(tx + tw, ty);
			glVertex2f(x + w, y);

			glTexCoord2f(tx + tw, ty + th);
			glVertex2f(x + w, y + h);

			glTexCoord2f(tx, ty + th);
			glVertex2f(x, y + h);
		}
		glEnd();
	}

	/*void debug() {
    	float x = 0, y = 0;
    	float w = 2048, h = 2048;
    	float tx = 0, ty = 0, tw = 1, th = 1;

		glBindTexture(GL_TEXTURE_2D, fontTexture);
    	glBegin(GL_QUADS);
        {
			glTexCoord2f(x, y);
			glVertex2f(tx, ty);

			glTexCoord2f(tx + tw, ty);
			glVertex2f(x + w, y);

			glTexCoord2f(tx + tw, ty + th);
			glVertex2f(x + w, y + h);

			glTexCoord2f(tx, ty + th);
			glVertex2f(x, y + h);
        }
        glEnd();
    }*/

}
