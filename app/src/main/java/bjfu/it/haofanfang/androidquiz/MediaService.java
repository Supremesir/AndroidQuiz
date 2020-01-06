package bjfu.it.haofanfang.androidquiz;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;



public class MediaService extends IntentService {

    public MediaService() {
        super("MediaService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.notice);
            mediaPlayer.start();
        }

    }

}
