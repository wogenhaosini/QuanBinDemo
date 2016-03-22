package com.quan.demo.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quan.demo.MyApplication;
import com.quan.demo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stainberg on 6/24/15.
 */
public class CartView {

    View cartView;
    LinearLayout container;
    Context content;
    Map<ShoppingGroup, CartItemHolder> cartMap;
    CartListItemClick listItemClick;

    public CartView(Context context, CartListItemClick spListItemClick) {
        cartMap = new HashMap<>();
        cartView = LayoutInflater.from(context).inflate(R.layout.cart_view, null);
        container = (LinearLayout) cartView.findViewById(R.id.cart_view_container);
        this.content = context;
        listItemClick = spListItemClick;
    }

    public void realize(List<ShoppingGroup> sgl) {
        for(ShoppingGroup sg : sgl) {
            View cartItem = LayoutInflater.from(content).inflate(R.layout.cart_item, null);
            CartItemHolder itemHolder = new CartItemHolder(cartItem, sg);
            cartMap.put(sg, itemHolder);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.bottomMargin = Utils.DpToPx(MyApplication.getContext(), 12);
            container.addView(cartItem, lp);
        }
    }

    public View getView() {
        return cartView;
    }

    public void removeCartItem(View view) {
        container.removeView(view);
    }

    public void setTotalPrice(ShoppingGroup shoppingGroup, String price) {
        CartItemHolder holder = cartMap.get(shoppingGroup);
        if(holder != null) {
            holder.cashMoney.setText(price);
        }
    }

    public void setCommitButtonText(ShoppingGroup shoppingGroup, String tx) {
        CartItemHolder holder = cartMap.get(shoppingGroup);
        if(holder != null) {
            holder.commit.setText(tx);
        }
    }

    class CartItemHolder {
        public ImageView titleIcon;
        public TextView titleText;
        public TextView timeSet;
        public TextView timeType;
        public EditText memo;
        public TextView cashMoney;
        public TextView cashDeliver;
        public Button commit;
        public LinearLayout shoppingContainer;

        public CartItemHolder(final View itemView, final ShoppingGroup sg) {
            shoppingContainer = (LinearLayout) itemView.findViewById(R.id.cart_shopping_list_container);
            titleIcon = (ImageView) itemView.findViewById(R.id.cart_type_icon);
            titleText = (TextView) itemView.findViewById(R.id.cart_type_title);
            timeSet = (TextView) itemView.findViewById(R.id.cart_time_set);
            timeType = (TextView) itemView.findViewById(R.id.cart_time_set_type);
            memo = (EditText) itemView.findViewById(R.id.cart_memo_input);
            cashMoney = (TextView) itemView.findViewById(R.id.cart_pay_cash);
            cashDeliver = (TextView) itemView.findViewById(R.id.cart_pay_deliver);
            commit = (Button) itemView.findViewById(R.id.cart_commit);

            titleText.setText("Type " + sg.getCart_group_id());
            cashMoney.setText(sg.getPriceText());
            cashDeliver.setText(sg.getPropText());
            commit.setText(sg.getBtnText());
            for(final ShoppingGroup.ShoppingList sgsl : sg.getShoppingList()) {
                final View spi = LayoutInflater.from(content).inflate(R.layout.cart_detail_list_item, null);
                TextView name = (TextView) spi.findViewById(R.id.cart_detail_list_item_name);
                TextView price = (TextView) spi.findViewById(R.id.cart_detail_list_item_price);
                final Button sub = (Button) spi.findViewById(R.id.cart_detail_list_item_sub);
                final Button add = (Button) spi.findViewById(R.id.cart_detail_list_item_add);
                final TextView count = (TextView) spi.findViewById(R.id.cart_detail_list_item_count);
                name.setText(sgsl.getName() + " " + sgsl.getAttribute());
                price.setText(sgsl.getPrice());
                count.setText(String.valueOf(sgsl.getShoppingCount()));
                shoppingContainer.addView(spi);
                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int c = sgsl.getShoppingCount();
                        if (--c > 0) {
                            sub.setVisibility(View.VISIBLE);
                            sgsl.setShoppingCount(c);
                            count.setText(String.valueOf(sgsl.getShoppingCount()));
                        } else {
                            shoppingContainer.removeView(spi);
                            sg.getShoppingList().remove(sgsl);
                            sub.setVisibility(View.INVISIBLE);
                            sgsl.setShoppingCount(0);
                            count.setText(String.valueOf(sgsl.getShoppingCount()));
                            listItemClick.onDeleteItem(sg, itemView);
                        }
                        listItemClick.onSubtractionClick(sg, sgsl);
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sub.setVisibility(View.VISIBLE);
                        int c = sgsl.getShoppingCount();
                        sgsl.setShoppingCount(++c);
                        count.setText(String.valueOf(sgsl.getShoppingCount()));
                        listItemClick.onAdditionClick(sg, sgsl);
                    }
                });
            }
        }

    }

    public interface CartListItemClick {
        void onAdditionClick(ShoppingGroup shoppingGroup, ShoppingGroup.ShoppingList shoppingList);
        void onSubtractionClick(ShoppingGroup shoppingGroup, ShoppingGroup.ShoppingList shoppingList);
        void onDeleteItem(ShoppingGroup shoppingGroup, View cartView);
    }

}
