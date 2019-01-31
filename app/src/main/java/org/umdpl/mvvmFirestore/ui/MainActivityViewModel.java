package org.umdpl.mvvmFirestore.ui;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.umdpl.mvvmFirestore.FirebaseDocumentLiveData;
import org.umdpl.mvvmFirestore.model.HotStock;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private static final DocumentReference DOC_REF = FirebaseFirestore.getInstance().collection(
            "hotStock").document("G0kvHNa39dqfJt5kWopg");
    private final FirebaseDocumentLiveData livedata = new FirebaseDocumentLiveData(HotStock.class, DOC_REF);

    @NonNull
    public LiveData<DocumentSnapshot> getDataSnapshotLiveData() {
        return livedata;
    }
}
