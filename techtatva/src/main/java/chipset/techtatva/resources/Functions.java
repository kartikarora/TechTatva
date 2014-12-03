package chipset.techtatva.resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONObject;

public class Functions {

	JSONParser jParser = new JSONParser();
	JSONArrayParser jArrayParser = new JSONArrayParser();

	public JSONObject instaFeed(String URL) {
        return jParser.getJSONFromUrl(URL);
	}

	public JSONArray events(String URL) {
        return jArrayParser.getJSONFromUrl(URL);
	}

	public JSONArray results(String URL) {
        return jArrayParser.getJSONFromUrl(URL);
	}

	/*
	 * Function to hide keyboard
	 */
	public void hideKeyboard(Context context, View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/*
	 * Function to get Typeface
	 */
	public Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/mr.ttf");
	}

	/*
	 * Function to put SharedPrefrences data
	 */

	public void putSharedPrefrences(Context context, String preferenceName,
			int val) {
		SharedPreferences pref = context
				.getSharedPreferences(preferenceName, 0); // 0 - for private
															// mode
		Editor editor = pref.edit();
		editor.clear();
		editor.putInt(preferenceName, val);
		editor.apply();
	}

	/*
	 * Function to put SharedPrefrences data
	 */

	public int getSharedPrefrencesInt(Context context, String preferenceName) {
		SharedPreferences pref = context
				.getSharedPreferences(preferenceName, 0); // 0 - for private
															// mode

        return pref.getInt(preferenceName, 0);
	}/*
	 * Function to put SharedPrefrences data
	 */

	public void putSharedPrefrences(Context context, String preferenceName,
			String val) {
		SharedPreferences pref = context
				.getSharedPreferences(preferenceName, 0); // 0 - for private
															// mode
		Editor editor = pref.edit();
		editor.clear();
		editor.putString(preferenceName, val);
		editor.apply();
	}

	/*
	 * Function to put SharedPrefrences data
	 */

	public String getSharedPrefrencesString(Context context,
			String preferenceName) {
		SharedPreferences pref = context
				.getSharedPreferences(preferenceName, 0); // 0 - for private
															// mode

        return pref.getString(preferenceName, "null");
	}

	/**
	 * function make get connection status
	 * */
	public boolean isConnected(Context context) {
		boolean isConnected;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		isConnected = (activeNetwork != null)
				&& (activeNetwork.isConnectedOrConnecting());
		return isConnected;
	}
}