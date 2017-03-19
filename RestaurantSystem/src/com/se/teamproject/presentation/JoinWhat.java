package com.se.teamproject.presentation;

import com.se.teamproject.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class JoinWhat extends Activity {

	private ImageButton joinstaffbtn, joinclientbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.joinwhat);
		init();
	}

	private void init() {
		joinstaffbtn = (ImageButton) findViewById(R.id.joinstaffbtn);
		joinstaffbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(JoinWhat.this, JoinStaff.class);
				startActivity(i);
			}
		});
		joinclientbtn = (ImageButton) findViewById(R.id.joinclientbtn);
		joinclientbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(JoinWhat.this, JoinClient.class);
				startActivity(i);
			}
		});
	}
}
