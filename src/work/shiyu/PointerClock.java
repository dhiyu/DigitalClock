package work.shiyu;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;

public class PointerClock extends JPanel {
    TimeZone timeZone = TimeZone.getDefault();

    public void changeTimeZone(TimeZone newTimeZone) {
         timeZone = newTimeZone;
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);

        Calendar calendar = Calendar.getInstance(timeZone);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        //参数
        int clockRadius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;

        //表盘
        g.setColor(Color.WHITE);
        g.drawOval(xCenter - clockRadius,yCenter - clockRadius,
                2 * clockRadius,2 * clockRadius);

        //刻度
        g.drawString("12",xCenter - 5,yCenter - clockRadius + 12);
        g.drawString("9",xCenter - clockRadius + 3,yCenter + 5);
        g.drawString("3",xCenter + clockRadius - 10,yCenter + 3);
        g.drawString("6",xCenter - 3,yCenter + clockRadius - 3);

        //秒针
        int sLength=(int)(clockRadius * 0.9);
        int xSecond=(int)(xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
        int ySecond=(int)(yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
        g.setColor(Color.red);
        g.drawLine(xCenter, yCenter, xSecond, ySecond);

        //分针
        int mLength=(int)(clockRadius * 0.75);
        int xMinute=(int)(xCenter + mLength * Math.sin(minute * (2 * Math.PI / 60)));
        int yMinute=(int)(yCenter - mLength * Math.cos(minute * (2 * Math.PI / 60)));
        g.setColor(Color.blue);
        g.drawLine(xCenter, yCenter, xMinute, yMinute);

        //时针
        int hLength=(int)(clockRadius * 0.6);
        int xHour=(int)(xCenter + hLength * Math.sin((hour + minute / 60.0) * (2 * Math.PI / 12)));
        int yHour=(int)(yCenter - hLength * Math.cos((hour + minute / 60.0) * (2 * Math.PI / 12)));
        g.setColor(Color.green);
        g.drawLine(xCenter, yCenter, xHour, yHour);
    }
}
