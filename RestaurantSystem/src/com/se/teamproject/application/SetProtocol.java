package com.se.teamproject.application;

import com.se.teamproject.client.TCPclient;
import com.se.teamproject.presentation.StartMain;

public class SetProtocol {
	private StringBuilder out_Message; // 서버에게 보낼 프로토콜 메세지
	private int ProtMsgCheck; // 프로토콜 인자 갯수 체크
	private int PMC_i;

	public SetProtocol(){
	}
	public void getWord(String input){
		if(PMC_i == 0)
			this.out_Message.append(input);
		else
			this.out_Message.append("," + input);
		PMC_i++;
		if(PMC_i > ProtMsgCheck){
			// 인자 갯수가 프로토콜에 필요한 갯수보다 더 많이 설정될 경우, 에러처리를 해주어야 한다.
			return;
		}
	}
	public void setProtocolHeader(int menu){
		PMC_i = 0;
		out_Message = new StringBuilder();
		switch(menu){
		case 1 :
			ProtMsgCheck = 4;
			out_Message.append("[AJoin]");
			break;//[AJoin] 4
		case 2 : 
			ProtMsgCheck = 4;
			out_Message.append("[SJoin]");
			break;//[SJoin] 4
		case 3 : 
			ProtMsgCheck = 2;
			out_Message.append("[Login]");
			break;//[Login] 2
		case 4 : 
			ProtMsgCheck = 1;
			out_Message.append("[Check]");
			break;//[Check] 1
		case 5 : 
			ProtMsgCheck = 2;
			out_Message.append("[CheckNum]");
			break;//[CheckNum] 2
		case 6 : 
			ProtMsgCheck = 7;
			out_Message.append("[Reservation]");
			break;//[Reservation] 7
		case 7 : 
			ProtMsgCheck = 0;
			out_Message.append("[OverflowYes]");
			break;//[OverflowYes] 0
		case 8 : 
			ProtMsgCheck = 0;
			out_Message.append("[CancelReservation]");
			break;//[CancelReservation] 0
		case 9 : 
			ProtMsgCheck = 3;
			out_Message.append("[Modify]");
			break;//[Modify] 3
		case 10 : 
			ProtMsgCheck = 7;
			out_Message.append("[MReservation]");
			break;//[MReservation] 7
		case 11 : 
			ProtMsgCheck = 3;
			out_Message.append("[Cancel]");
			break;//[Cancel] 3
		case 12 :
			ProtMsgCheck = 4;
			out_Message.append("[ChangeTable]");
			break;//[ChangeTable] 4
		case 13 :
			ProtMsgCheck = 7;
			out_Message.append("[WalkIn]");
			break;//[WalkIn] 7
		case 14 : 
			ProtMsgCheck = 1;
			out_Message.append("[CheckCoversOfSelectedTable]");
			default : System.out.println("해당하는 프로토콜 헤더가 존재하지 않습니다.");
		}
	}
	public void sendProtocol(){
		PMC_i = 0;
		TCPclient connection = new TCPclient(out_Message);
		connection.run();
	}
}
