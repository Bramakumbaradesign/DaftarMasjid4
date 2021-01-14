package com.example.daftarmasjid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.daftarmasjid.model.Masjid;

import java.util.List;

public class DaftarMasjidAdapter extends ArrayAdapter<Masjid> {
    Context context;

    public DaftarMasjidAdapter(@NonNull Context context, @NonNull List<Masjid> objects) {
        super(context, R.layout.row_masjid, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txNama;
        TextView txDesa;
        TextView txKabupaten;
        TextView txKec;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Masjid tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_masjid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txNama = convertView.findViewById(R.id.row_nama);
            viewHolder.txKabupaten = convertView.findViewById(R.id.row_kb);
            viewHolder.txDesa = convertView.findViewById(R.id.row_ds);
            viewHolder.txKec = convertView.findViewById(R.id.row_kec);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txNama.setText(tr.getNama());
        viewHolder.txKabupaten.setText(tr.getKabupaten());
        viewHolder.txKec.setText(tr.getKecamatan());
        if (tr.getDesa().equals(Masjid.kelayu)) {
            viewHolder.txDesa.setText("kelayu");
        } else if (tr.getDesa().equals(Masjid.selong)) {
            viewHolder.txDesa.setText("selong");
        } else if (tr.getDesa().equals(Masjid.sakra)) {
            viewHolder.txDesa.setText("sakra");
        } else if (tr.getDesa().equals(Masjid.rakam)) {
            viewHolder.txDesa.setText("rakam");
        } else if (tr.getDesa().equals(Masjid.ketangga)) {
            viewHolder.txDesa.setText("ketangga");
        } else {
            viewHolder.txDesa.setText("UMUM");
        }
        return convertView;
    }
}
