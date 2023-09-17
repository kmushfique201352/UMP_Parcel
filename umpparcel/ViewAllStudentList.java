package com.example.umpparcel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAllStudentList extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVUserListAdapter adapter;
    DAOUserProfile dao;
    boolean isLoading=false;
    private FirebaseUser user;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_list);
        //uncomment after join login process
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        uid=user.getUid();

        swipeRefreshLayout = findViewById(R.id.swipUserProfile);
        recyclerView = findViewById(R.id.rvUserProfile);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RVUserListAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOUserProfile();
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
        //search record base on user login
        dao.getByUsername(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<UserModel> users = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    UserModel user = data.getValue(UserModel.class);
                    user.setKey(data.getKey());
                    users.add(user);
                }
                adapter.setItems(users);
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
