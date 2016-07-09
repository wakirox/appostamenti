package com.appostamento;

import android.app.Activity;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Marius Gabriel Magadan on 09/07/2016.
 */
@EActivity(R.layout.activity_main)
public class QRCodeActivity extends Activity {


    @ViewById(R.id.imageView)
    ImageView imageView;

    @AfterViews
    void initComponents(){
        imageView.setImageResource(R.drawable.generate);
    }

}
