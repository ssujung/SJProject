package com.se.teamproject.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.se.teamproject.R;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class JoinStaff extends Activity implements OnCheckedChangeListener {

	private RadioGroup rg;
	private RadioButton normal, manager;
	private EditText id, pwd, name, email;
	private ImageButton submitbtn, cancelbtn;

	String idData;
	String pwdData;
	String nameData;
	String emailData;
	int checkAdmin = 0;
//	private Socket socket;
//	BufferedReader socket_in;
//	PrintWriter socket_out;
//
//	String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.joinstaff);
		init();
	}

	private void init() {
		id = (EditText) findViewById(R.id.joinstaffid);
		pwd = (EditText) findViewById(R.id.joinstaffpwd);
		name = (EditText) findViewById(R.id.joinstaffname);
		email = (EditText) findViewById(R.id.joinstaffemail);
		rg = (RadioGroup) findViewById(R.id.radiogroup1);
		normal = (RadioButton) findViewById(R.id.joinnormal);
		normal.setChecked(true);
		manager = (RadioButton) findViewById(R.id.joinmanager);
		rg.setOnCheckedChangeListener(this);
		submitbtn = (ImageButton) findViewById(R.id.submitbtn);

		// 확인 버튼 눌렀을 때 아이디, 비밀번호, 이름, 이메일 넘겨줌
		submitbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String idData = id.getText().toString();
				String pwdData = pwd.getText().toString();
				String nameData = name.getText().toString();
				String emailData = email.getText().toString();
				Log.w("staffID :", " " + idData);
				Log.w("staffPwd :", " " + pwdData);
				Log.w("staffName :", " " + nameData);
				Log.w("staffEmail :", " " + emailData);
				if(idData == null || pwdData == null || nameData == null || emailData == null){
					Toast.makeText(getApplicationContext(), "정보를 정확히 입력하세요.", Toast.LENGTH_SHORT).show();
				}
				else{
					SetProtocol Sp = MethodInterface.Sp;
					Sp.setProtocolHeader(2 - checkAdmin);
					Sp.getWord(idData);
					Sp.getWord(pwdData);
					Sp.getWord(nameData);
					Sp.getWord(emailData);
					Sp.sendProtocol();
					Intent i = new Intent(JoinStaff.this, StartMain.class);
					startActivity(i);
				}
//				data = "[Sjoin]" + idData + "," + pwdData + "," + nameData + "," + emailData + "," + "admin";
//				// 데이터가 NULL이 아니면 서버로 보내
//				if (idData != null && pwdData != null && nameData != null && emailData != null) {
//
//					socket_out.println(data);
//
//				}

				// 회원가입 확인 버튼 누르면 첫페이지로 이동
			}
		});

//		Thread worker = new Thread() {
//			public void run() {
//				try {
//					socket = new Socket("203.249.22.100", 8888);
//					socket_out = new PrintWriter(socket.getOutputStream(), true);
//					socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				try {
//					while (true) {
//						data = socket_in.readLine();
//					}
//				} catch (Exception e) {
//				}
//			}
//
//		};
//		worker.start();

		cancelbtn = (ImageButton) findViewById(R.id.cancelbtn);
		cancelbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (rg.getCheckedRadioButtonId() == R.id.joinnormal) {
			checkAdmin = 0;
		} else if (rg.getCheckedRadioButtonId() == R.id.joinmanager) {
			checkAdmin = 1;
		}

	}

//	@Override
//	protected void onStop() {
//		super.onStop();
//		try {
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
