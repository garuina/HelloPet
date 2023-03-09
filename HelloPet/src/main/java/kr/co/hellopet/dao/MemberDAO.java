package kr.co.hellopet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hellopet.vo.Api_HospitalVO;
import kr.co.hellopet.vo.MedicalVO;
import kr.co.hellopet.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	
	// 일반회원등록
	public void insertMember(MemberVO vo);
	
	// 병원·약국 등록
	public void insertMedical(MedicalVO vo);
	
	public MemberVO selectMember(String uid);
	public List<MemberVO> selectMembers();
	public void updateMember(MemberVO vo);
	public void deleteMember(String uid);
	
	// register Medical 검색기능
	public List<Api_HospitalVO> selectName(@Param("trial") String trial, @Param("county") String county, @Param("name") String name);
	
	// uid 중복체크
	public int countUid(String uid);
	
	// hp 중복체크
	public int countHp(String hp);
	
	// email 중복체크
	public int countEmail(String email);
	
	// nick 중복체크
	public int countNick(String nick); 
}
