import java.util.*;

public class Alarm {
    private Timer timer;
    private Clock clock;
    private Calendar calendar;
    private TimeZone timeZone;
    private int hour;
    private int minute;
    private int second;


    public Alarm(Clock clock, TimeZone timeZone) {
        this.clock = clock;
        setAlarmTime(0, 0, 0, timeZone);
        timer = new Timer();
    }

    public void onAlarm() {
        //获取当前时间
        Calendar now_time = Calendar.getInstance(timeZone);
        //设置闹钟时间
        calendar = Calendar.getInstance(timeZone);
        calendar.set(Calendar.HOUR_OF_DAY, this.hour);
        calendar.set(Calendar.MINUTE, this.minute);
        calendar.set(Calendar.SECOND, this.second);
        calendar.set(Calendar.MILLISECOND, 0);
        //闹钟时间小于当前时间则加一天
        if (calendar.compareTo(now_time) < 0) {
            calendar.add(Calendar.DATE, 1);
        }
        //设置定时任务
        System.out.println("新建闹钟");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clock.alarmEnd();
            }
        }, calendar.getTime());
    }

    public void setAlarmTime(int hour, int minute, int second, TimeZone timeZone) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.timeZone = timeZone;
    }

    public void offAlarm() {
        System.out.println("闹钟取消");
        timer.cancel();
        timer = new Timer();
    }

    public void delayAlarm() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clock.alarmEnd();
            }
        }, 30000);
    }
}
