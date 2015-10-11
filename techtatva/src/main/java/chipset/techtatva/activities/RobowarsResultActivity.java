package chipset.techtatva.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.parse.ParseConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import chipset.potato.Potato;
import chipset.techtatva.R;
import chipset.techtatva.adapters.RobowarsResultAdapter;
import chipset.techtatva.resources.Constants;
import chipset.techtatva.resources.SwipeDownRefreshLayout;


public class RobowarsResultActivity extends AppCompatActivity {

    private static final String TAG_CAT = "categoryName";
    private static final String TAG_EVENT = "eventName";
    private static final String TAG_RES = "result";
    private ListView mResultView;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            loadResults();
            handler.postDelayed(this, 10000);
        }
    };
    ArrayList<HashMap<String, String>> resultList;
    private SwipeDownRefreshLayout mSwipeDownRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadActivty();
    }

    private void loadActivty() {
        setContentView(R.layout.activity_result);
        startActivity(new Intent(getApplicationContext(), FoodStallActivity.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSwipeDownRefreshLayout = (SwipeDownRefreshLayout) findViewById(R.id.result_swipe_refresh);
        mSwipeDownRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadResults();
            }
        });
        resultList = new ArrayList<>();
        loadResults();
        mResultView = (ListView) findViewById(R.id.Result_ListView);

        mResultView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout detailLayout = (LinearLayout) view.findViewById(R.id.detailsLayout);
                if (detailLayout.getVisibility() == View.VISIBLE) {
                    detailLayout.setVisibility(View.GONE);
                } else {
                    detailLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void loadResults() {
        resultList.clear();
        mSwipeDownRefreshLayout.setRefreshing(true);
        boolean nana = Potato.potate().Preferences().getSharedPreferenceBoolean(getApplicationContext(), "nana");
        String roboResultsURL = nana ? Constants.URL_ROBO_RESULTS : ParseConfig.getCurrentConfig().getString("robowars");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, roboResultsURL, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArr = response.getJSONArray("data");
                    Log.e("jArr", String.valueOf(jArr));
                    if (jArr.length() == 0) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("name", "Sorry");
                        map.put("college", "No results out yet!");
                        resultList.add(map);
                    } else {
                        for (int i = 0; i < jArr.length(); i++) {
                            JSONObject jObj = jArr.getJSONObject(i);
                            String name = jObj.getString("name");
                            int rank = jObj.getInt("rank");
                            String college = jObj.getString("college");
                            String points = jObj.getString("points");
                            String won = jObj.getString("won");
                            String lost = jObj.getString("lost");
                            String weight = jObj.getString("weight");
                            String dimension = jObj.getString("dimension");
                            String status = jObj.getString("status");
                            HashMap<String, String> map = new HashMap<>();
                            map.put("name", name);
                            map.put("college", college);
                            map.put("rank", String.valueOf(rank));
                            map.put("won", won);
                            map.put("lost", lost);
                            map.put("weight", weight);
                            map.put("dimension", dimension);
                            map.put("points", points);
                            map.put("status", status);
                            resultList.add(map);
                        }
                    }
                    RobowarsResultAdapter adapter = new RobowarsResultAdapter(getApplicationContext(), resultList);
                    adapter.notifyDataSetChanged();
                    mResultView.setAdapter(adapter);
                    mSwipeDownRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeDownRefreshLayout.setRefreshing(false);
                setContentView(R.layout.no_connection_layout);
                Button retryButton = (Button) findViewById(R.id.retry_button);
                retryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Potato.potate().Utils().isInternetConnected(getApplicationContext())) {
                            loadActivty();
                        }
                    }
                });
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
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

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 10000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}