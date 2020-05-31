package work.shiyu;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JWindow;

/**
 * 吐司提示框组件 
 *
 *
 * @author ccw
 * @since:2014-2-28
 */
public class Toast extends JWindow {

    private static final long serialVersionUID = 1L;
    private String message = "";
    private final Insets insets = new Insets(12, 24, 12, 24);
    private int period = 1500;
    private Font font;
    public static final int msg = 0;// 提示 黑色背景色  
    public static final int success = 1;// 成功提示 浅蓝色背景色  
    public static final int error = 2;// 错误提示 粉红色背景色  
    private Color background;
    private Color foreground;
    private Component parent;
    private Timer timer;

    /**
     *
     * @param parent
     *            父窗体 (Frame Dialog Window) 
     * @param message
     *            消息 
     * @param period
     *            显示时间 
     */
    public Toast(Window parent, String message, int period) {
        this(parent, message, period, 0);

    }

    /**
     *
     * @param parent
     * @param message
     * @param period
     * @param type
     *            提示类型 msg:黑色背景色 success :浅蓝色背景色  error: 粉红色背景色 
     */
    public Toast(Component parent, String message, int period, int type) {
        this.parent = parent;
        this.message = message;
        this.period = period;
        font = new Font("宋体", Font.PLAIN, 14);
        setSize(getStringSize(font, true, message));
        // 相对JFrame的位置------修改！！！针对于parent的位置
        setLocationRelativeTo(parent);
        installTheme(type);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // old  
        Composite oldComposite = g2.getComposite();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        g2.setColor(background);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        g2.setColor(foreground);
        g2.drawString(message, insets.left, fm.getAscent() + insets.top);
        // restore  
        g2.setComposite(oldComposite);
    }

    /**
     * 启动提示
     * 2020/5/18
     * 这个地方原作者没有停止计时器
     * 应该在执行完毕之后停止该计时器
     */

    public void start() {
        this.setVisible(true);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisible(false);
                timer.cancel();
            }
        }, period);
    }

    /**
     * 修改消息 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
        Dimension size = getStringSize(font, true, message);
        setSize(size);
        setLocationRelativeTo(parent);
        revalidate();
        repaint(0, 0, size.width, size.height);
        //新增一秒内重复点击时延长框的显示
        if (isVisible()) {
            timer.cancel();
        }
        start();
    }

    /*
     * 设置样式
     */
    private void installTheme(int type) {
        switch (type) {
            case msg:
                background = new Color(0x515151);
                foreground = Color.WHITE;
                break;
            case success:
                background = new Color(223, 240, 216);
                foreground = new Color(49, 112, 143);
                break;
            case error:
                background = new Color(242, 222, 222);
                foreground = new Color(221, 17, 68);
                break;
            default:
                background = new Color(0x515151);
                foreground = Color.WHITE;
                break;
        }
    }

    /**
     * 得到字符串的宽-高 
     *
     * @param font
     *            字体 
     * @param isAntiAliased
     *            反锯齿 
     * @param text
     *            文本 
     * @return
     */
    private Dimension getStringSize(Font font, boolean isAntiAliased,
                                    String text) {
        FontRenderContext renderContext = new FontRenderContext(null,
                isAntiAliased, false);
        Rectangle2D bounds = font.getStringBounds(text, renderContext);
        int width = (int) bounds.getWidth() + 2 * insets.left;
        int height = (int) bounds.getHeight() + insets.top * 2;
        return new Dimension(width, height);
    }

}  