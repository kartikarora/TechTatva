package chipset.techtatva.activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseConfig;

import java.util.ArrayList;

import chipset.potato.Potato;
import chipset.techtatva.R;
import chipset.techtatva.adapters.DayViewPagerAdapter;
import chipset.techtatva.adapters.DrawerAdapter;
import chipset.techtatva.chromecustomtabs.CustomTabActivityHelper;
import chipset.techtatva.chromecustomtabs.WebViewFallback;
import chipset.techtatva.database.DBHelper;
import chipset.techtatva.fragments.DayFragment;
import chipset.techtatva.model.events.Category;
import chipset.techtatva.model.events.DrawerItem;
import chipset.techtatva.resources.Constants;
import chipset.techtatva.resources.UpdateDatabase;


public class EventActivity extends AppCompatActivity {
    ViewPager viewPager;
    DayFragment day1, day2, day3, day4;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    public static DBHelper dbHelper;
    public static ArrayList<DrawerItem> drawerList;
    private static ListView drawerListView;
    public static Context mContext;
    public static ProgressDialog mProgressDialog;
    private boolean nana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        startActivity(new Intent(getApplicationContext(), FoodStallActivity.class));
        nana = Potato.potate().Preferences().getSharedPreferenceBoolean(getApplicationContext(), "nana");
        mContext = this;
        drawerListView = (ListView) findViewById(R.id.drawer_list_view);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DBHelper(this);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        day1 = new DayFragment();
        day1.day = 1;
        day2 = new DayFragment();
        day2.day = 2;
        day3 = new DayFragment();
        day3.day = 3;
        day4 = new DayFragment();
        day4.day = 4;
        if (dbHelper.getAllCategories().size() != 0 && dbHelper.getAllEvents().size() != 0) {
            if (Potato.potate().Utils().isInternetConnected(this)) {
                UpdateDatabase ud = new UpdateDatabase(getApplicationContext());
                ud.execute((String) null);
            }
        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        final String[] category = getResources().getStringArray(R.array.category);
        Potato.potate().Preferences().putSharedPreference(EventActivity.this, "cat", category[0]);
        drawerList = new ArrayList<>();
        Log.d("drawer", drawerList.size() + "");
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView) view.findViewById(R.id.category_name_text_view)).getText().toString();
                Potato.potate().Preferences().putSharedPreference(EventActivity.this, "cat", name);
                getSupportActionBar().setTitle(name);
                try {
                    day1.DataChange();
                    day2.DataChange();
                    day3.DataChange();
                    day4.DataChange();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        drawerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView) view.findViewById(R.id.category_name_text_view)).getText().toString();
                for (Category cat : dbHelper.getAllCategories()) {
                    if (cat.getCatName().toLowerCase().equals(name.toLowerCase())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EventActivity.this);
                        builder.setTitle(name);
                        builder.setCancelable(true);
                        builder.setMessage(cat.getDescription());
                        builder.setIcon(R.drawable.ic_action_about);
                        builder.show();
                    }
                }
                return false;
            }
        });
        drawerListView.setAdapter(new DrawerAdapter(EventActivity.this, drawerList));
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public static void setupDrawer() {
        Log.d("drawer", "adding");
        ArrayList<Category> categories = dbHelper.getAllCategories();
        drawerList.add(new DrawerItem("All Events", R.mipmap.ic_launcher));
        for (Category category : categories) {
            prepareDrawer(category.getCatName());
            Log.d("drawer", "added " + category.getCatName());
        }
        drawerListView.setAdapter(new DrawerAdapter(mContext, drawerList));
    }

    private static void prepareDrawer(String categoryName) {
        switch (categoryName) {
            case "Acumen":
                drawerList.add(new DrawerItem(categoryName, R.drawable.acumen));
                break;
            case "Airborne":
                drawerList.add(new DrawerItem(categoryName, R.drawable.airborne));
                break;
            case "Alacrity":
                drawerList.add(new DrawerItem(categoryName, R.drawable.alacrity));
                break;
            case "Bizzmaestro":
                drawerList.add(new DrawerItem(categoryName, R.drawable.bizzmeastro));
                break;
            case "Cheminova":
                drawerList.add(new DrawerItem(categoryName, R.drawable.cheminova));
                break;
            case "Constructure":
                drawerList.add(new DrawerItem(categoryName, R.drawable.constructure));
                break;
            case "Cryptoss":
                drawerList.add(new DrawerItem(categoryName, R.drawable.cryptoss));
                break;
            case "Electrific":
                drawerList.add(new DrawerItem(categoryName, R.drawable.electrific));
                break;
            case "Energia":
                drawerList.add(new DrawerItem(categoryName, R.drawable.energia));
                break;
            case "Epsilon":
                drawerList.add(new DrawerItem(categoryName, R.drawable.epsilon));
                break;
            case "Gaming":
                drawerList.add(new DrawerItem(categoryName, R.drawable.gaming));
                break;
            case "Featured Events":
                drawerList.add(new DrawerItem(categoryName, R.drawable.featured));
                break;
            case "Kraftwagen":
                drawerList.add(new DrawerItem(categoryName, R.drawable.kraftwagen));
                break;
            case "Mechatron":
                drawerList.add(new DrawerItem(categoryName, R.drawable.mechatron));
                break;
            case "Mechanize":
                drawerList.add(new DrawerItem(categoryName, R.drawable.mechanize));
                break;
            case "Robotrek":
                drawerList.add(new DrawerItem(categoryName, R.drawable.robotrek));
                break;
            case "Turing":
                drawerList.add(new DrawerItem(categoryName, R.drawable.turing));
                break;
            case "Open":
            case "Open Events":
                drawerList.add(new DrawerItem(categoryName, R.drawable.open));
                break;
            default:
                drawerList.add(new DrawerItem(categoryName, R.mipmap.ic_launcher));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_favourites:
                startActivity(new Intent(getApplicationContext(), FavouritesActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.action_results:
                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.action_registration:
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(getResources().getColor(R.color.primary));
                // Application exit animation, Chrome enter animation.
                builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
                // vice versa
                builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                String regURL = nana ? Constants.URL_REGISTRATION : ParseConfig.getCurrentConfig().getString("register");
                CustomTabActivityHelper.openCustomTab(
                        this, customTabsIntent, Uri.parse(regURL), new WebViewFallback());
                break;
            case R.id.action_online_events:
                CustomTabsIntent.Builder builder1 = new CustomTabsIntent.Builder();
                builder1.setToolbarColor(getResources().getColor(R.color.primary));
                // Application exit animation, Chrome enter animation.
                builder1.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
                // vice versa
                builder1.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);
                CustomTabsIntent customTabsIntent1 = builder1.build();
                String ooURL = nana ? Constants.URL_ONLINE_EVENTS : ParseConfig.getCurrentConfig().getString("online");
                CustomTabActivityHelper.openCustomTab(
                        this, customTabsIntent1, Uri.parse(ooURL), new WebViewFallback());
                break;
            case R.id.action_developers:
                startActivity(new Intent(getApplicationContext(), DeveloperActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
        return super.onOptionsItemSelected(menuItem);
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

    private void setupViewPager(final ViewPager viewPager) {
        final DayViewPagerAdapter mDayViewPagerAdapter = new DayViewPagerAdapter(getSupportFragmentManager());
        mDayViewPagerAdapter.addFragment(day1, "DAY 1");
        mDayViewPagerAdapter.addFragment(day2, "DAY 2");
        mDayViewPagerAdapter.addFragment(day3, "DAY 3");
        mDayViewPagerAdapter.addFragment(day4, "DAY 4");
        viewPager.setAdapter(mDayViewPagerAdapter);
    }
}
