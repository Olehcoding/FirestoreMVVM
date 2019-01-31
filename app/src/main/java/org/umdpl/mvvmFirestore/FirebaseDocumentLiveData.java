package org.umdpl.mvvmFirestore;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import javax.annotation.Nullable;

import androidx.lifecycle.LiveData;

//ADD GENERICS
public class FirebaseDocumentLiveData<T> extends LiveData<DocumentSnapshot> implements EventListener<DocumentSnapshot> {

    public static final String TAG = "FirebaseQueryLiveData";
    final Class<T> typeParameterClass;
    private DocumentReference documentReference;
    private ListenerRegistration listenerRegistration;

    public FirebaseDocumentLiveData(Class<T> typeParameterClass, DocumentReference documentReference) {
        this.typeParameterClass = typeParameterClass;
        this.documentReference = documentReference;
    }

    @Override
    protected void onActive() {
        super.onActive();

        Log.d(TAG, "onActive");
        if (listenerRegistration == null) {
            listenerRegistration = documentReference.addSnapshotListener(this);
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
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
        if (documentSnapshot != null && documentSnapshot.exists()) {
            documentSnapshot.toObject(typeParameterClass);
            setValue(documentSnapshot);

        }
    }
}