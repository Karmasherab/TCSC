package com.kashey.conference;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kashey.conference.adapter.PostAdapter;
import com.kashey.conference.domain.Post;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    FirebaseFirestore db;
    List<Post> mPostLIst;
    RecyclerView mRecyclerView;
    PostAdapter postAdapter;
    ProgressDialog mDialog;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        db=FirebaseFirestore.getInstance();
        mPostLIst=new ArrayList<>();
        mRecyclerView=view.findViewById(R.id.home_recycler);
        mDialog=new ProgressDialog(getContext());
        mDialog.setTitle("Loading");
        mDialog.setMessage("Please wait while loading!");
        mDialog.show();
        postAdapter=new PostAdapter(mPostLIst,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(postAdapter);

        db.collection("post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        mDialog.dismiss();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Post post=document.toObject(Post.class);
                                mPostLIst.add(post);
                                postAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG--------", "Error getting documents.", task.getException());
                        }
                    }
                });
        return  view;
    }
}
