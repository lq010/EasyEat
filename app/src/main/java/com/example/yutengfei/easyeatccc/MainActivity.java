package com.example.yutengfei.easyeatccc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yutengfei.easyeatccc.Utils.FileManager;
import com.example.yutengfei.easyeatccc.Utils.MainArrayAdapter;
import com.example.yutengfei.easyeatccc.Utils.SelectEvents;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    public static final int IS_ICONS = 100;
    public static final int IS_RECOMMAND_RESTAURANT = 200;

    private MainArrayAdapter  adapter;
    private List<MainPageItems> listData;
    private FileManager fileManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /*Now used for create Dir and files */
        fileManager = new FileManager();
        fileManager.initFile();

        /*Toolbar */
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle("Easy Eat");
        tb.setSubtitle("esercizi trovati");
        setSupportActionBar(tb);
        tb.setOnMenuItemClickListener(this.onMenuItemClick);

        /*implement listview*/
        /*inital MainPageItems*/
        this.initalListData();

        adapter = new MainArrayAdapter(this, R.layout.activity_main_listview_item, this.listData);

        ListView lv = (ListView) findViewById(R.id.activity_main_listView);
        lv.setAdapter(this.adapter);

        Log.d("MainActivity", "Oncreate()");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    //in response to the click on the bottom toolbar "main", "order", "admin"
    public void onTabClicked(View view)
    {
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.click_anim));
                SelectEvents se=new SelectEvents(view, MainActivity.this);
                se.selectEvents(MainActivity.this, view);
                Toast.makeText(MainActivity.this,"pressed",Toast.LENGTH_SHORT).show();

            }
        });

    }






    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    private void initalListData(){

        this.listData = new ArrayList<>();

        MainPageItems iconsItems = new MainPageItems(IS_ICONS);
        iconsItems.setIcons(R.drawable.deals);
        iconsItems.setIcons(R.drawable.menu);
        iconsItems.setIcons(R.drawable.pizza);
        iconsItems.setIcons(R.drawable.dessert);
        iconsItems.setIcons(R.drawable.seafood);
        iconsItems.setIcons(R.drawable.special);
        iconsItems.setIcons(R.drawable.vege);
        iconsItems.setIcons(R.drawable.pasta);

        this.listData.add(iconsItems);

        /*Add Recommand files*/
        MainPageItems fileItems  = new MainPageItems(IS_RECOMMAND_RESTAURANT);
        try {
            fileItems.setMenuFile(this.fileManager.getRestaurant(FileManager.RESTAURANT_RECOMMEND));
        }catch (Exception e){}

        this.listData.add(fileItems);
        Log.d("MainActivity", "initialListData() ");
    }

    public class MainPageItems {

        private int theType;
        private List<Integer> icons;
        private File menuFile;

        /*Restrant list*/
        //private List<RecommandRestanrant> restanrants;

        public MainPageItems(int theType){
            this.theType = theType;
            icons = new ArrayList<>();
        }

        public void setMenuFile(File menuFile) {
            this.menuFile = menuFile;
        }

        public void setTheType(int theType) {
            this.theType = theType;
        }

        public File getMenuFile() {
            return menuFile;
        }

        public int getTheType(){
            return this.theType;
        }

        public void setIcons(int iconId){
            icons.add( iconId);
        }

        public Iterator<Integer> getIcons(){
            return this.icons.iterator();

        }

    }



}