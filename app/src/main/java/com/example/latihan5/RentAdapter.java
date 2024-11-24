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

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.CustomViewHolder> {
    private List<RentPost> dataList;
    private Context context;
    private NumberFormat formatRupiah;

    public RentAdapter(Context context, List<RentPost> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView namaKendaraan, platKendaraan, tahunKendaraan, hargaKendaraan, deskripsiKendaraan, jenisKendaraan;
        private ImageView converimgHotel;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            namaKendaraan = mView.findViewById(R.id.nama_kendaraan);
            platKendaraan = mView.findViewById(R.id.plat_nomor);
            tahunKendaraan = mView.findViewById(R.id.tahun);
            hargaKendaraan = mView.findViewById(R.id.harga_kendaraan);
            deskripsiKendaraan = mView.findViewById(R.id.deskripsi_kendaraan);
            jenisKendaraan = mView.findViewById(R.id.jenis_kendaraan);
        }
    }

    @Override
    public RentAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_kendaraan, parent, false);
        return new RentAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RentAdapter.CustomViewHolder holder, int position) {
        holder.namaKendaraan.setText(dataList.get(position).getNamaKendaraan());
        holder.platKendaraan.setText(dataList.get(position).getPlatNomor());
        holder.tahunKendaraan.setText(dataList.get(position).getTahun());
        holder.deskripsiKendaraan.setText(dataList.get(position).getDeskripsiKendaraan());
        holder.jenisKendaraan.setText(dataList.get(position).getJenisKendaraan());

        // Format harga ke dalam format Rupiah
        String harga = formatHargaRupiah(dataList.get(position).getHargaKendaraan());
        holder.hargaKendaraan.setText(harga);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateData(List<RentPost> newData) {
        this.dataList = newData;
        notifyDataSetChanged();
    }

    private String formatHargaRupiah(String harga) {
        try {
            double nominal = Double.parseDouble(harga);
            String formatted = formatRupiah.format(nominal);

            formatted = formatted.replaceAll("[Rp\\s]", "")
                    .split(",")[0];

            return "Rp " + formatted;
        } catch (NumberFormatException e) {
            return "Rp " + harga;
        }
    }
}

