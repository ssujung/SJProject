package com.se.teamproject.presentation;

import com.se.teamproject.*;
import com.se.teamproject.application.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class Admin_ReserveNew extends Activity {
	SetProtocol Sp = MethodInterface.Sp;
	GetProtocol Gp = MethodInterface.Gp;
	public EditText reservename, reservephone, reservehour, reservemin;
	public ImageButton submit, cancel;
	public Spinner reservetablenum, reservecount;
	private int ProtocolMin;
	String sec;

	String rnameData; 
	String rphoneData; 
	String rhourData;
	String rminData;
	String rtablenumData;
	String rcountData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_reservenew);
		init();
	}

	public void init() {
		Intent intent = getIntent();
		reservename = (EditText) findViewById(R.id.reservename);
		reservephone = (EditText) findViewById(R.id.reservephone);
		reservehour = (EditText) findViewById(R.id.reservehour);
		String hour = intent.getExtras().getString("hour");
		reservehour.setText(hour);
		reservemin = (EditText) findViewById(R.id.reservemin);
		String min = intent.getExtras().getString("min");
		reservemin.setText(min);
		sec = intent.getExtras().getString("sec");
		reservetablenum = (Spinner) findViewById(R.id.reservetablenum);
		int tabletotal = intent.getExtras().getInt("tabletotal");
		int tnum = intent.getExtras().getInt("tablenum");
		reservetablenum.setPrompt("테이블 번호 선택");

		ArrayAdapter<Integer> list1;
		String[] aa1 = new String[tabletotal];
		for (int i = 0; i < aa1.length; i++)
			aa1[i] = (i + 1) + "";
		list1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aa1);

		reservetablenum.setAdapter(list1);
        reservetablenum.setSelection(tnum-1);
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
		reservecount = (Spinner) findViewById(R.id.reservecount);
		reservecount.setPrompt("예약 인원 선택");

		ArrayAdapter<String> list2;
		int canTableIn = Integer.parseInt(Gp.parameter[0]);
		int Overflow = Integer.parseInt(Gp.parameter[0]) + Integer.parseInt(Gp.parameter[1]);
		String[] aa2 = new String[Overflow];
		for (int i = 0; i < Overflow; i++){
			if(i >= canTableIn)
				aa2[i] = "(OverFlow)" + (i + 1) + "";
			else
				aa2[i] = (i + 1) + "";
		}
		list2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aa2);
		reservecount.setAdapter(list2);
		reservecount.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		submit = (ImageButton) findViewById(R.id.submitbtn);
		submit.setOnClickListener(new OnClickListener() {
			// 예약하기 확인 버튼을 눌렀을 때
			@Override
			public void onClick(View v) {

				String rnameData = reservename.getText().toString();
				String rphoneData = reservephone.getText().toString();
				String rhourData = reservehour.getText().toString();
				String rminData = reservemin.getText().toString();
				String rsecData = sec.toString();
				String rtablenumData = reservetablenum.getSelectedItem().toString();
				String rcountData = reservecount.getSelectedItem().toString();
				if(rcountData.length() > 2){
					rcountData = rcountData.substring(10);
				}

				Log.w("예약 이름이 넘어가는 중 ", " " + rnameData);
				Log.w("예약 핸드폰 번호가 넘어가는 중", " " + rphoneData);
				Log.w("예약 시간이 넘어가는 중", " " + rhourData);
				Log.w("예약 분이 넘어가는 중", " " + rminData);
				Log.w("예약 초가 넘어가는 중", " " + rsecData);
				Log.w("예약 테이블번호가 넘어가는 중", " " + rtablenumData);
				Log.w("예약 식사인원이 넘어가는 중", " " + rcountData);
				if(rnameData.equals("") || rphoneData.equals("") || rhourData.equals("")|| rminData.equals("") || rtablenumData.equals("") || rcountData.equals("")){
					Toast.makeText(getApplicationContext(), "정보를 모두 다 입력하세요.", Toast.LENGTH_SHORT).show();
				}
				else{
					SetProtocol Sp = MethodInterface.Sp;
					Sp.setProtocolHeader(6);
					Sp.getWord(rnameData);
					Sp.getWord(rcountData);
					Sp.getWord(MethodInterface.Year + MethodInterface.Month + MethodInterface.Day);
					Sp.getWord(rhourData+ ":" + rminData + ":00");
					Sp.getWord(rtablenumData);
					Sp.getWord(MethodInterface.LoginId);
					Sp.getWord(rphoneData);
					Sp.sendProtocol();
					Log.w("[Reservation]", rnameData+","+rphoneData+","+rhourData+":"+rminData+":"+rsecData+","+rtablenumData+","+rcountData);
					if(Gp.isCheckOk() == true){
						Toast.makeText(getApplicationContext(), "예약 완료!", Toast.LENGTH_SHORT).show();
						Intent i = new Intent(Admin_ReserveNew.this, Admin_Reserve.class);
						startActivity(i);
					}
					else{
						if(Gp.getIn_Protocol().startsWith("[Overflow]")){
							//다이알로그?
						}
						else{
							Toast.makeText(getApplicationContext(), "해당 시간에 예약을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		});
		cancel = (ImageButton) findViewById(R.id.cancelbtn);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
