package com.example.umpparcel;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserVH extends RecyclerView.ViewHolder {
    public TextView txt_username, txt_Uname, txt_Utype;
    public View view;
    public UserVH(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        txt_username = itemView.findViewById(R.id.txt_Username);
        txt_Uname = itemView.findViewById(R.id.txt_Uname);
        txt_Utype = itemView.findViewById(R.id.txt_Utype);

    }
}
