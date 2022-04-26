package ru.itis.hotel.util;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender mailSender;
    private final Configuration configuration;

    @Value("${spring.mail.username}")
    private String from;


    public void sendMail(String to, String subject, String templateName, Map<String, String> data) {

        MimeMessagePreparator preparator = null;
        try {
            try {
                final String mailText = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName, "UTF-8"), data);
                preparator = mimeMessage -> {
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                    messageHelper.setSubject(subject);
                    messageHelper.setText(mailText, true);
                    messageHelper.setTo(to);
                    messageHelper.setFrom(from);
                };
                mailSender.send(preparator);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
