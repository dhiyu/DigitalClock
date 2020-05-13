import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
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
        initComponents();
    }
    public JLabel getJLabel(){
        return currentTime;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Yu Shi
        MainPanel = new JTabbedPane();
        Clock = new JPanel();
        label10 = new JLabel();
        currentTime = new JLabel();
        Alarm = new JPanel();
        AddAlarmPanel = new JPanel();
        hours = new JSpinner();
        label2 = new JLabel();
        minutes = new JSpinner();
        label4 = new JLabel();
        seconds = new JSpinner();
        label5 = new JLabel();
        AddAlarmButton = new JButton();
        AlarmInfo = new JScrollPane();
        AlarrmInfo = new JTable();
        Delete = new JPanel();
        DeleteSelected = new JButton();
        CountDown = new JPanel();
        panel15 = new JPanel();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        AddAlarmPanel2 = new JPanel();
        hours2 = new JSpinner();
        label3 = new JLabel();
        minutes2 = new JSpinner();
        label6 = new JLabel();
        seconds2 = new JSpinner();
        label7 = new JLabel();
        AddAlarmButton2 = new JButton();
        panel14 = new JPanel();
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
                Clock.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
                ( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
                . TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt
                . Color. red) ,Clock. getBorder( )) ); Clock. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
                propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( )
                ; }} );
                Clock.setLayout(new BorderLayout(5, 5));

                //---- label10 ----
                label10.setText("\u5f53\u524d\u65f6\u95f4\uff1a");
                Clock.add(label10, BorderLayout.WEST);
                Clock.add(currentTime, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u65f6\u949f", Clock);

            //======== Alarm ========
            {
                Alarm.setLayout(new BorderLayout(0, 5));

                //======== AddAlarmPanel ========
                {
                    AddAlarmPanel.setBorder(new TitledBorder("\u6dfb\u52a0\u95f9\u949f"));
                    AddAlarmPanel.setFont(AddAlarmPanel.getFont().deriveFont(AddAlarmPanel.getFont().getSize() + 6f));
                    AddAlarmPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    AddAlarmPanel.setLayout(new FlowLayout());

                    //---- hours ----
                    hours.setFont(hours.getFont().deriveFont(hours.getFont().getSize() + 2f));
                    AddAlarmPanel.add(hours);

                    //---- label2 ----
                    label2.setText("\u65f6");
                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
                    AddAlarmPanel.add(label2);

                    //---- minutes ----
                    minutes.setFont(minutes.getFont().deriveFont(minutes.getFont().getSize() + 2f));
                    AddAlarmPanel.add(minutes);

                    //---- label4 ----
                    label4.setText("\u5206");
                    label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
                    AddAlarmPanel.add(label4);

                    //---- seconds ----
                    seconds.setFont(seconds.getFont().deriveFont(seconds.getFont().getSize() + 2f));
                    AddAlarmPanel.add(seconds);

                    //---- label5 ----
                    label5.setText("\u79d2");
                    label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 5f));
                    AddAlarmPanel.add(label5);

                    //---- AddAlarmButton ----
                    AddAlarmButton.setText("\u6dfb\u52a0\u95f9\u949f");
                    AddAlarmButton.setFont(AddAlarmButton.getFont().deriveFont(AddAlarmButton.getFont().getSize() + 3f));
                    AddAlarmPanel.add(AddAlarmButton);
                }
                Alarm.add(AddAlarmPanel, BorderLayout.NORTH);

                //======== AlarmInfo ========
                {
                    AlarmInfo.setBorder(new CompoundBorder(
                        new TitledBorder("\u95f9\u949f\u4fe1\u606f"),
                        Borders.DLU2));

                    //---- AlarrmInfo ----
                    AlarrmInfo.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null, null, true},
                            {false, null, null, null, false},
                            {false, null, null, null, null},
                            {null, null, null, null, null},
                        },
                        new String[] {
                            "\u591a\u9009", "\u5e8f\u53f7", "\u95f9\u949f\u65f6\u95f4", "\u91cd\u590d", "\u5f00\u5173"
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            Boolean.class, Integer.class, Date.class, Boolean.class, Boolean.class
                        };
                        boolean[] columnEditable = new boolean[] {
                            true, false, false, true, true
                        };
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return columnEditable[columnIndex];
                        }
                    });
                    AlarrmInfo.setFont(AlarrmInfo.getFont().deriveFont(AlarrmInfo.getFont().getSize() + 5f));
                    AlarmInfo.setViewportView(AlarrmInfo);
                }
                Alarm.add(AlarmInfo, BorderLayout.CENTER);

                //======== Delete ========
                {
                    Delete.setLayout(new FlowLayout());

                    //---- DeleteSelected ----
                    DeleteSelected.setText("\u5220\u9664\u9009\u4e2d\u95f9\u949f");
                    DeleteSelected.setFont(DeleteSelected.getFont().deriveFont(DeleteSelected.getFont().getSize() + 5f));
                    Delete.add(DeleteSelected);
                }
                Alarm.add(Delete, BorderLayout.SOUTH);
            }
            MainPanel.addTab("\u95f9\u949f", Alarm);

            //======== CountDown ========
            {
                CountDown.setLayout(new BorderLayout(0, 5));

                //======== panel15 ========
                {
                    panel15.setFont(panel15.getFont().deriveFont(panel15.getFont().getSize() + 5f));
                    panel15.setLayout(new FlowLayout());

                    //---- button4 ----
                    button4.setText("\u5f00\u59cb");
                    button4.setFont(button4.getFont().deriveFont(button4.getFont().getSize() + 5f));
                    panel15.add(button4);

                    //---- button5 ----
                    button5.setText("\u6682\u505c");
                    button5.setFont(button5.getFont().deriveFont(button5.getFont().getSize() + 5f));
                    panel15.add(button5);

                    //---- button6 ----
                    button6.setText("\u590d\u4f4d");
                    button6.setFont(button6.getFont().deriveFont(button6.getFont().getSize() + 5f));
                    panel15.add(button6);
                }
                CountDown.add(panel15, BorderLayout.SOUTH);

                //======== AddAlarmPanel2 ========
                {
                    AddAlarmPanel2.setBorder(new TitledBorder("\u8bbe\u7f6e\u5012\u8ba1\u65f6"));
                    AddAlarmPanel2.setFont(AddAlarmPanel2.getFont().deriveFont(AddAlarmPanel2.getFont().getSize() + 6f));
                    AddAlarmPanel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    AddAlarmPanel2.setLayout(new FlowLayout());

                    //---- hours2 ----
                    hours2.setFont(hours2.getFont().deriveFont(hours2.getFont().getSize() + 2f));
                    AddAlarmPanel2.add(hours2);

                    //---- label3 ----
                    label3.setText("\u65f6");
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
                    AddAlarmPanel2.add(label3);

                    //---- minutes2 ----
                    minutes2.setFont(minutes2.getFont().deriveFont(minutes2.getFont().getSize() + 2f));
                    AddAlarmPanel2.add(minutes2);

                    //---- label6 ----
                    label6.setText("\u5206");
                    label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 5f));
                    AddAlarmPanel2.add(label6);

                    //---- seconds2 ----
                    seconds2.setFont(seconds2.getFont().deriveFont(seconds2.getFont().getSize() + 2f));
                    AddAlarmPanel2.add(seconds2);

                    //---- label7 ----
                    label7.setText("\u79d2");
                    label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 5f));
                    AddAlarmPanel2.add(label7);

                    //---- AddAlarmButton2 ----
                    AddAlarmButton2.setText("\u6dfb\u52a0\u5012\u8ba1\u65f6");
                    AddAlarmButton2.setFont(AddAlarmButton2.getFont().deriveFont(AddAlarmButton2.getFont().getSize() + 3f));
                    AddAlarmPanel2.add(AddAlarmButton2);
                }
                CountDown.add(AddAlarmPanel2, BorderLayout.NORTH);

                //======== panel14 ========
                {
                    panel14.setLayout(new FlowLayout());
                }
                CountDown.add(panel14, BorderLayout.CENTER);
            }
            MainPanel.addTab("\u5012\u8ba1\u65f6", CountDown);

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
            MainPanel.addTab("\u5173\u4e8e", About);
        }
        contentPane.add(MainPanel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Yu Shi
    private JTabbedPane MainPanel;
    private JPanel Clock;
    private JLabel label10;
    private JLabel currentTime;
    private JPanel Alarm;
    private JPanel AddAlarmPanel;
    private JSpinner hours;
    private JLabel label2;
    private JSpinner minutes;
    private JLabel label4;
    private JSpinner seconds;
    private JLabel label5;
    private JButton AddAlarmButton;
    private JScrollPane AlarmInfo;
    private JTable AlarrmInfo;
    private JPanel Delete;
    private JButton DeleteSelected;
    private JPanel CountDown;
    private JPanel panel15;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JPanel AddAlarmPanel2;
    private JSpinner hours2;
    private JLabel label3;
    private JSpinner minutes2;
    private JLabel label6;
    private JSpinner seconds2;
    private JLabel label7;
    private JButton AddAlarmButton2;
    private JPanel panel14;
    private JPanel About;
    private JPanel panel1;
    private JLabel label9;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
