package com.example.dancingbear;

import com.example.test.*;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Main extends Activity {

	Button btn1, btn2, btn3;
	int tom1, tom2, tom3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn1 = (Button) findViewById(R.id.help);
		btn2 = (Button) findViewById(R.id.play);
		btn3 = (Button) findViewById(R.id.exit);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, Help.class);
				startActivity(intent);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				//Log.d("main","connect");
				Intent intent = new Intent(Main.this, MainActivity.class);
				//Main.this.startActivityForResult(intent, 0);
				startActivity(intent);
			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				moveTaskToBack(true);
				finish();
				android.os.Process.killProcess(android.os.Process.myPid());


			}
		});
	}
}
