package com.se.teamproject.presentation;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.se.teamproject.R;
import com.se.teamproject.application.GetProtocol;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartMain extends Activity {
	private InputMethodManager imm;
	private EditText loginid, loginpwd;
	private ImageButton loginbtn, joinbtn;
	private LinearLayout mainLayout1;
	public static String ID;
	/*
	public void Connect() {
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ChannelPipeline p = ch.pipeline();
					p.addLast(new DelimiterBasedFrameDecoder(16384, Delimiters.lineDelimiter()));
					p.addLast(new LoggingHandler(LogLevel.DEBUG));
					p.addLast(CH);
				}
			});

			try {
				f = b.connect("203.249.22.100", 8888).sync();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				f.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			group.shutdownGracefully();
		}

	}
*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startmain);
		init();
	}

	private void init() {
		loginid = (EditText) findViewById(R.id.loginid);
		loginpwd = (EditText) findViewById(R.id.loginpwd);
		loginbtn = (ImageButton) findViewById(R.id.loginbtn);
		loginbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SetProtocol Sp = MethodInterface.Sp;
				GetProtocol Gp = MethodInterface.Gp;
				String loginidData = loginid.getText().toString();
				ID = loginidData;
				String loginpwdData = loginpwd.getText().toString();
				Sp.setProtocolHeader(3);
				Sp.getWord(loginidData);
				Sp.getWord(loginpwdData);
				Sp.sendProtocol();
				if(Gp.isCheckOk() == true){
					MethodInterface.LoginId = ID;
					Intent i = new Intent(StartMain.this, Admin_Login.class);
					startActivity(i);
				}
				else{
					Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		joinbtn = (ImageButton) findViewById(R.id.joinbtn);
		joinbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(StartMain.this, JoinWhat.class);
				startActivity(i);
			}
		});
		mainLayout1 = (LinearLayout) findViewById(R.id.mainLayout1);
		mainLayout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromInputMethod(loginid.getWindowToken(), 0);
				imm.hideSoftInputFromInputMethod(loginpwd.getWindowToken(), 0);
			}
		});
	}

}
