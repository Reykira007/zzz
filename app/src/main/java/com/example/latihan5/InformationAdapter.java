package com.example.latihan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.CustomViewHolder> {
    private List<InformationPost> dataList;
    private Context context;

    public InformationAdapter(Context context, List<InformationPost> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView namaKomentar, tglKomentar, emailKomentar, tanggapanKomentar;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            namaKomentar = mView.findViewById(R.id.nama_komentar);
            tglKomentar = mView.findViewById(R.id.tgl_komentar);
            emailKomentar = mView.findViewById(R.id.nama_email);
            tanggapanKomentar = mView.findViewById(R.id.tanggapan);
        }
    }

    @Override
    public InformationAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_informasi, parent, false);
        return new InformationAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InformationAdapter.CustomViewHolder holder, int position) {
        holder.namaKomentar.setText(dataList.get(position).getKomentar());
        holder.tglKomentar.setText(dataList.get(position).getTglInformation());
        holder.emailKomentar.setText(dataList.get(position).getEmail());
        holder.tanggapanKomentar.setText(dataList.get(position).getTanggapan());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

