package chipset.techtatva;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class SplashActivity extends Activity {
	VideoView splashVideo;
	ImageView splash;
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			startActivity(new Intent(SplashActivity.this, HomeActivity.class));
			finish();

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		splashVideo = (VideoView) findViewById(R.id.splash);
		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
				+ R.raw.techtatva);
		splash = (ImageView) findViewById(R.id.image);
		splashVideo.setVideoURI(uri);
		splashVideo.setZOrderOnTop(true);
		splashVideo.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				splashVideo.setVisibility(View.GONE);
				splash.setVisibility(View.VISIBLE);
				return false;
			}
		});
	}

	@Override
	protected void onPause() {

		splashVideo.stopPlayback();
		handler.removeCallbacks(runnable);
		super.onPause();
	}

	@Override
	protected void onResume() {

		splashVideo.start();
		handler.postDelayed(runnable, 5500);
		super.onResume();
	}
}