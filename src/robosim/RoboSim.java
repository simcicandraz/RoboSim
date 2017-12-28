package robosim;


public class RoboSim {
    public static void main(String[] args) {
        try {
            GameEngine game = new GameEngine(1280, 720, true, new GameLogic());
            game.start();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
