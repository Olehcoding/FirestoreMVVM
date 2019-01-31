package org.umdpl.mvvmFirestore.ui;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.umdpl.mvvmFirestore.R;
import org.umdpl.mvvmFirestore.adapter.DetailAdapter;
import org.umdpl.mvvmFirestore.model.Title;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";
    private RecyclerView recyclerView;
    private DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.rv_detail);
        adapter = new DetailAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        DetailActivityViewModel viewModel =
                ViewModelProviders.of(this).get(DetailActivityViewModel.class);
        LiveData<QuerySnapshot> liveData = viewModel.getDataSnapshotLiveData();
        liveData.observe(this, new Observer<QuerySnapshot>() {
            @Override
            public void onChanged(QuerySnapshot queryDocumentSnapshots) {
                Log.d(TAG, "onChanged: " + queryDocumentSnapshots);
                List<Title> titleList = new ArrayList<>();
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                    titleList.add(doc.toObject(Title.class));
                }
                adapter.setNewData(titleList);
            }
        });
    }
}
