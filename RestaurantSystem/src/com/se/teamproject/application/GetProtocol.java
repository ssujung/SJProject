package com.se.teamproject.application;

import java.util.*;

public class GetProtocol {
	private String in_Protocol;
	private String out_Protocol;
	public String parameter[];
	public String TableInfo[][]; // 스플릿된 테이블 정보 들어가있는 배열
	private boolean checkOk = false;
	ArrayList<String> Read = new ArrayList<String>();
	private int TableNum; // 들어올 테이블 갯수
	public int NowTableNum; // 현재 들어오고있는 테이블 갯수 번째의 정보?
	public int TotalTable; //총 테이블 갯수
	public void getMessage(String input){
		this.setIn_Protocol(input);
		System.out.println(getIn_Protocol() + " 가 들어왔다.");
		getProtocolHandler();
	}
	public void getTable(ArrayList<String> input){
		Read = input;
		for(int i=0;i < Read.size();i++){
			setIn_Protocol(Read.get(i));
			NowTableNum = i;
			getProtocolHandler();
		}
	}
	public void getProtocolHandler(){

		if(getIn_Protocol().startsWith("[JoinSuccess]")){
			parameter = split(getIn_Protocol(),13);
			setCheckOk(true);
		}
		else if(getIn_Protocol().startsWith("[JoinFalied]")){
			parameter = split(getIn_Protocol(),12);
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[LoginSuccess]")){
			parameter = split(getIn_Protocol(),14);
			setCheckOk(true);
		}
		else if(getIn_Protocol().startsWith("[LoginFail]")){
			parameter = split(getIn_Protocol(),11);
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[TableNum]")){
			parameter = split(getIn_Protocol(),10);
			TotalTable = Integer.parseInt(parameter[0]);
			TableNum = Integer.parseInt(parameter[1]);
			TableInfo = new String[TableNum][];
		}
		else if(getIn_Protocol().startsWith("[Table]")){
			TableInfo[NowTableNum] = split(getIn_Protocol(),7);
			System.out.println(TableInfo[NowTableNum][1]);
		}
		else if(getIn_Protocol().startsWith("[Overflow]")){
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[Booked]")){
			parameter = split(getIn_Protocol(),8);
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[ROk]")){
			parameter = split(getIn_Protocol(),5);
		}
		else if(getIn_Protocol().startsWith("[ModifyOK]")){
			parameter = split(getIn_Protocol(),10);
			setCheckOk(true);
		}
		else if(getIn_Protocol().startsWith("[ModifyFail]")){
			parameter = split(getIn_Protocol(),12);
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[MROk]")){
			parameter = split(getIn_Protocol(),6);
			setCheckOk(true);
		}
		else if(getIn_Protocol().startsWith("[MRFail]")){
			parameter = split(getIn_Protocol(),8);
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[CancelOK]")){
			parameter = split(getIn_Protocol(),10);
			setCheckOk(true);
		}
		else if(getIn_Protocol().startsWith("[ChangeTableOK]")){
			parameter = split(getIn_Protocol(),15);
			setCheckOk(true);
		}
		else if(getIn_Protocol().startsWith("[ChangeTableFail]")){
			parameter = split(getIn_Protocol(),17);
			setCheckOk(false);
		}
		else if(getIn_Protocol().startsWith("[WalkInOk]")){
			parameter = split(getIn_Protocol(),10);
		}
		else if(getIn_Protocol().startsWith("[CoversOfSelectedTable]")){
			parameter = split(getIn_Protocol(),23);
		}
		System.out.println("Split Complete!");
	}
	public String sendMessage(){
		return out_Protocol;
	}
	public String[] split(String input, int num){
		String[] Parameters = input.substring(num).split(",");
		return Parameters;
	}
	public boolean isCheckOk() {
		return checkOk;
	}
	public void setCheckOk(boolean checkOk) {
		this.checkOk = checkOk;
	}
	public String getIn_Protocol() {
		return in_Protocol;
	}
	public void setIn_Protocol(String in_Protocol) {
		this.in_Protocol = in_Protocol;
	}
}
