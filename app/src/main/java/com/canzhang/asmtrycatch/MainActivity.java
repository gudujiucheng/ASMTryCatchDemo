package com.canzhang.asmtrycatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.canzhang.asmtrycatch.tryCatch.TestCrash1;

import asm.canzhang.com.asmdemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
                TestCrash1.crashMethod1();
                TestCrash1.crashMethod2();
            }
        });
    }
}
