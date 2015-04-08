package com.lmntrx.soolu.mydailydiary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
    ImageButton settingsBTN;
    ImageButton newEntryBTN;
    DatabaseAdapter dbAdapter;
    DatabaseAdapter.DatabaseHelper dbHelper;
    Cursor cursor;
    int columnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsBTN= (ImageButton) findViewById(R.id.settingsBTN);
        newEntryBTN= (ImageButton) findViewById(R.id.newEntryBTN);
        dbAdapter=new DatabaseAdapter(this);
        dbHelper=new DatabaseAdapter.DatabaseHelper(this);
        Toast.makeText(this,getDates(),Toast.LENGTH_SHORT).show();

        View.OnClickListener settingsBTN_Action=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.lmntrx.soolu.mydailydiary.Settings.class);
                startActivity(intent);
            }
        };
        View.OnClickListener newEntryBTN_Action=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.lmntrx.soolu.mydailydiary.NewDiaryEntry.class);
                startActivity(intent);
            }
        };
        settingsBTN.setOnClickListener(settingsBTN_Action);
        newEntryBTN.setOnClickListener(newEntryBTN_Action);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public String getDates(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String date="No Entries To Display";
        String[] columns={dbHelper.ID, dbHelper.DIARY_ENTRY, dbHelper.DATE};
        cursor=db.query(dbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while (cursor.moveToNext()){
            columnDate=cursor.getColumnIndex(dbHelper.DATE);
            date=cursor.getString(columnDate);
            buffer.append(date+"\n");
        }
        return buffer.toString();
    }
}
