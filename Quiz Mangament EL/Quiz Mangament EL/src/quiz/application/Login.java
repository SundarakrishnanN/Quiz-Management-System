package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
 
    JButton signIn,signUp,forgotpwd;
    JTextField tfemail,tfpassword;
    private Connection conn;
    
    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
      // getContentPane().setSize(500,500);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);
        
        JLabel heading = new JLabel("Quizzz Beee");
        heading.setBounds(770, 60, 300, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 36));
        heading.setForeground(new Color(0, 0, 0));
        add(heading);
        
        JLabel name = new JLabel("Enter your email id");
        name.setBounds(810, 150, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(0,0,0));
        add(name);

        JLabel password = new JLabel("Enter your password");
        password.setBounds(810, 240, 300, 20);
        password.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        password.setForeground(new Color(0,0,0));
        add(password);

        tfemail = new JTextField();
        tfemail.setBounds(735, 200, 300, 25);
        tfemail.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfemail);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(735, 270, 300, 25);
        tfpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfpassword);
        
        signIn = new JButton("Sign In");
        signIn.setBounds(735, 320, 120, 25);
        signIn.setBackground(new Color(30, 144, 254));
        signIn.setForeground(Color.BLACK);
        signIn.addActionListener(this);
        add(signIn);

        forgotpwd = new JButton("Forgot Password");
        forgotpwd.setBounds(735, 350, 120, 25);
        forgotpwd.setBackground(new Color(30, 144, 254));
        forgotpwd.setForeground(Color.BLACK);
        forgotpwd.addActionListener(this);
        add(forgotpwd);
        
        signUp = new JButton("Sign Up");
       signUp.setBounds(915, 320, 120, 25);
        signUp.setBackground(new Color(30, 144, 254));
        signUp.setForeground(Color.BLACK);
        signUp.addActionListener(this);
        add(signUp);
        
        setSize(1200, 500);
        setLocation(200, 150);
        setResizable(false);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signIn) {
            String emailID = tfemail.getText();
            verify(emailID,tfpassword.getText());
                        
        } else if (ae.getSource() == signUp) {
        	setVisible(false);
        	new signUp();
        	
        }
        if(ae.getSource()==forgotpwd)
        {
        	setVisible(false);
            String name = tfemail.getText();
        	new forgotpwd();
        }
    }
    public void verify(String em,String pwd)
    {
    	connect();
              String sql="SELECT * from users where email_id=\""+em+"\";";
       Statement stmt;
	try {
	//	System.out.println("hi");
		stmt = conn.createStatement();
		
	
		ResultSet rs = stmt.executeQuery(sql);
		String sqlPwd="";
		while(rs.next())
		{
				sqlPwd=rs.getString("password");
				//System.out.println("got pwd");
		}
			

		if(sqlPwd.equals(pwd))
		{
		//	System.out.println("valid login0");
			setVisible(false);
            new Rules(em);
            

		}
		else
		{
			//System.out.println("invalid login");

			invalidLogin();
		}
	} catch (SQLException e) {
		//System.out.println("exception");
		// TODO Auto-generated catch block
		invalidLogin();
		
	} 
      

       
    }
    public void invalidLogin()
    {

        JLabel invalid = new JLabel("Invalid Email ID or password");
        invalid.setBounds(735, 400, 300, 50);
        invalid.setFont(new Font("Times New Roman", Font.BOLD, 18));
        invalid.setForeground(new Color(255,0,0));
        add(invalid);
        invalid.repaint();
       // setVisible(true);
    }
    public void connect()
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
    
    public static void main(String[] args) {
        new Login();
    }
}
