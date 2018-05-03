package com.azzgil.cglab3;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;

public class CGLab3 implements GLEventListener {

    double rotation = 0.1;
    double width = 2.0;
    double height = 3.0;
    double depth = 5.0;

    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1f);
        gl.glClearDepth(1.0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHT2);
        gl.glEnable(GL2.GL_NORMALIZE);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glRotated(rotation, 0, 1f, 0);

        gl.glBegin(GL2.GL_QUADS);

        gl.glColor4d(0.5, 0.5, 0.5, 0.5); // grey

        // bottom
//        gl.glColor4d(0, 0, 0, 0.5); // red
        gl.glNormal3d(0, -1.0, 0);
        gl.glVertex3d(-width / 2, 0, 0);
        gl.glVertex3d(-width / 2, 0, -depth);
        gl.glVertex3d(width / 2, 0, -depth);
        gl.glVertex3d(width / 2, 0, 0);

        // left
//        gl.glColor4d(0, 1.0, 0, 0.5); // green
        gl.glNormal3d(-1.0, 0, 0);
        gl.glVertex3d(-width / 2, 0, 0);
        gl.glVertex3d(-width / 2, 0, -depth);
        gl.glVertex3d(-width / 2, height, -depth);
        gl.glVertex3d(-width / 2, height, 0);

        // back
//        gl.glColor4d(0, 0, 1.0, 0.5); // blue
        gl.glNormal3d(0, 0, -1.0);
        gl.glVertex3d(-width / 2, 0, -depth);
        gl.glVertex3d(width / 2, 0, -depth);
        gl.glVertex3d(width / 2, height, -depth);
        gl.glVertex3d(-width / 2, height, -depth);

        // right
//        gl.glColor4d(1.0, 0, 0, 0.5); // red
        gl.glNormal3d(1.0, 0, 0);
        gl.glVertex3d(width / 2, 0, 0);
        gl.glVertex3d(width / 2, 0, -depth);
        gl.glVertex3d(width / 2, height, -depth);
        gl.glVertex3d(width / 2, height, 0);

        // top
//        gl.glColor4d(1.0, 1.0, 0, 0.5); // yellow
        gl.glNormal3d(0, 1.0, 0);
        gl.glVertex3d(-width / 2, height, 0);
        gl.glVertex3d(-width / 2, height, -depth);
        gl.glVertex3d(width / 2, height, -depth);
        gl.glVertex3d(width / 2, height, 0);

        // front
//        gl.glColor4d(0.5, 0.5, 0.5, 0.5); // grey
        gl.glNormal3d(0, 0, 1.0);
        gl.glVertex3d(-width / 2, 0, 0);
        gl.glVertex3d(width / 2, 0, 0);
        gl.glVertex3d(width / 2, height, 0);
        gl.glVertex3d(-width / 2, height, 0);

        gl.glEnd();
        gl.glFlush();

        gl.glPopMatrix();

        float[] diffuse = {1f, 0, 0, 1f};
        float[] position = {(float) width + 1f, (float) height + 1, (float) depth / -2f, 1f};
        float[] direction = {-1f, -1f, 0};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPOT_DIRECTION, direction, 0);

        diffuse = new float[]{0, 1f, 0, 1f};
        position = new float[]{(float) -width - 1f, (float) height + 1, (float) depth / -2f, 1f};
        direction = new float[]{1f, -1f, 0};
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPOT_DIRECTION, direction, 0);
        gl.glLighti(GL2.GL_LIGHT1, GL2.GL_SPOT_EXPONENT, 5);

        diffuse = new float[]{0, 0, 1f, 1f};
        position = new float[]{(float) -width - 1f, (float) height + 1, (float) depth / 2f, 1f};
        direction = new float[]{0, -1f, 1f};
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, position, 0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPOT_DIRECTION, direction, 0);
        gl.glLighti(GL2.GL_LIGHT2, GL2.GL_SPOT_EXPONENT, 5);

        rotation += 2;
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int width, int height) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        final GLU glu = GLU.createGLU(gl);

        gl.glViewport(0, 0, width, height);
        double ratio = (double) width / (double) height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(90.0f, ratio, 1, 20);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0, -5.0, -7.0);
    }

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        final GLCapabilities caps = new GLCapabilities(profile);
        final GLCanvas canvas = new GLCanvas(caps);

        CGLab3 app = new CGLab3();
        canvas.addGLEventListener(app);

        canvas.setSize(400, 400);

        final JFrame frame = new JFrame("Computer Graphics lab #3");
        frame.add(canvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}
