package chipset.techtatva;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

		splashVideo.setVideoURI(uri);
		splashVideo.setZOrderOnTop(true);
		/*
		 * splashVideo.setOnCompletionListener(new OnCompletionListener() {
		 * 
		 * @Override public void onCompletion(MediaPlayer arg0) {
		 * splashVideo.seekTo(3000); startActivity(new
		 * Intent(SplashActivity.this, HomeActivity.class)); finish();
		 * 
		 * } });
		 */
	}

	@Override
	protected void onPause() {
		splashVideo.suspend();
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