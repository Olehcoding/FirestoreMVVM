package org.umdpl.mvvmFirestore;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.umdpl.mvvmFirestore.adapter.DetailAdapter;
import org.umdpl.mvvmFirestore.model.Title;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;

public class FirebaseQueryLivedata<T> extends LiveData<QuerySnapshot> implements EventListener<QuerySnapshot> {

    public static final String TAG = "FirebaseQueryLiveData";
    final Class<T> typeParameterClass;
    private QuerySnapshot querySnapshot;
    private ListenerRegistration listenerRegistration;
    Query query;
    DetailAdapter adapter;

    public DetailAdapter getAdapter() {
        return adapter;
    }

    public FirebaseQueryLivedata(Class<T> typeParameterClass, Query query) {
        this.typeParameterClass = typeParameterClass;
        this.query = query;
    }

    @Override
    protected void onActive() {
        super.onActive();

        Log.d(TAG, "onActive");
        if (listenerRegistration == null) {
            listenerRegistration = query.addSnapshotListener(this);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();

        Log.d(TAG, "onInactive: ");
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }


    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            Log.w("", "exception in fetching from firestore", e);
        }
        if (queryDocumentSnapshots != null) {
            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                setValue(queryDocumentSnapshots);
            }
            Log.w(TAG, "onEvent: " + queryDocumentSnapshots.getDocuments() );
        }
    }
}

