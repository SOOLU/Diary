package com.lmntrx.soolu.mydailydiary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NewDiaryEntry extends Activity {

    DatabaseAdapter dbHelper;
    String dairyEntry,date;
    TextView dairyEntryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary_entry);
        dairyEntryTextView=(TextView)findViewById(R.id.newEntry);
        dairyEntry=dairyEntryTextView.getText().toString();
        dbHelper=new DatabaseAdapter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_diary_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newEntryAdd(View view){
        long id=dbHelper.insertEntry(dairyEntry,date);
        if (id<0){
            Toast.makeText(getApplicationContext(),"error adding value",Toast.LENGTH_LONG).show();
        }
        else if (id>0){
            Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
        }
    }
}
