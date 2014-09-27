package chipset.techtatva;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		VideoView splashVideo = (VideoView) findViewById(R.id.splash);
		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
				+ R.raw.techtatva);

		splashVideo.setVideoURI(uri);
		splashVideo.start();
		splashVideo.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
				startActivity(new Intent(SplashActivity.this,
						HomeActivity.class));
				finish();

			}
		});
	}
}
