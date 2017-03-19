package com.example.dancingbear;
/*package com.example.test;

import java.util.*;

import android.app.*;
import android.app.ActivityManager.*;
import android.content.*;
import android.os.*;

public class MyService extends Service {
	BluetoothService mBluetoothService;

	final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		mBluetoothService = new BluetoothService(new MainActivity(), mHandler);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mBluetoothService.enableBluetooth();
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		mBluetoothService.stop();
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
*/