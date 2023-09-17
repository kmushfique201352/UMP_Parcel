package com.example.umpparcel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminManageParcelActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    AdminParcelCustomAdapter adapter;
    DAOParcel dao;
    boolean isLoading=false;
    private FirebaseUser user;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_rvtrackingparcelstudent);
        swipeRefreshLayout = findViewById(R.id.swipp);
        recyclerView = findViewById(R.id.rvv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new AdminParcelCustomAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOParcel();
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem < lastVisible+3){
                    if(!isLoading){
                        isLoading=true;
                        loadData();
                    }
                }
            }
        });
    }
    private void loadData(){
        swipeRefreshLayout.setRefreshing(true);
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ParcelModel> parcels = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    ParcelModel parcel = data.getValue(ParcelModel.class);
                    parcel.setKey(data.getKey());
                    parcels.add(parcel);
                }
                adapter.setItems(parcels);
                adapter.notifyDataSetChanged();
                isLoading=false;
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}