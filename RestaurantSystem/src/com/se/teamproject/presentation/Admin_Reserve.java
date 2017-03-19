package com.se.teamproject.presentation;

import java.text.*;
import java.util.*;

import com.se.teamproject.*;
import com.se.teamproject.application.*;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.ViewGroup.*;
import android.widget.*;

// 레스토랑 예약 부분
public class Admin_Reserve extends Activity implements OnClickListener {
	SetProtocol Sp = MethodInterface.Sp;
	GetProtocol Gp = MethodInterface.Gp;
	private ImageButton datebtn;
	private Button gobtn;
	private EditText datetxt;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	private TableLayout tl;
	private int rows;
	private int cols;
	private int totaltable;
	private int TableId; // 테이블로부터 선택한 번호 받아옴

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_reserve);
		init();
	}

	public void init() {
		tl = (TableLayout) findViewById(R.id.tablelayout);
		datebtn = (ImageButton) findViewById(R.id.datebtn);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		datetxt = (EditText) findViewById(R.id.datetxt);
		datebtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		showDialog(0);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, year, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			String yearData = String.valueOf(selectedYear - 2000);
			String monthData = String.valueOf(selectedMonth + 1);
			String dayData = String.valueOf(selectedDay);
			if (selectedDay >= 1 && selectedDay <= 9) {
				dayData = "0" + dayData;
				datetxt.setText((selectedYear - 2000) + " / " + (selectedMonth + 1) + " / 0" + selectedDay);
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(4);
				Sp.getWord(yearData + monthData + dayData);
				Sp.sendProtocol();
				Log.w("year :", " " + yearData);
				Log.w("month :", " " + monthData);
				Log.w("day :", " " + dayData);
				MethodInterface.Year = yearData;
				MethodInterface.Month = monthData;
				MethodInterface.Day = dayData;
				totaltable = Gp.TotalTable;
				makeRowCol(totaltable); // parameter[0]=총
																// 갯수,
																// parameter[1]=예약된
																// 갯수
				RefreshUI();
			} else {
				datetxt.setText((selectedYear - 2000) + " / " + (selectedMonth + 1) + " / " + selectedDay);
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(4);
				Sp.getWord(yearData);
				Sp.getWord(monthData);
				Sp.getWord(dayData);
				Sp.sendProtocol();
				totaltable = Gp.TotalTable;
				makeRowCol(totaltable);
				RefreshUI();
			}
		}
	};

	// 테이블 생성
	public void BuildTable(int rows, int cols) {
		final String packName = this.getPackageName();
		final TableRow[] row = new TableRow[rows + 1];
		final int array[][] = new int[rows + 1][cols + 1];
		final Button btns[][] = new Button[rows + 1][cols + 1];

		int reside = getResources().getIdentifier("@drawable/emptytable", "drawable", packName);
		int residf = getResources().getIdentifier("@drawable/fulltable", "drawable", packName);
		Drawable de = getResources().getDrawable(reside);
		Drawable df = getResources().getDrawable(residf);
		Bitmap bitmape = ((BitmapDrawable) de).getBitmap();
		Bitmap bitmapf = ((BitmapDrawable) df).getBitmap();
		final int width = 600 / cols;
		final int height = 650 / rows;
		Drawable dre = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmape, width, height, true));
		Drawable drf = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapf, width, height, true));

		int cnt = 1;
		for (int i = 1; i <= rows; i++) {
			final int i2 = i;
			row[i2] = new TableRow(this);
			row[i2].setGravity(Gravity.CENTER);
			for (int j = 1; j <= cols; j++) {
				final int j2 = j;
				btns[i][j] = new Button(Admin_Reserve.this);
				btns[i][j].setId(cnt);
				btns[i][j].setBackgroundDrawable(dre);
				btns[i][j].setText("★ " + cnt + "번 ★");
				btns[i][j].setTextSize(20);
				cnt++;

				for (int a = 0; a < Gp.TableInfo.length; a++) {
					if (Gp.TableInfo[a][0].equals(Integer.toString(btns[i][j].getId()))) {
						btns[i][j].setBackgroundDrawable(drf);
					}
				}
				btns[i][j].setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (btns[i2][j2].isPressed()) {
							btns[i2][j2].setTag(new TableData(i2, j2));
							TableData tableData = (TableData) v.getTag();
							int n = 0;
							int cnt1 = 1;
							for (int k = 1; k < array.length; k++) {
								for (int s = 1; s < array[k].length; s++) {
									array[k][s] = cnt1;
									cnt1 += 1;
									if (k == tableData.RowIndex && s == tableData.ColumnIndex) {
										Log.w("array", String.valueOf(array[k][s])); // 테이블
																						// 번호
																						// !!!!!!!!!!
										n = array[k][s];
										TableId = n;
										break;
									}
								}
							}
							final ArrayList<String> phone = new ArrayList<String>();
							for (int a = 0; a < Gp.TableInfo.length; a++) {
								System.out.print("Admin_Reserve : ");
								for (int j = 0; j < Gp.TableInfo[Gp.NowTableNum].length; j++) {
									System.out.print(Gp.TableInfo[a][j] + "  ");
								}
								System.out.println();
							}
							// 예약 시간 다이얼로그 띄우기
							AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Admin_Reserve.this);
							alertBuilder.setIcon(R.drawable.ic_launcher);
							alertBuilder.setTitle("예약된 시간을 선택해주세요.");

							final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin_Reserve.this,
									android.R.layout.select_dialog_singlechoice);
							for (int i = 0; i < Gp.TableInfo.length; i++) {
								if (TableId == Integer.parseInt(Gp.TableInfo[i][0])) {
									adapter.add(Gp.TableInfo[i][1]+", "+Gp.TableInfo[i][4] + " ~ " + Gp.TableInfo[i][5]);
									phone.add(Gp.TableInfo[i][2]);
								}
							}

							alertBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							});
							alertBuilder.setPositiveButton("예약", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
									String time = sdfNow.format(new Date(System.currentTimeMillis()));
									String tarray[] = time.split(":");
									Intent i = new Intent(Admin_Reserve.this, Admin_ReserveNew.class);
									i.putExtra("tabletotal", totaltable);
									i.putExtra("tablenum", TableId);
									i.putExtra("hour", tarray[0].toString());
									i.putExtra("min", tarray[1].toString());
									i.putExtra("sec", tarray[2].toString());
									Sp.setProtocolHeader(14);
									Sp.getWord(Integer.toString(TableId));
									Sp.sendProtocol();
									startActivity(i);
								}
							});
							alertBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									String phoneNum = phone.get(id);
									String g = adapter.getItem(id);
									String g1[]=g.split(", ");
									System.out.println(g1[0]);
									String g2[]=g1[1].split(" ~ ");
									String g3[]=g2[0].split(":");
									for(int i=0;i<g3.length;i++)
										System.out.print(g3[i]+" ");
									Intent i = new Intent(Admin_Reserve.this, Admin_ReserveChoiceMD.class);
									i.putExtra("tabletotal", Integer.parseInt(Gp.parameter[0]));
									i.putExtra("tablenum", TableId);
									i.putExtra("name", g1[0].toString());
									i.putExtra("phone", phoneNum);
									i.putExtra("hour", g3[0].toString());
									i.putExtra("min", g3[1].toString());
									Sp.setProtocolHeader(14);
									Sp.getWord(Integer.toString(TableId));
									Sp.sendProtocol();
									startActivity(i);
								}
							});
							alertBuilder.show();
						}
					}
				});
				row[i2].addView(btns[i][j]);
			}
			tl.addView(row[i2]);
		}
	}

	public void makeRowCol(int totaltable) { // result * n = 행 * 열
		int n = 0;
		for (int i = 2; i <= 50; i++) {
			if (i == totaltable) {
				n = (int) Math.sqrt(i);
				if (i % n != 0) {
					while (true) {
						n--;
						if (i % n == 0)
							break;
					}
					int result1 = i / n;
					rows = result1;
					cols = n;
					System.out.println(result1 + "*" + n);
				} else {
					int result2 = i / n;
					rows = result2;
					cols = n;
					System.out.println(result2 + "*" + n);
				}
			}
		}
	}

	// UI 재생성해서 테이블 뿌려주기
	public void RefreshUI() {
		tl.removeAllViewsInLayout();
		tl.removeAllViews();
		tl.refreshDrawableState();

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					new Timer().schedule(new TimerTask() {
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									BuildTable(rows, cols);
								}
							});
						}
					}, (long) 500); // 1초후 실행
					break;
				}
				;
			}
		});
		t.start();
	}
}
