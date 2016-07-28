package com.example.gaurav.opengl;

/**
 * Created by Gaurav on 27-07-2016.
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class Triangle {

   private FloatBuffer mFVertexBuffer;
private ByteBuffer mIndexBuffer;

    public Triangle() {

        float vertices[] = {
                -0.5f, -0.29f, -20f,
                0.5f, -0.29f, -20f,
                0f, 0.58f, -20f
        };

        byte indices[] = { 0, 1, 2 };

        mFVertexBuffer = makeFloatBuffer(vertices);
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL11.GL_FLOAT, 0, mFVertexBuffer);
        gl.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_BYTE, mIndexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    private static FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }
}
