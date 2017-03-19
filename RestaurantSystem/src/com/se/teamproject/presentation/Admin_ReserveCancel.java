package com.se.teamproject.presentation;

import com.se.teamproject.*;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.app.*;
import android.os.*;
// 레스토랑 예약 취소 
public class Admin_ReserveCancel extends Activity {
	SetProtocol Sp = MethodInterface.Sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_reservecancel);
		init();
	}
	
	private void init(){
		
	}
}
