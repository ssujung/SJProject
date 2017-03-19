package com.se.teamproject.client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.se.teamproject.application.GetProtocol;
import com.se.teamproject.application.MethodInterface;
import com.se.teamproject.application.SetProtocol;

import android.os.StrictMode;
import android.util.*;

public class TCPclient implements Runnable{
	private static final String serverIP = "203.249.22.100";
	private static final int serverPort = 8888;
	private StringBuilder msg;
	private String return_msg;
	SetProtocol Sp = MethodInterface.Sp;
	GetProtocol Gp = MethodInterface.Gp;
	BufferedReader in;
	ArrayList <String> LineRead = new ArrayList<String>();
	public TCPclient(StringBuilder _msg){
		this.msg=_msg;
	}
	@Override
	public void run() {
		if (android.os.Build.VERSION.SDK_INT > 9) {

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy);

		}
		try{
			InetAddress serverAddr = InetAddress.getByName(serverIP);
			Log.d("TCP", "C: Connecting...");
			Socket socket = new Socket(serverAddr,serverPort);
			Log.d("TCP", "C: Connected!");
			
			try {
				Log.d("TCP","C: Sending: '" + msg + "'");
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"EUC-KR")),true);
				out.println(msg);
				Log.d("TCP","C: Sent.");
				Log.d("TCP","C: Done.");
				InputStream inst = socket.getInputStream();
				Log.d("TCP", "InputStream Complete");
				InputStreamReader instr = new InputStreamReader(inst,"EUC-KR");
				Log.d("TCP", "InputStreamReader Complete");
				//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//return_msg = String.valueOf(instr.read());
	        	in = new BufferedReader(instr);
	        	return_msg = in.readLine();
	        	Gp.getMessage(return_msg);
	        	if(return_msg.startsWith("[TableNum]")){
	        		for(int i=0;i < Integer.parseInt(Gp.parameter[1]);i++){
	        			return_msg = in.readLine();
	        			LineRead.add(return_msg);
	        		}
	        		for(int i=0;i<LineRead.size();i++)
	        			Gp.getTable(LineRead);
	        	}
				Log.d("TCP","C: Server send to me this message -->" + return_msg);
			} catch(Exception e){
				Log.e("TCP", "C: Send Msg Fail!",e);
			} finally {
				socket.close();
			}
		} catch(Exception e){
			Log.e("TCP", "C: Connect Fail!",e);
		}
	}
}