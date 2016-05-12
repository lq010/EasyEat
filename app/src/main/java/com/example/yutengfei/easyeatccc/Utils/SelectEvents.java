package com.example.yutengfei.easyeatccc.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yutengfei.easyeatccc.R;


/**
 * Created by yutengfei on 13/04/16.
 */
public class SelectEvents {

    private Context context;
    private View view;

    public SelectEvents(View view, Context context){
        this.view = view;
        this.context = context;
    }

    public void setListener(){

        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectEvents.this.selectEvents(SelectEvents.this.context, v);
            }
        });
    }

    public void selectEvents(Context Context,View view){

        int id = view.getId();

        switch (id){

            case R.id.icon_1:
            case R.id.icon_2:
            case R.id.icon_3:
            case R.id.icon_4:
            case R.id.icon_5:
            case R.id.icon_6:
            case R.id.icon_7:
            case R.id.icon_8:
                this.startActivity(context, com.example.yutengfei.easyeatccc.searchActivity.class,id, true);
                break;
            default:
                Toast.makeText(context,"default Item",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //finish==true if replace the activity
    //id passed to the new activity
    public void startActivity(Context context,Class theClass,int id, boolean finish){

        Intent intent = new Intent(context,theClass);
        Bundle switchingKey = new Bundle();
        intent.putExtra("id",id);
        intent.putExtras(switchingKey);
        Activity activity = (Activity)context;
        activity.startActivity(intent);
        if(finish)
            activity.finish();
    }
}