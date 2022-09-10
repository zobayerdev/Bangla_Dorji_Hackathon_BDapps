package com.trodev.banglardorji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TshirtAdapter extends RecyclerView.Adapter<TshirtAdapter.TshirtViewAdapter> {

    private Context context;
    private List<Mentshirt> mentshirtList;
    private String category;

    public TshirtAdapter(Context context, List<Mentshirt> mentshirtList, String category) {
        this.context = context;
        this.mentshirtList = mentshirtList;
        this.category = category;
    }

    @NonNull
    @Override
    public TshirtViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_layout, parent, false);
        return new TshirtViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TshirtViewAdapter holder, int position) {

        Mentshirt mentshirt = mentshirtList.get(position);

        holder.tsize.setText("T-shirt Type: " + mentshirt.getSize());
        holder.tlength.setText("T-shirt Length: " + mentshirt.getLength());
        holder.twidth.setText("T-shirt Width: " + mentshirt.getWidth());
        holder.tcolor.setText("T-shirt Color: " + mentshirt.getColor());
        holder.tfabric_TV.setText("T-shirt Fabric: " + mentshirt.getFabt());
        holder.tgsm_TV.setText("T-shirt GSM: " + mentshirt.getTgsm());
        holder.tquan_TV.setText("T-shirt Quantity: " + mentshirt.getQuan());
        holder.thome.setText("🏠 Address: " + mentshirt.getHome());
        holder.tmobile.setText("📞 Mobile: " + mentshirt.getPhn());

        // Here its a Time & Date Section
        holder.date.setText("Order Date: " + mentshirt.getDate());
        holder.time.setText("Order Time: " + mentshirt.getTime());

    }

    @Override
    public int getItemCount() {
        return mentshirtList.size();
    }

    public class TshirtViewAdapter extends RecyclerView.ViewHolder {
        private TextView tsize, tlength, twidth, tcolor, tquan_TV, tfabric_TV, tgsm_TV, thome, tmobile, date, time;

        public TshirtViewAdapter(@NonNull View itemView) {
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
