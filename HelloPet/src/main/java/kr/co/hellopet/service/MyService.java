package kr.co.hellopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hellopet.dao.MyDAO;
import kr.co.hellopet.vo.MemberVO;

@Service
public class MyService {
	
	@Autowired
	private MyDAO dao;
	
	public MemberVO selectUser(String uid) {
		return dao.selectUser(uid);
	}
	
	public void updateInfoModify(String name, String email, String nick, String hp, String uid) {
		dao.updateInfoModify(name, email, nick, hp, uid);
	}

}
