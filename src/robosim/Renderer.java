package robosim;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

public class Renderer {
    private int vboID;
    private int vaoID;
    private Shader shader;
    public Renderer() {}
    
    public void init() throws Exception {
        shader = new Shader();
        shader.createVertexShader(Utility.loadResources("Resources/vertex.vs"));
        shader.createFragmentShader(Utility.loadResources("Resources/fragment.fs"));
        shader.link();
        
        float[] vertices = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
        };
        FloatBuffer verticesBuffer = null;
        try {
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();
            
            vaoID = glGenVertexArrays();
            glBindVertexArray(vaoID);
            
            vboID = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER,vboID);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            
            //unbind vbo and vao
            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
            
        } finally {
            if(verticesBuffer != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
        }
    }
    
    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
    
    public void render (Window window) {
        clear();
        
        shader.bind();
        
        //bind to vao
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);
        
        //draw vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);
        
        //restore
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        
        shader.unbind();
    }
    
    public void cleanup() {
        if(shader != null) {
            shader.cleanup();
        }
        glDisableVertexAttribArray(0);
        
        //delete vbo
        glBindBuffer(GL_ARRAY_BUFFER,0);
        glDeleteBuffers(vboID);
        
        //delete vao
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoID);
    }
}
