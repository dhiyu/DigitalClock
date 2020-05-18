import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //启动主窗口
        Clock clock = new Clock();
        clock.setVisible(true);

        //启动提示toast窗口
        clock.msgToast = new Toast(clock.getMessage(), "启动完成！您可以使用啦！", 1000, Toast.success);
        clock.msgToast.start();
    }
}

