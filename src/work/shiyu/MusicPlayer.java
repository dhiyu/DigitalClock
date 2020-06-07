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
    private Play play;
    private ByteArrayInputStream byteArrayInputStream;

    class Play extends TimerTask {

        /**
         * The action to be performed by this timer task.
         */
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
    }

    public MusicPlayer() {
        File file=new File(musicFilePath);
        try {
            stream=new FileInputStream(file);
            byteArrayInputStream = new ByteArrayInputStream(stream.readAllBytes());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        timer = new Timer();
    }

    synchronized public void play() {
        if (play == null) {
            play = new Play();
            timer.schedule(play, 0, 2000);
        }

    }

    synchronized public void stop() {
        if (play == null) {
            return;
        }
        play.cancel();
        player.close();
        play = null;
    }
}

