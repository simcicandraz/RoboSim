/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robosim.graphics;

import java.awt.DisplayMode;
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
        
        
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        double x = (vidmode.width() - width) / 2;
        double y = (vidmode.height() - height) / 2;
        //System.out.printf("x:%d | y:%d\n", vidmode.width(), vidmode.height());
        
        glfwSetWindowPos(window, (int)x, (int)y);
        
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
    public void makeContext() {
        glfwMakeContextCurrent(window);
    }
    public void swapBuffer() {
        glfwSwapBuffers(window);
    }
    public int isKeyPressed(int key) {
        System.out.println("Pressed");
        if(glfwGetKey(window, GLFW_KEY_W) == GL_TRUE) {
            return 1;
        } else if(glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) {
            return 2;
        } else if(glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) {
            return 3;
        } else if(glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) {
            return 4;
        } else if(glfwGetKey(window, GLFW_KEY_SPACE) == GL_TRUE) {
            return 5;
        } else {
            return 0;
        }
    }
}
