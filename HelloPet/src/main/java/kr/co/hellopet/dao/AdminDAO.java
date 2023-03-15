package kr.co.hellopet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.hellopet.vo.MedicalVO;

/* 
 *  날짜 : 2023/03/14
 *  이름 : 김채영
 *  설명 : HelloPet Admin 페이지 기능구현
 */
@Mapper
@Repository
public interface AdminDAO {
	
	public MedicalVO selectAdmin(String uid);
	public int updateAdmin(MedicalVO vo);

}
