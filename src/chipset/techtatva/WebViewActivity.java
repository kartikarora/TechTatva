package chipset.techtatva;

import static chipset.techtatva.resources.Constants.URL_BLOG;
import static chipset.techtatva.resources.Constants.URL_REG;
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

public class WebViewActivity extends Activity {
	WebView registerView;
	ProgressDialog pDialog;
	boolean type;
	String title;

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
		type = getIntent().getExtras().getBoolean("URL");
		title = getIntent().getExtras().getString("title");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(title);
		if (type == true)
			registerView.loadUrl(URL_REG);
		else if (type == false)
			registerView.loadUrl(URL_BLOG);
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
			dialog.setMessage("Convener\n\tJal Panchal\n\tEmail: convener@techtatva.in\n\tPhone: +919740981697\n\nConvener\n\tAparna Sandhu\n\tEmail: convener@techtatva.in\n\tPhone: +918123677470");
			dialog.setCancelable(false);
			dialog.setNeutralButton(android.R.string.ok, null);
			dialog.create();
			dialog.show();
		}
		return super.onOptionsItemSelected(item);
	}

}
