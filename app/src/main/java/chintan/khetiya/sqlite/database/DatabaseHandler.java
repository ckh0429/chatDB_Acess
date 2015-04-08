package chintan.khetiya.sqlite.database;

import java.util.ArrayList;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private Context mContext;
    // Database Name
    private static final String DATABASE_NAME = "abChatDB";
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts table name

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_EMAIL = "email";
    private final ArrayList<Contact> contact_list = new ArrayList<Contact>();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("KH","onCreate(SQLiteDatabase db)");
        db.execSQL(createMyTable());
        db.execSQL(createContactTable());
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Table.MY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Table.CONTACT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Table.GROUP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Table.CHAT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Table.POST_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Table.MEETING_TABLE);
        // Create tables again
        onCreate(db);
    }


    // Getting single contact
    Contact Get_Contact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO, KEY_EMAIL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        cursor.close();
        db.close();

        return contact;
    }

    // Getting All Contacts
    public ArrayList<Contact> Get_Contacts() {
        try {
            contact_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.setID(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(2));
                    contact.setEmail(cursor.getString(3));
                    // Adding contact to list
                    contact_list.add(contact);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
            return contact_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_contact", "" + e);
        }

        return contact_list;
    }

    // Deleting single contact
    public void Delete_Contact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    // Getting contacts Count
    public int Get_Total_Contacts() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public String createMyTable() {
        String sql = "CREATE TABLE if not exists " + Table.MY_TABLE + "(" + Table.MY_ID
                + " CHAR(20) PRIMARY KEY, " + Table.MY_NAME + " CHAR(10) NOT NULL, " + Table.MY_PW
                + " CHAR(20) NOT NULL, " + Table.MY_MOBILE + " CHAR(10) NOT NULL, " + Table.MY_EXT
                + " CHAR(10) NOT NULL, " + Table.MY_EMAIL + " TEXT NOT NULL UNIQUE, " + Table.MY_DEPT + " TEXT, "
                + Table.MY_CONTACTS + " TEXT, " + Table.MY_INVITES + " TEXT, " + Table.MY_BE_INVITEd + " TEXT, "
                + Table.MY_GROUP + " TEXT, " + Table.MY_GROUP_BE_INVITEd + " TEXT, " + Table.MY_IMEI + " TEXT NOT NULL, "
                + Table.MY_REGISTER_ID + " TEXT NOT NULL, " + Table.MY_PICTURE + " TEXT, " + Table.MY_HOME_URI + " TEXT NOT NULL, "
                + Table.MY_DOMAIN + " TEXT NOT NULL" + ")";
        return sql;
    }

    public String createContactTable() {
        String sql = "CREATE TABLE if not exists " + Table.CONTACT_TABLE + "(" + Table.CONTACT_ID + " CHAR(20) PRIMARY KEY, " +
                Table.CONTACT_NAME + " CHAR(10) NOT NULL, " + Table.CONTACT_MOBILE + " CHAR(10) NOT NULL, "
                + Table.CONTACT_EXTENSION + " CHAR(10) NOT NULL, " + Table.CONTACT_EMAIL + " TEXT NOT NULL, "
                + Table.CONTACT_TYPE + " CHAR(1) NOT NULL, " + Table.CONTACT_DEPT + " TEXT, "
                + Table.CONTACT_HOME_URI + " TEXT, " + Table.CONTACT_DOMAIN + " TEXT, " + Table.IS_FAVORITE + " CHAR (10), "
                + Table.LAST_CHAT_CONTENT + " TEXT, "
                + Table.LAST_CHAT_TIME + " TEXT NOT NULL" + ")";
        return sql;
    }

    public void insertMyTable(Context context, JSONObject rawData) throws JSONException {
        String myID = (rawData.has(Table.USER_ID)) ? rawData.getString(Table.USER_ID) : "";
        String myName = (rawData.has(Table.USER_NAME)) ? rawData.getString(Table.USER_NAME) : "";
        String myPW = (rawData.has(Table.USER_PW)) ? rawData.getString(Table.USER_PW) : "";
        String myMobile = (rawData.has(Table.USER_MOBILE)) ? rawData.getString(Table.USER_MOBILE) : "";
        String myExt = (rawData.has(Table.USER_EXT)) ? rawData.getString(Table.USER_EXT) : "";
        String myEmail = (rawData.has(Table.USER_EMAIL)) ? rawData.getString(Table.USER_EMAIL) : "";
        String myDept = (rawData.has(Table.USER_DEPT)) ? rawData.getString(Table.USER_DEPT) : "";
        String myContacts = (rawData.has(Table.USER_CONTACTS)) ? rawData.getString(Table.USER_CONTACTS) : "";
        String myInvites = (rawData.has(Table.USER_INVITES)) ? rawData.getString(Table.USER_INVITES) : "";
        String myBeInvited = (rawData.has(Table.USER_BE_INVITED)) ? rawData.getString(Table.USER_BE_INVITED) : "";
        String myGroup = (rawData.has(Table.USER_GROUP)) ? rawData.getString(Table.USER_GROUP) : "";
        String myGroupBeInvited = (rawData.has(Table.USER_GROUP_BE_INVITED)) ? rawData.getString(Table.USER_GROUP_BE_INVITED) : "";
        String myIMEI = (rawData.has(Table.USER_IMEI)) ? rawData.getString(Table.USER_IMEI) : "";
        String myRegisterID = (rawData.has(Table.USER_REGISTER_ID)) ? rawData.getString(Table.USER_REGISTER_ID) : "";
        String myPicture = (rawData.has(Table.USER_PICTURE)) ? rawData.getString(Table.USER_PICTURE) : "";
        String myHomeUri = (rawData.has(Table.USER_HOME_URI)) ? rawData.getString(Table.USER_HOME_URI) : "";
        String myDomain = (rawData.has(Table.USER_DOMAIN)) ? rawData.getString(Table.USER_DOMAIN) : "";

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.MY_ID, myID);
        values.put(Table.MY_PW, myPW);
        values.put(Table.MY_NAME, myName);
        values.put(Table.MY_MOBILE, myMobile);
        values.put(Table.MY_EXT, myExt);
        values.put(Table.MY_EMAIL, myEmail);
        values.put(Table.MY_DEPT, myDept);
        values.put(Table.MY_CONTACTS, myContacts);
        values.put(Table.MY_INVITES, myInvites);
        values.put(Table.MY_BE_INVITEd, myBeInvited);
        values.put(Table.MY_GROUP, myGroup);
        values.put(Table.MY_GROUP_BE_INVITEd, myGroupBeInvited);
        values.put(Table.MY_IMEI, myIMEI);
        values.put(Table.MY_REGISTER_ID, myRegisterID);
        values.put(Table.MY_PICTURE, myPicture);
        values.put(Table.MY_HOME_URI, myHomeUri);
        values.put(Table.MY_DOMAIN, myDomain);
        // Inserting Row
        db.insert(Table.MY_TABLE, null, values);
        db.close();
        System.out.println("Records created successfully");
    }

    public void insertContactTable(Context context, JSONObject rawData) throws JSONException {
        String CONTACT_ID = (rawData.has(Table.CONTACT_ID)) ? rawData.getString(Table.CONTACT_ID) : "";
        String CONTACT_NAME = (rawData.has(Table.CONTACT_NAME)) ? rawData.getString(Table.CONTACT_NAME) : "";
        String CONTACT_MOBILE = (rawData.has(Table.CONTACT_MOBILE)) ? rawData.getString(Table.CONTACT_MOBILE) : "";
        String CONTACT_EXTENSION = (rawData.has(Table.CONTACT_EXTENSION)) ? rawData.getString(Table.CONTACT_EXTENSION) : "";
        String CONTACT_EMAIL = (rawData.has(Table.CONTACT_EMAIL)) ? rawData.getString(Table.CONTACT_EMAIL) : "";
        String CONTACT_TYPE = (rawData.has(Table.CONTACT_TYPE)) ? rawData.getString(Table.CONTACT_TYPE) : "";
        String CONTACT_DEPT = (rawData.has(Table.CONTACT_DEPT)) ? rawData.getString(Table.CONTACT_DEPT) : "";
        String CONTACT_HOME_URI = (rawData.has(Table.CONTACT_HOME_URI)) ? rawData.getString(Table.CONTACT_HOME_URI) : "";
        String CONTACT_DOMAIN = (rawData.has(Table.CONTACT_DOMAIN)) ? rawData.getString(Table.CONTACT_DOMAIN) : "";
        String IS_FAVORITE = (rawData.has(Table.IS_FAVORITE)) ? rawData.getString(Table.IS_FAVORITE) : "";
        String LAST_CHAT_CONTENT = (rawData.has(Table.LAST_CHAT_CONTENT)) ? rawData.getString(Table.LAST_CHAT_CONTENT) : "";
        String LAST_CHAT_TIME = (rawData.has(Table.LAST_CHAT_TIME)) ? rawData.getString(Table.LAST_CHAT_TIME) : "";


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.CONTACT_ID, CONTACT_ID);
        values.put(Table.CONTACT_NAME, CONTACT_NAME);
        values.put(Table.CONTACT_MOBILE, CONTACT_MOBILE);
        values.put(Table.CONTACT_EXTENSION, CONTACT_EXTENSION);
        values.put(Table.CONTACT_EMAIL, CONTACT_EMAIL);
        values.put(Table.CONTACT_TYPE, CONTACT_TYPE);
        values.put(Table.CONTACT_DEPT, CONTACT_DEPT);
        values.put(Table.CONTACT_HOME_URI, CONTACT_HOME_URI);
        values.put(Table.CONTACT_DOMAIN, CONTACT_DOMAIN);
        values.put(Table.IS_FAVORITE, IS_FAVORITE);
        values.put(Table.LAST_CHAT_CONTENT, LAST_CHAT_CONTENT);
        values.put(Table.LAST_CHAT_TIME, LAST_CHAT_TIME);

        // Inserting Row
        db.insert(Table.CONTACT_TABLE, null, values);
        db.close();
        System.out.println("Records created successfully");
    }

    public int updateMyTable(Context context, String myID, JSONObject rawData) throws JSONException {
        String myName = (rawData.has(Table.USER_NAME)) ? rawData.getString(Table.USER_NAME) : "";
        String myPW = (rawData.has(Table.USER_PW)) ? rawData.getString(Table.USER_PW) : "";
        String myMobile = (rawData.has(Table.USER_MOBILE)) ? rawData.getString(Table.USER_MOBILE) : "";
        String myExt = (rawData.has(Table.USER_EXT)) ? rawData.getString(Table.USER_EXT) : "";
        String myEmail = (rawData.has(Table.USER_EMAIL)) ? rawData.getString(Table.USER_EMAIL) : "";
        String myDept = (rawData.has(Table.USER_DEPT)) ? rawData.getString(Table.USER_DEPT) : "";
        String myContacts = (rawData.has(Table.USER_CONTACTS)) ? rawData.getString(Table.USER_CONTACTS) : "";
        String myInvites = (rawData.has(Table.USER_INVITES)) ? rawData.getString(Table.USER_INVITES) : "";
        String myBeInvited = (rawData.has(Table.USER_BE_INVITED)) ? rawData.getString(Table.USER_BE_INVITED) : "";
        String myGroup = (rawData.has(Table.USER_GROUP)) ? rawData.getString(Table.USER_GROUP) : "";
        String myGroupBeInvited = (rawData.has(Table.USER_GROUP_BE_INVITED)) ? rawData.getString(Table.USER_GROUP_BE_INVITED) : "";
        String myIMEI = (rawData.has(Table.USER_IMEI)) ? rawData.getString(Table.USER_IMEI) : "";
        String myRegisterID = (rawData.has(Table.USER_REGISTER_ID)) ? rawData.getString(Table.USER_REGISTER_ID) : "";
        String myPicture = (rawData.has(Table.USER_PICTURE)) ? rawData.getString(Table.USER_PICTURE) : "";
        String myHomeUri = (rawData.has(Table.USER_HOME_URI)) ? rawData.getString(Table.USER_HOME_URI) : "";
        String myDomain = (rawData.has(Table.USER_DOMAIN)) ? rawData.getString(Table.USER_DOMAIN) : "";

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.MY_ID, myID);
        values.put(Table.MY_PW, myPW);
        values.put(Table.MY_NAME, myName);
        values.put(Table.MY_MOBILE, myMobile);
        values.put(Table.MY_EXT, myExt);
        values.put(Table.MY_EMAIL, myEmail);
        values.put(Table.MY_DEPT, myDept);
        values.put(Table.MY_CONTACTS, myContacts);
        values.put(Table.MY_INVITES, myInvites);
        values.put(Table.MY_BE_INVITEd, myBeInvited);
        values.put(Table.MY_GROUP, myGroup);
        values.put(Table.MY_GROUP_BE_INVITEd, myGroupBeInvited);
        values.put(Table.MY_IMEI, myIMEI);
        values.put(Table.MY_REGISTER_ID, myRegisterID);
        values.put(Table.MY_PICTURE, myPicture);
        values.put(Table.MY_HOME_URI, myHomeUri);
        values.put(Table.MY_DOMAIN, myDomain);

        // updating Row
        return db.update(Table.MY_TABLE, values, Table.MY_ID + " = ?",
                new String[]{myID});
    }

    public int updateContactTable(Context context, String contactID, JSONObject rawData) throws JSONException {
        String CONTACT_ID = (rawData.has(Table.CONTACT_ID)) ? rawData.getString(Table.CONTACT_ID) : "";
        String CONTACT_NAME = (rawData.has(Table.CONTACT_NAME)) ? rawData.getString(Table.CONTACT_NAME) : "";
        String CONTACT_MOBILE = (rawData.has(Table.CONTACT_MOBILE)) ? rawData.getString(Table.CONTACT_MOBILE) : "";
        String CONTACT_EXTENSION = (rawData.has(Table.CONTACT_EXTENSION)) ? rawData.getString(Table.CONTACT_EXTENSION) : "";
        String CONTACT_EMAIL = (rawData.has(Table.CONTACT_EMAIL)) ? rawData.getString(Table.CONTACT_EMAIL) : "";
        String CONTACT_TYPE = (rawData.has(Table.CONTACT_TYPE)) ? rawData.getString(Table.CONTACT_TYPE) : "";
        String CONTACT_DEPT = (rawData.has(Table.CONTACT_DEPT)) ? rawData.getString(Table.CONTACT_DEPT) : "";
        String CONTACT_HOME_URI = (rawData.has(Table.CONTACT_HOME_URI)) ? rawData.getString(Table.CONTACT_HOME_URI) : "";
        String CONTACT_DOMAIN = (rawData.has(Table.CONTACT_DOMAIN)) ? rawData.getString(Table.CONTACT_DOMAIN) : "";
        String IS_FAVORITE = (rawData.has(Table.IS_FAVORITE)) ? rawData.getString(Table.IS_FAVORITE) : "";
        String LAST_CHAT_CONTENT = (rawData.has(Table.LAST_CHAT_CONTENT)) ? rawData.getString(Table.LAST_CHAT_CONTENT) : "";
        String LAST_CHAT_TIME = (rawData.has(Table.LAST_CHAT_TIME)) ? rawData.getString(Table.LAST_CHAT_TIME) : "";


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Table.CONTACT_ID, CONTACT_ID);
        values.put(Table.CONTACT_NAME, CONTACT_NAME);
        values.put(Table.CONTACT_MOBILE, CONTACT_MOBILE);
        values.put(Table.CONTACT_EXTENSION, CONTACT_EXTENSION);
        values.put(Table.CONTACT_EMAIL, CONTACT_EMAIL);
        values.put(Table.CONTACT_TYPE, CONTACT_TYPE);
        values.put(Table.CONTACT_DEPT, CONTACT_DEPT);
        values.put(Table.CONTACT_HOME_URI, CONTACT_HOME_URI);
        values.put(Table.CONTACT_DOMAIN, CONTACT_DOMAIN);
        values.put(Table.IS_FAVORITE, IS_FAVORITE);
        values.put(Table.LAST_CHAT_CONTENT, LAST_CHAT_CONTENT);
        values.put(Table.LAST_CHAT_TIME, LAST_CHAT_TIME);

        // Inserting Row
        return db.update(Table.CONTACT_TABLE, values, Table.CONTACT_ID + " = ?",
                new String[]{contactID});
    }
    public void deleteMyTable(Context context, String MyID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table.MY_TABLE, Table.MY_ID + " = ?",
                new String[]{MyID});
        db.close();
    }
    public void deleteContactTable(Context context, String contactID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table.CONTACT_TABLE, Table.CONTACT_ID + " = ?",
                new String[]{contactID});
        db.close();
    }
}
