package com.example.minhpq.firebasedemochat.presenter;

import com.example.minhpq.firebasedemochat.RealmService;
import com.example.minhpq.firebasedemochat.activity.MainActivity;
import com.example.minhpq.firebasedemochat.adapter.ListMemberAdapter;
import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.view.HomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhpq on 4/23/2018.
 */

public class HomePresenter {
    private RealmService realmService;
    private List<Member> memberList = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    private HomeView homeView;
    DatabaseReference mMemberRef;
    public HomePresenter(RealmService realmService,List<Member> memberList, HomeView homeView,DatabaseReference mMemberRef,FirebaseAuth firebaseAuth) {
        this.realmService=realmService;
        this.firebaseAuth=firebaseAuth;
        this.mMemberRef=mMemberRef;
        this.memberList = memberList;
        this.homeView = homeView;
    }

    public void getListmember() {
        mMemberRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memberList.clear();
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Member member=snapshot.getValue(Member.class);
                        if (!member.getUserId().equals(firebaseAuth.getCurrentUser().getUid())) {
                            memberList.add(member);
                            homeView.showListmember(memberList);
                            realmService.addListMember(memberList);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
