package com.se.teamproject.presentation;

import com.se.teamproject.*;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class JoinClient extends Activity {
	private EditText id, pwd, name, email;
	private ImageButton submitbtn, cancelbtn;

	String idData;
	String pwdData;
	String nameData;
	String emailData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.joinclient);
		init();
	}

	private void init() {
		id = (EditText) findViewById(R.id.joinclientid);
		pwd = (EditText) findViewById(R.id.joinclientpwd);
		name = (EditText) findViewById(R.id.joinclientname);
		email = (EditText) findViewById(R.id.joinclientemail);
		submitbtn = (ImageButton) findViewById(R.id.submitbtn);
		submitbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String idData = id.getText().toString();
				String pwdData = pwd.getText().toString();
				String nameData = name.getText().toString();
				String emailData = email.getText().toString();
				Log.w("clientID :", " " + idData);
				Log.w("clientPwd :", " " + pwdData);
				Log.w("clientName :", " " + nameData);
				Log.w("clientEmail :", " " + emailData);

				Toast.makeText(getApplicationContext(), "가입한 아이디로 로그인해줘", Toast.LENGTH_SHORT).show();
				// 회원가입 확인 버튼 누르면 첫페이지로 이동
				Intent i = new Intent(JoinClient.this, StartMain.class);
				startActivity(i);
			}
		});
		cancelbtn = (ImageButton) findViewById(R.id.cancelbtn);
		cancelbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
