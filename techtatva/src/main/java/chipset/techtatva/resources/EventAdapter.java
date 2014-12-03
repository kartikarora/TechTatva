package chipset.techtatva.resources;

import static chipset.techtatva.resources.Constants.EVENT_CONTACT;
import static chipset.techtatva.resources.Constants.EVENT_DATE;
import static chipset.techtatva.resources.Constants.EVENT_LOCATION;
import static chipset.techtatva.resources.Constants.EVENT_NAME;
import static chipset.techtatva.resources.Constants.EVENT_TIME;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import chipset.techtatva.R;

@SuppressLint("InflateParams")
public class EventAdapter extends BaseAdapter {
	ArrayList<HashMap<String, String>> Data = new ArrayList<HashMap<String, String>>();
	Context Cont;
	LayoutInflater inflater;
	int d, c;

	public EventAdapter(Context context,
			ArrayList<HashMap<String, String>> data, int d, int c) {
		Cont = context;
		Data = data;
		this.d = d;
		this.c = c;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return Data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return Data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.event_item, null);
		}

		TextView name, date, time, contact, location;
		Typeface tf = new Functions().getTypeface(Cont);
		name = (TextView) arg1.findViewById(R.id.eventName);
		date = (TextView) arg1.findViewById(R.id.eventDate);
		time = (TextView) arg1.findViewById(R.id.eventTime);
		contact = (TextView) arg1.findViewById(R.id.eventContact);
		location = (TextView) arg1.findViewById(R.id.eventLocation);
		name.setTypeface(tf);
		date.setTypeface(tf);
		time.setTypeface(tf);
		time.setTypeface(tf);
		contact.setTypeface(tf);
		location.setTypeface(tf);
		HashMap<String, String> map = new HashMap<String, String>();
		map = Data.get(arg0);
		String x = (String) map.get(EVENT_NAME);
		if (x.equals("No Event")) {
			name.setText(x);
			date.setVisibility(View.GONE);
			time.setVisibility(View.GONE);
			contact.setVisibility(View.GONE);
			location.setVisibility(View.GONE);
		} else {

			name.setText((String) map.get(EVENT_NAME));
			date.setText((String) map.get(EVENT_DATE));
			time.setText((String) map.get(EVENT_TIME));
			contact.setText((String) map.get(EVENT_CONTACT));
			location.setText((String) map.get(EVENT_LOCATION));
		}
		return arg1;
	}
}