package kr.co.hellopet.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.hellopet.vo.MemberVO;

@Mapper
@Repository
public interface MyDAO {
	
	public MemberVO selectUser(@Param("uid") String uid);
	
	public void updateInfoModify(@Param("name") String name, @Param("email") String email, @Param("nick") String nick, @Param("hp") String hp, @Param("uid") String uid);

}
