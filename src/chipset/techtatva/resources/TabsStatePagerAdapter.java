package chipset.techtatva.resources;

import static chipset.techtatva.resources.Constants.PREF_CAT;
import static chipset.techtatva.resources.Constants.PREF_DAY;
import static chipset.techtatva.resources.Constants.PREF_JSON;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import chipset.techtatva.fragments.EventFragment;
import chipset.techtatva.fragments.ProgressFragment;

public class TabsStatePagerAdapter extends FragmentStatePagerAdapter {

	Functions functions = new Functions();
	Context context;
	Activity activity;

	public TabsStatePagerAdapter(FragmentManager fm, Context c, Activity a) {
		super(fm);
		context = c;
		activity = a;
	}

	@Override
	public Fragment getItem(int index) {
		functions.putSharedPrefrences(context, PREF_DAY, index);
		if (functions.isConnected(context)) {
			if (functions.getSharedPrefrencesString(context, PREF_JSON).equals(
					"null")) {
				return new ProgressFragment(context, activity);
			} else {
				new EventsGetAsyncTask(context, activity).execute();
				return new EventFragment(functions.getSharedPrefrencesInt(
						context, PREF_CAT), index);
			}

		} else {
			return new EventFragment(functions.getSharedPrefrencesInt(context,
					PREF_CAT), index);
		}
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public int getItemPosition(Object object) {

		return PagerAdapter.POSITION_NONE;
	}

}