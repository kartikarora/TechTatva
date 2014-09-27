package chipset.techtatva.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import chipset.techtatva.R;

public class HomeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootview = inflater.inflate(R.layout.category_fragment, container,
				false);

		return rootview;
	}

	@Override
	public void onViewCreated(final View view, Bundle savedInstanceState) {
		RelativeLayout d, e;
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.LL);
		ll.setGravity(Gravity.CENTER);
		d = (RelativeLayout) view.findViewById(R.id.ev4);
		e = (RelativeLayout) view.findViewById(R.id.ev5);
		d.setVisibility(View.GONE);
		e.setVisibility(View.GONE);
		Button x, y, z;
		x = (Button) view.findViewById(R.id.ev1b);
		y = (Button) view.findViewById(R.id.ev2b);
		z = (Button) view.findViewById(R.id.ev3b);
		x.setVisibility(View.VISIBLE);
		y.setVisibility(View.VISIBLE);
		z.setVisibility(View.VISIBLE);
		x.setText("REGISTER");
		y.setText("EVENTS");
		z.setText("INSTA FEED");

		y.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Fragment fragment = new EventFragment();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.container, fragment).commit();

			}
		});
		x.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "Coming Soon", Toast.LENGTH_SHORT)
						.show();

			}
		});
		z.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "Coming Soon", Toast.LENGTH_SHORT)
						.show();

			}
		});

	}

}
