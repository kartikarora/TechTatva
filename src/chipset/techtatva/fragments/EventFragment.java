package chipset.techtatva.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import chipset.techtatva.R;

public class EventFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootview = inflater.inflate(R.layout.category_fragment, container,
				false);

		return rootview;
	}

}
