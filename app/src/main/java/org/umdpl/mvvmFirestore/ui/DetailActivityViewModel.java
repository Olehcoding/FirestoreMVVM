package org.umdpl.mvvmFirestore.ui;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.umdpl.mvvmFirestore.FirebaseQueryLivedata;
import org.umdpl.mvvmFirestore.model.Title;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailActivityViewModel extends ViewModel {
    private static final Query DB_REF = FirebaseFirestore.getInstance().collection("mainScr")
            .orderBy("position", Query.Direction.ASCENDING);


    private final FirebaseQueryLivedata livedata = new FirebaseQueryLivedata(Title.class,DB_REF);

    @NonNull
    public LiveData<QuerySnapshot> getDataSnapshotLiveData() {
        return livedata;
    }
}

