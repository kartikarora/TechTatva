package chipset.techtatva;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import chipset.techtatva.resources.Functions;

public class FeaturedEventsActivity extends Activity {

	LinearLayout fe1, fe2, fe3, fe4, fe5;
	TextView fet1, fet2, fet3, fet4, fet5;
	String[] fed1, fed2, fed3, fed4, fed5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_featured_events);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		fed1 = getResources().getStringArray(R.array.fe1);
		fed2 = getResources().getStringArray(R.array.fe2);
		fed3 = getResources().getStringArray(R.array.fe3);
		fed4 = getResources().getStringArray(R.array.fe4);
		fed5 = getResources().getStringArray(R.array.fe5);

		Typeface tf = new Functions().getTypeface(getApplicationContext());

		fe1 = (LinearLayout) findViewById(R.id.fe1);
		fet1 = (TextView) findViewById(R.id.fet1);
		fet1.setTypeface(tf);
		fet1.setText(fed1[0]);

		fe2 = (LinearLayout) findViewById(R.id.fe2);
		fet2 = (TextView) findViewById(R.id.fet2);
		fet2.setTypeface(tf);
		fet2.setText(fed2[0]);

		fe3 = (LinearLayout) findViewById(R.id.fe3);
		fet3 = (TextView) findViewById(R.id.fet3);
		fet3.setTypeface(tf);
		fet3.setText(fed3[0]);

		fe4 = (LinearLayout) findViewById(R.id.fe4);
		fet4 = (TextView) findViewById(R.id.fet4);
		fet4.setTypeface(tf);
		fet4.setText(fed4[0]);

		fe5 = (LinearLayout) findViewById(R.id.fe5);
		fet5 = (TextView) findViewById(R.id.fet5);
		fet5.setText(fed5[0]);
		fet5.setTypeface(tf);

		fe1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						FeaturedEventsActivity.this);
				dialog.setTitle(fed1[0]);
				dialog.setMessage(fed1[1] + fed1[2] + fed1[3]);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}
		});

		fe2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						FeaturedEventsActivity.this);
				dialog.setTitle(fed2[0]);
				dialog.setMessage(fed2[1] + fed2[2] + fed2[3] + fed2[4]);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}
		});

		fe3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						FeaturedEventsActivity.this);
				dialog.setTitle(fed3[0]);
				dialog.setMessage(fed3[1] + fed3[2] + fed3[3] + fed3[4]);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}
		});

		fe4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						FeaturedEventsActivity.this);
				dialog.setTitle(fed4[0]);
				dialog.setMessage(fed4[1] + fed4[2] + fed4[3] + fed4[4]
						+ fed4[5]);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}
		});

		fe5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						FeaturedEventsActivity.this);
				dialog.setTitle(fed5[0]);
				dialog.setMessage(fed5[1] + fed5[2] + fed5[3]);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		} else if (id == R.id.action_dev) {
			startActivity(new Intent(FeaturedEventsActivity.this,
					DeveloperActivity.class));
		} else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					FeaturedEventsActivity.this);
			dialog.setTitle("Contact Us");
			dialog.setMessage(getResources().getString(R.string.contactus));
			dialog.setCancelable(false);
			dialog.setNeutralButton(android.R.string.ok, null);
			dialog.create();
			dialog.show();
		} else if (id == R.id.action_result) {
			if (new Functions().isConnected(getApplicationContext()) == true) {
				startActivity(new Intent(FeaturedEventsActivity.this,
						ResultActivity.class));
			} else {
				Toast.makeText(getApplicationContext(),
						"No Internet Connection", Toast.LENGTH_SHORT).show();
			}
		}
		return super.onOptionsItemSelected(item);
	}

}
