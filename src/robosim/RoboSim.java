package robosim;

import robosim.graphics.*;
import static org.lwjgl.glfw.GLFW.*;
//import robosim.graphics.Window;



/**
 *
 * @author Andraz & David
 */

public class RoboSim {
    public static void main(String[] args) {
        glfwInit();
        Window window = new Window(1280,720,"RoboSim");
        
        while(true) {
            glfwPollEvents();
        }
        //window.dispose();
    }
}
