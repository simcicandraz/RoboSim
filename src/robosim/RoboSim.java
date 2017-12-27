package robosim;

import robosim.graphics.*;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;


/**
 *
 * @author Andraz & David
 */

public class RoboSim {
    public static void main(String[] args) {
        if(!glfwInit()) {
            System.err.println("GLFW Failed to initialize!");
            System.exit(1);
        }
        Window window = new Window(1280,720,"RoboSim");
        
        window.makeContext();
        GL.createCapabilities();
        
        while(true) {
            glfwPollEvents();
            if(window.shouldClose()) {
                break;
            }
            glClear(GL_COLOR_BUFFER_BIT);
            
            //glBegin(GL_QUADS);
            glBegin(GL_TRIANGLES);
            glColor4f(1, 0, 0, 0);
            glVertex3d(-0.5, 0.5, 0.5);
            glColor4f(0, 1, 0, 0);
            glVertex3d(0.5, -0.5, 0.5);
            glColor4f(0, 0, 1, 0);
            glVertex3d(-0.5, -0.5, 0.5);
            glEnd();
            
            window.swapBuffer();
        }
        window.dispose();
        
        glfwTerminate();
    }
}
