package com.quan.demo.cart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quan.demo.R;

/**
 * Created by QB on 2015/9/10.
 */
public class DiscoveryDetailActivity  extends AppCompatActivity {

    private CartView cartView;
    private Toolbar mToolbar;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_discovery_detail, null);
        setContentView(rootView);
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler.post(new Runnable() {
            @Override
            public void run() {
                cartView = new CartView(DiscoveryDetailActivity.this, operationClickListener);
                cartView.realize(Cart.getInstance().buildCart(JSON));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                rootView.addView(cartView.getView(), lp);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_left_fade_in, R.anim.activity_left_fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private CartView.CartListItemClick operationClickListener = new CartView.CartListItemClick() {

        @Override
        public void onAdditionClick(ShoppingGroup shoppingGroup, ShoppingGroup.ShoppingList shoppingList) {
            Float price = Float.valueOf(shoppingGroup.getPrice()) + Float.valueOf(shoppingList.getPrice());
            shoppingGroup.setPrice(String.valueOf(price));
            cartView.setTotalPrice(shoppingGroup, shoppingGroup.getPrice());
        }

        @Override
        public void onSubtractionClick(ShoppingGroup shoppingGroup, ShoppingGroup.ShoppingList shoppingList) {
            Float price = Float.valueOf(shoppingGroup.getPrice()) - Float.valueOf(shoppingList.getPrice());
            shoppingGroup.setPrice(String.valueOf(price));
            cartView.setTotalPrice(shoppingGroup, shoppingGroup.getPrice());
        }

        @Override
        public void onDeleteItem(ShoppingGroup shoppingGroup, View view) {
            if(shoppingGroup.getShoppingList().size() <= 0 ) {
                cartView.removeCartItem(view);
            }
        }
    };

    private String JSON = "{\n" +
            "    \"type\": \"jsReturn\",\n" +
            "    \"method\": \"cart.add\",\n" +
            "    \"data\": {\n" +
            "        \"cart_group_id\": 0,\n" +
            "        \"isOk\": false,\n" +
            "        \"payType\": 2,\n" +
            "        \"price\": 13.5,\n" +
            "        \"count\": 1,\n" +
            "        \"minPrice\": 30,\n" +
            "        \"priceText\": \"共￥13.5\",\n" +
            "        \"btnText\": \"差￥16.5起送\",\n" +
            "        \"propText\": \"闪电送，免运费\",\n" +
            "        \"emptyText\": \"购物车是空的\",\n" +
            "        \"shoppingList\": [\n" +
            "            {\n" +
            "                \"attribute\": \"\",\n" +
            "                \"brand_id\": \"1125\",\n" +
            "                \"brand_name\": \"\",\n" +
            "                \"cart_group_id\": \"0\",\n" +
            "                \"category_id\": \"105\",\n" +
            "                \"cid\": \"105\",\n" +
            "                \"corner_mark\": \"\",\n" +
            "                \"dealer_id\": \"3413\",\n" +
            "                \"hot_degree\": \"0\",\n" +
            "                \"store_nums\": \"50\",\n" +
            "                \"img\": \"http://images.beequick.cn/upload/goods/000/000/8936/300_300_0000008936.jpg\",\n" +
            "                \"specifics\": \"150g\",\n" +
            "                \"market_price\": \"15.80\",\n" +
            "                \"name\": \"【周年庆】祥德斋夹馅小麻花(椒盐)\",\n" +
            "                \"number\": \"6\",\n" +
            "                \"price\": \"13.50\",\n" +
            "                \"product_id\": \"8936\",\n" +
            "                \"shoppingCount\": 1,\n" +
            "                \"is_gift\": 0,\n" +
            "                \"id\": 8936,\n" +
            "                \"disabled\": false\n" +
            "            }\n" +
            "        ],\n" +
            "        \"group100002\": {\n" +
            "            \"cart_group_id\": \"100002\",\n" +
            "            \"isOk\": true,\n" +
            "            \"payType\": 2,\n" +
            "            \"price\": 195,\n" +
            "            \"count\": 2,\n" +
            "            \"minPrice\": 30,\n" +
            "            \"priceText\": \"共￥195\",\n" +
            "            \"btnText\": \"选好了\",\n" +
            "            \"propText\": \"新鲜美食，闪电送达\",\n" +
            "            \"emptyText\": \"购物车是空的\",\n" +
            "            \"shoppingList\": [\n" +
            "                {\n" +
            "                    \"attribute\": \"中杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100002\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/2247/200_200_0000002247.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"30.00\",\n" +
            "                    \"name\": \"100002 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"30.00\",\n" +
            "                    \"product_id\": \"2247\",\n" +
            "                    \"shoppingCount\": 1,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 2247,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100002\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4457/200_200_0000004457.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"33.00\",\n" +
            "                    \"name\": \"摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"33.00\",\n" +
            "                    \"product_id\": \"4457\",\n" +
            "                    \"shoppingCount\": 1,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4457,\n" +
            "                    \"disabled\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"group100003\": {\n" +
            "            \"cart_group_id\": \"100003\",\n" +
            "            \"isOk\": true,\n" +
            "            \"payType\": 2,\n" +
            "            \"price\": 195,\n" +
            "            \"count\": 3,\n" +
            "            \"minPrice\": 30,\n" +
            "            \"priceText\": \"共￥195\",\n" +
            "            \"btnText\": \"选好了\",\n" +
            "            \"propText\": \"新鲜美食，闪电送达\",\n" +
            "            \"emptyText\": \"购物车是空的\",\n" +
            "            \"shoppingList\": [\n" +
            "                {\n" +
            "                    \"attribute\": \"中杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100003\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/2247/200_200_0000002247.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"30.00\",\n" +
            "                    \"name\": \"100003 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"30.00\",\n" +
            "                    \"product_id\": \"2247\",\n" +
            "                    \"shoppingCount\": 2,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 2247,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100003\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4457/200_200_0000004457.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"33.00\",\n" +
            "                    \"name\": \"100003 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"33.00\",\n" +
            "                    \"product_id\": \"4457\",\n" +
            "                    \"shoppingCount\": 3,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4457,\n" +
            "                    \"disabled\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"group100004\": {\n" +
            "            \"cart_group_id\": \"100004\",\n" +
            "            \"isOk\": true,\n" +
            "            \"payType\": 2,\n" +
            "            \"price\": 195,\n" +
            "            \"count\": 6,\n" +
            "            \"minPrice\": 30,\n" +
            "            \"priceText\": \"共￥195\",\n" +
            "            \"btnText\": \"选好了\",\n" +
            "            \"propText\": \"新鲜美食，闪电送达\",\n" +
            "            \"emptyText\": \"购物车是空的\",\n" +
            "            \"shoppingList\": [\n" +
            "                {\n" +
            "                    \"attribute\": \"中杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100004\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/2247/200_200_0000002247.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"30.00\",\n" +
            "                    \"name\": \"100004 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"30.00\",\n" +
            "                    \"product_id\": \"2247\",\n" +
            "                    \"shoppingCount\": 2,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 2247,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100004\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4457/200_200_0000004457.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"33.00\",\n" +
            "                    \"name\": \"100004 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"33.00\",\n" +
            "                    \"product_id\": \"4457\",\n" +
            "                    \"shoppingCount\": 3,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4457,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"超大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100004\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4486/200_200_0000004486.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"36.00\",\n" +
            "                    \"name\": \"100004 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"36.00\",\n" +
            "                    \"product_id\": \"4486\",\n" +
            "                    \"shoppingCount\": 1,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4486,\n" +
            "                    \"disabled\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"group100006\": {\n" +
            "            \"cart_group_id\": \"100006\",\n" +
            "            \"isOk\": true,\n" +
            "            \"payType\": 2,\n" +
            "            \"price\": 195,\n" +
            "            \"count\": 6,\n" +
            "            \"minPrice\": 30,\n" +
            "            \"priceText\": \"共￥195\",\n" +
            "            \"btnText\": \"选好了\",\n" +
            "            \"propText\": \"新鲜美食，闪电送达\",\n" +
            "            \"emptyText\": \"购物车是空的\",\n" +
            "            \"shoppingList\": [\n" +
            "                {\n" +
            "                    \"attribute\": \"中杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100006\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/2247/200_200_0000002247.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"30.00\",\n" +
            "                    \"name\": \"100006 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"30.00\",\n" +
            "                    \"product_id\": \"2247\",\n" +
            "                    \"shoppingCount\": 2,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 2247,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100006\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4457/200_200_0000004457.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"33.00\",\n" +
            "                    \"name\": \"100006 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"33.00\",\n" +
            "                    \"product_id\": \"4457\",\n" +
            "                    \"shoppingCount\": 3,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4457,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"超大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100006\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4486/200_200_0000004486.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"36.00\",\n" +
            "                    \"name\": \"100006 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"36.00\",\n" +
            "                    \"product_id\": \"4486\",\n" +
            "                    \"shoppingCount\": 1,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4486,\n" +
            "                    \"disabled\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"group100005\": {\n" +
            "            \"cart_group_id\": \"100005\",\n" +
            "            \"isOk\": true,\n" +
            "            \"payType\": 2,\n" +
            "            \"price\": 195,\n" +
            "            \"count\": 6,\n" +
            "            \"minPrice\": 30,\n" +
            "            \"priceText\": \"共￥195\",\n" +
            "            \"btnText\": \"选好了\",\n" +
            "            \"propText\": \"新鲜美食，闪电送达\",\n" +
            "            \"emptyText\": \"购物车是空的\",\n" +
            "            \"shoppingList\": [\n" +
            "                {\n" +
            "                    \"attribute\": \"中杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100005\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/2247/200_200_0000002247.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"30.00\",\n" +
            "                    \"name\": \"100005 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"30.00\",\n" +
            "                    \"product_id\": \"2247\",\n" +
            "                    \"shoppingCount\": 2,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 2247,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100005\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4457/200_200_0000004457.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"33.00\",\n" +
            "                    \"name\": \"100005 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"33.00\",\n" +
            "                    \"product_id\": \"4457\",\n" +
            "                    \"shoppingCount\": 3,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4457,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"超大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"100005\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4486/200_200_0000004486.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"36.00\",\n" +
            "                    \"name\": \"100005 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"36.00\",\n" +
            "                    \"product_id\": \"4486\",\n" +
            "                    \"shoppingCount\": 1,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4486,\n" +
            "                    \"disabled\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"groups\": [\n" +
            "            \"group100002\",\n" +
            "            \"group100003\",\n" +
            "            \"group100004\",\n" +
            "            \"group100005\",\n" +
            "            \"group100006\"\n" +
            "        ],\n" +
            "        \"group1\": {\n" +
            "            \"cart_group_id\": 1,\n" +
            "            \"isOk\": false,\n" +
            "            \"payType\": 2,\n" +
            "            \"price\": 60,\n" +
            "            \"count\": 2,\n" +
            "            \"minPrice\": 30,\n" +
            "            \"priceText\": \"共￥60\",\n" +
            "            \"btnText\": \"满￥30起送\",\n" +
            "            \"propText\": \"满30可预订，全城免运费\",\n" +
            "            \"emptyText\": \"购物车是空的\",\n" +
            "            \"shoppingList\": [\n" +
            "                {\n" +
            "                    \"attribute\": \"中杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"1\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/2247/200_200_0000002247.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"30.00\",\n" +
            "                    \"name\": \"group1 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"30.00\",\n" +
            "                    \"product_id\": \"2247\",\n" +
            "                    \"shoppingCount\": 2,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 2247,\n" +
            "                    \"disabled\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"attribute\": \"大杯\",\n" +
            "                    \"brand_id\": \"43\",\n" +
            "                    \"brand_name\": \"星巴克\",\n" +
            "                    \"cart_group_id\": \"1\",\n" +
            "                    \"category_id\": \"108\",\n" +
            "                    \"cid\": \"108\",\n" +
            "                    \"dealer_id\": \"3413\",\n" +
            "                    \"had_pm\": \"0\",\n" +
            "                    \"hot_degree\": \"0\",\n" +
            "                    \"store_nums\": \"100\",\n" +
            "                    \"img\": \"http://images.beequick.cn/upload/goods/000/000/4457/200_200_0000004457.jpg\",\n" +
            "                    \"specifics\": \"\",\n" +
            "                    \"is_xf\": \"0\",\n" +
            "                    \"market_price\": \"33.00\",\n" +
            "                    \"name\": \"group1 摩卡咖啡\",\n" +
            "                    \"number\": \"100\",\n" +
            "                    \"pm_desc\": \"\",\n" +
            "                    \"price\": \"33.00\",\n" +
            "                    \"product_id\": \"4457\",\n" +
            "                    \"shoppingCount\": 3,\n" +
            "                    \"is_gift\": 0,\n" +
            "                    \"id\": 4457,\n" +
            "                    \"disabled\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"opSuccess\": true,\n" +
            "        \"opErrorMsg\": \"\",\n" +
            "        \"noticeMsg\": \"\"\n" +
            "    },\n" +
            "    \"callbackId\": \"9\"\n" +
            "}";

}
