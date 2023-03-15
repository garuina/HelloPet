package kr.co.hellopet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hellopet.dao.AdminDAO;
import kr.co.hellopet.vo.MedicalVO;

/* 
 *  날짜 : 2023/03/14
 *  이름 : 김채영
 *  설명 : HelloPet Admin 페이지 기능구현
 */
@Service
public class AdminService {
	
	@Autowired
	private AdminDAO dao;

	public MedicalVO selectAdmin(String uid) {
		return dao.selectAdmin(uid);
	}
	public int updateAdmin(MedicalVO vo) {
		return dao.updateAdmin(vo);
	}
	
}
