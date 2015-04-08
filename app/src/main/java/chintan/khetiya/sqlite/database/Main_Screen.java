package chintan.khetiya.sqlite.database;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import chintan.khetiya.sqlite.cursor.R;

public class Main_Screen extends Activity {
    Button add_btn;
    Button add_myTable,update_myTable,deleteMyTable;
    Button add_ContactTable,update_ContactTable,deleteContactTable;
    ListView Contact_listview;
    ArrayList<Contact> contact_data = new ArrayList<Contact>();
    Contact_Adapter cAdapter;
    DatabaseHandler db;
    String Toast_msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
        try {
            Contact_listview = (ListView) findViewById(R.id.list);
            Contact_listview.setItemsCanFocus(false);
            add_btn = (Button) findViewById(R.id.add_btn);
            add_myTable = (Button) findViewById(R.id.button);
            update_myTable= (Button) findViewById(R.id.button2);
            deleteMyTable = (Button) findViewById(R.id.button3);
            add_ContactTable= (Button) findViewById(R.id.button4);
            update_ContactTable= (Button) findViewById(R.id.button5);
            deleteContactTable= (Button) findViewById(R.id.button6);
            Set_Referash_Data();

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("some error", "" + e);
        }
        add_myTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    try {
                        Log.i("KH","addMyTable");
                        dbHandler.insertMyTable(getBaseContext(), createUserTableTestJson(false));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        });
        update_myTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    try {
                        Log.i("KH","updateMyTable");
                        int i = dbHandler.updateMyTable(getBaseContext(), "457371",createUserTableTestJson(true));
                        Log.i("KH update result",i+"");
                        dbHandler.close();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        });
        deleteMyTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Log.i("KH","deleteMyTable");
                    dbHandler.deleteMyTable(getBaseContext(), "4428");
            }
        });
        add_ContactTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Log.i("KH","add_ContactTable");
                    dbHandler.insertContactTable(getBaseContext(), createContactTableTestJson(false));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        update_ContactTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Log.i("KH","update_ContactTable");
                    dbHandler.updateContactTable(getBaseContext(), "206321",createContactTableTestJson(true));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        deleteContactTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Log.i("KH","deleteContactTable");
                    dbHandler.deleteContactTable(getBaseContext(), "206321");
            }
        });
    }
    public JSONObject createContactTableTestJson(boolean isUpdate) throws JSONException {
        JSONObject insertDataJob = new JSONObject();
        if (!isUpdate)
        insertDataJob.put(Table.CONTACT_ID, getRandomString(6));
        else
            insertDataJob.put(Table.CONTACT_ID, "5566");
        insertDataJob.put(Table.CONTACT_NAME, "KH");
        insertDataJob.put(Table.CONTACT_MOBILE, getRandomString(10));
        insertDataJob.put(Table.CONTACT_EXTENSION, "0933913329");
        insertDataJob.put(Table.CONTACT_EMAIL, "5173");
        insertDataJob.put(Table.CONTACT_TYPE, getRandomString(1));
        insertDataJob.put(Table.CONTACT_DEPT, "Acer");
        insertDataJob.put(Table.CONTACT_HOME_URI, "Acer");
        insertDataJob.put(Table.CONTACT_DOMAIN, "Acer");
        insertDataJob.put(Table.IS_FAVORITE, "Acer");
        insertDataJob.put(Table.LAST_CHAT_CONTENT, "Acer");
        insertDataJob.put(Table.LAST_CHAT_TIME, "Acer");
        return insertDataJob;
    }
    public JSONObject createUserTableTestJson(boolean isUpdate) throws JSONException {
        JSONObject insertDataJob = new JSONObject();
        if (!isUpdate)
        insertDataJob.put(Table.USER_ID, getRandomString(4));
        else
        insertDataJob.put(Table.USER_ID, "5566");
        insertDataJob.put(Table.USER_PW, "KH");
        insertDataJob.put(Table.USER_NAME, getRandomString(2));
        insertDataJob.put(Table.USER_MOBILE, "0933913329");
        insertDataJob.put(Table.USER_EXT, "5173");
        insertDataJob.put(Table.USER_EMAIL, getRandomString(10));
        insertDataJob.put(Table.USER_DEPT, "Acer");
        insertDataJob.put(Table.USER_CONTACTS, "Acer");
        insertDataJob.put(Table.USER_INVITES, "Acer");
        insertDataJob.put(Table.USER_BE_INVITED, "Acer");
        insertDataJob.put(Table.USER_GROUP, "Acer");
        insertDataJob.put(Table.USER_GROUP_BE_INVITED, "Acer");
        insertDataJob.put(Table.USER_IMEI, "Acer");
        insertDataJob.put(Table.USER_REGISTER_ID, "Acer");
        insertDataJob.put(Table.USER_PICTURE, "Acer");
        insertDataJob.put(Table.USER_HOME_URI, "Acer");
        insertDataJob.put(Table.USER_DOMAIN, "Acer");

        return insertDataJob;
    }
    private static final String ALLOWED_CHARACTERS ="0123456789";
    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
    public void Call_My_Blog(View v) {
        Intent intent = new Intent(Main_Screen.this, My_Blog.class);
        startActivity(intent);

    }

    public void Set_Referash_Data() {
        contact_data.clear();
        db = new DatabaseHandler(this);
        ArrayList<Contact> contact_array_from_db = db.Get_Contacts();

        for (int i = 0; i < contact_array_from_db.size(); i++) {

            int tidno = contact_array_from_db.get(i).getID();
            String name = contact_array_from_db.get(i).getName();
            String mobile = contact_array_from_db.get(i).getPhoneNumber();
            String email = contact_array_from_db.get(i).getEmail();
            Contact cnt = new Contact();
            cnt.setID(tidno);
            cnt.setName(name);
            cnt.setEmail(email);
            cnt.setPhoneNumber(mobile);

            contact_data.add(cnt);
        }
        db.close();
        cAdapter = new Contact_Adapter(Main_Screen.this, R.layout.listview_row,
                contact_data);
        Contact_listview.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();
    }

    public void Show_Toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Set_Referash_Data();

    }

    public class Contact_Adapter extends ArrayAdapter<Contact> {
        Activity activity;
        int layoutResourceId;
        Contact user;
        ArrayList<Contact> data = new ArrayList<Contact>();

        public Contact_Adapter(Activity act, int layoutResourceId,
                               ArrayList<Contact> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            UserHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new UserHolder();
                holder.name = (TextView) row.findViewById(R.id.user_name_txt);
                holder.email = (TextView) row.findViewById(R.id.user_email_txt);
                holder.number = (TextView) row.findViewById(R.id.user_mob_txt);
                holder.edit = (Button) row.findViewById(R.id.btn_update);
                holder.delete = (Button) row.findViewById(R.id.btn_delete);
                row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            user = data.get(position);
            holder.edit.setTag(user.getID());
            holder.delete.setTag(user.getID());
            holder.name.setText(user.getName());
            holder.email.setText(user.getEmail());
            holder.number.setText(user.getPhoneNumber());

            holder.edit.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("Edit Button Clicked", "**********");

                    Intent update_user = new Intent(activity,
                            Add_Update_User.class);
                    update_user.putExtra("called", "update");
                    update_user.putExtra("USER_ID", v.getTag().toString());
                    activity.startActivity(update_user);

                }
            });
            holder.delete.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(final View v) {
                    // TODO Auto-generated method stub

                    // show a message while loader is loading

                    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete ");
                    final int user_id = Integer.parseInt(v.getTag().toString());
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok",
                            new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // MyDataObject.remove(positionToRemove);
                                    DatabaseHandler dBHandler = new DatabaseHandler(
                                            activity.getApplicationContext());
                                    dBHandler.Delete_Contact(user_id);
                                    Main_Screen.this.onResume();

                                }
                            });
                    adb.show();
                }

            });
            return row;

        }

        class UserHolder {
            TextView name;
            TextView email;
            TextView number;
            Button edit;
            Button delete;
        }

    }
}
