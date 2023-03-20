package kr.co.hellopet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hellopet.vo.AdminReserveVO;
import kr.co.hellopet.vo.MedicalVO;

/* 
 *  날짜 : 2023/03/14
 *  이름 : 김채영
 *  설명 : HelloPet Admin 페이지 기능구현
 */
@Mapper
@Repository
public interface AdminDAO {
	
	/* 현재 로그인한 관리자 가져오기*/
	public MedicalVO selectAdmin(String uid);
	
	/* 관리자 정보 수정 */
	public int updateAdmin(MedicalVO vo);
	
	/* 예약내역 */
	public List<AdminReserveVO> selectReserves(@Param("start") int start, String medNo);
	public int selectCountTotal(String medNo);
	public AdminReserveVO selectReserve(@RequestParam("revNo") Integer revNo);
	
	/* 예약 상태 변경 */
	public int updateConfirm(int revNo);
	public int updateReject(AdminReserveVO vo);
}
