package quiz.application;

import javax.swing.*;

import loginPackage.emailer;

import java.awt.*;
import java.awt.event.*;

public class forgotpwd extends JFrame implements ActionListener{

    String name;
    JButton sendOtp;
    JTextField tfemailid;

    forgotpwd()
    {
    	getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
       
        JLabel heading = new JLabel("Welcome,please enter your email id to get otp");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        
        JLabel emailid = new JLabel("Enter your registered email id");
        emailid.setBounds(100,75, 300, 20);
        emailid.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
       emailid.setForeground(new Color(30, 144, 254));
        add(emailid);
        tfemailid = new JTextField();
        tfemailid.setBounds(100,120,300,30);
        tfemailid.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfemailid.setForeground(new Color(30, 144, 254));
        add(tfemailid);
        sendOtp=new JButton("Send OTP");
        sendOtp.setBounds(100,150,90,30);
        sendOtp.setBackground(new Color(30, 144, 254));
        sendOtp.setFont(new Font("Times New Roman",Font.BOLD,12));
        sendOtp.setForeground(Color.BLUE);
        sendOtp.addActionListener(this);
        add(sendOtp);
       
         
    }
    public void actionPerformed(ActionEvent ae) {
    	  if(ae.getSource()==sendOtp)
          {
          	setVisible(false);
              String ID = tfemailid.getText();
             
          	new validateOtp(ID);
          }
    }
    
    public static void main(String[] args) {
        new forgotpwd();
}
}