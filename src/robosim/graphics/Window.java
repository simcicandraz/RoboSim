/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robosim.graphics;

import java.nio.ByteBuffer;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;


/**
 *
 * @author Andraz & David
 */

public class Window {
    
    private long window;
    
    public Window(int width, int height, String title) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
        glfwWindowHint(GLFW_DECORATED, GL_TRUE);
        glfwWindowHint(GLFW_FOCUSED, GL_TRUE);
        
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        
        
        GLFWVidMode vidmode = glfwGetVideoMode(window);
        int x = (GLFWVidMode.WIDTH - width) / 2;
        int y = (GLFWVidMode.HEIGHT - height) / 2;
        glfwSetWindowPos(window, x, y);
        
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
    public void render() {
        glfwSwapBuffers(window);
    }
    public void setTitle(String title) {
        glfwSetWindowTitle(window, title);
    }
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }
}
