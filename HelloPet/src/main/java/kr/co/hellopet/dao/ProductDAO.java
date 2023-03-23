package kr.co.hellopet.dao;
/*
 * 날짜 : 2023-03-23
 * 이름 : 장인화
 * 내용 : productDAO 작성
 * 
 * */


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.hellopet.vo.Cate1VO;
import kr.co.hellopet.vo.Cate2VO;
import kr.co.hellopet.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	
	
	// cate 불러오기
	public List<Cate1VO> Cate1();
	public List<Cate2VO> Cate2();
	
	public List<Cate2VO> SelectCate2(String cate1);
	public int SelectCountTotal(String cate1, String cate2);
	
	// product 불러오기
	public List<ProductVO> SelectProduct(String cate1, String cate2, int start);
	
	
	
	
}
