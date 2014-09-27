package chipset.techtatva.fragments;

import static chipset.techtatva.resources.Constants.EVENT_CATEGORY;
import static chipset.techtatva.resources.Constants.EVENT_CONTACT;
import static chipset.techtatva.resources.Constants.EVENT_DATE;
import static chipset.techtatva.resources.Constants.EVENT_DETAIL;
import static chipset.techtatva.resources.Constants.EVENT_LOCATION;
import static chipset.techtatva.resources.Constants.EVENT_NAME;
import static chipset.techtatva.resources.Constants.EVENT_TIME;
import static chipset.techtatva.resources.Constants.PREF_JSON;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import chipset.techtatva.R;
import chipset.techtatva.resources.EventAdapter;
import chipset.techtatva.resources.Functions;

public class EventFragmentFour extends Fragment {

	JSONArray jArr = null;
	Functions functions = new Functions();
	ArrayList<HashMap<String, String>> eventData = new ArrayList<HashMap<String, String>>();
	String cat, name, detail, location, time, date, cont;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.category_fragment, container,
				false);

		return rootview;
	}

	@Override
	public void onViewCreated(final View view, Bundle savedInstanceState) {
		ListView eventList = (ListView) view.findViewById(R.id.eventList);
		String getData = functions.getSharedPrefrencesString(getActivity(),
				PREF_JSON);

		try {
			jArr = new JSONArray(getData);
			Log.e("jArr", String.valueOf(jArr));
			for (int i = 0; i < jArr.length(); i++) {

				JSONObject jObj = jArr.getJSONObject(i);
				date = jObj.getString(EVENT_DATE);
				if (date.startsWith("11")) {
					HashMap<String, String> map = new HashMap<String, String>();
					cat = jObj.getString(EVENT_CATEGORY);
					name = jObj.getString(EVENT_NAME);
					detail = jObj.getString(EVENT_DETAIL);
					location = jObj.getString(EVENT_LOCATION);
					time = jObj.getString(EVENT_TIME);
					cont = jObj.getString(EVENT_CONTACT);
					map.put(EVENT_CATEGORY, cat);
					map.put(EVENT_NAME, name);
					map.put(EVENT_DETAIL, detail);
					map.put(EVENT_LOCATION, location);
					map.put(EVENT_TIME, time);
					map.put(EVENT_DATE, date);
					map.put(EVENT_CONTACT, cont);
					eventData.add(map);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		eventList.setAdapter(new EventAdapter(getActivity(), eventData));

		eventList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle((String) eventData.get(arg2).get(EVENT_NAME));
				dialog.setMessage((String) eventData.get(arg2)
						.get(EVENT_DETAIL));
				dialog.setCancelable(false);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}

		});

	}

	@Override
	public void onDetach() {
		eventData.clear();
		super.onDetach();
	}
}
