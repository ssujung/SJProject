package com.se.teamproject.presentation;

import com.se.teamproject.R;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
// 레스토랑 관리자모드 -레스토랑 예약, 레스토랑 관리 부분
public class Admin_Login extends Activity {
	SetProtocol Sp = MethodInterface.Sp;
	private ImageButton goreserve, gomanage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_login);
		init();
	}
	
	private void init(){
		goreserve = (ImageButton)findViewById(R.id.go_restau_reserve);
		goreserve.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Admin_Login.this, Admin_Reserve.class);
				startActivity(i);
			}
		});
		gomanage = (ImageButton)findViewById(R.id.go_restau_manage);
		gomanage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Admin_Login.this, Admin_RManagement.class);
				startActivity(i);
			}
		});
	}
}
