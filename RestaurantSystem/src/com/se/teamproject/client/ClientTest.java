package com.se.teamproject.client;

import com.se.teamproject.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class ClientTest extends Activity {
	private String return_msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clienttest);

		if (android.os.Build.VERSION.SDK_INT > 9) {

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy);

		}

		final EditText et = (EditText) findViewById(R.id.EditText01);
		Button btn = (Button) findViewById(R.id.Button01);
		final TextView tv = (TextView) findViewById(R.id.TextView01);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (et.getText().toString() != null
						|| !et.getText().toString().equals("")) {
					//TCPclient tp = new TCPclient(et.getText().toString());
					//tp.run();

					Toast t = Toast.makeText(getApplicationContext(),
							return_msg, Toast.LENGTH_LONG);
					t.show();
					tv.setText(return_msg);
				}
			}
		});
	}
}
