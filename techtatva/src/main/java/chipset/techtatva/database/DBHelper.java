package chipset.techtatva.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import chipset.techtatva.model.events.Category;
import chipset.techtatva.model.events.Event;
import chipset.techtatva.resources.Constants;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TTEvents.db";
    public static final String CATEGORIES_TABLE_NAME = "categories";
    public static final String EVENTS_TABLE_NAME = "events";
    public static final String FAV_EVENTS_TABLE_NAME = "favevents";
    public static final String COLUMN_ID = "id";
    public static final String CATEGORY_ID = "cid";
    public static final String EVENT_ID = "eid";
    public static final String DESCRIPTION = "description";
    public static final String NAME = "name";
    public static final String EVENT_MAX_NO = "maxno";
    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + CATEGORIES_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + CATEGORY_ID
                + " INTEGER," + NAME + " TEXT," + DESCRIPTION + " TEXT);");
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + EVENTS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + EVENT_ID
                + " INTEGER," + CATEGORY_ID + " INTEGER," + NAME + " TEXT," + DESCRIPTION + " TEXT," + EVENT_MAX_NO + " INTEGER," + Constants.EVENT_LOCATION + " TEXT," +
                Constants.EVENT_DATE + " TEXT," + Constants.EVENT_START_TIME + " TEXT," + Constants.EVENT_END_TIME + " TEXT," + Constants.EVENT_CONTACT_NAME + " TEXT," + Constants.EVENT_CONTACT_NUMBER + " TEXT," +
                Constants.EVENT_DAY + " INTEGER);");
    }

    public void createFavTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + FAV_EVENTS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + EVENT_ID
                + " INTEGER," + CATEGORY_ID + " INTEGER," + NAME + " TEXT," + DESCRIPTION + " TEXT," + EVENT_MAX_NO + " INTEGER," + Constants.EVENT_LOCATION + " TEXT," +
                Constants.EVENT_DATE + " TEXT," + Constants.EVENT_START_TIME + " TEXT," + Constants.EVENT_END_TIME + " TEXT," + Constants.EVENT_CONTACT_NAME + " TEXT," + Constants.EVENT_CONTACT_NUMBER + " TEXT," +
                Constants.EVENT_DAY + " INTEGER);");
        Log.d("db", "created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
        onCreate(db);
    }

    public void insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean CatExists = false;
        for (Category category1 : getAllCategories()) {
            if (category1.getCatId() == category.getCatId() &&category1.getCatName().equals(category.getCatName()) &&category.getDescription().equals(category1.getDescription())) {
                CatExists = true;
                break;
            }
        }
        if (!CatExists) {
            contentValues.put(CATEGORY_ID, category.getCatId());
            contentValues.put(NAME, category.getCatName());
            contentValues.put(DESCRIPTION, category.getDescription());
            db.insert(CATEGORIES_TABLE_NAME, null, contentValues);
        }
    }

    public void insertEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean eventExists = false;
        for(Event event1 : getAllEvents()){
            if(event.getEvent_name().equals(event1.getEvent_name()) && event.getCatId()==event1.getCatId() && event.getStartTime().equals(event1.getStartTime()) && event.getDay()==event1.getDay()){
                eventExists = true;
            }
        }
        if (!eventExists) {
            contentValues.put(EVENT_ID, event.getEvent_id());
            contentValues.put(CATEGORY_ID, event.getCatId());
            contentValues.put(NAME, event.getEvent_name());
            contentValues.put(DESCRIPTION, event.getDescription());
            contentValues.put(EVENT_MAX_NO, event.getEventMaxTeamNumber());
            contentValues.put(Constants.EVENT_LOCATION, event.getLocation());
            contentValues.put(Constants.EVENT_CONTACT_NAME, event.getContactName());
            contentValues.put(Constants.EVENT_CONTACT_NUMBER, event.getContactNumber());
            contentValues.put(Constants.EVENT_DATE, event.getDate());
            contentValues.put(Constants.EVENT_DAY, event.getDay());
            contentValues.put(Constants.EVENT_START_TIME, event.getStartTime());
            contentValues.put(Constants.EVENT_END_TIME, event.getEndTime());
            db.insert(EVENTS_TABLE_NAME, null, contentValues);
        }
    }

    public void dropTables() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + CATEGORIES_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + CATEGORY_ID
                + " INTEGER," + NAME + " TEXT," + DESCRIPTION + " TEXT);");
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + EVENTS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + EVENT_ID
                + " INTEGER," + CATEGORY_ID + " INTEGER," + NAME + " TEXT," + DESCRIPTION + " TEXT," + EVENT_MAX_NO + " INTEGER," + Constants.EVENT_LOCATION + " TEXT," +
                Constants.EVENT_DATE + " TEXT," + Constants.EVENT_START_TIME + " TEXT," + Constants.EVENT_END_TIME + " TEXT," + Constants.EVENT_CONTACT_NAME + " TEXT," + Constants.EVENT_CONTACT_NUMBER + " TEXT," +
                Constants.EVENT_DAY + " INTEGER);");

    }

    public void deleteAllFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + FAV_EVENTS_TABLE_NAME);
        db.execSQL("CREATE TABLE IF  NOT EXISTS " + FAV_EVENTS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + EVENT_ID
                + " INTEGER," + CATEGORY_ID + " INTEGER," + NAME + " TEXT," + DESCRIPTION + " TEXT," + EVENT_MAX_NO + " INTEGER," + Constants.EVENT_LOCATION + " TEXT," +
                Constants.EVENT_DATE + " TEXT," + Constants.EVENT_START_TIME + " TEXT," + Constants.EVENT_END_TIME + " TEXT," + Constants.EVENT_CONTACT_NAME + " TEXT," + Constants.EVENT_CONTACT_NUMBER + " TEXT," +
                Constants.EVENT_DAY + " INTEGER);");
    }

    public void addToFavorites(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean EventExists = false;
        for (Event event1 : getFavEvents())
            if (event.getEvent_id() == event1.getEvent_id()) {
                EventExists = true;
                Toast.makeText(mContext, "Already Exists", Toast.LENGTH_SHORT).show();
            }
        if (!EventExists) {
            contentValues.put(EVENT_ID, event.getEvent_id());
            contentValues.put(CATEGORY_ID, event.getCatId());
            contentValues.put(NAME, event.getEvent_name());
            contentValues.put(DESCRIPTION, event.getDescription());
            contentValues.put(EVENT_MAX_NO, event.getEventMaxTeamNumber());
            contentValues.put(Constants.EVENT_LOCATION, event.getLocation());
            contentValues.put(Constants.EVENT_CONTACT_NAME, event.getContactName());
            contentValues.put(Constants.EVENT_CONTACT_NUMBER, event.getContactNumber());
            contentValues.put(Constants.EVENT_DATE, event.getDate());
            contentValues.put(Constants.EVENT_DAY, event.getDay());
            contentValues.put(Constants.EVENT_START_TIME, event.getStartTime());
            contentValues.put(Constants.EVENT_END_TIME, event.getEndTime());
            db.insert(FAV_EVENTS_TABLE_NAME, null, contentValues);
            Toast.makeText(mContext, "Added!", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Event> getFavEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rows = db.rawQuery("SELECT * FROM " + FAV_EVENTS_TABLE_NAME, null);
        ArrayList<Event> events = new ArrayList<Event>();
        if (rows != null) {
            for (int i = 0; i < rows.getCount(); i++) {
                rows.moveToPosition(i);
                Event event = new Event();
                event.setEvent_id(rows.getInt(1));
                event.setCatId(rows.getInt(2));
                event.setEvent_name(rows.getString(3));
                event.setDescription(rows.getString(4));
                event.setEventMaxTeamNumber(rows.getInt(5));
                event.setLocation(rows.getString(6));
                event.setDate(rows.getString(7));
                event.setStartTime(rows.getString(8));
                event.setEndTime(rows.getString(9));
                event.setContactName(rows.getString(10));
                event.setContactNumber(rows.getString(11));
                event.setDay(rows.getInt(12));
                events.add(event);
            }
        }
        return events;
    }

    public ArrayList<Category> getAllCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rows = db.rawQuery("SELECT * FROM " + CATEGORIES_TABLE_NAME, null);
        ArrayList<Category> categories = new ArrayList<Category>();
        if (rows != null) {
            for (int i = 0; i < rows.getCount(); i++) {
                rows.moveToPosition(i);
                Category category = new Category();
                category.setCatId(rows.getInt(1));
                category.setCatName(rows.getString(2));
                category.setDescription(rows.getString(3));
                categories.add(category);
            }
        }
        return categories;
    }

    public ArrayList<Event> getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rows = db.rawQuery("SELECT * FROM " + EVENTS_TABLE_NAME, null);
        ArrayList<Event> events = new ArrayList<Event>();
        if (rows != null) {
            for (int i = 0; i < rows.getCount(); i++) {
                rows.moveToPosition(i);
                Event event = new Event();
                event.setEvent_id(rows.getInt(1));
                event.setCatId(rows.getInt(2));
                event.setEvent_name(rows.getString(3));
                event.setDescription(rows.getString(4));
                event.setEventMaxTeamNumber(rows.getInt(5));
                event.setLocation(rows.getString(6));
                event.setDate(rows.getString(7));
                event.setStartTime(rows.getString(8));
                event.setEndTime(rows.getString(9));
                event.setContactName(rows.getString(10));
                event.setContactNumber(rows.getString(11));
                event.setDay(rows.getInt(12));
                events.add(event);
            }
        }
        return events;
    }
}

