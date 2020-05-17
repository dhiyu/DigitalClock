import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
/*
 * Created by JFormDesigner on Tue May 12 12:54:18 CST 2020
 */



/**
 * @author Yu Shi
 */
public class Clock extends JFrame {
    public Clock() {
        //初始化组件
        initComponents();
        //打开当前时间更新线程
        Thread thread = new Thread(new UpdateTime(currentTime));
        thread.start();
        
    }

    private void AddCountdownButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        int hours = (int) hours2.getValue();
        int minutes = (int) minutes2.getValue();
        int seconds = (int) seconds2.getValue();
        
        countdown.setText(String.format("%2d: %02d: %02d", hours, minutes, seconds));
    }

    private void startMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void hangOnMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void resetMouseClicked(MouseEvent e) {
        // TODO add your code here

    }

    private void hours2MouseWheelMoved(MouseWheelEvent e) {
        // TODO add your code here
    }

    private void hours2StateChanged(ChangeEvent e) {
        // TODO add your code here
        //0表示无穷大
        restrictMaxAndMin(hours2, 0);
    }

    private void minutes2StateChanged(ChangeEvent e) {
        // TODO add your code here
        restrictMaxAndMin(minutes2, 59);
    }

    private void seconds2StateChanged(ChangeEvent e) {
        // TODO add your code here
        restrictMaxAndMin(seconds2, 59);
    }
    
    private void restrictMaxAndMin(JSpinner jSpinner, int max) {
        int currentValue = (int) jSpinner.getValue();
        if (max != 0 && currentValue > max){
            jSpinner.setValue(max);
        }
        else if (currentValue < 0){
            jSpinner.setValue(0);
        }
    }

    private void hoursStateChanged(ChangeEvent e) {
        // TODO add your code here
        restrictMaxAndMin(hours, 0);
    }

    private void minutesStateChanged(ChangeEvent e) {
        // TODO add your code here
        restrictMaxAndMin(minutes, 59);
    }

    private void secondsStateChanged(ChangeEvent e) {
        // TODO add your code here
        restrictMaxAndMin(seconds, 59);
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yu Shi
        MainPanel = new JTabbedPane();
        Clock = new JPanel();
        panel5 = new JPanel();
        label10 = new JLabel();
        currentTime = new JLabel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();
        panel4 = new JPanel();
        Alarm = new JPanel();
        AddAlarmPanel = new JPanel();
        panel9 = new JPanel();
        panel17 = new JPanel();
        hours = new JSpinner();
        label2 = new JLabel();
        panel18 = new JPanel();
        minutes = new JSpinner();
        label4 = new JLabel();
        panel19 = new JPanel();
        seconds = new JSpinner();
        label5 = new JLabel();
        panel16 = new JPanel();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();
        panel20 = new JPanel();
        AddAlarmButton = new JButton();
        CountDown = new JPanel();
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
        checkBox1 = new JCheckBox();
        AddCountdownButton = new JButton();
        panel14 = new JPanel();
        label1 = new JLabel();
        countdown = new JLabel();
        About = new JPanel();
        panel1 = new JPanel();
        label9 = new JLabel();
        label8 = new JLabel();

        //======== this ========
        setTitle("\u591a\u529f\u80fd\u6570\u5b57\u949f");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== MainPanel ========
        {
            MainPanel.setBorder(new EtchedBorder());

            //======== Clock ========
            {
                Clock.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,Clock. getBorder
                ( )) ); Clock. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );
                Clock.setLayout(new BoxLayout(Clock, BoxLayout.X_AXIS));

                //======== panel5 ========
                {
                    panel5.setLayout(new BorderLayout());

                    //---- label10 ----
                    label10.setText("\u5f53\u524d\u65f6\u95f4:");
                    label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 3f));
                    label10.setHorizontalAlignment(SwingConstants.CENTER);
                    panel5.add(label10, BorderLayout.WEST);

                    //---- currentTime ----
                    currentTime.setHorizontalAlignment(SwingConstants.CENTER);
                    currentTime.setFont(currentTime.getFont().deriveFont(currentTime.getFont().getStyle() | Font.BOLD, currentTime.getFont().getSize() + 10f));
                    panel5.add(currentTime, BorderLayout.CENTER);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new BorderLayout());
                    }
                    panel5.add(panel6, BorderLayout.NORTH);

                    //======== panel7 ========
                    {
                        panel7.setLayout(new BorderLayout());
                    }
                    panel5.add(panel7, BorderLayout.SOUTH);

                    //======== panel8 ========
                    {
                        panel8.setLayout(new BorderLayout());
                    }
                    panel5.add(panel8, BorderLayout.EAST);
                }
                Clock.add(panel5);

                //======== panel4 ========
                {
                    panel4.setLayout(new BorderLayout());
                }
                Clock.add(panel4);
            }
            MainPanel.addTab("\u65f6\u949f", null, Clock, "\u4e16\u754c\u65f6\u949f");

            //======== Alarm ========
            {
                Alarm.setLayout(new BorderLayout());

                //======== AddAlarmPanel ========
                {
                    AddAlarmPanel.setBorder(new TitledBorder("\u8bbe\u7f6e\u95f9\u949f"));
                    AddAlarmPanel.setFont(AddAlarmPanel.getFont().deriveFont(AddAlarmPanel.getFont().getSize() + 6f));
                    AddAlarmPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    AddAlarmPanel.setLayout(new GridLayout(0, 1));

                    //======== panel9 ========
                    {
                        panel9.setLayout(new GridLayout(3, 1));

                        //======== panel17 ========
                        {
                            panel17.setLayout(new FlowLayout());

                            //---- hours ----
                            hours.setFont(hours.getFont().deriveFont(hours.getFont().getSize() + 2f));
                            hours.addChangeListener(e -> hoursStateChanged(e));
                            panel17.add(hours);

                            //---- label2 ----
                            label2.setText("\u65f6");
                            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
                            panel17.add(label2);
                        }
                        panel9.add(panel17);

                        //======== panel18 ========
                        {
                            panel18.setLayout(new FlowLayout());

                            //---- minutes ----
                            minutes.setFont(minutes.getFont().deriveFont(minutes.getFont().getSize() + 2f));
                            minutes.addChangeListener(e -> minutesStateChanged(e));
                            panel18.add(minutes);

                            //---- label4 ----
                            label4.setText("\u5206");
                            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
                            panel18.add(label4);
                        }
                        panel9.add(panel18);

                        //======== panel19 ========
                        {
                            panel19.setLayout(new FlowLayout());

                            //---- seconds ----
                            seconds.setFont(seconds.getFont().deriveFont(seconds.getFont().getSize() + 2f));
                            seconds.addChangeListener(e -> secondsStateChanged(e));
                            panel19.add(seconds);

                            //---- label5 ----
                            label5.setText("\u79d2");
                            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 5f));
                            panel19.add(label5);
                        }
                        panel9.add(panel19);
                    }
                    AddAlarmPanel.add(panel9);

                    //======== panel16 ========
                    {
                        panel16.setLayout(new GridLayout());

                        //---- checkBox2 ----
                        checkBox2.setText("\u58f0\u97f3\u5f00\u5173");
                        checkBox2.setHorizontalAlignment(SwingConstants.CENTER);
                        panel16.add(checkBox2);

                        //---- checkBox3 ----
                        checkBox3.setText("\u95f9\u949f\u5f00\u5173");
                        checkBox3.setHorizontalAlignment(SwingConstants.CENTER);
                        panel16.add(checkBox3);

                        //======== panel20 ========
                        {
                            panel20.setLayout(new BoxLayout(panel20, BoxLayout.X_AXIS));

                            //---- AddAlarmButton ----
                            AddAlarmButton.setText("\u8bbe\u7f6e\u95f9\u949f");
                            AddAlarmButton.setFont(AddAlarmButton.getFont().deriveFont(AddAlarmButton.getFont().getSize() + 3f));
                            panel20.add(AddAlarmButton);
                        }
                        panel16.add(panel20);
                    }
                    AddAlarmPanel.add(panel16);
                }
                Alarm.add(AddAlarmPanel, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u95f9\u949f\u8bbe\u7f6e", null, Alarm, "\u8bbe\u7f6e\u95f9\u949f\u4fe1\u606f");

            //======== CountDown ========
            {
                CountDown.setLayout(new BorderLayout(0, 5));

                //======== panel15 ========
                {
                    panel15.setFont(panel15.getFont().deriveFont(panel15.getFont().getSize() + 5f));
                    panel15.setLayout(new FlowLayout());

                    //---- start ----
                    start.setText("\u5f00\u59cb");
                    start.setFont(start.getFont().deriveFont(start.getFont().getSize() + 5f));
                    start.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            startMouseClicked(e);
                        }
                    });
                    panel15.add(start);

                    //---- hangOn ----
                    hangOn.setText("\u6682\u505c");
                    hangOn.setFont(hangOn.getFont().deriveFont(hangOn.getFont().getSize() + 5f));
                    hangOn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            hangOnMouseClicked(e);
                        }
                    });
                    panel15.add(hangOn);

                    //---- reset ----
                    reset.setText("\u590d\u4f4d");
                    reset.setFont(reset.getFont().deriveFont(reset.getFont().getSize() + 5f));
                    reset.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            resetMouseClicked(e);
                        }
                    });
                    panel15.add(reset);
                }
                CountDown.add(panel15, BorderLayout.SOUTH);

                //======== AddCountdownPanel ========
                {
                    AddCountdownPanel.setBorder(new TitledBorder("\u8bbe\u7f6e\u5012\u8ba1\u65f6"));
                    AddCountdownPanel.setFont(AddCountdownPanel.getFont().deriveFont(AddCountdownPanel.getFont().getSize() + 6f));
                    AddCountdownPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    AddCountdownPanel.setLayout(new FlowLayout());

                    //---- hours2 ----
                    hours2.setFont(hours2.getFont().deriveFont(hours2.getFont().getSize() + 2f));
                    hours2.setPreferredSize(new Dimension(88, 31));
                    hours2.addMouseWheelListener(e -> hours2MouseWheelMoved(e));
                    hours2.addChangeListener(e -> hours2StateChanged(e));
                    AddCountdownPanel.add(hours2);

                    //---- label3 ----
                    label3.setText("\u65f6");
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
                    AddCountdownPanel.add(label3);

                    //---- minutes2 ----
                    minutes2.setFont(minutes2.getFont().deriveFont(minutes2.getFont().getSize() + 2f));
                    minutes2.addChangeListener(e -> minutes2StateChanged(e));
                    AddCountdownPanel.add(minutes2);

                    //---- label6 ----
                    label6.setText("\u5206");
                    label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 5f));
                    AddCountdownPanel.add(label6);

                    //---- seconds2 ----
                    seconds2.setFont(seconds2.getFont().deriveFont(seconds2.getFont().getSize() + 2f));
                    seconds2.addChangeListener(e -> seconds2StateChanged(e));
                    AddCountdownPanel.add(seconds2);

                    //---- label7 ----
                    label7.setText("\u79d2");
                    label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 5f));
                    AddCountdownPanel.add(label7);

                    //---- checkBox1 ----
                    checkBox1.setText("\u6253\u5f00\u58f0\u97f3\u63d0\u9192");
                    AddCountdownPanel.add(checkBox1);

                    //---- AddCountdownButton ----
                    AddCountdownButton.setText("\u8bbe\u7f6e\u5012\u8ba1\u65f6");
                    AddCountdownButton.setFont(AddCountdownButton.getFont().deriveFont(AddCountdownButton.getFont().getSize() + 3f));
                    AddCountdownButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            AddCountdownButtonMouseClicked(e);
                            AddCountdownButtonMouseClicked(e);
                        }
                    });
                    AddCountdownPanel.add(AddCountdownButton);
                }
                CountDown.add(AddCountdownPanel, BorderLayout.NORTH);

                //======== panel14 ========
                {
                    panel14.setLayout(new BorderLayout());

                    //---- label1 ----
                    label1.setText("\u5269\u4f59\u65f6\u95f4:");
                    label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
                    panel14.add(label1, BorderLayout.WEST);

                    //---- countdown ----
                    countdown.setText("0: 00: 00");
                    countdown.setHorizontalAlignment(SwingConstants.CENTER);
                    countdown.setFont(countdown.getFont().deriveFont(countdown.getFont().getStyle() | Font.BOLD, countdown.getFont().getSize() + 10f));
                    panel14.add(countdown, BorderLayout.CENTER);
                }
                CountDown.add(panel14, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u5012\u8ba1\u65f6", null, CountDown, "\u5012\u8ba1\u65f6");

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
                    label8.setText("<html> <p style=\"text-align:center\">2019-2020\u7b2c\u4e8c\u5b66\u671f<br>  Q17010307 \u77f3\u5b87<br>  JAVA\u5927\u4f5c\u4e1a<br> Ver1.0.2<br></p> </html>");
                    label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD, label8.getFont().getSize() + 5f));
                    label8.setHorizontalAlignment(SwingConstants.CENTER);
                    label8.setHorizontalTextPosition(SwingConstants.CENTER);
                    label8.setBorder(Borders.DLU2);
                    panel1.add(label8);
                }
                About.add(panel1, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u5173\u4e8e", null, About, "\u5173\u4e8e\u6211");
        }
        contentPane.add(MainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yu Shi
    private JTabbedPane MainPanel;
    private JPanel Clock;
    private JPanel panel5;
    private JLabel label10;
    private JLabel currentTime;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;
    private JPanel panel4;
    private JPanel Alarm;
    private JPanel AddAlarmPanel;
    private JPanel panel9;
    private JPanel panel17;
    private JSpinner hours;
    private JLabel label2;
    private JPanel panel18;
    private JSpinner minutes;
    private JLabel label4;
    private JPanel panel19;
    private JSpinner seconds;
    private JLabel label5;
    private JPanel panel16;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JPanel panel20;
    private JButton AddAlarmButton;
    private JPanel CountDown;
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
    private JCheckBox checkBox1;
    private JButton AddCountdownButton;
    private JPanel panel14;
    private JLabel label1;
    private JLabel countdown;
    private JPanel About;
    private JPanel panel1;
    private JLabel label9;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
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
                    + (calendar.get(Calendar.MONTH) + 1) + "月"
                    + calendar.get(Calendar.DATE) + "日"
                    + String.format("%2d", calendar.get(Calendar.HOUR_OF_DAY)) + ":"
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
