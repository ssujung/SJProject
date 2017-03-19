package com.se.teamproject.presentation;

import java.text.*;
import java.util.*;

import org.achartengine.*;
import org.achartengine.chart.BarChart.*;
import org.achartengine.model.*;
import org.achartengine.renderer.*;

import com.se.teamproject.*;
import com.se.teamproject.application.*;

import android.app.*;
import android.graphics.*;
import android.graphics.Paint.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class Admin_ReserveState extends Activity {

	ImageButton nowday_state, week_state, month_state, datebtn1, datebtn2;
	TextView startday, endday;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	List<double[]> values = new ArrayList<double[]>();

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_reservestate);
		init();
		makeGraph();
	}

	public void init() {
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		final String yearData = String.valueOf(year - 2000);
		final String monthData = String.valueOf(month + 1);
		final String dayData = String.valueOf(day);
		startday = (TextView) findViewById(R.id.startday);
		endday = (TextView) findViewById(R.id.endday);
		nowday_state = (ImageButton) findViewById(R.id.nowday);
		nowday_state.setOnClickListener(new OnClickListener() {  // 당일
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (day >= 1 && day <= 9) {
					String dayss;
					dayss = "0" + dayData;
					startday.setText(yearData+monthData+dayss);
					endday.setText(yearData+monthData+dayss);
				} else {
					startday.setText(yearData+monthData+dayData);
					endday.setText(yearData+monthData+dayData);
				}
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(startday.getText().toString());
				Sp.getWord(endday.getText().toString());
				Sp.sendProtocol();
			}
		});
		final java.text.DateFormat format = new java.text.SimpleDateFormat("yyMMdd");
		week_state = (ImageButton) findViewById(R.id.week);
		week_state.setOnClickListener(new OnClickListener() {  // 1주일
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar cal1 = java.util.Calendar.getInstance();
				Calendar cal2 = java.util.Calendar.getInstance();
				cal1.add(Calendar.DAY_OF_MONTH, -3);
				startday.setText(format.format(cal1.getTime()));
				cal2.add(Calendar.DAY_OF_MONTH, +4);
				endday.setText(format.format(cal2.getTime()));
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(startday.getText().toString());
				Sp.getWord(endday.getText().toString());
				Sp.sendProtocol();
			}
		});
		month_state = (ImageButton) findViewById(R.id.month);
		month_state.setOnClickListener(new OnClickListener() {  // 1개월
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar cal3 = java.util.Calendar.getInstance();
				Calendar cal4 = java.util.Calendar.getInstance();
				cal3.add(Calendar.DAY_OF_MONTH, -15);
				startday.setText(format.format(cal3.getTime()));
				cal4.add(Calendar.DAY_OF_MONTH, +15);
				endday.setText(format.format(cal4.getTime()));
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(startday.getText().toString());
				Sp.getWord(endday.getText().toString());
				Sp.sendProtocol();
			}
		});
		datebtn1 = (ImageButton) findViewById(R.id.datebtn1);
		datebtn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(0);
			}
		});
		datebtn2 = (ImageButton) findViewById(R.id.datebtn2);
		datebtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(1);
			}
		});
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if (id == 0)
			return new DatePickerDialog(this, datePickerListener1, year, month, day);
		else if (id == 1)
			return new DatePickerDialog(this, datePickerListener2, year, month, day);
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			String yearData = String.valueOf(selectedYear - 2000);
			String monthData = String.valueOf(selectedMonth + 1);
			String dayData = String.valueOf(selectedDay);

			if (selectedDay >= 1 && selectedDay <= 9) {
				dayData = "0" + dayData;
				startday.setText((selectedYear - 2000) + (selectedMonth + 1) + "0" + selectedDay);
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(yearData+monthData+dayData);
				Sp.sendProtocol();
				Log.w("year :", " " + yearData);
				Log.w("month :", " " + monthData);
				Log.w("day :", " " + dayData);
			} else {
				startday.setText((selectedYear - 2000) + (selectedMonth + 1) + selectedDay);
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(yearData+monthData+dayData);
				Sp.sendProtocol();
			}

		}
	};
	private DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			String yearData = String.valueOf(selectedYear - 2000);
			String monthData = String.valueOf(selectedMonth + 1);
			String dayData = String.valueOf(selectedDay);

			if (selectedDay >= 1 && selectedDay <= 9) {
				dayData = "0" + dayData;
				endday.setText((selectedYear - 2000) + " / " + (selectedMonth + 1) + " / 0" + selectedDay);
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(yearData+monthData+dayData);
				Sp.sendProtocol();
				Log.w("year :", " " + yearData);
				Log.w("month :", " " + monthData);
				Log.w("day :", " " + dayData);
			} else {
				endday.setText((selectedYear - 2000) + " / " + (selectedMonth + 1) + selectedDay);
				SetProtocol Sp = MethodInterface.Sp;
				Sp.setProtocolHeader(16);
				Sp.getWord(yearData+monthData+dayData);
				Sp.sendProtocol();
			}

		}
	};

	public void makeGraph() {
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500, 12600, 14000 });
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		// 상단 표시 제목과 글자 크기
		renderer.setChartTitle("날짜별 예약현황");
		renderer.setChartTitleTextSize(20);

		// 분류에 대한 이름
		String[] days = new String[] { "당일 예약" };
		String[] weeks = new String[] { "주별 예약" };
		String[] monthts = new String[] { "월별 예약" };

		// 항목을 표시하는데 사용될 색상값
		int[] colors = new int[] { Color.YELLOW };

		// 분류명 글자 크기 및 각 색상 지정
		renderer.setLegendTextSize(20);
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}

		// X,Y축 항목이름과 글자 크기
		renderer.setXTitle("날짜");
		renderer.setYTitle("예약 총 수");
		renderer.setAxisTitleTextSize(18);

		// 수치값 글자 크기 / X축 최소,최대값 / Y축 최소,최대값
		renderer.setLabelsTextSize(15);
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(12.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(24000);

		// X,Y축 라인 색상
		renderer.setAxesColor(Color.WHITE);
		// 상단제목, X,Y축 제목, 수치값의 글자 색상
		renderer.setLabelsColor(Color.CYAN);

		// X축의 표시 간격
		renderer.setXLabels(12);
		// Y축의 표시 간격
		renderer.setYLabels(5);

		// X,Y축 정렬방향
		renderer.setXLabelsAlign(Align.LEFT);
		renderer.setYLabelsAlign(Align.LEFT);
		// X,Y축 스크롤 여부 ON/OFF
		renderer.setPanEnabled(false, false);
		// ZOOM기능 ON/OFF
		renderer.setZoomEnabled(false, false);
		// ZOOM 비율
		renderer.setZoomRate(1.0f);
		// 막대간 간격
		renderer.setBarSpacing(0.5f);

		// 설정 정보 설정
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		for (int i = 0; i < days.length; i++) {
			CategorySeries series = new CategorySeries(days[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}

		// 그래프 객체 생성
		GraphicalView gv = ChartFactory.getBarChartView(this, dataset, renderer, Type.STACKED);

		// 그래프를 LinearLayout에 추가
		LinearLayout llBody = (LinearLayout) findViewById(R.id.llBody);
		llBody.addView(gv);
	}
}
