package com.example.daftarmasjid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.daftarmasjid.model.Masjid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharePereferenceUtility {


    private static final String PREF_FILE = "com.haedy.daftarmasjid.DATA";
    private static final String TRANS_KEY = "TRANS";
    private static final String USER_NAME_KEY = "USERNAME";

    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharePref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharePref;
    }

    public static String getUserNameKey(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY, "NO NAME");
    }

    public static void saveUserName(Context ctx, String useName) {
        Log.d("SH PREF", "Cange user name to" + useName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY, useName).apply();
    }

    public static List<Masjid> getAllMasjid(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY, null);
        List<Masjid> str = new ArrayList<>();
        if (jsonString != null) {
            Log.d("SH PREF", "json string is:" + jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    str.add(Masjid.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(str, (transaksi, t1) -> {
            return transaksi.getId().compareTo(t1.getId());
        }); // urutkan transaksi berdasarkan id
        return str;
    }

    private static void saveAllTransaksi(Context ctx, List<Masjid> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for (Masjid tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY, jsonArr.toString()).apply();
    }

    /*
        Tambah data transaksi baru ke Shared Preference
     */
    public static void addAllMasjid(Context ctx, Masjid tr) {
        List<Masjid> trs = getAllMasjid(ctx);
        trs.add(tr);
        saveAllTransaksi(ctx, trs);
    }
}
