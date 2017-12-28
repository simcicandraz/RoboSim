package robosim;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private final int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    
    public Shader() throws Exception {
        programID = glCreateProgram();
        if(programID == 0) {
            throw new Exception("Problem while creating Shader");
        }
    }
    
    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderID = glCreateShader(shaderType);
        if(shaderID == 0) {
            throw new Exception("Error creating Shader. "+ shaderType);
        }
        //System.out.println(shaderCode);
        glShaderSource(shaderID, shaderCode);
        glCompileShader(shaderID);
        
        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error creating Shader. "+ glGetShaderInfoLog(shaderID));
        }
        
        glAttachShader(programID, shaderID);
        return shaderID;
    }
    
    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderID = createShader(shaderCode, GL_VERTEX_SHADER);
    }
    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderID = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }
    
    public void bind() {
        glUseProgram(programID);
    }
    
    public void unbind() {
        glUseProgram(0);
    }
    
    public void cleanup() {
        unbind();
        if (programID != 0) {
            glDeleteProgram(programID);
        }
    }
    
    public void link() throws Exception{
        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader");
        }
        if(vertexShaderID != 0) {
            glDetachShader(programID, vertexShaderID);
        }
        if(fragmentShaderID != 0) {
            glDetachShader(programID, fragmentShaderID);
        }
        glValidateProgram(programID);
        if(glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader");
        }
    }
}
