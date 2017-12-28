package robosim;

public class GameEngine implements Runnable {
    public static final int FPS = 60;
    public static final int UPS = 30;
    private final Window window;
    private final Thread gameThread;
    private final Timer timer;
    
    public GameEngine(int width, int height, boolean vSync) throws Exception {
        gameThread = new Thread(this, "GAME_THREAD");
        window = new Window(width,height,vSync);
        timer = new Timer();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void start() {
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if(osName.contains("Mac")) {
            gameThread.run();
        } else {
            gameThread.start();
        }
    }
    
    protected void init() {
        window.init();
        timer.init();
    }
    
    protected void gameLoop() {
        float pretekliCas;
        float dodatek = 0.0f;
        float interval = 1.0f / UPS;
        
        boolean seIzvaja = true;
        while(seIzvaja && !window.shouldClose()) {
            pretekliCas = timer.getElapsedTime();
            dodatek += pretekliCas;
            
            while(dodatek >= interval) {
                
                dodatek -= interval;
            }
            
            this.render();
            
            if(!window.isvSync()) {
                float fpsLoop = 1.0f / FPS;
                double endTime = timer.getLastLoopTime() + fpsLoop;
                while(timer.getTime() < endTime) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ie) {
                        
                    }
                }
            }
        }
    }
    
    protected void render() {
        window.update();
    }
}
