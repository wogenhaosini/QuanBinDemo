package com.quan.demo.cart;

import java.util.List;

/**
 * Created by Stainberg on 6/23/15.
 */
public class ShoppingGroup {
    public static final String KEY = "shoppingList";
    private String btnText;
    private String cart_group_id;
    private String count;
    private String emptyText;
    private boolean isOk;
    private String minPrice;
    private String payType;
    private String price;
    private String priceText;
    private String propText;
    private List<ShoppingList> shoppingList;
    private String cartType;
    private String cartDeliverTime;

    public String getCartDeliverTime() {
        return cartDeliverTime;
    }

    public void setCartDeliverTime(String cartDeliverTime) {
        this.cartDeliverTime = cartDeliverTime;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public String getCart_group_id() {
        return cart_group_id;
    }

    public void setCart_group_id(String cart_group_id) {
        this.cart_group_id = cart_group_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getPropText() {
        return propText;
    }

    public void setPropText(String propText) {
        this.propText = propText;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public class ShoppingList {

        public ShoppingList() {

        }

        private String attribute;
        private String brand_id;
        private String brand_name;
        private String cart_group_id;
        private String category_id;
        private String cid;
        private String corner_mark;
        private String dealer_id;
        private boolean disabled;
        private String had_pm;
        private String hot_degree;
        private String id;
        private String img;
        private String is_gift;
        private String is_xf;
        private String market_price;
        private String name;
        private String number;
        private String pm_desc;
        private String price;
        private String product_id;
        private int shoppingCount;
        private String specifics;
        private String store_nums;

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getCart_group_id() {
            return cart_group_id;
        }

        public void setCart_group_id(String cart_group_id) {
            this.cart_group_id = cart_group_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCorner_mark() {
            return corner_mark;
        }

        public void setCorner_mark(String corner_mark) {
            this.corner_mark = corner_mark;
        }

        public String getDealer_id() {
            return dealer_id;
        }

        public void setDealer_id(String dealer_id) {
            this.dealer_id = dealer_id;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getHot_degree() {
            return hot_degree;
        }

        public void setHot_degree(String hot_degree) {
            this.hot_degree = hot_degree;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIs_gift() {
            return is_gift;
        }

        public void setIs_gift(String is_gift) {
            this.is_gift = is_gift;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getShoppingCount() {
            return shoppingCount;
        }

        public void setShoppingCount(int shoppingCount) {
            this.shoppingCount = shoppingCount;
        }

        public String getSpecifics() {
            return specifics;
        }

        public void setSpecifics(String specifics) {
            this.specifics = specifics;
        }

        public String getStore_nums() {
            return store_nums;
        }

        public void setStore_nums(String store_nums) {
            this.store_nums = store_nums;
        }

        public String getHad_pm() {
            return had_pm;
        }

        public void setHad_pm(String had_pm) {
            this.had_pm = had_pm;
        }

        public String getIs_xf() {
            return is_xf;
        }

        public void setIs_xf(String is_xf) {
            this.is_xf = is_xf;
        }

        public String getPm_desc() {
            return pm_desc;
        }

        public void setPm_desc(String pm_desc) {
            this.pm_desc = pm_desc;
        }
    }
}
