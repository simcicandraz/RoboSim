package robosim;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {
    
    private long window;
    private final String title = "RoboSim";
    private int width;
    private int height;
    private boolean vSync;
    
    public Window(int width, int height, boolean vSync) {
        this.width = width;
        this.height = height;
        this.vSync = vSync;
    }
    public void init() {
        if(!glfwInit()) {
            System.out.println("Unable to initialize glfw");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if(window == NULL) {
            System.out.println("Failed to initalize window");
        }
        
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        double x = (vidmode.width() - width) / 2;
        double y = (vidmode.height() - height) / 2;
        //System.out.printf("x:%d | y:%d", vidmode.width(), vidmode.height());
        glfwSetWindowPos(window, (int)x, (int)y);
        
        glfwMakeContextCurrent(window);
        
        if(isvSync()) {
            glfwSwapInterval(1);
        }
        System.out.println("se zgodi");
        glfwShowWindow(window);
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }
    public boolean isvSync() {
        return vSync;
    }
    public void setvSync(boolean vSync) {
        this.vSync = vSync;
    }
    public void setClearColor(float r, float g, float b, float alpha) {
        glClearColor(r, g, b, alpha);
    }
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }
}
