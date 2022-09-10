package com.trodev.banglardorji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewAdapter> {

    private Context context;
    private List<Mentshirt> mentshirtList;
    private String category;

    public CartAdapter(Context context, List<Mentshirt> mentshirtList, String category) {
        this.context = context;
        this.mentshirtList = mentshirtList;
        this.category = category;
    }

    @NonNull
    @Override
    public CartViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new CartAdapter.CartViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewAdapter holder, int position) {

        Mentshirt mentshirt = mentshirtList.get(position);

        holder.tsize.setText(" Type: " + mentshirt.getSize());
        holder.tlength.setText(" Length: " + mentshirt.getLength());
        holder.twidth.setText("Width: " + mentshirt.getWidth());
        holder.tcolor.setText(" Color: " + mentshirt.getColor());
        holder.tfabric_TV.setText("Fabric: " + mentshirt.getFabt());
        holder.tgsm_TV.setText(" GSM: " + mentshirt.getTgsm());
        holder.tquan_TV.setText(" Quantity: " + mentshirt.getQuan());
        holder.thome.setText("🏠 Address: " + mentshirt.getHome());
        holder.tmobile.setText("📞 Mobile: " + mentshirt.getPhn());

        // Here its a Time & Date Section
        holder.date.setText("Date: " + mentshirt.getDate());
        holder.time.setText("Time: " + mentshirt.getTime());

    }

    @Override
    public int getItemCount() {
        return mentshirtList.size();
    }

    public class CartViewAdapter extends RecyclerView.ViewHolder {
        private TextView tsize, tlength, twidth, tcolor, tquan_TV, tfabric_TV, tgsm_TV, thome, tmobile, date, time;

        public CartViewAdapter(@NonNull View itemView) {
            super(itemView);

            tsize = itemView.findViewById(R.id.tsize_TV);
            tlength = itemView.findViewById(R.id.tlength_TV);
            twidth = itemView.findViewById(R.id.twidth_TV);
            tcolor = itemView.findViewById(R.id.tcolor_TV);
            thome = itemView.findViewById(R.id.homeTV);
            tmobile = itemView.findViewById(R.id.mobileTV);
            tfabric_TV = itemView.findViewById(R.id.tfabric_TV);
            tgsm_TV = itemView.findViewById(R.id.tgsm_TV);
            tquan_TV = itemView.findViewById(R.id.tquan_TV);

            // Here its a Time & Date Section
            date = itemView.findViewById(R.id.date_TV);
            time = itemView.findViewById(R.id.time_TV);

        }
    }
}
