package chipset.techtatva;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import chipset.techtatva.resources.Functions;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class DeveloperActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_developer);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		TextView a;
		Typeface tf = new Functions().getTypeface(getApplicationContext());
		a = (TextView) findViewById(R.id.a);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.a1);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.b);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.b1);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.c);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.c1);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.d);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.d1);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.e);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.f);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.g);
		a.setTypeface(tf);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.e1);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.f1);
		a.setTypeface(tf);
		a = (TextView) findViewById(R.id.g1);
		a.setTypeface(tf);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.developer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		} else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					DeveloperActivity.this);
			dialog.setTitle("Contact Us");
			dialog.setMessage("Convener\n\tJal Panchal\n\tEmail: convener@techtatva.in\n\tPhone: +919740981697\n\nConvener\n\tAparna Sandhu\n\tEmail: convener@techtatva.in\n\tPhone: +918123677470");
			dialog.setCancelable(false);
			dialog.setNeutralButton(android.R.string.ok, null);
			dialog.create();
			dialog.show();
		} else if (id == R.id.action_result) {
			if (new Functions().isConnected(getApplicationContext()) == true) {
				startActivity(new Intent(DeveloperActivity.this,
						ResultActivity.class));
			} else {
				Crouton.showText(DeveloperActivity.this,
						"No Internet Connection", Style.ALERT);
			}
		}
		return super.onOptionsItemSelected(item);
	}
}