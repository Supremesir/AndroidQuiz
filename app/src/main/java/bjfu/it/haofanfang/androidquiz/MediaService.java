package bjfu.it.haofanfang.androidquiz;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;


public class MediaService extends IntentService {

    public MediaService() {
        super("MediaService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //音频服务创建
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.notice);
        //启动音频服务
        mediaPlayer.start();
        Log.v("music", "start music");
    }
}



