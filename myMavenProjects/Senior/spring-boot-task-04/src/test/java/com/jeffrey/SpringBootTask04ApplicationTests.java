package com.jeffrey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringBootTask04ApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;


    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("<h1>今晚开会</h1>");
        message.setText("具体时间7:30");
        message.setTo("979874648@qq.com");
        message.setFrom("664490254@qq.com");
        javaMailSender.send(message);
    }

    @Test
    public void test02() throws Exception {
        // 创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("<h1>今晚开会</h1>");
        mimeMessageHelper.setText("<b style='color:red'>今天七点三十开会</b>", true);
        mimeMessageHelper.setTo("664490254@qq.com");
        mimeMessageHelper.setFrom("664490254@qq.com");
        // 上传附件
        mimeMessageHelper.addAttachment("test_1.jpg", new File("/Users/jeffrey/IdeaProjects/SpringBootLearn/Note/images/1721595151446_.pic_hd.png"));
        mimeMessageHelper.addAttachment("test_2.jpg", new File("/Users/jeffrey/IdeaProjects/SpringBootLearn/Note/images/concrete-bindings.png"));
        javaMailSender.send(mimeMessage);


    }

}
