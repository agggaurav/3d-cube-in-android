package com.example.gaurav.opengl;

/**
 * Created by Gaurav on 27-07-2016.
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

public class ShapeRenderer implements GLSurfaceView.Renderer {

    private Triangle shape;



    public ShapeRenderer() {
        shape = new Triangle();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();
        long time= SystemClock.uptimeMillis()%4000L;
        float angle=.090f*((int)time);

        gl.glTranslatef(1.0f, 0.0f, 0.0f);
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        gl.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        shape.draw(gl);

        gl.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);

        float ratio;
        float zNear = .1f;
        float zFar = 1000f;
        float fieldOfView = (float) Math.toRadians(30);
        float size;

        gl.glEnable(GL11.GL_NORMALIZE);

        ratio = (float) width / (float) height;

        gl.glMatrixMode(GL11.GL_PROJECTION);

        size = zNear * (float) (Math.tan((double) (fieldOfView / 2.0f)));

        gl.glFrustumf(-size, size, -size / ratio, size / ratio, zNear, zFar);
      //  Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glDisable(GL11.GL_DITHER);

        gl.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);

        gl.glClearColor(0, 0, 0, 0);

       //gl.glEnable(GL11.GL_CULL_FACE);
        //gl.glFrontFace(GL11.GL_CCW);

        gl.glShadeModel(GL11.GL_SMOOTH);

        gl.glEnable(GL11.GL_DEPTH_TEST);
    }

}