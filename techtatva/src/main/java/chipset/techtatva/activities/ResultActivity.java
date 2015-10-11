package chipset.techtatva.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import chipset.techtatva.adapters.ResultAdapter;
import chipset.techtatva.resources.Constants;
import chipset.techtatva.resources.SwipeDownRefreshLayout;


public class ResultActivity extends AppCompatActivity {

    private static final String TAG_CAT = "categoryName";
    private static final String TAG_EVENT = "eventName";
    private static final String TAG_RES = "result";
    private ListView mResultView;
    ArrayList<HashMap<String, String>> resultList;
    private SwipeDownRefreshLayout mSwipeDownRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadActivity();
    }

    private void loadResults() {
        resultList.clear();
        mSwipeDownRefreshLayout.setRefreshing(true);
        boolean nana = Potato.potate().Preferences().getSharedPreferenceBoolean(getApplicationContext(), "nana");
        String resultsURL = nana ? Constants.URL_RESULTS : ParseConfig.getCurrentConfig().getString("results");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, resultsURL, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArr = response.getJSONArray("data");
                    Log.e("jArr", String.valueOf(jArr));
                    if (jArr.length() == 0) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put(TAG_EVENT, "Sorry");
                        map.put(TAG_CAT, "No results out yet!");
                        resultList.add(map);
                    } else {
                        for (int i = 0; i < jArr.length(); i++) {
                            JSONObject jObj = jArr.getJSONObject(i);
                            String name = jObj.getString(TAG_EVENT);
                            String categ = jObj.getString(TAG_CAT);
                            HashMap<String, String> map = new HashMap<>();
                            map.put(TAG_EVENT, name);
                            map.put(TAG_CAT, categ);
                            map.put(TAG_RES, jObj.getString(TAG_RES));
                            resultList.add(map);
                        }
                    }
                    ResultAdapter adapter = new ResultAdapter(
                            getApplicationContext(), resultList);
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
                            loadActivity();
                        }
                    }
                });
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        else if (item.getItemId() == R.id.action_robowars)
            startActivity(new Intent(ResultActivity.this, RobowarsResultActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void loadActivity() {
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
        try {
            mResultView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int arg2, long arg3) {

                    TextView resView = (TextView) arg1.findViewById(R.id.resultRes);
                    if (resView.getVisibility() == View.GONE)
                        resView.setVisibility(View.VISIBLE);
                    else
                        resView.setVisibility(View.GONE);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}