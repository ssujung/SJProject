package com.se.teamproject.presentation;

import com.se.teamproject.*;
import com.se.teamproject.application.GetProtocol;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class Admin_ReserveModify extends Activity {
	public EditText reservehour, reservemin;
	public TextView reservenameview, reservephoneview;
	public ImageButton submitbtn, cancelbtn;
	public Spinner reservetablenum, reservecount;
	String rnameData;
	String rphoneData;
	String rhourData;
	String rminData;
	String rtablenumData;
	String rcountData;
	SetProtocol Sp = MethodInterface.Sp;
	GetProtocol Gp = MethodInterface.Gp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_reservemodify);
		init();
	}

	public void init() {
		Intent intent = getIntent();
		reservenameview = (TextView)findViewById(R.id.modifynameview);
		final String name = intent.getExtras().getString("name");
		reservenameview.setText(name);
		
		reservephoneview = (TextView)findViewById(R.id.modifyphoneview);
		String phone = intent.getExtras().getString("phone");
		reservephoneview.setText(phone);
		
		reservehour = (EditText) findViewById(R.id.modifyhour);
		String hour = intent.getExtras().getString("hour");
		reservehour.setText(hour);
		
		reservemin = (EditText) findViewById(R.id.modifymin);
		String min = intent.getExtras().getString("min");
		reservemin.setText(min);
		
		reservetablenum = (Spinner) findViewById(R.id.modifytablenum);
		int tabletotal = intent.getExtras().getInt("tabletotal");
		int tnum = intent.getExtras().getInt("tablenum");
		reservetablenum.setPrompt("테이블 번호 선택");

		ArrayAdapter<Integer> list1;
		String[] aa1 = new String[tabletotal];
		for (int i = 0; i < aa1.length; i++)
			aa1[i] = (i + 1) + "";
		list1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aa1);
		reservetablenum.setAdapter(list1);
		reservetablenum.setSelection(tnum - 1);
		reservetablenum.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// Toast.makeText(getApplicationContext(), (id+1)+"번 테이블 선택됨",
				// Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		reservecount = (Spinner) findViewById(R.id.modifycount);
		reservecount.setPrompt("예약 인원 선택");

		ArrayAdapter<String> list2;
		String[] aa2 = new String[20];
		for (int i = 0; i < 20; i++)
			aa2[i] = (i + 1) + "";
		list2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aa2);
		reservecount.setAdapter(list2);
		reservecount.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// Toast.makeText(getApplicationContext(), (id+1)+"명 예약",
				// Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		submitbtn = (ImageButton) findViewById(R.id.submitbtn);
		submitbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String rnameData = reservenameview.getText().toString();
				String rphoneData = reservephoneview.getText().toString();
				String rhourData = reservehour.getText().toString();
				String rminData = reservemin.getText().toString();
				String rtablenumData = reservetablenum.getSelectedItem().toString();
				String rcountData = reservecount.getSelectedItem().toString();

				Log.w("예약 이름이 넘어가는 중 ", " " + rnameData);
				Log.w("예약 핸드폰 번호가 넘어가는 중", " " + rphoneData);
				Log.w("예약 시간이 넘어가는 중", " " + rhourData);
				Log.w("예약 분이 넘어가는 중", " " + rminData);
				Log.w("예약 테이블번호가 넘어가는 중", " " + rtablenumData);
				Log.w("예약 식사인원이 넘어가는 중", " " + rcountData);
				Sp.setProtocolHeader(10);
				Sp.getWord(rnameData);
				Sp.getWord(rcountData);
				Sp.getWord(MethodInterface.Year + MethodInterface.Month + MethodInterface.Day);
				Sp.getWord(rhourData + ":" +rminData + ":" + "00");
				Sp.getWord(rtablenumData);
				Sp.getWord(StartMain.ID);
				Sp.getWord(rphoneData);
				Sp.sendProtocol();
				if (Gp.isCheckOk() == true) {
					Toast.makeText(getApplicationContext(), "수정 완료!", Toast.LENGTH_SHORT).show();

					Intent i = new Intent(Admin_ReserveModify.this, Admin_Reserve.class);
					startActivity(i);
				} else
					Toast.makeText(getApplicationContext(), "수정 실패", Toast.LENGTH_SHORT).show();

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
