package org.umdpl.mvvmFirestore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private TextView tvPrice;
    private HotStock model;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTicker = findViewById(R.id.tv_ticker);
        tvPrice = findViewById(R.id.tv_price);
        button = findViewById(R.id.btn_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
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