package com.example.daftarmasjid.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Masjid {

    public static final String sakra = "sakra";
    public static final String kelayu = "kelayu";
    public static final String ketangga = "ketangga";
    public static final String selong = "selong";
    public static final String rakam = "rakam";


    private String id;
    private String nama;
    private String kecamatan;
    private String desa;
    private String kabupaten;

    public Masjid() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public static Masjid fromJSONObject(JSONObject obj) {
        Masjid tr = new Masjid();
        try {
            tr.setId(obj.getString("id"));
            tr.setNama(obj.getString("nama"));
            tr.setKecamatan(obj.getString("kecamatan"));
            tr.setDesa(obj.getString("desa"));
            tr.setKabupaten(obj.getString("kabupaten"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", this.id);
            jsonObj.put("nama", this.nama);
            jsonObj.put("kecamatan", this.kecamatan);
            jsonObj.put("desa", this.desa);
            jsonObj.put("kabupaten", this.kabupaten);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

}
