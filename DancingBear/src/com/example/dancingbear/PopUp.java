package com.example.dancingbear;
//package com.example.test;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.os.Bundle;
//import android.view.*;
//import android.view.View.OnClickListener;
//import android.widget.*;
//
//public class PopUp extends Activity implements OnClickListener {
//	
//	Button btn1;
//	SeekBar seekbar1;
//	
// 	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.settingpopup);
//		
//		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
//		final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
//		int nMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//		int nCurrentVolumn = audioManager
//				.getStreamVolume(AudioManager.STREAM_MUSIC);
//		seekbar1.setMax(nMax);
//		seekbar1.setProgress(nCurrentVolumn);
//
//		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//			@Override
//			public void onStopTrackingTouch(SeekBar seekBar) {
//			}
//
//			@Override
//			public void onStartTrackingTouch(SeekBar seekBar) {
//			}
//
//			@Override
//			public void onProgressChanged(SeekBar seekBar, int progress,
//					boolean fromUser) {
//				if (progress < 1) {
//					progress = 1;
//					seekbar1.setProgress(progress);
//				}
//				// TODO Auto-generated method stub
//				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//						progress, 0);
//			}
//		});
//		btn1 = (Button) findViewById(R.id.back);
//		btn1.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(PopUp.this, Main.class);
//				startActivity(intent);
//			}
//		});
//
//	}
//	public void onClick(View arg0) {
//	
//	}
//} 
//
