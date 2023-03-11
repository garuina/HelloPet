package kr.co.hellopet.config;

import java.security.SecureRandom;

public class TestPassword {
	public static String tempPassword(int leng){
		int index = 0;
		char[] charSet = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
		};	//배열안의 문자 숫자는 원하는대로

		StringBuffer password = new StringBuffer();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < leng ; i++) {
			double rd = random.nextDouble();
			index = (int) (charSet.length * rd);
			
			password.append(charSet[index]);
			
			System.out.println("index::" + index + "	charSet::"+ charSet[index]);
		}
		
		return password.toString(); 
	    //StringBuffer를 String으로 변환해서 return 하려면 toString()을 사용하면 된다.
	}	
		
	public static void main(String[] args) {
		String pw = tempPassword(10);
		
		System.out.println("임시비밀번호::"+pw);
	}
}
