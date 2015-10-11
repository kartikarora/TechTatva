package chipset.techtatva.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import chipset.techtatva.R;
import chipset.techtatva.adapters.InstaFeedListAdapter;
import chipset.techtatva.model.instagram.InstaFeed;
import chipset.techtatva.network.APIClient;
import chipset.techtatva.resources.SwipeDownRefreshLayout;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class InstaFeedActivity extends AppCompatActivity {

    private static final String TAG = InstaFeedActivity.class.getSimpleName();
    private ListView mInstaFeedListView;
    private SwipeDownRefreshLayout mSwipeDownRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadInstaFeed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void loadInstaFeed() {
        setContentView(R.layout.activity_insta_feed);
        startActivity(new Intent(getApplicationContext(), FoodStallActivity.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mInstaFeedListView = (ListView) findViewById(R.id.insta_feed_list_view);
        mSwipeDownRefreshLayout = (SwipeDownRefreshLayout) findViewById(R.id.insta_feed_swipe_refresh);

        mSwipeDownRefreshLayout.setColorSchemeResources(R.color.primary, R.color.primary_dark);

        APIClient.getInstagram().getFeed(new Callback<InstaFeed>() {
            @Override
            public void success(InstaFeed instaFeed, Response response) {
                mInstaFeedListView.setAdapter(new InstaFeedListAdapter(getApplicationContext(), instaFeed));
                mInstaFeedListView.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                setContentView(R.layout.no_connection_layout);
                Button retryButton = (Button) findViewById(R.id.retry_button);
                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadInstaFeed();
                    }
                });
            }
        });

        mSwipeDownRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeDownRefreshLayout.setRefreshing(true);
                APIClient.getInstagram().getFeed(new Callback<InstaFeed>() {
                    @Override
                    public void success(InstaFeed instaFeed, Response response) {
                        InstaFeedListAdapter adapter = new InstaFeedListAdapter(getApplicationContext(), instaFeed);
                        adapter.notifyDataSetChanged();
                        mInstaFeedListView.setAdapter(adapter);
                        mInstaFeedListView.setVisibility(View.VISIBLE);
                        mSwipeDownRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mSwipeDownRefreshLayout.setRefreshing(false);
                        setContentView(R.layout.no_connection_layout);
                        Button retryButton = (Button) findViewById(R.id.retry_button);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadInstaFeed();
                            }
                        });
                    }
                });
            }
        });

        mInstaFeedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0 && visibleItemCount > 0 && mInstaFeedListView.getChildAt(0).getTop() >= 0)
                    mSwipeDownRefreshLayout.setEnabled(true);
                else mSwipeDownRefreshLayout.setEnabled(false);
            }
        });
    }

}
