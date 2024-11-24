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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.CustomViewHolder> {
    private List<HotelPost> dataList;
    private Context context;
    private NumberFormat formatRupiah;

    public HotelAdapter(Context context, List<HotelPost> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView namaHotel, bintangHotel, lokasiHotel, hargaHotel, deskripsiHotel;
        private ImageView converimgHotel;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            lokasiHotel = mView.findViewById(R.id.lokasi_hotel);
            namaHotel = mView.findViewById(R.id.nama_hotel);
            bintangHotel = mView.findViewById(R.id.bintang_hotel);
            hargaHotel = mView.findViewById(R.id.harga_hotel);
            converimgHotel = mView.findViewById(R.id.foto_hotel);
            deskripsiHotel = mView.findViewById(R.id.deskripsi_hotel);
        }
    }

    @Override
    public HotelAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_hotel, parent, false);
        return new HotelAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.CustomViewHolder holder, int position) {
        holder.lokasiHotel.setText(dataList.get(position).getLokasi());
        holder.namaHotel.setText(dataList.get(position).getNamaHotel());
        holder.bintangHotel.setText(dataList.get(position).getBintang());
        holder.deskripsiHotel.setText(dataList.get(position).getDeskripsi());

        // Format harga ke dalam format Rupiah
        String harga = formatPrice(dataList.get(position).getRangeHarga());
        holder.hargaHotel.setText(harga);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateData(List<HotelPost> newData) {
        this.dataList = newData;
        notifyDataSetChanged();
    }

    public static String formatPrice(String price) {
        try {
            DecimalFormat formatter = new DecimalFormat("#,###");
            formatter.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("id", "ID")));

            if (price.contains("-")) {
                String[] ranges = price.split("-");
                if (ranges.length == 2) {
                    double minPrice = Double.parseDouble(ranges[0].trim());
                    double maxPrice = Double.parseDouble(ranges[1].trim());

                    String formattedMin = "Rp " + formatter.format(minPrice);
                    String formattedMax = "Rp " + formatter.format(maxPrice);

                    return formattedMin + " - " + formattedMax;
                }
            }

            double amount = Double.parseDouble(price);
            return "Rp " + formatter.format(amount);
        } catch (NumberFormatException e) {
            return "Rp " + price;
        }
    }
}
