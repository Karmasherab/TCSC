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
import android.widget.Adapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kashey.conference.adapter.MembersAdapter;
import com.kashey.conference.domain.Members;
import com.kashey.conference.domain.Post;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment {
    FirebaseFirestore db;
    List<Members> mMembersLIst;
    MembersAdapter mMembersAdapter;
    RecyclerView recyclerView;


    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_member, container, false);

        db=FirebaseFirestore.getInstance();
        mMembersLIst=new ArrayList<>();
        mMembersAdapter=new MembersAdapter(mMembersLIst,getContext());
        recyclerView=view.findViewById(R.id.members_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMembersAdapter);

        db.collection("Members")
                .orderBy("tiimestamp", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Members members=document.toObject(Members.class);
                                mMembersLIst.add(members);
                                mMembersAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG--------", "Error getting documents.", task.getException());
                        }
                    }
                });
        return view;

    }
}
