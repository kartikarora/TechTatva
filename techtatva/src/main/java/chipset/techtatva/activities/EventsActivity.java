package chipset.techtatva.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

import chipset.techtatva.R;
import chipset.techtatva.resources.DrawerAdapter;
import chipset.techtatva.resources.DrawerItem;
import chipset.techtatva.resources.Functions;
import chipset.techtatva.resources.SlidingTabLayout;
import chipset.techtatva.resources.TabsStatePagerAdapter;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import static chipset.techtatva.resources.Constants.PREF_CAT;
import static chipset.techtatva.resources.Constants.PREF_DAY;

public class EventsActivity extends ActionBarActivity {
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ViewPager viewPager;
    android.support.v7.app.ActionBar actionBar;
    TabsStatePagerAdapter mAdapter;
    ArrayList<DrawerItem> dataList;
    ActionBarDrawerToggle mDrawerToggle;
    Functions functions = new Functions();
    SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        mAdapter = new TabsStatePagerAdapter(getSupportFragmentManager(),
                EventsActivity.this, EventsActivity.this);
        actionBar.setDisplayHomeAsUpEnabled(true);
        String days[] = {"DAY 1", "DAY 2", "DAY 3", "DAY 4"};
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.dayViewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawerlist);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_opened,
                R.string.drawer_closed);


        viewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(R.color.techtatva);


        final String[] cat = getResources().getStringArray(R.array.cat);
        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(cat[0], R.drawable.android4b));
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
        dataList.add(new DrawerItem(cat[16], R.drawable.gaming2));

        mDrawerList.setAdapter(new DrawerAdapter(EventsActivity.this,
                R.layout.drawer_item, dataList));
        mDrawerLayout.setDrawerShadow(
                getResources().getDrawable(R.drawable.drawer_shadow),
                GravityCompat.START);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        functions.putSharedPrefrences(getApplicationContext(), PREF_CAT, 0);

        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                mDrawerList.setItemChecked(arg2, true);
                getSupportActionBar().setTitle(cat[arg2]);
                functions.putSharedPrefrences(getApplicationContext(),
                        PREF_CAT, arg2);
                mDrawerLayout.closeDrawer(mDrawerList);
                mAdapter.getItem(functions.getSharedPrefrencesInt(
                        getApplicationContext(), PREF_DAY));
                mAdapter.notifyDataSetChanged();

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
            startActivity(new Intent(EventsActivity.this,
                    DeveloperActivity.class));
        } else if (id == R.id.action_contact) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(
                    EventsActivity.this);
            dialog.setTitle("Contact Us");
            dialog.setMessage(getResources().getString(R.string.contactus));
            dialog.setCancelable(false);
            dialog.setNeutralButton(android.R.string.ok, null);
            dialog.create();
            dialog.show();
        } else if (id == R.id.action_result) {
            if (new Functions().isConnected(getApplicationContext())) {
                startActivity(new Intent(EventsActivity.this,
                        ResultActivity.class));
            } else {
                Crouton.showText(EventsActivity.this, "No Internet Connection",
                        Style.ALERT);
            }
        }
        return super.onOptionsItemSelected(item);

    }
}