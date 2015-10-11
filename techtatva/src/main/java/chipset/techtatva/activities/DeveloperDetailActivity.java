package chipset.techtatva.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import chipset.techtatva.R;
import chipset.techtatva.resources.Constants;

public class DeveloperDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_detail);

        TextView titleTextView = (TextView) findViewById(R.id.dev_title_text_view);
        TextView subtitleTextView = (TextView) findViewById(R.id.dev_subtitle_text_view);
        ImageView imageView = (ImageView) findViewById(R.id.dev_detail_image_view);

        titleTextView.setText(getIntent().getStringExtra(Constants.TITLE));
        subtitleTextView.setText(getIntent().getStringExtra(Constants.SUBTITLE));
        imageView.setImageDrawable(getResources().getDrawable(getIntent().getIntExtra(Constants.IMG, R.drawable.app_icon)));
    }
}
