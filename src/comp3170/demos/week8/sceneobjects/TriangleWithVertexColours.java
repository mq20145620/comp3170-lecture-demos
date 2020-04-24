package comp3170.demos.week8.sceneobjects;

import java.awt.Color;

import org.joml.Vector3f;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.SceneObject;
import comp3170.Shader;

public class TriangleWithVertexColours extends SceneObject {

	private float[] vertices = {
		 0, 1, 0,
		 1, 0, 0,
	    -1, 0, 0,	    
	};

	private int vertexBuffer;

	private float[] colours = { 
			1.0f, 1.0f, 1.0f, 1.0f,	// white 
			1.0f, 1.0f, 1.0f, 1.0f, // white
			1.0f, 1.0f, 1.0f, 1.0f, // white
	}; 

	private int colourBuffer;

	public TriangleWithVertexColours(Shader shader, Color[] colour) {

		int j = 0;
		for (int i = 0; i < 3; i++) {
			// read the RGBA values into this.colours
			// note getRed/Blue/Green/Alpha return ints in the range 0-255
			this.colours[j++] = colour[i].getRed() / 255.0f;		
			this.colours[j++] = colour[i].getGreen() / 255.0f;		
			this.colours[j++] = colour[i].getBlue() / 255.0f;		
			this.colours[j++] = colour[i].getAlpha() / 255.0f;		
		}
		this.vertexBuffer = shader.createBuffer(this.vertices);
		this.colourBuffer = shader.createBuffer(this.colours);
	}

	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		// draw the triangle
		
		shader.setAttribute("a_position", this.vertexBuffer);
		shader.setAttribute("a_colour", this.colourBuffer);
				
		gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.vertices.length / 3);

	}

}