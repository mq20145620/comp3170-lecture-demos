package comp3170.demos.week8.sceneobjects;

import java.awt.Color;

import org.joml.Vector3f;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.SceneObject;
import comp3170.Shader;

public class Quad extends SceneObject {

	private float[] vertices = {
		 1, 1, 0,
		 1, -1, 0,
	    -1, -1, 0,	    

		 1, 1, 0,
		-1, 1, 0,
	    -1, -1, 0,	    

	};

	private int vertexBuffer;
	
	private float[] colour = { 1.0f, 1.0f, 1.0f, 1.0f}; // white
	
	public Quad(Shader shader, Color colour) {
		
		// read the RGBA values into this.colour
		colour.getComponents(this.colour);

		this.vertexBuffer = shader.createBuffer(this.vertices);
	}

	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		// draw the triangle
		
		shader.setAttribute("a_position", this.vertexBuffer);
		if (shader.hasUniform("u_colour")) {
			shader.setUniform("u_colour", this.colour);
		}

		gl.glDrawArrays(GL.GL_TRIANGLES, 0, this.vertices.length / 3);

	}

}