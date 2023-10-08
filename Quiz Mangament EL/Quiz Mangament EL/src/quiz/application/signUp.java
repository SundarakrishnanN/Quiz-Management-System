package quiz.application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class signUp extends JFrame implements ActionListener{

	JLabel name,email,password;
	JTextField tname,temail,tpassword;
	
	JButton createAcc;
	 private Connection conn;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==createAcc)
		{
			verify(temail.getText(),tpassword.getText(),tname.getText());
		}
		// TODO Auto-generated method stub
		
	}
	signUp()
	{
		createUI();
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
	 public void verify(String em,String pwd,String nm)
	    {
	    	connect();
	              String sql="Insert into users values('"+em+"','"+pwd+"','"+nm+"','S');";
	              System.out.println(sql);
	              
	       Statement stmt;
		try {
		//	System.out.println("hi");
			stmt = conn.createStatement();
			
	         

		
			 stmt.executeUpdate(sql);
			 AccountCreated();
			 int delay = 3000;//specify the delay for the timer
		        Timer timer = new Timer( delay, e -> {
		          //The following code will be executed once the delay is reached
		         // new Login();
		        	new Login();
					setVisible(false);
		        } );
		        timer.setRepeats( false );//make sure the timer only runs once
		        timer.start();
			
			//invalidLogin();
			
		} catch (SQLException e) {
			//System.out.println("exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
			invalidLogin();
			
		} 
	      

	       
	    }
	 public void AccountCreated()
	 
	 {
		 JLabel invalid = new JLabel("Account Successfully Created");
	        invalid.setBounds(280,250, 600, 50);
	        invalid.setFont(new Font("Times New Roman", Font.BOLD, 25));
	        invalid.setForeground(new Color(0,100,0));
	        add(invalid);
	        invalid.repaint();
	 }
	 public void invalidLogin()
	 {

	        JLabel invalid = new JLabel("Invalid Login");
	        invalid.setBounds(300,250, 300, 50);
	        invalid.setFont(new Font("Times New Roman", Font.BOLD, 25));
	        invalid.setForeground(new Color(255,0,0));
	        add(invalid);
	        invalid.repaint();
	 }
	void createUI()
	{
		setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        getContentPane();
        setLayout(null);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(350-50-20, 100);
        add(name);
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(450-50-20, 100);
        add(tname);
        email = new JLabel("Email ID");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(350-50-20, 140);
        add(email);
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(450-50-20, 140);
        add(temail);
        add(tname);
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(350-50-20, 180);
        add(password);
        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(450-50-20, 180);
        add(tpassword);
        createAcc = new JButton("Create Account");
        createAcc.setBounds(400, 215, 120, 25);
         createAcc.setBackground(new Color(30, 144, 254));
         createAcc.setForeground(Color.BLACK);
         createAcc.addActionListener(this);
         add(createAcc);
        setVisible(true);
        
	}

}
