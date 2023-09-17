package com.example.umpparcel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVParcelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  Context context;
    ArrayList<ParcelModel> list = new ArrayList<>();

    public RVParcelAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<ParcelModel> parcel){
        list.addAll(parcel);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_parcel, parent, false );
        return new ParcelVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ParcelVH vh = (ParcelVH) holder;
        vh.parcelModel = list.get(position);
        vh.txt_parcelName.setText(vh.parcelModel.getParcelName());
        vh.txt_studentName.setText(vh.parcelModel.getStudentName());
        vh.txt_studentId.setText(vh.parcelModel.getStudentID());
        vh.txt_trackingNo.setText(vh.parcelModel.getTrackingNo());
        vh.txt_rackNo.setText(vh.parcelModel.getRackNo());
        vh.txt_statusTaken.setText( (vh.parcelModel.isTaken()) ? "Taken" : "Not Taken");
        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TrackingParcelDetailActivity.class);
                i.putExtra("sendParcelModel", vh.parcelModel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
