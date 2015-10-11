package chipset.techtatva.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import chipset.potato.Potato;
import chipset.techtatva.R;
import chipset.techtatva.activities.EventActivity;
import chipset.techtatva.activities.InstaFeedActivity;
import chipset.techtatva.adapters.EventCardListAdapter;
import chipset.techtatva.database.DBHelper;
import chipset.techtatva.model.events.Category;
import chipset.techtatva.model.events.Event;
import chipset.techtatva.resources.Constants;

public class DayFragment extends Fragment {
    private EventCardListAdapter mEventAdapter;
    public int day;
    DBHelper dbHelper;
    ArrayList<Event> mEventList;
    ArrayList<Category> mCategoryList;
    RecyclerView mRecyclerView;
    private boolean nana;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getActivity());
        dbHelper.createFavTable();
        mEventList = new ArrayList<Event>();
        mEventList.addAll(dbHelper.getAllEvents());
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.day1, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView search = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mEventAdapter.filterData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mEventAdapter.filterData(query);
                return false;
            }
        });
        search.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mEventAdapter.filterData("");
                return false;
            }
        });
        search.setSubmitButtonEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_insta) {
            startActivity(new Intent(getActivity(), InstaFeedActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        nana = Potato.potate().Preferences().getSharedPreferenceBoolean(getContext(), "nana");
        final View[] rootView = {inflater.inflate(R.layout.fragment_day, container, false)};
        mRecyclerView = (RecyclerView) rootView[0].findViewById(R.id.event_list_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCategoryList = new ArrayList<Category>();
        mCategoryList.addAll(dbHelper.getAllCategories());
        if (dbHelper.getAllCategories().size() != 0 && dbHelper.getAllEvents().size() != 0) {
            mCategoryList.addAll(dbHelper.getAllCategories());
            DataChange();
            if (EventActivity.drawerList.size() == 0)
                EventActivity.setupDrawer();
        } else if (Potato.potate().Utils().isInternetConnected(getActivity())) {
            prepareData();
        } else {
            rootView[0] = inflater.inflate(R.layout.no_connection_layout, container, false);
            Button retryButton = (Button) rootView[0].findViewById(R.id.retry_button);
            retryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Potato.potate().Utils().isInternetConnected(getActivity())) {
                        rootView[0] = inflater.inflate(R.layout.fragment_day, container, false);
                        prepareData();
                    }
                }
            });
        }
        return rootView[0];
    }


    private void prepareData() {
        if (!EventActivity.mProgressDialog.isShowing()) {
            Log.d("progress", "showing");
            EventActivity.mProgressDialog.show();
        }
        String scheduleURL = nana ? Constants.URL_SCHEDULE : ParseConfig.getCurrentConfig().getString("schedule");
        JsonObjectRequest eventRequest = new JsonObjectRequest(Request.Method.GET, scheduleURL, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mEventList.clear();
                Log.d("Events", response.toString());
                try {
                    Log.d("JSON", "loading");
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Event event = new Event();
                        event.setCatId(Integer.parseInt(data.getJSONObject(i).getString(Constants.EVENT_CATEGORY_ID)));
                        event.setDescription(data.getJSONObject(i).getString(Constants.EVENT_DETAIL));
                        event.setEvent_id(Integer.parseInt(data.getJSONObject(i).getString(Constants.EVENT_ID)));
                        event.setEvent_name(data.getJSONObject(i).getString(Constants.EVENT_NAME));
                        event.setEventMaxTeamNumber(Integer.parseInt(data.getJSONObject(i).getString(Constants.EVENT_MAX_TEAM_SIZE)));
                        event.setDay(Integer.parseInt(data.getJSONObject(i).getString(Constants.EVENT_DAY)));
                        event.setContactName(data.getJSONObject(i).getString(Constants.EVENT_CONTACT_NAME));
                        event.setContactNumber(data.getJSONObject(i).getString(Constants.EVENT_CONTACT_NUMBER));
                        event.setDate(data.getJSONObject(i).getString(Constants.EVENT_DATE));
                        event.setStartTime(data.getJSONObject(i).getString(Constants.EVENT_START_TIME));
                        event.setEndTime(data.getJSONObject(i).getString(Constants.EVENT_END_TIME));
                        event.setLocation(data.getJSONObject(i).getString(Constants.EVENT_LOCATION));
                        dbHelper.insertEvent(event);
                        mEventList.add(event);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                DataChange();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        String categoriesURL = nana ? Constants.URL_CATEGORIES : ParseConfig.getCurrentConfig().getString("categories");
        JsonObjectRequest catRequest = new JsonObjectRequest(Request.Method.GET, categoriesURL, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mCategoryList.clear();
                dbHelper.dropTables();
                Log.d("Categories", response.toString());
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Category category = new Category();
                        category.setCatName(data.getJSONObject(i).getString("categoryName"));
                        category.setDescription(data.getJSONObject(i).getString("description"));
                        category.setCatType(data.getJSONObject(i).getString("categoryType"));
                        category.setCatId(Integer.parseInt(data.getJSONObject(i).getString("categoryID")));
                        dbHelper.insertCategory(category);
                        mCategoryList.add(category);
                    }
                    if (EventActivity.drawerList.size() == 0)
                        EventActivity.setupDrawer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(getActivity()).add(catRequest);
        Volley.newRequestQueue(getActivity()).add(eventRequest);
        if (nana)
            ParseConfig.getInBackground();
    }

    private void Display() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mEventAdapter = new EventCardListAdapter(getActivity(), mEventList, day);
        mRecyclerView.setAdapter(mEventAdapter);
        try {
            EventActivity.mProgressDialog.dismiss();
        } catch (Exception e) {
        }
    }

    public void DataChange() {
        String catName = Potato.potate().Preferences().getSharedPreferenceString(getActivity(), "cat");
        Log.d("change", catName);
        mEventList.clear();
        if (!catName.toLowerCase().equals("all events")) {
            int catId = 0;
            for (Category category : mCategoryList)
                if (category.getCatName().toLowerCase().equals(catName.toLowerCase())) {
                    catId = category.getCatId();
                    break;
                }
            for (Event event : dbHelper.getAllEvents())
                if (event.getCatId() == catId)
                    mEventList.add(event);
        } else {
            mEventList.addAll(dbHelper.getAllEvents());
        }
        Display();
    }
}



