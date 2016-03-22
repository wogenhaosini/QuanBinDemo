package com.quan.demo.cart;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stainberg on 6/23/15.
 */
public class Cart {

    private static Cart instance = null;
    private Map<String, ShoppingGroup> groups;
    private static final String ROOT = "data";
    private static final String GROUPID = "cart_group_id";
    private static final String GROUPS1 = "group1";
    private static final String GROUPS = "groups";

    private Cart() {
        groups = new HashMap<>();
    }

    public static Cart getInstance() {
        if(instance == null) {
            synchronized (Cart.class) {
                if(instance == null) {
                    instance = new Cart();
                }
            }
        }
        return instance;
    }

    public List<ShoppingGroup> buildCart(String jsonStr) {
        List<ShoppingGroup> sgl = new ArrayList<>();
        if(!TextUtils.isEmpty(jsonStr)) {
            try {
                JSONObject json = new JSONObject(jsonStr);
                JSONObject data = json.optJSONObject(ROOT);
                JSONArray spl0 = data.optJSONArray(ShoppingGroup.KEY);
                if(spl0.length() > 0) {
                    groups.put(data.optString(GROUPID), fillClass(new ShoppingGroup(), data));
                }
                JSONObject group1 = data.optJSONObject(GROUPS1);
                JSONArray spl1 = group1.optJSONArray(ShoppingGroup.KEY);
                if(spl1.length() > 0) {
                    groups.put(group1.optString(GROUPID), fillClass(new ShoppingGroup(), group1));
                }
                JSONArray groupsKey = data.optJSONArray(GROUPS);
                for(int i=0; i<groupsKey.length(); i++) {
                    groups.put(data.optJSONObject(groupsKey.optString(i)).optString(GROUPID), fillClass(new ShoppingGroup(), data.optJSONObject(groupsKey.optString(i))));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        for(ShoppingGroup group : groups.values()) {
            sgl.add(group);
        }
        return sgl;
    }

    public ShoppingGroup getGroupById(String id) {
        return groups.get(id);
    }

    public Map<String, ShoppingGroup> getGroups() {
        return groups;
    }

    private <T> T fillClass(T cls, JSONObject src) {
        JSONArray names = src.names();
        for(int i=0; i<names.length(); i++) {
            try {
                String key = names.optString(i);
                Field field = cls.getClass().getDeclaredField(key);
                field.setAccessible(true);
                if(field.getType().equals(String.class)) {
                    field.set(cls, src.optString(key));
                } else if(field.getType().equals(List.class)) {
                    List list = new ArrayList<>();
                    Class[] clss = cls.getClass().getDeclaredClasses();
                    Class subClass = null;
                    for(int p=0; p<clss.length; p++) {
                        String upKey = toUpperCaseFirstOne(key);
                        if(upKey != null) {
                            if(upKey.equals(clss[p].getSimpleName())) {
                                subClass = clss[p];
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if(subClass != null) {
                        JSONArray array = src.optJSONArray(key);
                        for(int k=0; k<array.length(); k++) {
                            list.add(fillClass(subClass.getConstructor(cls.getClass()).newInstance(cls), array.optJSONObject(k)));
                        }
                    }
                    field.set(cls, list);
                } else if(field.getType().equals(Boolean.class) || field.getType().getName().equals("boolean")) {
                    field.set(cls, src.optBoolean(key));
                } else if(field.getType().equals(Integer.class) || field.getType().getName().equals("int")) {
                    field.set(cls, src.optInt(key));
                } else if(field.getType().equals(Double.class) || field.getType().getName().equals("double")) {
                    field.set(cls, src.optDouble(key));
                } else if(field.getType().equals(Long.class) || field.getType().getName().equals("long")) {
                    field.set(cls, src.optLong(key));
                }else {
                    Class[] clss = cls.getClass().getDeclaredClasses();
                    Class subClass = null;
                    for(int p=0; p<clss.length; p++) {
                        if(field.getType().getSimpleName().equals(clss[p].getSimpleName())) {
                            subClass = clss[p];
                            break;
                        }
                    }
                    if(subClass != null) {
                        JSONObject object = src.optJSONObject(key);
                        if(object.length() > 0) {
                            fillClass(subClass.getConstructor(cls.getClass()).newInstance(cls), object);
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return cls;
    }

    private String toLowerCaseFirstOne(String s) {
        String upKey = null;
        if(s.length() > 2) {
            upKey = new StringBuilder().append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        } else if(s.length() == 1) {
            upKey = new StringBuilder().append(Character.toLowerCase(s.charAt(0))).toString();
        }
        return upKey;
    }
    private String toUpperCaseFirstOne(String s) {
        String upKey = null;
        if(s.length() > 2) {
            upKey = new StringBuilder().append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        } else if(s.length() == 1) {
            upKey = new StringBuilder().append(Character.toUpperCase(s.charAt(0))).toString();
        }
        return upKey;
    }

}
