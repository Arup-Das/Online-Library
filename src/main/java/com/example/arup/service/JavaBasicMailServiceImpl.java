package com.example.arup.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.arup.entity.UserInfo;
import com.example.arup.repository.UserInfoRepository;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class JavaBasicMailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
    private Configuration freemarkerConfig;
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Value("${mail.verificationapi.url}")
	private String verifcationUrlPrefix;
	
	public boolean sendMail(String toEmail, String subject, String body) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
        return true;
	}
	
	public boolean sendVerificationMail(String toEmail, String verificationCode) {
		UserInfo user = userInfoRepository.findByEmail(toEmail).orElseThrow(() -> new UsernameNotFoundException(toEmail+" not found") );
        String subject = "Please verify your email";
        String body = "";
        try {
            Template t = freemarkerConfig.getTemplate("email-verification.ftl");
            Map<String, String> map = new HashMap<>();
            map.put("User", user.getFirstName());
            map.put("VERIFICATION_URL", verifcationUrlPrefix + verificationCode);
            body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sendMail(toEmail, subject, body);
    }
}
