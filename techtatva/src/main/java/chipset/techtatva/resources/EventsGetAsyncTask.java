package chipset.techtatva.resources;

import static chipset.techtatva.resources.Constants.PREF_JSON;
import static chipset.techtatva.resources.Constants.URL_EVENTS;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import chipset.techtatva.fragments.EventFragment;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class EventsGetAsyncTask extends AsyncTask<String, String, JSONArray> {

	Functions functions = new Functions();
	Context context;
	Activity activity;

	public EventsGetAsyncTask(Context context, Activity activity) {
		this.context = context;
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected JSONArray doInBackground(String... args) {
		JSONArray jObj = functions.events(URL_EVENTS);
		return jObj;
	}

	@Override
	protected void onPostExecute(JSONArray jObj) {
		try {
			String data = jObj.toString();
			functions.putSharedPrefrences(context, PREF_JSON, data);

			new EventFragment(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
			Crouton.showText(activity,
					"Error communicating with the server! Please retry",
					Style.INFO);
		}
	}
}