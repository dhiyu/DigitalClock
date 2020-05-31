package work.shiyu;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayer{
    final private String musicFilePath = ".\\music.mp3";
    private FileInputStream stream;
    private Player player;
    private Timer timer;
    private ByteArrayInputStream byteArrayInputStream;

    public MusicPlayer() {
        File file=new File(musicFilePath);
        try {
            stream=new FileInputStream(file);
            byteArrayInputStream = new ByteArrayInputStream(stream.readAllBytes());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void play() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    byteArrayInputStream.reset();
                    player = new Player(byteArrayInputStream);
                    System.out.println("播放提示音！");
                    player.play();
                } catch (JavaLayerException javaLayerException) {
                    javaLayerException.printStackTrace();
                }
            }
        }, 0, 2000);
    }

    public void stop() {
        player.close();
        timer.cancel();
    }
}
