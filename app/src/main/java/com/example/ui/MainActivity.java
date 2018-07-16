package com.example.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;
    //水果
    private Fruit[] fruits = {
            new Fruit("Apple1",R.drawable.mv_1),
            new Fruit("Apple2",R.drawable.mv_2),
            new Fruit("Apple3",R.drawable.mv_3),
            new Fruit("Apple4",R.drawable.mv_4),
            new Fruit("Apple5",R.drawable.mv_5),
            new Fruit("Apple6",R.drawable.mv_6),
            new Fruit("Apple7",R.drawable.mv_7),
            new Fruit("Apple8",R.drawable.mv_8),
            new Fruit("Apple9",R.drawable.mv_9),
            new Fruit("Apple10",R.drawable.mv_10),




    };
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"你点击了回退",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"你点击了删除",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"你点击了设置",Toast.LENGTH_SHORT).show();
                break;
            default:

        }
        return true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Toast.makeText(MainActivity.this, "点击了悬浮", Toast.LENGTH_SHORT).show();
                Snackbar.make(view,"删除数据",Snackbar.LENGTH_SHORT).setAction("确定",new View.OnClickListener(){
                    public void onClick(View v){
                        Toast.makeText(MainActivity.this, "点击了悬浮", Toast.LENGTH_SHORT).show();
                    }

                }).show();

            }

        });




        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
           // actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            public boolean onNavigationItemSelected(MenuItem item){
                mDrawerLayout.closeDrawers();
                return true;
            }


        });

        //水果
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        //下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                refreshFruits();
            }

        });


    }

        private void refreshFruits(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                     Thread.sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initFruits();
                            adapter.notifyDataSetChanged();
                            swipeRefresh.setRefreshing(false);
                        }
                    });

                }
            }).start();
        }

        private void initFruits(){
            fruitList.clear();
            for(int i=0;i<50;i++){
                Random random = new Random();
                int index = random.nextInt(fruits.length);
                fruitList.add(fruits[index]);
        }

    }











}





































































