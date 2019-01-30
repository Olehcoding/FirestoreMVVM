package org.umdpl.mvvmFirestore.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;

import org.umdpl.mvvmFirestore.R;
import org.umdpl.mvvmFirestore.model.HotStock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity1";
    private TextView tvTicker;
    private TextView tv_price;
    private HotStock model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTicker = findViewById(R.id.tv_ticker);
        tv_price = findViewById(R.id.tv_price);

        MainActivityViewModel viewModel =
                ViewModelProviders.of(this).get(MainActivityViewModel.class);
        LiveData<DocumentSnapshot> liveData = viewModel.getDataSnapshotLiveData();
        liveData.observe(this, documentSnapshot -> {
            Log.d(TAG, "onCreate: ");
            model = documentSnapshot.toObject(HotStock.class);
            tvTicker.setText(model.getTicker());
        });
//        liveData.observe(this, new Observer<DocumentSnapshot>() {
//            @Override
//            public void onChanged(@NonNull DocumentSnapshot documentSnapshot) {
//                model = documentSnapshot.toObject(HotStock.class);
//                tvTicker.setText(model.getTicker());
//            }
    }
}