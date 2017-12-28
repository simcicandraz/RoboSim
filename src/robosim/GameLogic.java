package robosim;

public class GameLogic {
    private Renderer renderer;
    
    public GameLogic() {
        renderer = new Renderer();
    }
    
    public void init() throws Exception {
        renderer.init();
    }
    
    public void update(float interval) {
        
    }
    
    public void render(Window window) {
        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        renderer.render(window);
    }
    
    public void cleanup() {
        renderer.cleanup();
    }
}
