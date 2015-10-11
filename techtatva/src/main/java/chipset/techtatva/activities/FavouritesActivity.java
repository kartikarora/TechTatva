package chipset.techtatva.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import chipset.techtatva.R;
import chipset.techtatva.adapters.EventCardListAdapter;
import chipset.techtatva.database.DBHelper;
import chipset.techtatva.model.events.Event;

public class FavouritesActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ArrayList<Event> mEventList;
    private RecyclerView mRecyclerView;
    private EventCardListAdapter mEventAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        startActivity(new Intent(getApplicationContext(), FoodStallActivity.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DBHelper(this);
        mEventList = new ArrayList<>();
        mEventList.addAll(dbHelper.getFavEvents());
        mEventAdapter = new EventCardListAdapter(this, mEventList, 0);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.event_list_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mEventAdapter);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_insta:
                startActivity(new Intent(getApplicationContext(), InstaFeedActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.action_results:
                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.action_deleteFavs:
                dbHelper.deleteAllFavorites();
                mProgressDialog.show();
                mEventList.clear();
                mEventList.addAll(dbHelper.getFavEvents());
                mEventAdapter = new EventCardListAdapter(getApplicationContext(), mEventList, 0);
                mRecyclerView.setAdapter(mEventAdapter);
                mProgressDialog.dismiss();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
