package com.example.demo.dto.request.seller;

import com.example.demo.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqSellerAdd {
	
	private Long user_sid;
	
	@NotBlank(message = "이메일은 필수입니다~!")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "이메일 형식에 맞게 써주세요!")
	private String user_email;			// 유저 이메일 
	
	@ NotBlank(message = "회원명을 입력해주세요! (공백 불가능)")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣_-]{2,6}$",message = "회원명은 2~6자리여야하고 숫자 한.영어 가능, 특수기호는 -, _만 사용가능합니다")
	private String user_name;			// 유저명
	
	@NotBlank(message = "비밀번호는 필수입니다, 입력해주세요~!")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;				// 유저 비밀번호
	
	@NotBlank(message = "비밀번호 재확인을 적지않았어요~!")
	private String password2;			// 유저 비밀번호 재확인
	
	
	private String business_number; 	// 판매자 사업자 번호
	
	private String resident_number;	// 판매자 주민등록번호
	
	private String hotel_name;	//  호텔명 
	
	private final Role role = Role.SELLER; // 판매자 회원 권한
}
