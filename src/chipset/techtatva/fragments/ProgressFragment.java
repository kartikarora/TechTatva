package chipset.techtatva.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import chipset.techtatva.R;
import chipset.techtatva.resources.EventsGetAsyncTask;
import chipset.techtatva.resources.Functions;

public class ProgressFragment extends Fragment {

	Context context;
	Functions functions = new Functions();
	Activity activity;

	public ProgressFragment(Context c, Activity a) {
		context = c;
		activity = a;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.loading, container, false);

		return rootview;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		new EventsGetAsyncTask(context, activity).execute();
		super.onViewCreated(view, savedInstanceState);
	}

}
