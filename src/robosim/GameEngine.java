package robosim;

public class GameEngine implements Runnable {
    public static final int FPS = 60;
    public static final int UPS = 30;
    private final Window window;
    private final Thread gameThread;
    private final Timer timer;
    private final GameLogic gameLogic;
    
    public GameEngine(int width, int height, boolean vSync, GameLogic gameLogic) throws Exception {
        gameThread = new Thread(this, "GAME_THREAD");
        window = new Window(width,height,vSync);
        timer = new Timer();
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            gameLogic.cleanup();
        }
    }
    
    public void start() {
        String osName = System.getProperty("os.name");
        //System.out.println(osName);
        if(osName.contains("Mac")) {
            gameThread.run();
        } else {
            gameThread.start();
        }
    }
    
    protected void init() throws Exception {
        window.init();
        timer.init();
        gameLogic.init();
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
                gameLogic.update(interval);
                dodatek -= interval;
            }
            
            this.render();
            
            if(!window.isvSync()) {
                float fpsLoop = 1.0f / FPS;
                double endTime = timer.getLastLoopTime() + fpsLoop;
                while(timer.getTime() < endTime) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ie) {}
                }
            }
        }
    }
    
    protected void render() {
        window.update();
    }
}
