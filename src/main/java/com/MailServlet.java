package com;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String fromaddress = req.getParameter("fromaddress");
        String toaddress = req.getParameter("toaddress");
        String subject = req.getParameter("subject");
        String body = req.getParameter("body");


        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromaddress, "Test Mail"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress, "Hello User"));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            resp.getWriter().println("Mail sent successfully");
				resp.getWriter().println("<html><a href=\"http://13.sd03is032.appspot.com\"> Send One More Mail </a></html>");          
        } catch (AddressException e) {
            resp.getWriter().println(e);
        } catch (MessagingException e) {
            resp.getWriter().println(e);


        }

    }
}
