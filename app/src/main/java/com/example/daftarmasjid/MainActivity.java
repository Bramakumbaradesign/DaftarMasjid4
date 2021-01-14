package com.example.daftarmasjid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daftarmasjid.model.Masjid;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnupdate;
    ListView lvdaftar;
    TextView txnodata, txusername;
    DaftarMasjidAdapter adapter;
    List<Masjid> daftarMasjid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inisialisasiView();
        loadDataTransaksi();
        setupListview();
    }

    private void inisialisasiView() {
        btnupdate = findViewById(R.id.btntambah);
        btnupdate.setOnClickListener(view -> bukaFormTambahPhone());
        lvdaftar = findViewById(R.id.lv_list);
        txnodata = findViewById(R.id.tx_nodata);
        txusername = findViewById(R.id.tx_user_name);
        txusername.setText(SharePereferenceUtility.getUserNameKey(this));
        txusername = findViewById(R.id.tx_user_name);
    }

    private void setupListview() {
        adapter = new DaftarMasjidAdapter(this, daftarMasjid);
        lvdaftar.setAdapter(adapter);

    }

    private void loadDataTransaksi() {
        daftarMasjid = SharePereferenceUtility.getAllMasjid(this);
        if (daftarMasjid.size() > 0) {
            txnodata.setVisibility(View.GONE);
        } else {
            txnodata.setVisibility(View.VISIBLE);
        }

    }

    private void refreshListView() {
        adapter.clear();
        loadDataTransaksi();
        adapter.addAll(daftarMasjid);
    }

    private void bukaFormTambahPhone() {
        Intent intent = new Intent(this, FormMasjidActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);
    }
}