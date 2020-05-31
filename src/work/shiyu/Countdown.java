package work.shiyu;

/**
 * @author shiyu
 */
public class Countdown{
    private int seconds;
    final Clock clock;
    CountdownTask countdownTask;

    public Countdown(int seconds, Clock clock) {
        this.seconds = seconds;
        this.clock = clock;
    }

    /**
     *
     */
    public void start() {
            countdownTask = new CountdownTask(this.seconds, clock);
            countdownTask.start();
    }

    /**
     *
     */
    public void hangOn() {
        countdownTask.interrupt();
        this.seconds = countdownTask.getTime();
    }

    /**
     *
     */
    public void reset(int seconds) {
        countdownTask.interrupt();
        this.seconds = seconds;
        clock.updateCountdown(seconds);
    }

}

class CountdownTask extends Thread {
    private int seconds;
    final Clock clock;

    public CountdownTask(int seconds, Clock clock) {
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
            if (this.seconds == 5) {
                clock.msgToast.setMessage("倒计时即将结束！");
            }
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