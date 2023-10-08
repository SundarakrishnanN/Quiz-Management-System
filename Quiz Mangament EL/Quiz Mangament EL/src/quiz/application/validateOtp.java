package quiz.application;

import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
public class validateOtp extends JFrame implements ActionListener{

	int otp;
	String email;
	JTextField otpField;
	JTextField pwdField;
	JButton savePwd;
	ResultSet rs=null;
	 private Connection conn;
	validateOtp(String email)
	{
		this.email=email;		
		//this.UIHandler();
		this.validateEmailId();
		//this.UIHandler();
		//this.emailer();
	}
	 public boolean verify()
	    {
	    	connect();
	              String sql="SELECT * from users where email_id=\""+email+"\";";
	       Statement stmt;
		try {
		//	System.out.println("hi");
			stmt = conn.createStatement();
			
		
			 rs = stmt.executeQuery(sql);
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	    }
		void connect()
		{

	        try
	        {
	        	 Class.forName("com.mysql.cj.jdbc.Driver");	
	    		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/QuizEL","root","password");
	      //  System.out.println("connection success");
	        }
	        catch(ClassNotFoundException | SQLException e)
	        {
	     	   e.printStackTrace();
	        }
	        
		}
	void validateEmailId()
	{
		boolean validEmail=false;
		validEmail=verify();
		//CHECK EMAIL HERE
		if(validEmail)
		{
		this.UIHandler();
		this.emailer();
		}
		else
		{
			getContentPane().setBackground(Color.WHITE);
	        setLayout(null);
	        setSize(800, 650);
	        setLocation(350, 100);
	        setVisible(true);
	       
	        JLabel heading = new JLabel("Email id not registered");
	        
	        heading.setBounds(50, 20, 700, 30);
	        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));
	        heading.setForeground(new Color(0, 0, 0));
	        add(heading);
	        int delay = 2000;//specify the delay for the timer
	        Timer timer = new Timer( delay, e -> {
	          //The following code will be executed once the delay is reached
	          new Login();
	          setVisible(false);
	        } );
	        timer.setRepeats( false );//make sure the timer only runs once
	        timer.start();
	      
		}
	}
	public void UIHandler()
	{
		getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
       
        JLabel heading = new JLabel("Enter OTP and new password");
        
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 28));
        heading.setForeground(new Color(0, 0, 0));
        add(heading);

        JLabel otpLabel = new JLabel("Enter OTP");
        otpLabel.setBounds(100,75, 300, 20);
       otpLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
       otpLabel.setForeground(new Color(30, 144, 254));
        add(otpLabel);
        otpField = new JTextField();
        otpField.setBounds(100,120,300,30);
        otpField.setFont(new Font("Times New Roman", Font.BOLD, 20));
       otpField.setForeground(new Color(30, 144, 254));
        add(otpField);
        JLabel password = new JLabel("Enter password");
        password.setBounds(100,170, 300, 20);
       password.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
       password.setForeground(new Color(30, 144, 254));
        add(password);
        pwdField = new JPasswordField();
       pwdField.setBounds(100,215,300,30);
        pwdField.setFont(new Font("Times New Roman", Font.BOLD, 20));
       pwdField.setForeground(new Color(30, 144, 254));
        add(pwdField);
        savePwd=new JButton("Save New Password");
        savePwd.setBounds(100,260,150,30);
        savePwd.setBackground(new Color(30, 144, 254));
        savePwd.setFont(new Font("Times New Roman",Font.BOLD,12));
        savePwd.setForeground(Color.BLUE);
        savePwd.addActionListener(this);
        add(savePwd);
        
	}

	void emailer()
	{
	
		otp=(int)(Math.random()*1000);
        String message = "Hello,\nYour OTP is "+otp+"\nIf you didn't request this,you can ignore the message.\nThank you,\nYour Quiz App";

		String subject = "Automatic Email : Confirmation";
		String to = email;
		String from = "terrifictiger05@gmail.com";
		System.out.println(to);
		this.sendEmail(message,subject,to,from);
	}
private void sendEmail(String message, String subject, String to, String from) {
		
		//Variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("terrifictiger05@gmail.com", "lpcg yyuf stgb udlc");
			}
			
			
			
		});
		
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding text to message
		m.setText(message);
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		System.out.println("Sent success...................");
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==savePwd)
	{
        
		if(otpField.getText().equals(Integer.toString(otp)))
		{
			String sql="Update users set password='"+pwdField.getText()+"' where email_id='"+email+"';";
		       Statement stmt;
			try {
			//	System.out.println("hi");
				stmt = conn.createStatement();
				
			
				 stmt.executeUpdate(sql);
				 new Login();
				setVisible(false);
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
		}
				
	}

	
}
}
