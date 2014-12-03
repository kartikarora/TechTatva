package chipset.techtatva.resources;

import static chipset.techtatva.resources.Constants.RES_CATEGORY;
import static chipset.techtatva.resources.Constants.RES_NAME;

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
public class ResultAdapter extends BaseAdapter {

	ArrayList<HashMap<String, String>> Data = new ArrayList<HashMap<String, String>>();
	Context Cont;
	LayoutInflater inflater;

	public ResultAdapter(Context context,
			ArrayList<HashMap<String, String>> data) {
		Cont = context;
		Data = data;
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
		HashMap<String, String> map = new HashMap<String, String>();
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.result_item, null);
		}
		map = Data.get(arg0);
		TextView name, category;
		Typeface tf = new Functions().getTypeface(Cont);
		name = (TextView) arg1.findViewById(R.id.resultName);
		category = (TextView) arg1.findViewById(R.id.resultCategory);
		name.setTypeface(tf);
		name.setText((String) map.get(RES_NAME));
		category.setTypeface(tf);
		category.setText((String) map.get(RES_CATEGORY));
		return arg1;
	}
}