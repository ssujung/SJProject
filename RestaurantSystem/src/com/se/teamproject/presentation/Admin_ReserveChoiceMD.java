package com.se.teamproject.presentation;

import com.se.teamproject.*;
import com.se.teamproject.application.GetProtocol;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
// 레스토랑 예약 날짜 시간 
public class Admin_ReserveChoiceMD extends Activity {

	ImageButton gomodifybtn, godeletebtn;
	String name, phone, hour, min;
	int tabletotal, tnum;
	SetProtocol Sp = MethodInterface.Sp;
	GetProtocol Gp = MethodInterface.Gp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_reservechoicemd);
		init();
	}

	public void init() {
		Intent intent = getIntent();
		name = intent.getExtras().getString("name");
		phone = intent.getExtras().getString("phone");
		hour = intent.getExtras().getString("hour");
		min = intent.getExtras().getString("min");
		tabletotal = intent.getExtras().getInt("tabletotal");
		tnum = intent.getExtras().getInt("tablenum");
		gomodifybtn = (ImageButton) findViewById(R.id.go_reserve_modify);
		gomodifybtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Admin_ReserveChoiceMD.this, Admin_ReserveModify.class);
                i.putExtra("tabletotal",tabletotal);
                i.putExtra("tablenum", tnum);
                i.putExtra("name", name);
                i.putExtra("phone", phone);
                i.putExtra("hour", hour);
                i.putExtra("min", min);
                startActivity(i);

			}
		});
		godeletebtn = (ImageButton) findViewById(R.id.go_reserve_delete);
		godeletebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alt_bld = new AlertDialog.Builder(Admin_ReserveChoiceMD.this);
			    alt_bld.setMessage("정말 삭제하시겠습니까?").setCancelable(false).setPositiveButton("예",
			        new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int id) {
			        	Sp.setProtocolHeader(11);
			        	Sp.getWord(Integer.toString(tnum));
			        	Sp.getWord(MethodInterface.Year + MethodInterface.Month + MethodInterface.Day);
			        	Sp.getWord(hour + ":" + min);
			        	Sp.sendProtocol();
						AlertDialog.Builder alt_bld1 = new AlertDialog.Builder(Admin_ReserveChoiceMD.this);
						alt_bld1.setMessage("삭제가 완료되었습니다.");
						final AlertDialog alert1 = alt_bld1.create();
						alert1.show();
						Runnable mRunnable = new Runnable() {
							public void run() {
								alert1.dismiss();
							}
						};
						Handler handler = new Handler();
			            handler.postDelayed(mRunnable, 1000);
			        }
			        }).setNegativeButton("아니오",
			        new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int id) {
			            // Action for 'NO' Button
			            dialog.cancel();
			        }
			        });
			    AlertDialog alert = alt_bld.create();
			    alert.setTitle("삭제 확인");
			    alert.show();
			}
		});
	}
}
