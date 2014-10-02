package chipset.techtatva.fragments;

import static chipset.techtatva.resources.Constants.PREF_JSON;
import static chipset.techtatva.resources.Constants.URL_EVENTS;

import org.json.JSONArray;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import chipset.techtatva.R;
import chipset.techtatva.resources.Functions;

public class ProgressFragment extends Fragment {

	Context context;
	Functions functions = new Functions();

	public ProgressFragment(Context c) {
		context = c;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.loading, container, false);

		return rootview;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		new EventsGet().execute();
		super.onViewCreated(view, savedInstanceState);
	}

	private class EventsGet extends AsyncTask<String, String, JSONArray> {

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
			String data = jObj.toString();
			functions.putSharedPrefrences(context, PREF_JSON, data);
			new EventFragment(0, 0);
		}
	}

}
