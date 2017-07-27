package com.example.enchanterswapna.detectml;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    TextView tw1,tw2,tw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tw1 = (TextView) findViewById(R.id.textView1);
        tw2=(TextView)findViewById(R.id.text1);
        tw3=(TextView)findViewById(R.id.text2);
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Context context;

        Account[] accounts = AccountManager.get(this).getAccounts();

        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                tw1.setText(possibleEmail);

            }
        }

        Cursor c = getApplication().getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        tw2.setText(c.getString(c.getColumnIndex("display_name")));
        c.close();

        String main_data[] = {"data1", "is_primary", "data3", "data2", "data1", "is_primary", "photo_uri", "mimetype"};
        Object object = getContentResolver().query(Uri.withAppendedPath(android.provider.ContactsContract.Profile.CONTENT_URI, "data"),
                main_data, "mimetype=?",
                new String[]{"vnd.android.cursor.item/phone_v2"},
                "is_primary DESC");
        if (object != null) {
            do {
                if (!((Cursor) (object)).moveToNext())
                    break;
                String sw1 = ((Cursor) (object)).getString(4);
                tw3.setText(sw1);
            } while (true);
            ((Cursor) (object)).close();
        }

    }

}
