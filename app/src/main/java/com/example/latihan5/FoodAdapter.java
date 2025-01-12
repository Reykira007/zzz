package com.example.latihan5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.CustomViewHolder> {
    private List<FoodPost> dataList;
    private Context context;
    private NumberFormat formatRupiah;

    public FoodAdapter(Context context, List<FoodPost> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView namaMakanan, lokasiToko, hargaMakanan, deskripsiMakanan;
        private ImageView converimgFood;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            lokasiToko = mView.findViewById(R.id.lokasi_toko);
            namaMakanan = mView.findViewById(R.id.nama_makanan);
            hargaMakanan = mView.findViewById(R.id.harga_makanan);
            converimgFood = mView.findViewById(R.id.foto_makanan);
            deskripsiMakanan = mView.findViewById(R.id.deskripsi_makanan);
        }
    }

    @Override
    public FoodAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_food, parent, false);
        return new FoodAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodAdapter.CustomViewHolder holder, int position) {
        holder.lokasiToko.setText(dataList.get(position).getLokasiToko());
        holder.namaMakanan.setText(dataList.get(position).getNamaMakanan());
        holder.deskripsiMakanan.setText(dataList.get(position).getDeskripsiMakanan());

        // Format harga ke dalam format Rupiah
        String harga = formatHargaRupiah(dataList.get(position).getHargaMakanan());
        holder.hargaMakanan.setText(harga);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateData(List<FoodPost> newData) {
        this.dataList = newData;
        notifyDataSetChanged();
    }

    public static String formatHargaRupiah(String price) {
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