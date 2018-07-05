package com.example.meri.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static com.example.meri.myapplication.R.*;

public class MainActivity extends AppCompatActivity {
    private final int[] mColors =
            {Color.BLUE, Color.GREEN, Color.RED, Color.LTGRAY, Color.MAGENTA, Color.CYAN,
                    Color.YELLOW, Color.WHITE};
    File file=null;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,0);
        im=findViewById(id.imageView);
        Button b=findViewById(id.button);
        Button b1=findViewById(id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(file!=null){
         Bitmap bitmap= BitmapFactory.decodeFile(getFilesDir().getAbsoluteFile()+"/"+"v"+"/createdfile.jpg");
                Random r=new Random();
              //  int n=mColors.length;

       ImageView im1=findViewById(id.imageView2);
       im1.setImageBitmap(bitmap);
       im1.setColorFilter(mColors[r.nextInt(mColors.length)], PorterDuff.Mode.MULTIPLY);
               }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = getFilesDir();
                File doc = new File(folder.getAbsoluteFile()+"/"+"v");
                Log.d("log",folder.getAbsolutePath());
                doc.mkdirs();
                file=new File(doc,"createdfile.jpg");

                try {
                   FileOutputStream bw = new FileOutputStream(file);
                    Drawable drawable=im.getDrawable();
                    Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bw);
                    bw.flush();
                    bw.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri=data.getData();
        im.setImageURI(uri);
    }
}
