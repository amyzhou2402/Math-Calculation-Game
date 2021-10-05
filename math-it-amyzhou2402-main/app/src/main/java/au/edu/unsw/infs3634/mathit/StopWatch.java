package au.edu.unsw.infs3634.mathit;

public class StopWatch {
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    public String getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        } else {
            elapsed = (stopTime - startTime);
        }
        String tvTimer = "Hey, not bad! It only took you: " + (elapsed/1000) + " seconds and " + (elapsed%1000) + " milliseconds to complete the question. Let's try to beat that next time!";
        return tvTimer;
    }
}
