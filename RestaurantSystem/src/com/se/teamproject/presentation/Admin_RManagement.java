package com.se.teamproject.presentation;

import com.se.teamproject.*;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class Admin_RManagement extends Activity {
	
	ImageButton gochange;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_rmanagement);
		init();
	}

	private void init() {
		gochange = (ImageButton)findViewById(R.id.gochangetablenum);
		gochange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Admin_RManagement.this, Admin_ChangeTableNum.class);
				startActivity(i);
			}
		});
	}
}
