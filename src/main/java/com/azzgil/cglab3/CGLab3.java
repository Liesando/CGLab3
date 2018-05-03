package com.azzgil.cglab3;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;

public class CGLab3 implements GLEventListener {

    public void init(GLAutoDrawable glAutoDrawable) {

    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable glAutoDrawable) {

    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

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
