package chipset.techtatva;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import chipset.techtatva.resources.Functions;

@SuppressLint("SetJavaScriptEnabled")
public class HomeActivity extends Activity {
	Functions functions = new Functions();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Button registerButton = (Button) findViewById(R.id.registerButton);
		Button eventsButton = (Button) findViewById(R.id.eventButton);
		Button instaFeedButton = (Button) findViewById(R.id.instaFeedButton);
		Button liveBlogButton = (Button) findViewById(R.id.liveBlogButton);
		Typeface tf = functions.getTypeface(getApplicationContext());
		registerButton.setTypeface(tf);
		eventsButton.setTypeface(tf);
		instaFeedButton.setTypeface(tf);
		liveBlogButton.setTypeface(tf);
		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (functions.isConnected(getApplicationContext())) {
					startActivity(new Intent(HomeActivity.this,
							WebViewActivity.class).putExtra("URL", true)
							.putExtra("title", "Register"));
				} else {
					Toast.makeText(getApplicationContext(),
							"No Internet Connection", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		liveBlogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (functions.isConnected(getApplicationContext())) {
					startActivity(new Intent(HomeActivity.this,
							WebViewActivity.class).putExtra("URL", false)
							.putExtra("title", "Live Blog"));
				} else {
					Toast.makeText(getApplicationContext(),
							"No Internet Connection", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		eventsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(HomeActivity.this, MainActivity.class));
			}
		});

		instaFeedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (functions.isConnected(getApplicationContext())) {
					startActivity(new Intent(HomeActivity.this,
							InstaFeedActivity.class));
				} else {
					Toast.makeText(getApplicationContext(),
							"No Internet Connection", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_dev) {
			startActivity(new Intent(HomeActivity.this, DeveloperActivity.class));
		} else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					HomeActivity.this);
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