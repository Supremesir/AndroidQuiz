package bjfu.it.haofanfang.androidquiz;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MediaService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "bjfu.it.haofanfang.androidquiz.action.FOO";
    public static final String ACTION_BAZ = "bjfu.it.haofanfang.androidquiz.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "bjfu.it.haofanfang.androidquiz.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "bjfu.it.haofanfang.androidquiz.extra.PARAM2";

    public MediaService() {
        super("MediaService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.notice);
        mediaPlayer.start();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
