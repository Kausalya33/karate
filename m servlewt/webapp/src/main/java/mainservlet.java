
import java.io.IOException; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.*;
import java.net.Authenticator;
import java.util.*; 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@WebServlet("/mainservlet")
@MultipartConfig(location="C://TEMP")
public class mainservlet extends HttpServlet {
   mainservlet javaEmail=null;
 public void init() throws ServletException {}
     public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
       {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
           String toMail = request.getParameter("email");
           String filename=request.getParameter("filename");

          request.getPart("content").write("C://TEMP/"+filename+".txt");

             if(request.getPart("content") !=null){
        	 out.write("<h3>File uploaded successfully</h3>");}
           String cc=request.getParameter("cc");
           String[] recipientList = cc.trim().split(",");
           //String name="hello" + request.getParameter("name");

          try {
           mainservlet javaEmail = new mainservlet();
          
          for(String dd: recipientList)
          {

         javaEmail.sendEmail(toMail,dd);
           }
            
            out.println("Process Completed\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name="event registered successfully";
        response.getWriter().write(name);
         RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
        rd.include(request, response); 

    }

        public static void sendEmail(String to,String ddd)
{
final String username = "divyadharshini.g@kggroup.com";
final String password = "dd.nayagam";
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "false");
props.put("mail.smtp.host", "webmail.kggroup.com");
props.put("mail.smtp.port", "25");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("divyadharshini.g@kggroup.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(to));
message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ddd));
message.setSubject("A testing mail header !!!");
message.setText("Dear Mail Crawler,"
+ "\n\n No spam to my email, please!");
message.setText("Dear Mail Crawler,"
+ "\n\n No spam to my email, please!");


BodyPart messageBodyPart = new MimeBodyPart();
messageBodyPart.setText("This is message body");

 MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
        
         String htmlText = "<H1>Hello</H1><img src=\"http://www.bharatstudent.com/events/events_homebanner.jpg\">";
         messageBodyPart.setContent(htmlText, "text/html");
         // add it
         multipart.addBodyPart(messageBodyPart);

         // second part (the image)
         messageBodyPart = new MimeBodyPart();
          DataSource fds = new FileDataSource("D://eveimg.jpg");
          messageBodyPart.setDataHandler(new DataHandler(fds));
           
        // multipart.addBodyPart(messageBodyPart);

       //  messageBodyPart.setDataHandler(new DataHandler(fds));
      
         messageBodyPart.setHeader("Content-ID", "<image>");
         messageBodyPart.setText("you have been registered successfully");
         multipart.addBodyPart(messageBodyPart);

         // put everything together
         message.setContent(multipart);

         String filename="C://TEMP/Eventdetail.txt";

           DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
        
        multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart);
        // message.setText(" you have been registered successfully");
       
Transport.send(message);
System.out.println("Done");


}
catch (MessagingException e)
{
throw new RuntimeException(e);

}
}

}