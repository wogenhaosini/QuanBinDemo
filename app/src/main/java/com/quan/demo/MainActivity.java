package com.quan.demo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by quan_Bin on 15/07/01.
 */
public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolbar.setTitle(R.string.nav_titile); //標題
        mToolbar.setSubtitle(R.string.nav_subTitle); //副標題
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        /**
         *关于字体的样式，可以在布局文件设置属性app:titleTextAppearance、app:subtitleTextAppearance
         * 或者代码setTitleTextAppearance、setSubTitleTextAppearance设置。
         */
        mToolbar.setSubtitleTextAppearance(this, R.style.Nav_SubTitle);


        /**
         * actionBar.setDisplayHomeAsUpEnabled(true)    // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP

         actionBar.setDisplayShowHomeEnabled(true)   //使左上角图标可点击，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME

         actionBar.setDisplayShowCustomEnabled(true)  // 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用，对应ActionBar.DISPLAY_SHOW_CUSTOM

         actionBar.setDisplayShowTitleEnabled(true)   //对应ActionBar.DISPLAY_SHOW_TITLE
         *
         */

        getSupportActionBar().setHomeButtonEnabled(false); //设置返回键可用
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //設置了 左上角的icon可動
        getSupportActionBar().setDisplayShowHomeEnabled(false);//使左上角图标 是否展示
        getSupportActionBar().setIcon(R.drawable.ic_launcher); //設置左上角的icon

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.dakai, R.string.guanbi) ;
        mActionBarDrawerToggle.syncState(); //設置為三道杠
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle); //不設置監聽 也不會動
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
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
