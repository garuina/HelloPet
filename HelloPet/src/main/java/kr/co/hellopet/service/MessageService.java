package kr.co.hellopet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hellopet.dao.MessageDAO;
import kr.co.hellopet.vo.MessageVO;

@Service
public class MessageService {

	@Autowired
	private MessageDAO dao;
	
	public List<MessageVO> selectMsgs(String uid){
		return dao.selectMsgs(uid);
	}
	
	public MessageVO selectMsg(String msgNo) {
		return dao.selectMsg(msgNo);
	}
	
	public int updateMsg(String msgNo) {
		return dao.updateMsg(msgNo);
	}
}
