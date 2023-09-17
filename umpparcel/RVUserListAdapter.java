package com.example.umpparcel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVUserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<UserModel> list = new ArrayList<>();
    UserModel user;


    public RVUserListAdapter(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<UserModel> user){
        list.addAll(user);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_user_profile_view_list, parent, false );
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserVH vh = (UserVH) holder;
        user = list.get(position);
        vh.txt_username.setText(user.getUsername());
        vh.txt_Uname.setText(user.getName());
        vh.txt_Utype.setText(user.getUserType());

        vh.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UserProfileDetailActivity.class);
                i.putExtra("sendUserModel", user);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
