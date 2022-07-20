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

public class OrdersRVAdapterU extends RecyclerView.Adapter<OrdersRVAdapterU.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<Order> orderArrayList;
    private Context context;

    // creating constructor for our adapter class
    public OrdersRVAdapterU(ArrayList<Order> coursesArrayList, Context context) {
        this.orderArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersRVAdapterU.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new OrdersRVAdapterU.ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRVAdapterU.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        Order order = orderArrayList.get(position);
        holder.ucourseNameTV.setText(order.getNama());
        holder.ucourseDurationTV.setText(order.getPaket());
        holder.ucourseDescTV.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return orderArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView ucourseNameTV;
        private final TextView ucourseDurationTV;
        private final TextView ucourseDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            ucourseNameTV = itemView.findViewById(R.id.idTVCourseName);
            ucourseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            ucourseDescTV = itemView.findViewById(R.id.idTVCourseDescription);


        }
    }
}
