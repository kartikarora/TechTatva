package chipset.techtatva.resources;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import chipset.techtatva.fragments.EventFragmentFour;
import chipset.techtatva.fragments.EventFragmentOne;
import chipset.techtatva.fragments.EventFragmentThree;
import chipset.techtatva.fragments.EventFragmentTwo;

public class TabsStatePagerAdapter extends FragmentStatePagerAdapter {

	public TabsStatePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new EventFragmentOne();
		case 1:
			return new EventFragmentTwo();
		case 2:
			return new EventFragmentThree();
		case 3:
			return new EventFragmentFour();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 4;
	}

}