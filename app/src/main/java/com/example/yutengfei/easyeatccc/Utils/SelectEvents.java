package com.example.yutengfei.easyeatccc.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.yutengfei.easyeatccc.DetailRestaurant.DetailRestaurant;
import com.example.yutengfei.easyeatccc.R;


/**
 * Created by yutengfei on 13/04/16.
 */
public class SelectEvents {

    public static final String DEALS = "Deals";
    public static final String MENU = "Menu";
    public static final String SEAFOOD="Seafood";
    public static final String SPECIAL_OFF="SpecialOffer";
    public static final String PASTA="Pasta";
    public static final String DESSERT="Dessert";
    public static final String VEGETABLE="Vegetable";
    public static final String PIZAA="Pizza";

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

    public void selectEvents(Context context,View view){

        int id = view.getId();

        switch (id){
            case R.id.icon_1:   //Deals
                startActivity(context, DetailRestaurant.class,false,DEALS);
                break;
            case R.id.icon_2:   //dessert
                startActivity(context, DetailRestaurant.class,false,DESSERT);
                break;
            case R.id.icon_3:   //menu
                startActivity(context, DetailRestaurant.class,false,MENU);
                break;
            case R.id.icon_4:   //pasta
                startActivity(context, DetailRestaurant.class,false,PASTA);
                break;
            case R.id.icon_5:   //pizza
                startActivity(context, DetailRestaurant.class,false,PIZAA);
                break;
            case R.id.icon_6:   //seafood
                startActivity(context, DetailRestaurant.class,false,SEAFOOD);
                break;
            case R.id.icon_7:   //specialofffer
                startActivity(context, DetailRestaurant.class,false,SPECIAL_OFF);
                break;
            case R.id.icon_8:   //vegetable
                startActivity(context, DetailRestaurant.class,false,VEGETABLE);
                break;
            default:
                Toast.makeText(context,"default Item",Toast.LENGTH_SHORT).show();
                break;
        }

        return;

    }


    public void startActivity(Context context,Class theClass, boolean finish,String orderBy){

        Intent intent = new Intent();
        Activity activity = (Activity)context;
        intent.setClass(context,theClass);
        intent.putExtra("orderBy",orderBy);
        activity.startActivity(intent);
        if(finish)
            activity.finish();



    }
}