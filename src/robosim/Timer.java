package robosim;

public class Timer {
    
    private double loopTime;
    
    public double getTime() {
        return System.nanoTime()/1000000000.0;
    }
    
    public void init() {
        loopTime = getTime();
    }
    
    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - loopTime);
        loopTime = time;
        return elapsedTime;
    }
    
    public double getLastLoopTime() {
        return loopTime;
    }
}
