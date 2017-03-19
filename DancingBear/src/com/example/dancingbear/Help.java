package com.example.dancingbear;

import com.example.test.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class Help extends Activity {

	Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.help);

		ImageView image1=(ImageView)findViewById(R.id.pointright);
		ImageView image2=(ImageView)findViewById(R.id.pointleft);
		ImageView image3=(ImageView)findViewById(R.id.pointup);
		ImageView image4=(ImageView)findViewById(R.id.pointdown);
		ImageView image5=(ImageView)findViewById(R.id.tap);
		ImageView image6=(ImageView)findViewById(R.id.press);
		ImageView image7=(ImageView)findViewById(R.id.shaking);
		ImageView image8=(ImageView)findViewById(R.id.tostart);
		
		Animation animation1=AnimationUtils.loadAnimation(this, R.anim.pointright);
		image1.startAnimation(animation1);
		image1.setVisibility(View.INVISIBLE);
		Animation animation2=AnimationUtils.loadAnimation(this, R.anim.pointleft);
		image2.startAnimation(animation2);
		image2.setVisibility(View.INVISIBLE);
		Animation animation3=AnimationUtils.loadAnimation(this, R.anim.pointup);
		image3.startAnimation(animation3);
		image3.setVisibility(View.INVISIBLE);
		Animation animation4=AnimationUtils.loadAnimation(this, R.anim.pointdown);
		image4.startAnimation(animation4);
		image4.setVisibility(View.INVISIBLE);
		Animation animation5=AnimationUtils.loadAnimation(this, R.anim.tap);
		image5.startAnimation(animation5);
		image5.setVisibility(View.INVISIBLE);
		Animation animation6=AnimationUtils.loadAnimation(this, R.anim.press);
		image6.startAnimation(animation6);
		image6.setVisibility(View.INVISIBLE);
		Animation animation7=AnimationUtils.loadAnimation(this, R.anim.shaking);
		image7.startAnimation(animation7);
		image7.setVisibility(View.INVISIBLE);
		Animation animation8=AnimationUtils.loadAnimation(this, R.anim.tostart);
		image8.startAnimation(animation8);
		
		btn1 = (Button) findViewById(R.id.back);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(Help.this, Main.class);
//				startActivity(intent);
				finish();
			}
		});
	}

}
