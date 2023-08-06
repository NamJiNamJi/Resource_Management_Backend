package com.douzone.wehago.service;

import com.douzone.wehago.common.exception.BusinessException;
import com.douzone.wehago.common.exception.ErrorCode;
import com.douzone.wehago.dto.MailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String wehagoEmail;

    public void mailSend(MailDTO mailDTO) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            // 발신자
            mimeMessage.setFrom(new InternetAddress(wehagoEmail, "WEHAGO_ADMIN"));

            // 수신자
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, mailDTO.getEmpEmail());

            // 제목
            mimeMessage.setSubject("WEHAGO 구성원 초대");

            // 발송 내용 및 형식
            mimeMessage.setText(setMessage(mailDTO),"utf-8","html");

            // 메일 발송
            javaMailSender.send(mimeMessage);

        } catch(Exception e) {
            e.printStackTrace();
            throw new BusinessException("메일 발송 실패", ErrorCode.MAIL_SEND_FAIL);
        }
    }

    private String setMessage(MailDTO mailDTO) {
        Context context = new Context();
        context.setVariable("empEmail", mailDTO.getEmpEmail());
        context.setVariable("empName", mailDTO.getEmpName());
        context.setVariable("empPosition", mailDTO.getEmpPosition());
        context.setVariable("copSeq", mailDTO.getCopSeq());
        context.setVariable("userSeq", mailDTO.getUserSeq());

        // 회사명 넣을라면 회사번호로 db 조회 하거나 프론트에서 아예 받아올 수 있는지 확인필요
        // url 쿼리스트링으로 넣어줄 수 있음 회사 일련번호랑 회원 번호로 만들어서 서비스 시작하기 누르면 연결될 수 있도록 하면됨
        // 근데 그..그러면 그 회원가입 그쪽 프론트 폼나오는 화면 보내주고, 쿼리스트링 받는 백엔드 api 만들어야함
        // 좀 더 생각을 더하면 로그인 되어있을 경우 회원가입폼으로 넘어가는거 막아야 할 듯 ???

        return templateEngine.process("mail", context);
    }

}
