package cn.mylife.utils.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author yangjie
 * 发送邮件
 */
public class SendEmail {
    public static void SendEmail(String emailTo, String messages) {
        // 收件人电子邮箱
//        String to = "18202975766@163.com";

        // 发件人电子邮箱
        String from = "1483956887@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        //QQ 邮件服务器
        String host = "smtp.qq.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");

        //如果是ssl连接，需要设置这个
        properties.setProperty("mail.smtp.ssl.enable", "true");

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("1483956887@qq.com", "elwicihgcbpsgccf");
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailTo));

            // Set Subject: 头部头字段
            message.setSubject("温馨提示:  邮箱验证");

            // 设置消息体
            message.setText("您的邮箱验证码为:" + messages + ", 请勿告诉他人, 若非本人操作, 请忽略此邮件！");

            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
