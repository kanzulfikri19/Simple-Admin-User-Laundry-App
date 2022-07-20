package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersRVAdapter extends RecyclerView.Adapter<OrdersRVAdapter.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<Order> orderArrayList;
    private Context context;

    // creating constructor for our adapter class
    public OrdersRVAdapter(ArrayList<Order> coursesArrayList, Context context) {
        this.orderArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRVAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        Order order = orderArrayList.get(position);
        holder.courseNameTV.setText(order.getNama());
        holder.courseDurationTV.setText(order.getPaket());
        holder.courseDescTV.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return orderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView courseNameTV;
        private final TextView courseDurationTV;
        private final TextView courseDescTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // after clicking of the item of recycler view.
                    // we are passing our course object to the new activity.
                    Order order = orderArrayList.get(getAdapterPosition());

                    // below line is creating a new intent.
                    Intent i = new Intent(context, UpdateOrders.class);

                    // below line is for putting our course object to our next activity.
                    i.putExtra("Order", order);

                    // after passing the data we are starting our activity.
                    context.startActivity(i);
                }
            });
        }
    }
}
