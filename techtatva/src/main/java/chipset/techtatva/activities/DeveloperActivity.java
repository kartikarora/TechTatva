package chipset.techtatva.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;

import chipset.techtatva.R;
import chipset.techtatva.resources.Constants;
import io.kimo.konamicode.KonamiCode;
import io.kimo.konamicode.KonamiCodeLayout;

public class DeveloperActivity extends Activity {
    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        startActivity(new Intent(getApplicationContext(), FoodStallActivity.class));
        ImageView anuraagImageView = (ImageView) findViewById(R.id.anuraag_image_view);
        ImageView kartikImageView = (ImageView) findViewById(R.id.kartik_image_view);
        ImageView sakethImageView = (ImageView) findViewById(R.id.saketh_image_view);
        ImageView yashImageView = (ImageView) findViewById(R.id.yash_image_view);
        ImageView samarthImageView = (ImageView) findViewById(R.id.samarth_image_view);
        ImageView sushantImageView = (ImageView) findViewById(R.id.sushant_image_view);
        ImageView rohilImageView = (ImageView) findViewById(R.id.rohil_image_view);
        ImageView manojImageView = (ImageView) findViewById(R.id.manoj_image_view);
        ImageView mayankImageView = (ImageView) findViewById(R.id.mayank_image_view);
        anuraagImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.android)).putExtra(Constants.SUBTITLE, getString(R.string.anuraag)).putExtra(Constants.IMG, R.drawable.anuraag));
            }
        });
        kartikImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.team_lead)).putExtra(Constants.SUBTITLE, getString(R.string.kartik)).putExtra(Constants.IMG, R.drawable.kartik));
            }
        });
        sakethImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.android)).putExtra(Constants.SUBTITLE, getString(R.string.saketh)).putExtra(Constants.IMG, R.drawable.saketh));
            }
        });
        yashImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.ios)).putExtra(Constants.SUBTITLE, getString(R.string.yash)).putExtra(Constants.IMG, R.drawable.yash));
            }
        });
        samarthImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.coordinator)).putExtra(Constants.SUBTITLE, getString(R.string.samarth)).putExtra(Constants.IMG, R.drawable.samarth));
            }
        });
        sushantImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.ios)).putExtra(Constants.SUBTITLE, getString(R.string.sushant)).putExtra(Constants.IMG, R.drawable.sushant));
            }
        });
        rohilImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.windows)).putExtra(Constants.SUBTITLE, getString(R.string.rohil)).putExtra(Constants.IMG, R.drawable.rohil));
            }
        });
        manojImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.graphics)).putExtra(Constants.SUBTITLE, getString(R.string.manoj)).putExtra(Constants.IMG, R.drawable.manoj));
            }
        });
        mayankImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeveloperActivity.this, DeveloperDetailActivity.class).putExtra(Constants.TITLE, getString(R.string.windows)).putExtra(Constants.SUBTITLE, getString(R.string.mayank)).putExtra(Constants.IMG, R.drawable.mayank));
            }
        });

        new KonamiCode.Installer(DeveloperActivity.this)
                .on(DeveloperActivity.this)
                .callback(new KonamiCodeLayout.Callback() {
                    @Override
                    public void onFinish() {
                        startActivity(new Intent(DeveloperActivity.this, EasterEggActivity.class));
                        mVibrator.vibrate(1000);
                    }
                })
                .install();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }
}
