package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.mapper.EmailMapper;
import com.example.demo.util.CommonUtils;
import com.example.demo.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailMapper emailMapper;
    private final JavaMailSender javaMailSender;
    private final RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String senderEmail;     // 구글 SMTP 에 등록된 내이메일

    /*이메일 전송*/
    public void sendEmail(String email) throws MessagingException, UnsupportedEncodingException {
        if(redisUtil.existData(email)){
            redisUtil.deleteData(email);
        }
        javaMailSender.send(createEmail(email));
    }

    /*이메일 폼 생성*/
    public MimeMessage createEmail(String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        String authCode = createAuthCode();
        String msg = "";
        msg += "<div><h1>MUNG LA HOTEL AUTH CODE</h1>";
        msg += "<h3>아래에 인증코드를 홈페이지에 작성하세요</h3>";
        msg += "<h5>인증코드 : "+authCode+"</h5>";
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setFrom(new InternetAddress(senderEmail,"MUNG LA 운영진"));
        message.setSubject("MUNG LA HOTEL AUTH CODE");
        message.setText(msg,"UTF-8","html");

        redisUtil.setDataExpire(email,authCode, 60 * 30L);

        return message;
    }

    /*인증코드 생성*/
    public String createAuthCode(){
        StringBuilder authCode = new StringBuilder();
        Random ramdom = new Random();
        for(int i  = 0; i<7; i++) {
            authCode.append(ramdom.nextInt(10)); //각자리마다  0~9 까지 랜덤숫자로 7자리 생성
        }
        return authCode.toString();
    }

    /*인증코드 검증*/
    public void validateAuthCode(String email, String authCode){
        String findAuthCodeByEmail = redisUtil.getData(email);
        CommonUtils.throwRestCustomExceptionIf(!findAuthCodeByEmail.equals(authCode), ErrorCode.FAIL_AUTHENTICATION);
    }

}
