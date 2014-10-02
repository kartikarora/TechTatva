package chipset.techtatva;

import static chipset.techtatva.resources.Constants.RES_CATEGORY;
import static chipset.techtatva.resources.Constants.RES_NAME;
import static chipset.techtatva.resources.Constants.RES_RESULT;
import static chipset.techtatva.resources.Constants.URL_RESULTS;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import chipset.techtatva.resources.Functions;
import chipset.techtatva.resources.ResultAdapter;

public class ResultActivity extends Activity {

	SwipeRefreshLayout resultSwipe;
	ListView resultList;
	Functions functions = new Functions();
	ArrayList<HashMap<String, String>> dataMap = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		resultSwipe = (SwipeRefreshLayout) findViewById(R.id.resultSwipe);
		resultList = (ListView) findViewById(R.id.result_list);

		resultSwipe.setColorSchemeResources(R.color.green_light,
				R.color.red_light, R.color.purple, R.color.blue_light);
		resultSwipe.setRefreshing(true);
		new ResultGet().execute();
		resultSwipe.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				dataMap.clear();
				new ResultGet().execute();
			}
		});

		resultList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						ResultActivity.this);
				dialog.setTitle((String) dataMap.get(arg2).get(RES_NAME)
						+ " - " + (String) dataMap.get(arg2).get(RES_CATEGORY));
				dialog.setMessage((String) dataMap.get(arg2).get(RES_RESULT));
				dialog.setCancelable(false);
				dialog.setNeutralButton(android.R.string.ok, null);
				dialog.create();
				dialog.show();

			}
		});
	}

	private class ResultGet extends AsyncTask<String, String, JSONArray> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected JSONArray doInBackground(String... args) {
			JSONArray jArr = functions.results(URL_RESULTS);
			return jArr;
		}

		@Override
		protected void onPostExecute(JSONArray jArr) {
			resultSwipe.setRefreshing(false);
			try {

				Log.e("jArr", String.valueOf(jArr));
				if (jArr.length() == 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(RES_NAME, "Sorry");
					map.put(RES_CATEGORY, "No results out yet!");
					dataMap.add(map);
				} else {
					for (int i = 0; i < jArr.length(); i++) {

						JSONObject jObj = jArr.getJSONObject(i);
						String name = jObj.getString(RES_NAME);
						String categ = jObj.getString(RES_CATEGORY);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(RES_NAME, name);
						map.put(RES_CATEGORY, categ);
						map.put(RES_RESULT, jObj.getString(RES_RESULT));
						dataMap.add(map);
					}

				}
				resultList.setAdapter(new ResultAdapter(
						getApplicationContext(), dataMap));

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		} else if (id == R.id.action_dev) {
			startActivity(new Intent(ResultActivity.this,
					DeveloperActivity.class));
		} else if (id == R.id.action_contact) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					ResultActivity.this);
			dialog.setTitle("Contact Us");
			dialog.setMessage(getResources().getString(R.string.contactus));
			dialog.setCancelable(false);
			dialog.setNeutralButton(android.R.string.ok, null);
			dialog.create();
			dialog.show();
		}
		return super.onOptionsItemSelected(item);
	}
}