package chipset.techtatva.applications;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;

import io.fabric.sdk.android.Fabric;

public class InitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(getApplicationContext(), "3kIBsNTFceZmlpCiPpJA0WTYTiFNSVZVngTGJj7k", "Msd1PiRbmLovdfpA4PETqFQlImilNd54RSKzMkd6");
    }
}