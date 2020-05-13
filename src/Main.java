import java.util.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Clock clock = new Clock();
        clock.setVisible(true);
        Thread thread = new Thread(new UpdateTime(clock.getJLabel()));
        thread.start();
    }


}

class UpdateTime implements Runnable {
    private JLabel jLabel;

    public UpdateTime(JLabel jlabel) {
        this.jLabel = jlabel;
    }
    @Override
    public void run() {
        while(true) {
            Calendar calendar = Calendar.getInstance();
            String currentTime = calendar.get(Calendar.YEAR) + "年"
                    + calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DATE) + "日"
                    + String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                    + String.format("%02d", calendar.get(Calendar.MINUTE)) + ":"
                    + String.format("%02d", calendar.get(Calendar.SECOND));
            this.jLabel.setText(currentTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}