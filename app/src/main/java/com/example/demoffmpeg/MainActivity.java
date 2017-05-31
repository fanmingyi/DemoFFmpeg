package com.example.demoffmpeg;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final File inputFile = new File(Environment.getExternalStorageDirectory(),"a.mov");
        final File outFile = new File(Environment.getExternalStorageDirectory(),"b.yuv");
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
       tv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new Thread(){
                   public void run() {
                       ffmpeg(inputFile.getAbsolutePath(), outFile.getAbsolutePath());
                   };
               }.start();
           }
       });
    }



    public native void ffmpeg(String inputSrc,String outSrc);
}
