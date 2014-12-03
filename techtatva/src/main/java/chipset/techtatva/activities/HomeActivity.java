package chipset.techtatva.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import chipset.techtatva.R;
import chipset.techtatva.resources.Functions;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import static chipset.techtatva.resources.Constants.PREF_JSON;

@SuppressLint("SetJavaScriptEnabled")
public class HomeActivity extends ActionBarActivity {
    Functions functions = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_r);
        setSupportActionBar(toolbar);

        Button resultButton = (Button) findViewById(R.id.resultButton);
        Button eventsButton = (Button) findViewById(R.id.eventButton);
        Button instaFeedButton = (Button) findViewById(R.id.instaFeedButton);
        Button liveBlogButton = (Button) findViewById(R.id.liveBlogButton);
        Button featuredEventButton = (Button) findViewById(R.id.featuredEventButton);
        final Button tmctButton = (Button) findViewById(R.id.tmcButton);
        Typeface tf = functions.getTypeface(getApplicationContext());
        resultButton.setTypeface(tf);
        eventsButton.setTypeface(tf);
        instaFeedButton.setTypeface(tf);
        liveBlogButton.setTypeface(tf);
        featuredEventButton.setTypeface(tf);
        tmctButton.setTypeface(tf);

        resultButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (functions.isConnected(getApplicationContext())) {
                    startActivity(new Intent(HomeActivity.this,
                            ResultActivity.class));
                } else {
                    Crouton.showText(HomeActivity.this,
                            "No Internet Connection", Style.ALERT);
                }
            }
        });

        liveBlogButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (functions.isConnected(getApplicationContext())) {
                    startActivity(new Intent(HomeActivity.this,
                            WebViewActivity.class).putExtra("TYPE", 2));
                } else {
                    Crouton.showText(HomeActivity.this,
                            "No Internet Connection", Style.ALERT);
                }
            }
        });

        tmctButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (functions.isConnected(getApplicationContext())) {
                    startActivity(new Intent(HomeActivity.this,
                            WebViewActivity.class).putExtra("TYPE", 3));
                } else {
                    String fe6[] = getResources().getStringArray(R.array.fe6);
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                            HomeActivity.this);
                    alert.setTitle(fe6[0]);
                    alert.setMessage(fe6[1]
                            + fe6[2]
                            + fe6[3]
                            + fe6[4]
                            + "\n\nFor more details, switch on your internet and tap on Details");
                    alert.setNegativeButton(android.R.string.ok, null);
                    alert.setPositiveButton("DETAILS",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    tmctButton.performClick();
                                }
                            });
                    alert.create();
                    alert.show();

                }

            }
        });

        eventsButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (functions.isConnected(getApplicationContext())) {
                    startActivity(new Intent(HomeActivity.this,
                            EventsActivity.class));
                } else {
                    if (functions.getSharedPrefrencesString(
                            getApplicationContext(), PREF_JSON).equals("null")) {
                        Crouton.showText(HomeActivity.this,
                                "No Internet Connection", Style.ALERT);
                    } else {
                        startActivity(new Intent(HomeActivity.this,
                                EventsActivity.class));
                    }
                }

            }
        });

        instaFeedButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (functions.isConnected(getApplicationContext())) {
                    startActivity(new Intent(HomeActivity.this,
                            InstaFeedActivity.class));
                } else {

                    Crouton.showText(HomeActivity.this,
                            "No Internet Connection", Style.ALERT);
                }
            }
        });

        featuredEventButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                startActivity(new Intent(HomeActivity.this,
                        FeaturedEventsActivity.class));

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_dev) {
            startActivity(new Intent(HomeActivity.this, DeveloperActivity.class));
        } else if (id == R.id.action_contact) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(
                    HomeActivity.this);
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
