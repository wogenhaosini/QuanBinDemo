package com.quan.demo.cart;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.telephony.TelephonyManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Utils {
    public static int DpToPx(Context context, int x) {
        int result = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        result = (int) (x * scale + 0.5f);
        return result;
    }

    public static int PxToDp(Context context, int x) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (x / scale + 0.5f);
    }

    public static String getSimpleVersionName(Context context) {
        String result = "";
        PackageInfo packInfo;
        PackageManager pkgManager = context.getPackageManager();
        try {
            packInfo = pkgManager.getPackageInfo(context.getPackageName(), 0);
            result = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getVersionCode(Context context) {
        int result = 0;
        PackageInfo packInfo;
        PackageManager pkgManager = context.getPackageManager();
        try {
            packInfo = pkgManager.getPackageInfo(context.getPackageName(), 0);
            result = packInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getRandomStr() {
        return SecurityMD5.ToMD5(String.valueOf(System.currentTimeMillis()));
    }

    public static String getIMEI(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    public static Bitmap convertDrawable2Bitmap(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    public static Drawable convertBitmap2Drawable(Context context, Bitmap bitmap) {
        Resources res = context.getResources();
        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
        return bd;
    }

    public static Drawable getImageDrawable(Context context, int resId, TileMode mode) {
        BitmapDrawable drawble;
        drawble = new BitmapDrawable(context.getResources(), getImageBitmap(context, resId));
        if (mode != null) drawble.setTileModeX(mode);
        return drawble;
    }

    public static Bitmap getImageBitmap(Context context, int resId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, opts);
        opts.inSampleSize = 1;
        opts.inDensity = (int) (opts.inDensity / context.getResources().getDisplayMetrics().density);
        opts.inJustDecodeBounds = false;
        Bitmap result = BitmapFactory.decodeResource(context.getResources(), resId, opts);
        return result;
    }

    public static byte[] convertBitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static final byte[] convertObject2Bytes(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public static final Object convertBytes2Object(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public static String convertMapToStr(Map<String, String> args) {
        if (args == null) {
            return null;
        }
        JSONObject tmp = null;
        tmp = new JSONObject(args);
        return tmp.toString();
    }

    public static Map<String, String> convertStrToMap(String args) {
        if (args == null) {
            return null;
        }
        Map<String, String> result = new HashMap<String, String>();
        JSONObject tmp = null;
        try {
            tmp = new JSONObject(args);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<?> iterator = tmp.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = tmp.optString(key);
            result.put(key, value);
        }
        return result;
    }

    public static boolean isAppOnForeground(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasksInfo = manager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            String src = context.getPackageName();
            String des = tasksInfo.get(0).topActivity.getPackageName();
            if (src.equalsIgnoreCase(des)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNull(String key) {
        if (key == null) {
            return true;
        }
        if (key.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
