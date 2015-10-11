package chipset.techtatva.resources;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.parse.ParseConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import chipset.potato.Potato;
import chipset.techtatva.activities.EventActivity;
import chipset.techtatva.model.events.Category;
import chipset.techtatva.model.events.Event;

/**
 * Created by saketh on 26/9/15.
 */
public class UpdateDatabase extends AsyncTask<String, Void, String> {

    private boolean nana;

    public UpdateDatabase(Context context) {
        this.nana = Potato.potate().Preferences().getSharedPreferenceBoolean(context, "nana");
    }

    @Override
    protected String doInBackground(String... params) {
        String scheduleURL = nana ? Constants.URL_SCHEDULE : ParseConfig.getCurrentConfig().getString("schedule");
        JsonObjectRequest eventRequest = new JsonObjectRequest(Request.Method.GET, scheduleURL, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
                        EventActivity.dbHelper.insertEvent(event);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                EventActivity.dbHelper.dropTables();
                Log.d("Categories", response.toString());
                try {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        Category category = new Category();
                        category.setCatName(data.getJSONObject(i).getString("categoryName"));
                        category.setDescription(data.getJSONObject(i).getString("description"));
                        category.setCatType(data.getJSONObject(i).getString("categoryType"));
                        category.setCatId(Integer.parseInt(data.getJSONObject(i).getString("categoryID")));
                        EventActivity.dbHelper.insertCategory(category);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(EventActivity.mContext).add(catRequest);
        Volley.newRequestQueue(EventActivity.mContext).add(eventRequest);
        return null;
    }

}
