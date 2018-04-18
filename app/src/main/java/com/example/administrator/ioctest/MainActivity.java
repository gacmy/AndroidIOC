package com.example.administrator.ioctest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.tv) TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv.setText("annotation successful");

    }


    @EventInject(R.id.tv)
    public void onClickTv(View view){
        Toast.makeText(this,"tv is clicked",Toast.LENGTH_LONG).show();
    }
}
