package com.example.dancingbear;

import java.io.*;
import java.util.*;

import com.example.dancingbear.BluetoothService.*;
import com.example.test.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.*;
import android.content.*;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus.Listener;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener,
		SensorEventListener, OnClickListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureScanner;
	private long lastTime;
	private float speed;
	private float lastX;
	private float lastY;
	private float lastZ;
	private float x, y, z;
	private static final int SHAKE_THRESHOLD = 800;
	private static final int DATA_X = SensorManager.DATA_X;
	private static final int DATA_Y = SensorManager.DATA_Y;
	private static final int DATA_Z = SensorManager.DATA_Z;
	private SensorManager sensorManager;
	private Sensor accelerormeterSensor;
	private Paint paint;
	private ImageView image;
	private Button btn1, btn2;
	private Button btn_Connect;
	byte[] cmd;

	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;
	private static final String TAG = "Main";

	// RFCOMM Protocol
	private static final UUID MY_UUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");

	private BluetoothAdapter btAdapter;

	private Activity mActivity;

	private int mState;

	// 상태를 나타내는 상태 변수
	private static final int STATE_NONE = 0; // we're doing nothing
	private static final int STATE_LISTEN = 1; // now listening for incoming
												// connections
	private static final int STATE_CONNECTING = 2; // now initiating an outgoing
													// connection
	private static final int STATE_CONNECTED = 3; // now connected to a remote
													// device

	private BluetoothService btService = null;

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
		setContentView(R.layout.activity_main);

		gestureScanner = new GestureDetector(this);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerormeterSensor = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		if (btService == null) {
			btService = new BluetoothService(this, mHandler);
		}
		
		btn1 = (Button) findViewById(R.id.back);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(MainActivity.this, Main.class);
				btService.stop();
				//startActivity(intent);
				finish();
			}
		});

		btn_Connect = (Button) findViewById(R.id.btn_connect);
		btn_Connect.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if (btService.getDeviceState()) {
			// 블루투스가 지원 가능한 기기일 때
			btService.enableBluetooth();
		} else {
			//stopSelf();
			finish();
			//stopService(new Intent(this, BluetoothService.class));
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "onActivityResult " + resultCode);

		switch (requestCode) {

		case REQUEST_CONNECT_DEVICE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				btService.getDeviceInfo(data);
			}
			break;
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Next Step
				btService.scanDevice();
			} else {
				Toast.makeText(mActivity, "기기가 블루투스를 지원하지 않습니다.",
						1).show();
				Log.d(TAG, "Bluetooth is not enabled");
			}
			break;
		}
	}

	// Swipe

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		return gestureScanner.onTouchEvent(me);
	}

	public boolean onDown(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		try {
			// right to left swipe
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// sendData("1");
				String str="1";
				try {
					btService.write(str.getBytes());
					//Toast.makeText(getBaseContext(), "1성공!", 1).show();
				} catch (Exception ee) {
					Toast.makeText(getBaseContext(), "데이터 전송 실패!", 1).show();
				}
//				MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.lowdo);
//				mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//				    @Override
//				    public void onPrepared(MediaPlayer mediaPlayer) {
//				        mediaPlayer.start();
//				    }
//				});

				return true;

			}
			// left to right swipe
			else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// sendData("2");
				String str="2";
				try {
					btService.write(str.getBytes());
					//Toast.makeText(getBaseContext(), "2성공!", 1).show();
				} catch (Exception ee) {
					Toast.makeText(getBaseContext(), "데이터 전송 실패!", 1).show();
				}
//				MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.le);
//				mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//				    @Override
//				    public void onPrepared(MediaPlayer mediaPlayer) {
//				        mediaPlayer.start();
//				    }
//				});
				return true;
			}
			// down to up swipe
			else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				// sendData("3");
				String str="3";
				try {
					btService.write(str.getBytes());
					//Toast.makeText(getBaseContext(), "3성공!", 1).show();
				} catch (Exception ee) {
					Toast.makeText(getBaseContext(), "데이터 전송 실패!", 1).show();
				}
//				MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mi);
//				mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//				    @Override
//				    public void onPrepared(MediaPlayer mediaPlayer) {
//				        mediaPlayer.start();
//				    }
//				});
				return true;
			}
			// up to down swipe
			else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
				// sendData("4");
				String str="4";
				try {
					btService.write(str.getBytes());
					//Toast.makeText(getBaseContext(), "4성공!", 1).show();
				} catch (Exception ee) {
					Toast.makeText(getBaseContext(), "데이터 전송 실패!", 1).show();
				}
//				MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.pa);
//				/*mediaPlayer.start();
//				mediaPlayer.reset();
//				mediaPlayer.release();*/
//				mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//				    @Override
//				    public void onPrepared(MediaPlayer mediaPlayer) {
//				        mediaPlayer.start();
//				    }
//				});
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

	public void onLongPress(MotionEvent e) {
	}

	public boolean onSingleTapUp(MotionEvent e) {
		// sendData("5");
		String str="5";
		try {
			btService.write(str.getBytes());
			//Toast.makeText(getBaseContext(), "5성공!", 1).show();
		} catch (Exception ee) {
			Toast.makeText(getBaseContext(), "데이터 전송 실패!", 1).show();
		}
//		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.la);
//		mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//		    @Override
//		    public void onPrepared(MediaPlayer mediaPlayer) {
//		        mediaPlayer.start();
//		    }
//		});

		return true;
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// sendData("6");
		String str="6";
		try {
			btService.write(str.getBytes());
			//Toast.makeText(getBaseContext(), "6성공!", 1).show();
		} catch (Exception ee) {
			Toast.makeText(getBaseContext(), "데이터 전송 실패!", 1).show();
		}
//		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sol);
//		mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//		    @Override
//		    public void onPrepared(MediaPlayer mediaPlayer) {
//		        mediaPlayer.start();
//		    }
//		});
	}

	public boolean onDoubleTap(MotionEvent e) {
		return false;
	}

	// Shake

	@Override
	public void onStart() {
		super.onStart();
		if (accelerormeterSensor != null)
			sensorManager.registerListener(this, accelerormeterSensor,
					SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (sensorManager != null)
			sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			long currentTime = System.currentTimeMillis();
			long gabOfTime = (currentTime - lastTime);
			if (gabOfTime > 200) {										// 최근 측정한 시간과 현재 시간을 비교하여 0.2초 이상되었을 때, 흔듬을 감지
				lastTime = currentTime;
				x = event.values[SensorManager.DATA_X];
				y = event.values[SensorManager.DATA_Y];
				z = event.values[SensorManager.DATA_Z];
				speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime
						* 10000;
				if (speed > SHAKE_THRESHOLD) {
					// 이벤트 발생!!
					// sendData("7");
					String str="7";
					try {
						btService.write(str.getBytes());
						//Toast.makeText(getApplicationContext(), "7성공!", 1).show();
					} catch (Exception ee) {
						Toast.makeText(getApplicationContext(), "데이터 전송 실패!", 1).show();
					}
//					MediaPlayer mediaPlayer = MediaPlayer.create(this,
//							R.raw.highdo);
//					mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//					    @Override
//					    public void onPrepared(MediaPlayer mediaPlayer) {
//					        mediaPlayer.start();
//					    }
//					});
				}
				lastX = event.values[DATA_X];
				lastY = event.values[DATA_Y];
				lastZ = event.values[DATA_Z];
			}
		}
	}
}
