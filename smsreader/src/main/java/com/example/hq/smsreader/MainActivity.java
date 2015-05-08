package com.example.hq.smsreader;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    private Button bt=null;
    private TextView tv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        bt=(Button)findViewById(R.id.button1);
        tv=(TextView)findViewById(R.id.textview1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过contentResolver类读取短信
                ContentResolver contentResolver=MainActivity.this.getContentResolver();
                //短信的uri
                Uri uri=Uri.parse("content://sms/");
                //短信游标
                Cursor cursor=contentResolver.query(uri,new String[]{"address","date","body","type"}," type='1' and address='13048880249'",null,null);
                StringBuilder sb=new StringBuilder();
                while(cursor.moveToNext())
                {
                    sb.append("address:"+cursor.getString(0)+"\n");
                    Date date=new Date();
                    date.setTime(Long.parseLong(cursor.getString(1)));
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str=sdf.format(date);
                    sb.append("date:"+str+"\n");
                    sb.append("body:"+cursor.getString(2)+"\n");
                    sb.append("type:"+cursor.getString(3)+"\n");

                 }

                Log.v("------",sb.toString());
                cursor.close();
                tv.setText(sb.toString());
            }
        });

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
}
