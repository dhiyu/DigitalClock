import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
/*
 * Created by JFormDesigner on Tue May 12 12:54:18 CST 2020
 */


/**
 * @author Yu Shi
 */
public class Clock extends JFrame {
    //--------------变量区start--------------//
    private Countdown countdownTask = null;
    private int countdownTime;
    private boolean alarmRemainderSwitch = false;
    private boolean alarmVoiceSwitch = false;
    private boolean countdownVoiceSwitch = false;
    public Toast msgToast;
    private MusicPlayer musicPlayer;
    private Calendar calendar;
    private String zoneGMT = "GMT+8:00";
    private TimeZone timeZone = TimeZone.getDefault();
    private Alarm alarm;
    private int alarmHour = 0;
    private int alarmMinute = 0;
    private int alarmSecond = 0;
    //--------------变量区end--------------//


    //--------------时钟区start--------------//
    public Clock() {
        //初始化组件
        initComponents();

        //初始化状态
        addCountdownButton.setEnabled(false);
        start.setEnabled(false);
        hangOn.setEnabled(false);
        reset.setEnabled(false);

        //打开当前时间更新线程
        Thread thread = new Thread(new UpdateTime(currentTime));
        thread.start();

        //初始化音乐播放器
        musicPlayer = new MusicPlayer();

        //初始化闹钟
        alarm = new Alarm(this, timeZone);

    }

    /**
     * @author shiyu
     */
    class UpdateTime implements Runnable {
        private JLabel jLabel;
        HashMap<Integer, String> week = new HashMap<>();

