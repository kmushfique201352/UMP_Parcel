package com.example.umpparcel;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ParcelVH extends RecyclerView.ViewHolder {
    public TextView txt_rackNo, txt_trackingNo, txt_parcelName, txt_studentName, txt_studentId, txt_option, txt_statusTaken;
    public View view;
    public ParcelModel parcelModel;
    public ParcelVH(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        txt_rackNo = itemView.findViewById(R.id.txt_rackNo);
        txt_trackingNo = itemView.findViewById(R.id.txt_trackingNo);
        txt_parcelName = itemView.findViewById(R.id.txt_parcelName);
        txt_studentName = itemView.findViewById(R.id.txt_studentName);
        txt_studentId = itemView.findViewById(R.id.txt_studentId);
        txt_statusTaken = itemView.findViewById(R.id.txt_statusTaken);

    }
}
