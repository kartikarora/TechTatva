package chipset.techtatva.resources;

import static chipset.techtatva.resources.Constants.PREF_CAT;
import static chipset.techtatva.resources.Constants.*;
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

	public TabsStatePagerAdapter(FragmentManager fm, Context c) {
		super(fm);
		context = c;
	}

	@Override
	public Fragment getItem(int index) {
		// switch (index) {
		// case 0:
		functions.putSharedPrefrences(context, PREF_DAY, index);
		if (functions.getSharedPrefrencesString(context, PREF_JSON).equals(
				"null")) {

			return new ProgressFragment(context);

		}
		return new EventFragment(functions.getSharedPrefrencesInt(context,
				PREF_CAT), index);
		// case 1:
		// return new EventFragmentTwo();
		// case 2:
		// return new EventFragmentThree();
		// case 3:
		// return new EventFragmentFour();
		// }
		// return null;
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