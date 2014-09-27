package chipset.techtatva;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DeveloperActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_developer);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
		}else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					DeveloperActivity.this);
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
