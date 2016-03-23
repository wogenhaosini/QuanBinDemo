package com.quan.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.quan.demo.cart.DiscoveryDetailActivity;

/**
 * Created by quan_Bin on 15/07/01.
 */
public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }*/
        setContentView(R.layout.main_layout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        CoordinatorLayout coord = (CoordinatorLayout) findViewById(R.id.coord);
        CollapsingToolbarLayout toolBarLayouy = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayouy.setTitle("测试");
        //mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.LEFT);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //mToolbar.setTitle(R.string.nav_titile); //標題
        //mToolbar.setSubtitle(R.string.nav_subTitle); //副標題
        //mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        /**
         *关于字体的样式，可以在布局文件设置属性app:titleTextAppearance、app:subtitleTextAppearance
         * 或者代码setTitleTextAppearance、setSubTitleTextAppearance设置。
         */
        //mToolbar.setSubtitleTextAppearance(this, R.style.Nav_SubTitle);

        /**
         * actionBar.setDisplayHomeAsUpEnabled(true)    // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP

         actionBar.setDisplayShowHomeEnabled(true)   //使左上角图标可点击，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME

         actionBar.setDisplayShowCustomEnabled(true)  // 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用，对应ActionBar.DISPLAY_SHOW_CUSTOM

         actionBar.setDisplayShowTitleEnabled(true)   //对应ActionBar.DISPLAY_SHOW_TITLE
         *
         */

        //getSupportActionBar().setHomeButtonEnabled(false); //设置返回键可用
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //設置了 左上角的icon可動
        //getSupportActionBar().setDisplayShowHomeEnabled(false);//使左上角图标 是否展示
        //getSupportActionBar().setIcon(R.drawable.ic_launcher); //設置左上角的icon

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.dakai, R.string.guanbi);
        mActionBarDrawerToggle.syncState(); //設置為三道杠
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle); //不設置監聽 也不會動
        /**
         * setItemBackgroundResource(int)：给menu设置背景资源，对应的属性app:itemBackground
         * setItemIconTintList(ColorStateList)：给menu的icon设置颜色，对应的属性app:itemIconTint
         * setItemTextColor(ColorStateList)：给menu的item设置字体颜色，对应的属性app:itemTextColor
         navigationView.setItemIconTintList(new ColorStateList(){});
         */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(MainActivity.this, DiscoveryDetailActivity.class));
                return true;
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.quan.demo/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.quan.demo/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }
}
