package chipset.techtatva;

import static chipset.techtatva.resources.Constants.PREF_CAT;
import static chipset.techtatva.resources.Constants.PREF_FR;
import static chipset.techtatva.resources.Constants.PREF_JSON;
import static chipset.techtatva.resources.Constants.URL_EVENTS;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import chipset.techtatva.resources.DrawerAdapter;
import chipset.techtatva.resources.DrawerItem;
import chipset.techtatva.resources.Functions;
import chipset.techtatva.resources.TabsStatePagerAdapter;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ViewPager viewPager;
	ActionBar actionBar;
	TabsStatePagerAdapter mAdapter;
	ArrayList<DrawerItem> dataList;
	ActionBarDrawerToggle mDrawerToggle;
	Functions functions = new Functions();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		mAdapter = new TabsStatePagerAdapter(getSupportFragmentManager());
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		String days[] = { "Day 1", "Day 2", "Day 3", "Day 4" };

		viewPager = (ViewPager) findViewById(R.id.dayViewPager);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawerlist);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_opened,
				R.string.drawer_closed);

		// Adding action bar tabs
		for (String tab_name : days) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}
		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				actionBar.setSelectedNavigationItem(arg0);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		final String[] cat = getResources().getStringArray(R.array.cat);
		dataList = new ArrayList<DrawerItem>();
		dataList.add(new DrawerItem(cat[0], R.drawable.android4));
		dataList.add(new DrawerItem(cat[1], R.drawable.acumen2));
		dataList.add(new DrawerItem(cat[2], R.drawable.airborne2));
		dataList.add(new DrawerItem(cat[3], R.drawable.bizzmaestro2));
		dataList.add(new DrawerItem(cat[4], R.drawable.cheminova2));
		dataList.add(new DrawerItem(cat[5], R.drawable.constructure2));
		dataList.add(new DrawerItem(cat[6], R.drawable.cryptoss2));
		dataList.add(new DrawerItem(cat[7], R.drawable.electrific2));
		dataList.add(new DrawerItem(cat[8], R.drawable.energia2));
		dataList.add(new DrawerItem(cat[9], R.drawable.epsilon2));
		dataList.add(new DrawerItem(cat[10], R.drawable.gizmo2));
		dataList.add(new DrawerItem(cat[11], R.drawable.kraftwagen2));
		dataList.add(new DrawerItem(cat[12], R.drawable.mechanize2));
		dataList.add(new DrawerItem(cat[13], R.drawable.mechatron2));
		dataList.add(new DrawerItem(cat[14], R.drawable.robotrek2));
		dataList.add(new DrawerItem(cat[15], R.drawable.turing2));

		mDrawerList.setAdapter(new DrawerAdapter(MainActivity.this,
				R.layout.drawer_item, dataList));
		mDrawerLayout.setDrawerShadow(
				getResources().getDrawable(R.drawable.drawer_shadow),
				GravityCompat.START);
		try {
			if (functions.isConnected(getApplicationContext()) == true
					|| functions.getSharedPrefrencesString(
							getApplicationContext(), PREF_JSON).equals(null)
					|| functions.getSharedPrefrencesInt(
							getApplicationContext(), PREF_FR) == 0) {
				new EventsGet().execute();
				functions.putSharedPrefrences(getApplicationContext(), PREF_FR,
						1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mDrawerList.setItemChecked(arg2, true);
				getActionBar().setTitle(cat[arg2]);
				functions.putSharedPrefrences(getApplicationContext(),
						PREF_CAT, arg2);
				mDrawerLayout.closeDrawer(mDrawerList);

			}
		});

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.action_dev) {
			startActivity(new Intent(MainActivity.this, DeveloperActivity.class));
		} else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					MainActivity.this);
			dialog.setTitle("Contact Us");
			dialog.setMessage("Convener\n\tJal Panchal\n\tEmail: convener@techtatva.in\n\tPhone: +919740981697\n\nConvener\n\tAparna Sandhu\n\tEmail: convener@techtatva.in\n\tPhone: +918123677470");
			dialog.setCancelable(false);
			dialog.setNeutralButton(android.R.string.ok, null);
			dialog.create();
			dialog.show();
		}
		return super.onOptionsItemSelected(item);
	}

	private class EventsGet extends AsyncTask<String, String, JSONArray> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected JSONArray doInBackground(String... args) {
			JSONArray jObj = functions.events(URL_EVENTS);
			return jObj;
		}

		@Override
		protected void onPostExecute(JSONArray jObj) {
			String data = jObj.toString();
			functions.putSharedPrefrences(getApplicationContext(), PREF_JSON,
					data);
			Log.e("TEST", data);
		}
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}