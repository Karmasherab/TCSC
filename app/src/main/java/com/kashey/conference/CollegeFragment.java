package com.kashey.conference;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kashey.conference.adapter.CollegeAdapter;
import com.kashey.conference.adapter.MembersAdapter;
import com.kashey.conference.domain.College;
import com.kashey.conference.domain.Members;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends Fragment {
    FirebaseFirestore db;
    List<College> mCollegeLIst;
    CollegeAdapter mCollegeAdapter;
    RecyclerView recyclerView;

    public CollegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_college, container, false);

        db=FirebaseFirestore.getInstance();
        mCollegeLIst=new ArrayList<>();
        recyclerView=view.findViewById(R.id.college_recycler);

        mCollegeAdapter=new CollegeAdapter(mCollegeLIst,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mCollegeAdapter);
        recyclerView.setHasFixedSize(true);
        // Inflate the layout for this fragment

        db.collection("College")
                .orderBy("tiimestamp", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot document : task.getResult()) {
                                College college=document.toObject(College.class);

                                mCollegeLIst.add(college);
                                mCollegeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG--------", "Error getting documents.", task.getException());
                        }
                    }
                });
        return view;
    }
}