        public UpdateTime(JLabel jlabel) {
            this.jLabel = jlabel;

            //从数字转换成星期
            week.put(1, "日");
            week.put(2, "一");
            week.put(3, "二");
            week.put(4, "三");
            week.put(5, "四");
            week.put(6, "五");
            week.put(7, "六");
        }
        @Override
        public void run() {
            while(true) {
                calendar = Calendar.getInstance(timeZone);
                String currentTime = "<html><p style=\"text-align:center\">"
                        + calendar.get(Calendar.YEAR) + "年"
                        + (calendar.get(Calendar.MONTH) + 1) + "月"
                        + calendar.get(Calendar.DATE) + "日" + "<br>"
                        + "星期" + week.get(calendar.get(Calendar.DAY_OF_WEEK)) + "<br>"
                        + String.format("%2d", calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                        + String.format("%02d", calendar.get(Calendar.MINUTE)) + ":"
                        + String.format("%02d", calendar.get(Calendar.SECOND))
                        + "</p></html>";
                this.jLabel.setText(currentTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param e
     */
    private void hoursStateChanged(ChangeEvent e) {
        restrictMaxAndMin(hours, 23);
    }

    /**
     *
     * @param e
     */
    private void minutesStateChanged(ChangeEvent e) {
        restrictMaxAndMin(minutes, 59);
    }

    /**
     *
     * @param e
     */
    private void secondsStateChanged(ChangeEvent e) {
        restrictMaxAndMin(seconds, 59);
    }

    /**
     *
     * @param e
     */
    private void selectZoneItemStateChanged(ItemEvent e) {
        String zone = e.getItem().toString().split("\\(")[1];
        zone = zone.split("\\)")[0];
        if (zoneGMT.equals(zone)) {
            return;
        }
        else {
            timeZone=TimeZone.getTimeZone(zone);
            zoneGMT = zone;
            msgToast.setMessage("时区和闹钟已切换到" + zone);
            if (alarmRemainderSwitch) {
                //重设闹钟
                alarm.offAlarm();
                alarm.setAlarmTime(alarmHour, alarmMinute, alarmSecond, timeZone);
                alarm.onAlarm();
            }
            else {
                alarm.setAlarmTime(alarmHour, alarmMinute, alarmSecond, timeZone);
            }
        }
    }

    private void alarmSwitchActionPerformed(ActionEvent e) {
        alarmRemainderSwitch = alarmSwitch.isSelected();
        if (alarmRemainderSwitch) {
            msgToast.setMessage("闹钟已打开！");
            System.out.println("按钮闹钟打开");
            alarm.onAlarm();
        }
        else {
            msgToast.setMessage("闹钟已关闭！");
            alarm.offAlarm();
        }
    }

    private void alarmVoiceActionPerformed(ActionEvent e) {
        alarmVoiceSwitch = alarmVoice.isSelected();
        if (alarmVoiceSwitch) {
            msgToast.setMessage("闹钟声音已打开！");
        }
        else {
            msgToast.setMessage("闹钟声音已关闭！");
        }
    }

    private void AddAlarmButtonActionPerformed(ActionEvent e) {
        alarmHour = (int)hours.getValue();
        alarmMinute = (int)minutes.getValue();
        alarmSecond = (int)seconds.getValue();
        alarmTime.setText(String.format("%2d", alarmHour) + ":"
                + String.format("%02d", alarmMinute) + ":"
                + String.format("%02d", alarmSecond));
        if (alarmRemainderSwitch) {
            alarm.offAlarm();
            alarm.setAlarmTime(alarmHour, alarmMinute, alarmSecond, timeZone);
            System.out.println("添加时打开");
            alarm.onAlarm();
        }
        else {
            alarm.setAlarmTime(alarmHour, alarmMinute, alarmSecond, timeZone);
        }
        msgToast.setMessage("设置闹钟成功！");
    }

    public void alarmEnd() {
        alarmSwitch.setSelected(false);
        alarmRemainderSwitch = false;
        //播放音乐
        if (alarmVoiceSwitch)
            musicPlayer.play();

        //弹窗提示
        Object[] options = {"停止", "延迟再响"};
        int selected_value;
        selected_value = JOptionPane.showOptionDialog(this, "闹钟时间到！", "闹钟",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (selected_value == 1) {
            System.out.println("延迟再响");
            alarm.delayAlarm();
            msgToast.setMessage("30S后将再次响铃！");
        }

        //停止播放
        if (alarmVoiceSwitch)
            musicPlayer.stop();
    }
    //--------------时钟区end--------------//


    //--------------倒计时部分start--------------//
    private void addCountdownButtonActionPerformed(ActionEvent e) {
        int hours = (int) hours2.getValue();
        int minutes = (int) minutes2.getValue();
        int seconds = (int) seconds2.getValue();
        countdownTime = hours * 3600 + minutes * 60 + seconds;

        countdown.setText(String.format("%2d: %02d: %02d", hours, minutes, seconds));
        countdownTask = new Countdown(countdownTime, this);

        start.setEnabled(true);

        msgToast.setMessage("时长已设置！");
    }

    private void startActionPerformed(ActionEvent e) {
        countdownTask.start();

        addCountdownButton.setEnabled(false);
        start.setEnabled(false);
        hangOn.setEnabled(true);
        reset.setEnabled(false);

        msgToast.setMessage("倒计时开始！");
    }

    private void hangOnActionPerformed(ActionEvent e) {
        countdownTask.hangOn();

        addCountdownButton.setEnabled(true);
        start.setEnabled(true);
        reset.setEnabled(true);
        hangOn.setEnabled(false);

        msgToast.setMessage("倒计时暂停！");
    }

    private void resetActionPerformed(ActionEvent e) {
        countdownTask.reset(countdownTime);

        msgToast.setMessage("倒计时复位！");
    }

    /**
     *
     * @param seconds
     */
    public void updateCountdown(int seconds) {
        countdown.setText(String.format("%2d: %02d: %02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));
    }

    /**
     *
     */
    public void countdownEnd() {
        
        addCountdownButton.setEnabled(true);
        start.setEnabled(false);
        hangOn.setEnabled(false);
        reset.setEnabled(false);

        //播放音乐
        if (countdownVoiceSwitch)
            musicPlayer.play();

        //弹窗提示
        JOptionPane.showMessageDialog(this, "倒计时时间到！", "倒计时提醒",
                JOptionPane.INFORMATION_MESSAGE);

        //停止播放
        if (countdownVoiceSwitch)
            musicPlayer.stop();
    }

    /**
     *
     * @param e
     */
    private void hours2StateChanged(ChangeEvent e) {
        //0表示无穷大
        restrictMaxAndMin(hours2, 0);
        checkCountdownTime();
    }

    /**
     *
     * @param e
     */
    private void minutes2StateChanged(ChangeEvent e) {
        restrictMaxAndMin(minutes2, 59);
        checkCountdownTime();
    }

    /**
     *
     * @param e
     */
    private void seconds2StateChanged(ChangeEvent e) {
        restrictMaxAndMin(seconds2, 59);
        checkCountdownTime();
    }

    /**
     *
     */
    private void checkCountdownTime() {
        int hours = (int)hours2.getValue();
        int minutes = (int)minutes2.getValue();
        int seconds = (int)seconds2.getValue();
        if (hours == 0 && minutes == 0 && seconds == 0){
            addCountdownButton.setEnabled(false);
        }
        else {
            addCountdownButton.setEnabled(true);
        }
    }

    private void countdownVoiceActionPerformed(ActionEvent e) {
        countdownVoiceSwitch = countdownVoice.isSelected();
        if (countdownVoiceSwitch) {
            msgToast.setMessage("倒计时提醒声音已打开！");
        }
        else {
            msgToast.setMessage("倒计时提醒声音已关闭！");
        }
    }
    //--------------倒计时部分end--------------//


    //--------------通用部分start--------------//
    /**
     *
     * @param jSpinner
     * @param max
     */
    private void restrictMaxAndMin(JSpinner jSpinner, int max) {
        int currentValue = (int) jSpinner.getValue();
        if (max != 0 && currentValue > max){
            jSpinner.setValue(max);
            msgToast.setMessage("老兄，太大了，设不上去了……");
        }
        else if (currentValue < 0){
            jSpinner.setValue(0);
            msgToast.setMessage("别捣乱^&*&^%*^$#%不能再减了TNT");
        }
    }

    public JLabel getMessage() {
        return message;
    }
    //--------------通用部分end--------------//


    //--------------自动生成部分start--------------//
    /**
     *
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yu Shi
        toast = new JPanel();
        message = new JLabel();
        MainPanel = new JTabbedPane();
        Clock = new JPanel();
        panel5 = new JPanel();
        panel4 = new JPanel();
        currentTime = new JLabel();
        panel7 = new JPanel();
        label15 = new JLabel();
        panel6 = new JPanel();
        selectZone = new JComboBox<>();
        panel2 = new JPanel();
        label11 = new JLabel();
        panel3 = new JPanel();
        label12 = new JLabel();
        alarmTime = new JLabel();
        label14 = new JLabel();
        alarmSwitch = new JCheckBox();
        alarmVoice = new JCheckBox();
        label19 = new JLabel();
        panel8 = new JPanel();
        hours = new JSpinner();
        label2 = new JLabel();
        minutes = new JSpinner();
        label4 = new JLabel();
        seconds = new JSpinner();
        label5 = new JLabel();
        AddAlarmButton = new JButton();
        Countdown = new JPanel();
        panel15 = new JPanel();
        start = new JButton();
        hangOn = new JButton();
        reset = new JButton();
        AddCountdownPanel = new JPanel();
        hours2 = new JSpinner();
        label3 = new JLabel();
        minutes2 = new JSpinner();
        label6 = new JLabel();
        seconds2 = new JSpinner();
        label7 = new JLabel();
        countdownVoice = new JCheckBox();
        addCountdownButton = new JButton();
        panel14 = new JPanel();
        countdown = new JLabel();
        About = new JPanel();
        panel1 = new JPanel();
        label9 = new JLabel();
        label8 = new JLabel();

        //======== this ========
        setTitle("\u591a\u529f\u80fd\u6570\u5b57\u949f");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== toast ========
        {
            toast.setBorder(new TitledBorder("\u6d88\u606f\u63d0\u793a"));
//            toast.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
//            javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e",javax
//            .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
//            .awt.Font("Dialo\u0067",java.awt.Font.BOLD,12),java.awt
//            .Color.red),toast. getBorder()));toast. addPropertyChangeListener(new java.beans.
//            PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("borde\u0072".
//            equals(e.getPropertyName()))throw new RuntimeException();}});
            toast.setLayout(new BorderLayout(5, 5));

            //---- message ----
            message.setText("    ");
            message.setFont(message.getFont().deriveFont(message.getFont().getSize() + 12f));
            toast.add(message, BorderLayout.CENTER);
        }
        contentPane.add(toast, BorderLayout.SOUTH);

        //======== MainPanel ========
        {
            MainPanel.setBorder(new EtchedBorder());

            //======== Clock ========
            {
                Clock.setLayout(new BoxLayout(Clock, BoxLayout.Y_AXIS));

                //======== panel5 ========
                {
                    panel5.setLayout(new GridBagLayout());
                    ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                    ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
                    ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 1.0E-4};
                    ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {1.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //======== panel4 ========
                    {
                        panel4.setBorder(new TitledBorder("\u5f53\u524d\u65f6\u95f4"));
                        panel4.setLayout(new BorderLayout());

                        //---- currentTime ----
                        currentTime.setHorizontalAlignment(SwingConstants.CENTER);
                        currentTime.setFont(currentTime.getFont().deriveFont(currentTime.getFont().getStyle() | Font.BOLD, currentTime.getFont().getSize() + 15f));
                        panel4.add(currentTime, BorderLayout.CENTER);
                    }
                    panel5.add(panel4, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel7 ========
                    {
                        panel7.setLayout(new FlowLayout());

                        //---- label15 ----
                        label15.setText("     \u65f6\u533a\u9009\u62e9:     ");
                        label15.setHorizontalAlignment(SwingConstants.CENTER);
                        label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 3f));
                        panel7.add(label15);
                    }
                    panel5.add(panel7, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel6 ========
                    {
                        panel6.setLayout(new FlowLayout());

                        //---- selectZone ----
                        selectZone.setModel(new DefaultComboBoxModel<>(new String[] {
                            "\u4e2d\u56fd-\u5317\u4eac(GMT+8:00)",
                            "\u4e2d\u56fd-\u9999\u6e2f(GMT+8:00)",
                            "\u82f1\u56fd-\u4f26\u6566(GMT+0:00)",
                            "\u51b0\u5c9b-\u96f7\u514b\u96c5\u672a\u514b(GMT+0:00)",
                            "\u7231\u5c14\u5170-\u90fd\u67cf\u6797(GMT+0:00)",
                            "\u963f\u5c14\u53ca\u5229\u4e9a-\u963f\u5c14\u53ca\u5c14(GMT+0:00)",
                            "\u82f1\u56fd-\u683c\u6797\u5a01\u6cbb(GMT+0:00)",
                            "\u632a\u5a01-\u5965\u65af\u9646(GMT+1:00)",
                            "\u5fb7\u56fd-\u67cf\u6797(GMT+1:00)",
                            "\u5965\u5730\u5229-\u7ef4\u4e5f\u7eb3(GMT+1:00)",
                            "\u610f\u5927\u5229-\u7f57\u9a6c(GMT+1:00)",
                            "\u7a81\u5c3c\u65af(GMT+1:00)",
                            "\u571f\u8033\u5176-\u5b89\u5361\u62c9(GMT+2:00)",
                            "\u57c3\u53ca-\u5f00\u7f57(GMT+2:00)",
                            "\u4fc4\u7f57\u65af-\u83ab\u65af\u79d1(GMT+3:00)",
                            "\u4f0a\u62c9\u514b-\u5df4\u683c\u8fbe(GMT+3:00)",
                            "\u4ee5\u8272\u5217-\u8036\u8def\u6492\u51b7(GMT+3:00)",
                            "\u5df4\u52d2\u65af\u5766-\u8036\u8def\u6492\u51b7(GMT+3:00)",
                            "\u54c8\u8428\u514b\u65af\u5766-\u963f\u65af\u5854\u7eb3(GMT+5:00)",
                            "\u4e4c\u5179\u522b\u514b\u65af\u5766-\u5854\u4ec0\u5e72(GMT+5:00)",
                            "\u5370\u5ea6-\u65b0\u5fb7\u91cc(GMT+5:00)",
                            "\u5df4\u57fa\u65af\u5766-\u4f0a\u65af\u5170\u5821(GMT+5:00)",
                            "\u5df4\u57fa\u65af\u5766-\u5361\u62c9\u68cb(GMT+5:00)",
                            "\u5b5f\u52a0\u62c9\u56fd-\u8fbe\u5361(GMT+6:00)",
                            "\u8d8a\u5357-\u6cb3\u5185(GMT+7:00)",
                            "\u8001\u631d-\u4e07\u8c61(GMT+7:00)",
                            "\u6cf0\u56fd-\u66fc\u8c37(GMT+7:00)",
                            "\u67ec\u57d4\u5be8-\u91d1\u8fb9(GMT+7:00)",
                            "\u65b0\u52a0\u5761(GMT+7:00)",
                            "\u671d\u9c9c-\u5e73\u58e4(GMT+9:00)",
                            "\u97e9\u56fd-\u9996\u5c14(GMT+9:00)",
                            "\u65e5\u672c-\u4e1c\u4eac(GMT+9:00)",
                            "\u6fb3\u5927\u5229\u4e9a-\u6089\u5c3c(GMT+10:00)",
                            "\u6fb3\u5927\u5229\u4e9a-\u582a\u57f9\u62c9(GMT+10:00)",
                            "\u65b0\u897f\u5170-\u60e0\u7075\u987f(GMT+12:00)",
                            "\u7f8e\u56fd-\u6a80\u9999\u5c71(GMT-10:00)",
                            "\u7f8e\u56fd-\u534e\u76db\u987f(GMT-5:00)",
                            "\u7f8e\u56fd-\u7ebd\u7ea6(GMT-5:00)",
                            "\u79d8\u9c81-\u5229\u9a6c(GMT-5:00)",
                            "\u5384\u74dc\u591a\u5c14-\u57fa\u591a(GMT-5:00)",
                            "\u667a\u5229-\u5723\u5730\u4e9a\u54e5(GMT-5:00)",
                            "\u5df4\u897f-\u5df4\u897f\u5229\u4e9a(GMT-3:00)",
                            "\u65b0\u5580\u91cc\u591a\u5c3c\u4e9a-\u52aa\u963f\u7f8e(GMT+11:00)",
                            "\u683c\u9c81\u5409\u4e9a-\u7b2c\u6bd4\u5229\u65af(GMT+4:00)"
                        }));
                        selectZone.addItemListener(e -> selectZoneItemStateChanged(e));
                        panel6.add(selectZone);
                    }
                    panel5.add(panel6, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new FlowLayout());

                        //---- label11 ----
                        label11.setText("     \u95f9\u949f\u4fe1\u606f:     ");
                        label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 3f));
                        panel2.add(label11);
                    }
                    panel5.add(panel2, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel3 ========
                    {
                        panel3.setLayout(new FlowLayout());

                        //---- label12 ----
                        label12.setText("\u95f9\u949f\u65f6\u95f4:");
                        label12.setFont(label12.getFont().deriveFont(label12.getFont().getSize() + 3f));
                        panel3.add(label12);

                        //---- alarmTime ----
                        alarmTime.setText("0: 00:00");
                        alarmTime.setFont(alarmTime.getFont().deriveFont(alarmTime.getFont().getSize() + 3f));
                        panel3.add(alarmTime);

                        //---- label14 ----
                        label14.setText("                 ");
                        panel3.add(label14);

                        //---- alarmSwitch ----
                        alarmSwitch.setText("\u95f9\u949f\u5f00\u5173");
                        alarmSwitch.setFont(alarmSwitch.getFont().deriveFont(alarmSwitch.getFont().getSize() + 3f));
                        alarmSwitch.addActionListener(e -> alarmSwitchActionPerformed(e));
                        panel3.add(alarmSwitch);

                        //---- alarmVoice ----
                        alarmVoice.setText("\u58f0\u97f3\u5f00\u5173");
                        alarmVoice.setFont(alarmVoice.getFont().deriveFont(alarmVoice.getFont().getSize() + 3f));
                        alarmVoice.addActionListener(e -> alarmVoiceActionPerformed(e));
                        panel3.add(alarmVoice);
                    }
                    panel5.add(panel3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- label19 ----
                    label19.setText("      \u95f9\u949f\u8bbe\u7f6e:     ");
                    label19.setFont(label19.getFont().deriveFont(label19.getFont().getSize() + 3f));
                    panel5.add(label19, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

                    //======== panel8 ========
                    {
                        panel8.setLayout(new FlowLayout());

                        //---- hours ----
                        hours.setFont(hours.getFont().deriveFont(hours.getFont().getSize() + 2f));
                        hours.setModel(new SpinnerNumberModel(0, null, null, 1));
                        hours.setPreferredSize(new Dimension(60, 31));
                        hours.addChangeListener(e -> {
			hoursStateChanged(e);
			hoursStateChanged(e);
		});
                        panel8.add(hours);

                        //---- label2 ----
                        label2.setText("\u65f6");
                        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
                        panel8.add(label2);

                        //---- minutes ----
                        minutes.setFont(minutes.getFont().deriveFont(minutes.getFont().getSize() + 2f));
                        minutes.setModel(new SpinnerNumberModel(0, null, null, 1));
                        minutes.setPreferredSize(new Dimension(60, 31));
                        minutes.addChangeListener(e -> minutesStateChanged(e));
                        panel8.add(minutes);

                        //---- label4 ----
                        label4.setText("\u5206");
                        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
                        panel8.add(label4);

                        //---- seconds ----
                        seconds.setFont(seconds.getFont().deriveFont(seconds.getFont().getSize() + 2f));
                        seconds.setPreferredSize(new Dimension(60, 31));
                        seconds.addChangeListener(e -> secondsStateChanged(e));
                        panel8.add(seconds);

                        //---- label5 ----
                        label5.setText("\u79d2");
                        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 5f));
                        panel8.add(label5);

                        //---- AddAlarmButton ----
                        AddAlarmButton.setText("\u8bbe\u7f6e\u95f9\u949f\u65f6\u95f4");
                        AddAlarmButton.setFont(AddAlarmButton.getFont().deriveFont(AddAlarmButton.getFont().getSize() + 3f));
                        AddAlarmButton.addActionListener(e -> AddAlarmButtonActionPerformed(e));
                        panel8.add(AddAlarmButton);
                    }
                    panel5.add(panel8, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                Clock.add(panel5);
            }
            MainPanel.addTab("\u65f6\u949f\u548c\u95f9\u949f", null, Clock, "\u4e16\u754c\u65f6\u949f");

            //======== Countdown ========
            {
                Countdown.setLayout(new BorderLayout(0, 5));

                //======== panel15 ========
                {
                    panel15.setFont(panel15.getFont().deriveFont(panel15.getFont().getSize() + 5f));
                    panel15.setBorder(new TitledBorder("\u64cd\u4f5c"));
                    panel15.setLayout(new FlowLayout());

                    //---- start ----
                    start.setText("\u5f00\u59cb");
                    start.setFont(start.getFont().deriveFont(start.getFont().getSize() + 5f));
                    start.addActionListener(e -> startActionPerformed(e));
                    panel15.add(start);

                    //---- hangOn ----
                    hangOn.setText("\u6682\u505c");
                    hangOn.setFont(hangOn.getFont().deriveFont(hangOn.getFont().getSize() + 5f));
                    hangOn.addActionListener(e -> hangOnActionPerformed(e));
                    panel15.add(hangOn);

                    //---- reset ----
                    reset.setText("\u590d\u4f4d");
                    reset.setFont(reset.getFont().deriveFont(reset.getFont().getSize() + 5f));
                    reset.addActionListener(e -> resetActionPerformed(e));
                    panel15.add(reset);
                }
                Countdown.add(panel15, BorderLayout.SOUTH);

                //======== AddCountdownPanel ========
                {
                    AddCountdownPanel.setBorder(new TitledBorder("\u8bbe\u7f6e\u5012\u8ba1\u65f6"));
                    AddCountdownPanel.setFont(AddCountdownPanel.getFont().deriveFont(AddCountdownPanel.getFont().getSize() + 6f));
                    AddCountdownPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    AddCountdownPanel.setLayout(new FlowLayout());

                    //---- hours2 ----
                    hours2.setFont(hours2.getFont().deriveFont(hours2.getFont().getSize() + 2f));
                    hours2.setPreferredSize(new Dimension(60, 31));
                    hours2.addChangeListener(e -> hours2StateChanged(e));
                    AddCountdownPanel.add(hours2);

                    //---- label3 ----
                    label3.setText("\u65f6");
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
                    AddCountdownPanel.add(label3);

                    //---- minutes2 ----
                    minutes2.setFont(minutes2.getFont().deriveFont(minutes2.getFont().getSize() + 2f));
                    minutes2.setPreferredSize(new Dimension(60, 31));
                    minutes2.addChangeListener(e -> minutes2StateChanged(e));
                    AddCountdownPanel.add(minutes2);

                    //---- label6 ----
                    label6.setText("\u5206");
                    label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 5f));
                    AddCountdownPanel.add(label6);

                    //---- seconds2 ----
                    seconds2.setFont(seconds2.getFont().deriveFont(seconds2.getFont().getSize() + 2f));
                    seconds2.setPreferredSize(new Dimension(60, 31));
                    seconds2.addChangeListener(e -> seconds2StateChanged(e));
                    AddCountdownPanel.add(seconds2);

                    //---- label7 ----
                    label7.setText("\u79d2");
                    label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 5f));
                    AddCountdownPanel.add(label7);

                    //---- countdownVoice ----
                    countdownVoice.setText("\u6253\u5f00\u58f0\u97f3\u63d0\u9192");
                    countdownVoice.setFont(countdownVoice.getFont().deriveFont(countdownVoice.getFont().getSize() + 3f));
                    countdownVoice.addActionListener(e -> countdownVoiceActionPerformed(e));
                    AddCountdownPanel.add(countdownVoice);

                    //---- addCountdownButton ----
                    addCountdownButton.setText("\u8bbe\u7f6e\u65f6\u957f");
                    addCountdownButton.setFont(addCountdownButton.getFont().deriveFont(addCountdownButton.getFont().getSize() + 3f));
                    addCountdownButton.addActionListener(e -> addCountdownButtonActionPerformed(e));
                    AddCountdownPanel.add(addCountdownButton);
                }
                Countdown.add(AddCountdownPanel, BorderLayout.NORTH);

                //======== panel14 ========
                {
                    panel14.setBorder(new TitledBorder("\u5269\u4f59\u65f6\u95f4"));
                    panel14.setLayout(new BorderLayout());

                    //---- countdown ----
                    countdown.setText("0: 00: 00");
                    countdown.setHorizontalAlignment(SwingConstants.CENTER);
                    countdown.setFont(countdown.getFont().deriveFont(countdown.getFont().getStyle() | Font.BOLD, countdown.getFont().getSize() + 15f));
                    panel14.add(countdown, BorderLayout.CENTER);
                }
                Countdown.add(panel14, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u5012\u8ba1\u65f6", null, Countdown, "\u5012\u8ba1\u65f6");

            //======== About ========
            {
                About.setLayout(new BorderLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(2, 1, 5, 5));

                    //---- label9 ----
                    label9.setIcon(new ImageIcon(getClass().getResource("/about_122.47482014388px_1210622_easyicon.net.png")));
                    label9.setHorizontalAlignment(SwingConstants.CENTER);
                    panel1.add(label9);

                    //---- label8 ----
                    label8.setText("<html> <p style=\"text-align:center\">2019-2020\u7b2c\u4e8c\u5b66\u671f<br>  Q17010307 \u77f3\u5b87<br>  JAVA\u5927\u4f5c\u4e1a<br> Ver2.0.1<br></p> </html>");
                    label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD, label8.getFont().getSize() + 5f));
                    label8.setHorizontalAlignment(SwingConstants.CENTER);
                    label8.setHorizontalTextPosition(SwingConstants.CENTER);
                    label8.setBorder(new EmptyBorder(5, 5, 5, 5));
                    panel1.add(label8);
                }
                About.add(panel1, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u5173\u4e8e", null, About, "\u5173\u4e8e\u6211");
        }
        contentPane.add(MainPanel, BorderLayout.CENTER);
        setSize(595, 445);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yu Shi
    private JPanel toast;
    private JLabel message;
    private JTabbedPane MainPanel;
    private JPanel Clock;
    private JPanel panel5;
    private JPanel panel4;
    private JLabel currentTime;
    private JPanel panel7;
    private JLabel label15;
    private JPanel panel6;
    private JComboBox<String> selectZone;
    private JPanel panel2;
    private JLabel label11;
    private JPanel panel3;
    private JLabel label12;
    private JLabel alarmTime;
    private JLabel label14;
    private JCheckBox alarmSwitch;
    private JCheckBox alarmVoice;
    private JLabel label19;
    private JPanel panel8;
    private JSpinner hours;
    private JLabel label2;
    private JSpinner minutes;
    private JLabel label4;
    private JSpinner seconds;
    private JLabel label5;
    private JButton AddAlarmButton;
    private JPanel Countdown;
    private JPanel panel15;
    private JButton start;
    private JButton hangOn;
    private JButton reset;
    private JPanel AddCountdownPanel;
    private JSpinner hours2;
    private JLabel label3;
    private JSpinner minutes2;
    private JLabel label6;
    private JSpinner seconds2;
    private JLabel label7;
    private JCheckBox countdownVoice;
    private JButton addCountdownButton;
    private JPanel panel14;
    private JLabel countdown;
    private JPanel About;
    private JPanel panel1;
    private JLabel label9;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    //--------------自动生成部分end--------------//
}
