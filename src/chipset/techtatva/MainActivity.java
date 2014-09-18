package chipset.techtatva;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import chipset.techtatva.fragment.EventFragment;
import chipset.techtatva.resources.DrawerAdapter;
import chipset.techtatva.resources.DrawerItem;
import chipset.techtatva.R;

public class MainActivity extends Activity {
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ArrayList<DrawerItem> dataList;
	ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			Fragment fragment = new EventFragment();
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();

		}
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawerlist);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_opened,
				R.string.drawer_closed);

		final String[] cat = getResources().getStringArray(R.array.cat);
		dataList = new ArrayList<DrawerItem>();
		dataList.add(new DrawerItem(cat[0], R.drawable.acumen2));
		dataList.add(new DrawerItem(cat[1], R.drawable.airborne2));
		dataList.add(new DrawerItem(cat[2], R.drawable.bizzmaestro2));
		dataList.add(new DrawerItem(cat[3], R.drawable.cheminova2));
		dataList.add(new DrawerItem(cat[4], R.drawable.constructure2));
		dataList.add(new DrawerItem(cat[5], R.drawable.cryptoss2));
		dataList.add(new DrawerItem(cat[6], R.drawable.electrific2));
		dataList.add(new DrawerItem(cat[7], R.drawable.energia2));
		dataList.add(new DrawerItem(cat[8], R.drawable.epsilon2));
		dataList.add(new DrawerItem(cat[9], R.drawable.gizmo2));
		dataList.add(new DrawerItem(cat[10], R.drawable.kraftwagen2));
		dataList.add(new DrawerItem(cat[11], R.drawable.mechanize2));
		dataList.add(new DrawerItem(cat[12], R.drawable.mechatron2));
		dataList.add(new DrawerItem(cat[13], R.drawable.robotrek2));
		dataList.add(new DrawerItem(cat[14], R.drawable.turing2));

		mDrawerList.setAdapter(new DrawerAdapter(MainActivity.this,
				R.layout.drawer_item, dataList));

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDrawerLayout.setDrawerShadow(
				getResources().getDrawable(R.drawable.drawer_shadow),
				GravityCompat.START);

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mDrawerList.setItemChecked(arg2, true);
				getActionBar().setTitle(cat[arg2]);
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
