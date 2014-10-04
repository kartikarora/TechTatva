package chipset.techtatva;

import static chipset.techtatva.resources.Constants.URL_BLOG;
import static chipset.techtatva.resources.Constants.URL_REG;
import static chipset.techtatva.resources.Constants.URL_TMC;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import chipset.techtatva.resources.Functions;

public class WebViewActivity extends Activity {
	WebView registerView;
	ProgressDialog pDialog;
	int type;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		pDialog = new ProgressDialog(WebViewActivity.this);
		pDialog.setCancelable(true);
		pDialog.setMessage("Loading...");
		pDialog.setTitle("Please Wait");
		pDialog.setIndeterminate(true);
		registerView = (WebView) findViewById(R.id.registerView);
		registerView.setWebViewClient(new MyBrowser());
		registerView.getSettings().setLoadsImagesAutomatically(true);
		registerView.getSettings().setJavaScriptEnabled(true);
		registerView.getSettings().setAllowFileAccess(true);
		registerView.getSettings().setBuiltInZoomControls(true);
		registerView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		type = getIntent().getExtras().getInt("TYPE");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (type == 1) {
			registerView.loadUrl(URL_REG);
			getActionBar().setTitle(getResources().getString(R.string.reg));
		} else if (type == 2) {
			registerView.loadUrl(URL_BLOG);
			getActionBar().setTitle(
					getResources().getString(R.string.live_blog));
		} else if (type == 3) {
			registerView.loadUrl(URL_TMC);
			getActionBar().setTitle(
					getResources().getString(R.string.the_manipal_conclave));
		}
	}

	private class MyBrowser extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			pDialog.show();
			view.setVisibility(View.GONE);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			pDialog.dismiss();
			view.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onBackPressed() {

		if (registerView.isFocused() && registerView.canGoBack()) {
			registerView.goBack();
		} else {
			registerView.setVisibility(View.GONE);
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		} else if (id == R.id.action_dev) {
			startActivity(new Intent(WebViewActivity.this,
					DeveloperActivity.class));
		} else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					WebViewActivity.this);
			dialog.setTitle("Contact Us");
			dialog.setMessage(getResources().getString(R.string.contactus));
			dialog.setCancelable(false);
			dialog.setNeutralButton(android.R.string.ok, null);
			dialog.create();
			dialog.show();
		} else if (id == R.id.action_result) {
			if (new Functions().isConnected(getApplicationContext()) == true) {
				startActivity(new Intent(WebViewActivity.this,
						ResultActivity.class));
			} else {
				Toast.makeText(getApplicationContext(),
						"No Internet Connection", Toast.LENGTH_SHORT).show();
			}
		}
		return super.onOptionsItemSelected(item);
	}

}