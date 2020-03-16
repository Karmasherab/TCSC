package com.kashey.conference;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kashey.conference.adapter.CollegeAdapter;
import com.kashey.conference.adapter.GalleryAdapter;
import com.kashey.conference.domain.College;
import com.kashey.conference.domain.Gallery;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    FirebaseFirestore db;
    List<Gallery> mGalleryLIst;
    public GalleryAdapter mGalleryAdapter;
    RecyclerView mRecyclerView;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);
        mRecyclerView=view.findViewById(R.id.gall_rec);
        db=FirebaseFirestore.getInstance();
        mGalleryLIst=new ArrayList<>();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL ));
        mGalleryAdapter=new GalleryAdapter(mGalleryLIst,getContext());
        mRecyclerView.setAdapter(mGalleryAdapter);
        mRecyclerView.setHasFixedSize(true);
        // Inflate the layout for this fragment

        db.collection("Gallery")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot document : task.getResult()) {
                                Gallery gallery=document.toObject(Gallery.class);

                                mGalleryLIst.add(gallery);
                                mGalleryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG--------", "Error getting documents.", task.getException());
                        }
                    }
                });
        return view;
        // Inflate the layout for this fragment
    }
}
