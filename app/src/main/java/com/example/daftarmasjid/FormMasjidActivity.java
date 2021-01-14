package com.example.daftarmasjid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.daftarmasjid.model.Masjid;
import com.google.android.material.textfield.TextInputLayout;

public class FormMasjidActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilNama, tilKabupaten, tilKec;
    Spinner spnDesa;
    final String[] tipedesa = {Masjid.ketangga, Masjid.kelayu, Masjid.rakam, Masjid.sakra, Masjid.selong};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_masjid);
        inisialisasiView();
    }

    private void inisialisasiView(){
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> bukaFormMasjid());
        tilNama = findViewById(R.id.til_nama);
        tilKabupaten = findViewById(R.id.til_kabupaten);
        tilKec = findViewById(R.id.til_kecamatan);
        spnDesa =findViewById(R.id.spn_desa);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipedesa);
        spnDesa.setAdapter(adapter);
        spnDesa.setSelection(0);
    }

    private void bukaFormMasjid(){
        if (isDataValid()){
            Masjid tr = new Masjid();
            tr.setNama(tilNama.getEditText().getText().toString());
            tr.setKabupaten(tilKabupaten.getEditText().getText().toString());
            tr.setKecamatan(tilKec.getEditText().getText().toString());
            tr.setDesa(spnDesa.getSelectedItem().toString());
            SharePereferenceUtility.addAllMasjid(this, tr);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilNama.getEditText().getText().toString().isEmpty()
                || tilKabupaten.getEditText().getText().toString().isEmpty()
                || tilKec.getEditText().getText().toString().isEmpty()
                || spnDesa.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this, "Data tidak bileh ada yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}