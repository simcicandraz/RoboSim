/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robosim.graphics;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengles.GLES20;
import static org.lwjgl.opengles.GLES20.GL_FALSE;
import static org.lwjgl.system.MemoryUtil.*;


/**
 *
 * @author Andraz & David
 */

public class Window {
    
    private long window;
    
    public Window(int width, int height, String title) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLES20.GL_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLES20.GL_TRUE);
        glfwWindowHint(GLFW_DECORATED, GLES20.GL_TRUE);
        glfwWindowHint(GLFW_FOCUSED, GLES20.GL_TRUE);
        
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        
    }
    
    public void dispose() {
        glfwDestroyWindow(window);
    }
    public void hide() {
        glfwHideWindow(window);
    }
    public void show() {
        glfwShowWindow(window);
    }
    public void setTitle(String title) {
        glfwSetWindowTitle(window, title);
    }
}
