/**
 * @author shiyu
 */
public class Countdown{
    private int seconds;
    final Clock clock;
    TimerTask timerTask;

    public Countdown(int seconds, Clock clock) {
        this.seconds = seconds;
        this.clock = clock;
    }

    /**
     *
     */
    public void start() {
            timerTask = new TimerTask(this.seconds, clock);
            timerTask.start();
    }

    /**
     *
     */
    public void hangOn() {
        timerTask.interrupt();
        this.seconds = timerTask.getTime();
    }

    /**
     *
     */
    public void reset(int seconds) {
        timerTask.interrupt();
        this.seconds = seconds;
        clock.updateCountdown(seconds);
    }

}

class TimerTask extends Thread {
    private int seconds;
    final Clock clock;

    public TimerTask(int seconds, Clock clock) {
        this.seconds = seconds;
        this.clock = clock;
    }

    public int getTime() {
        return seconds;
    }
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (this.seconds > 0) {
            this.seconds--;
            clock.updateCountdown(seconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
        clock.countdownEnd();
    }
}