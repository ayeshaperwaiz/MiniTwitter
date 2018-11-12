package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

import Util.MailUtilEmail;
import javax.mail.*;
import business.User;

import dataaccess.UserDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.security.SecureRandom;
import java.util.Base64;

@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    String fullname;
    String emailAddress;
    String birthdate;
    String username; 
    String password; 
    String questionNo; 
    String answer; 
    String confirm;
    String message; 
    
    boolean success = false; 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        
        String action = request.getParameter("action"); 
        HttpSession session = request.getSession();
        if(action == null){
            action = "join"; 
        }
        
        String url = "/signup.jsp"; 
        if (action.equals("join")){
            url = "/signup.jsp"; 
        }

        if(action.equals("signup")){
            fullname = request.getParameter("fullname");
            emailAddress = request.getParameter("emailAddress");
            birthdate = request.getParameter("birthdate");
            username = request.getParameter("username");
            password = request.getParameter("password"); 
            confirm = request.getParameter("confirm");
            questionNo = request.getParameter("questionNo");
            answer = request.getParameter("answer"); 
            
        // validate the parameters so it's not empty (server side)
        if (fullname.isEmpty() || username.isEmpty()
                    || emailAddress.isEmpty() || password.isEmpty() || birthdate.isEmpty() || answer.isEmpty()) {
                message = "please fill out all the fields";
                session.setAttribute("message", message);
                url = "/signup.jsp";
            }  
            
            else if (!password.equals(confirm)) {
                message = "Please make sure passwords are the same";
                session.setAttribute("message", message);
                url = "/signup.jsp";
            }

            else  {
                
                User user; 
                user = new User(fullname, emailAddress, birthdate, username, password, questionNo, answer);
                try { 
                    UserDB.insert(user);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                    success = true; 
                    session.setAttribute("success",success); 
                    session.setAttribute("user", user);
                    url = "/home.jsp";

            }       

                    getServletContext()
                                .getRequestDispatcher(url)
                                .forward(request, response);
            
        }    
        
         if(action.equals("login"))
        {
            emailAddress = request.getParameter ("emailAddress"); 
            password = request.getParameter("password");
            User user = UserDB.search(emailAddress); 
            session.setAttribute("user", user); 
            
            url = "/login.jsp"; 
            
            if(user != null){
                if(user.getEmail().equals(emailAddress) && user.getPassword().equals(password)){
                    request.setAttribute("user", user); 
                    session.setAttribute("user", user); 
                    
                    success = true; 
                    session.setAttribute("success",success); 
                    url = "/home.jsp"; 
                }
                else{
                    String message; 
                    message = "Email/Password is incorrect"; 
                    session.setAttribute("message", message);
                    url = "/login.jsp";
                
                }
            }
            
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
            
        }
         
        if(action.equals("forgot")){
            emailAddress = request.getParameter ("emailAddress"); 
            User user = UserDB.search(emailAddress); 
            session.setAttribute("user", user); 
            
            url = "/forgotpassword.jsp"; 
            
            if(user != null){
                if(user.getEmail().equals(emailAddress)){
                    request.setAttribute("user", user); 
                    session.setAttribute("user", user); 
                    
                    //success = true; 
                    //session.setAttribute("success",success); 
                    
                    String newPassword = generatePassword(4);
                    user.setPassword(newPassword);
                    UserDB.update(user); 
                    
                    sendEmail(user.getEmail(), user.getPassword(), user.getFullName());
                    
                    
                    String message; 
                    message = "Please check your email for new password."; 
                    session.setAttribute("message", message);
                    url = "/login.jsp"; 
                }
                else{
                    String forgotMessage; 
                    forgotMessage = "User does not exist!"; 
                    session.setAttribute("forgotMessage", forgotMessage);
                    url = "/forgotpassword.jsp";
                
                }
            }
            
            if(action.equals("update")){
                fullname = request.getParameter("fullname");
                emailAddress = request.getParameter("emailAddress");
                birthdate = request.getParameter("birthdate");
                username = request.getParameter("username");
                password = request.getParameter("password"); 
                confirm = request.getParameter("confirm");
                questionNo = request.getParameter("questionNo");
                answer = request.getParameter("answer"); 
 
                user = new User();
                user.setFullName(fullname);        
                user.setBirthDate(birthdate);        
                user.setQuestionNo(questionNo);
                user.setAnswer(answer);
                user.setPassword(password);
                UserDB.update(user);
                
                request.setAttribute("user", user); 
                url = "/signup.jsp";
            }
            
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
            
            
        } 
  
    }
    
    protected String generatePassword(int bytes) {
        
        Random r = new SecureRandom();
        byte[] tempBytes = new byte[bytes];
        r.nextBytes(tempBytes);
        return Base64.getEncoder().encodeToString(tempBytes);

    }
    
     private void sendEmail(String email, String password, String name) {
    
        String to = email;
        String from = "MiniTwitter";
        String subject = "New Password";
        String body = "Hi " + name + ",\n\n" +
        "We received a request to reset your MiniTwitter password.\n" + 
        "Here is your new password: \n\n" +
        password +
        "\n\nMiniTwitter Team"; 
        boolean isBodyHTML = false;

        try
        {
            MailUtilEmail.sendMail(to, from, subject, body, isBodyHTML);
        }
        catch (MessagingException e)
        {
            this.log(
                "Unable to send email. \n" +
                "Here is the email you tried to send: \n" +
                "=====================================\n" +
                "TO: " + email + "\n" +
                "FROM: " + from + "\n" +
                "SUBJECT: " + subject + "\n" +
                "\n" +
                body + "\n\n");
        }            
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doPost(request, response);
        
    }


   /* @Override
    public String getServletInfo() {
        return "Short description";
    } */

}