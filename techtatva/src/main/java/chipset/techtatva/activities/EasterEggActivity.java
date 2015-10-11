package chipset.techtatva.activities;

import android.content.Context;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.seismic.ShakeDetector;

import chipset.potato.Potato;
import chipset.techtatva.R;
import chipset.techtatva.resources.Constants;

public class EasterEggActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private Vibrator mVibrator;
    private ShakeDetector mShakeDetector;
    private SensorManager mSensorManager;
    private ShakeDetector.Listener mListener;

    ImageView lugFbImageView, lugGitHubImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_egg);

        mMediaPlayer = MediaPlayer.create(EasterEggActivity.this, R.raw.dance);
        mMediaPlayer.setLooping(true);
        lugFbImageView = (ImageView) findViewById(R.id.lug_fb_image_view);
        lugGitHubImageView = (ImageView) findViewById(R.id.lug_github_image_view);

        lugFbImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate().Intents().browserIntent(getApplicationContext(), Constants.URL_LUG_FB);
            }
        });

        lugGitHubImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Potato.potate().Intents().browserIntent(getApplicationContext(), Constants.URL_LUG_GITHUB);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(1000);

        mListener = new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                finish();

            }
        };


        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)
            mMediaPlayer.start();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mShakeDetector = new ShakeDetector(mListener);
        mShakeDetector.start(mSensorManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShakeDetector.stop();
        mMediaPlayer.stop();
    }
}
