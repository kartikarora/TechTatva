package chipset.techtatva.activities;

/**
 * Created by saketh on 20/9/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.parse.ParseConfig;

import chipset.potato.Potato;
import chipset.techtatva.R;
import chipset.techtatva.resources.Constants;

/**
 * This Activity is used as a fallback when there is no browser installed that supports
 * Chrome Custom Tabs
 */
public class WebViewActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "extra.url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        startActivity(new Intent(getApplicationContext(), FoodStallActivity.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String url = getIntent().getStringExtra(EXTRA_URL);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        boolean nana = Potato.potate().Preferences().getSharedPreferenceBoolean(getApplicationContext(), "nana");
        String regURL = nana ? Constants.URL_REGISTRATION : ParseConfig.getCurrentConfig().getString("register");
        if (url.equals(regURL)) {
            setTitle("Register Online");
        } else {
            setTitle("Online Events");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}