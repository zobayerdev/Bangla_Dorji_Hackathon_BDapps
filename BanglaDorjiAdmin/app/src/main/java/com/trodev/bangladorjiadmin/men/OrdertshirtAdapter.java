package com.trodev.bangladorjiadmin.men;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.trodev.bangladorjiadmin.R;

import java.util.List;

public class OrdertshirtAdapter extends RecyclerView.Adapter<OrdertshirtAdapter.OrdertshirtViewAdapter> {

    private Context context;
    private List<Ordertshirt> list;
    private String category;

    public OrdertshirtAdapter(Context context, List<Ordertshirt> list, String category) {
        this.context = context;
        this.list = list;
        this.category = category;
    }

    @NonNull
    @Override
    public OrdertshirtViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tshirt_layout, parent, false);
        return new OrdertshirtViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdertshirtViewAdapter holder, int position) {

        Ordertshirt mentshirt = list.get(position);

        holder.tsize.setText("Type: " + mentshirt.getSize());
        holder.tlength.setText(" Length: " + mentshirt.getLength());
        holder.twidth.setText(" Width: " + mentshirt.getWidth());
        holder.tcolor.setText(" Color: " + mentshirt.getColor());
        holder.tfabric_TV.setText(" Fabric: " + mentshirt.getFabt());
        holder.tgsm_TV.setText(" GSM: " + mentshirt.getTgsm());
        holder.tquan_TV.setText(" Quantity: " + mentshirt.getQuan());
        holder.thome.setText("🏠 Address: " + mentshirt.getHome());
        holder.tmobile.setText("📞 Mobile: " + mentshirt.getPhn());

        // Here its a Time & Date Section
        holder.date.setText("Order Date: " + mentshirt.getDate());
        holder.time.setText("Order Time: " + mentshirt.getTime());


        // phone call
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tell: " + mentshirt.getPhn(), Toast.LENGTH_SHORT).show();
            }
        });


        // phone sms
        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sms Send Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrdertshirtViewAdapter extends RecyclerView.ViewHolder {
        private TextView tsize, tlength, twidth, tcolor, tquan_TV, tfabric_TV, tgsm_TV, thome, tmobile, date, time;
        private MaterialButton call, sms;

        public OrdertshirtViewAdapter(@NonNull View itemView) {
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

            call = itemView.findViewById(R.id.call);
            sms = itemView.findViewById(R.id.sms);
        }
    }
}
