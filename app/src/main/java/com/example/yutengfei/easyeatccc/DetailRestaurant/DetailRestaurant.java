package com.example.yutengfei.easyeatccc.DetailRestaurant;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yutengfei.easyeatccc.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DetailRestaurant extends AppCompatActivity {

    private String orderBy;
    private Spinner spinner;
    private TimePicker time;
    private List<String> orderList;
    private ArrayAdapter<String> adapter;
    private RelativeLayout lunchTime;
    private Calendar calendar;
    private TextView tv ;
    private TimePickerDialog timeDialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        Intent intent = getIntent();
        orderBy = intent.getStringExtra("orderBy");

        Toolbar tb = (Toolbar)findViewById(R.id.order_toolbar);
        tb.setTitle(orderBy);
        setSupportActionBar(tb);

        spinner = (Spinner) findViewById(R.id.orderList);
        tv=(TextView)findViewById(R.id.lunchTime);
        lunchTime = (RelativeLayout) findViewById(R.id.setTime);

        adaptOrder();
        lunchTimeSet();

    }


    private void lunchTimeSet() {

        lunchTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timeDialog==null){
                    TimePickerDialog.OnTimeSetListener otsl = new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if(minute<10){
                                tv.setText(hourOfDay+":"+0+minute);
                            }else{
                                tv.setText(hourOfDay+":"+minute);
                            }

                            timeDialog.dismiss();
                        }
                    };
                    calendar = Calendar.getInstance(TimeZone.getDefault());
                    int hourOfDay=calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    timeDialog=new TimePickerDialog(DetailRestaurant.this,otsl,hourOfDay,minute,true);
                }
                timeDialog.show();
            }

        });
    }


    private void adaptOrder() {
        orderList = new ArrayList<String>();
        orderList.add("Distance");
        orderList.add("Price");
        orderList.add("Rating");
        orderList.add("Hot");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,orderList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                Toast.makeText(getApplicationContext(),"xxx"+spinner.getItemAtPosition(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"nothing",Toast.LENGTH_LONG).show();
            }
        });


    }
}
