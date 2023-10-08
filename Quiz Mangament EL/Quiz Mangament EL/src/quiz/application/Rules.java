package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rules extends JFrame implements ActionListener{

    String email,name;
    JButton result, takeTest;
    private Connection conn;
    Rules(String email) {
        this.email=email;
    	uiHandler();
       // validateButtons();
    } 
    public void validateButtons()
    {
    	connect();
    	String getName="select * from users where email_id='"+email+"';";
    	Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("this only wrong sir");
			e1.printStackTrace();
		}
    	ResultSet rs = null;
		try {
			rs = stmt.executeQuery(getName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("could not get name ");
			e.printStackTrace();
		}
		//String sqlPwd="";
		try {
			while(rs.next())
			{
					name=rs.getString("name");
					//Sem.out.println("got pwd");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String sql="insert into testData values('"+email+"','"+name+"','0');";
    	//String sql="Update users set password='"+pwdField.getText()+"' where email_id='"+email+"';";
	       Statement stmt1;
		try {
		//	System.out.println("hi");
			stmt1 = conn.createStatement();
			
		
			 stmt1.executeUpdate(sql);
			 new Quiz(email);
			 setVisible(false);
			 
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
			System.out.println("not able to add ra");
		}
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
    
    private void uiHandler()
    {
    	 // this.email = email;
          getContentPane().setBackground(Color.WHITE);
          setLayout(null);
          
         // JLabel heading = new JLabel("Welcome " + name + " to Simple Minds");
        //  heading.setBounds(50, 20, 700, 30);
        //  heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
       //   heading.setForeground(new Color(30, 144, 254));
       //   add(heading);
          
          JLabel rules = new JLabel();
          rules.setBounds(20, 90, 700, 350);
          rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
          rules.setText(
              "<html>"+ 
                  "1. The test is 10 minutes long." + "<br><br>" +
                  "2. Retest will not be given,please answer at one go." + "<br><br>" +
                  "3. Each Question carries ten marks." + "<br><br>" +
                  "4. No negative marking." + "<br><br>" +
              "<html>"
          );
          add(rules);
          
          result = new JButton("View Result");
           result.setBounds(250, 500, 100, 30);
          result.setBackground(new Color(30, 144, 254));
          result.setForeground(Color.BLUE);
          result.addActionListener(this);
          add(result);
          
          takeTest = new JButton("Take Test");
          takeTest.setBounds(400, 500, 100, 30);
          takeTest.setBackground(new Color(30, 144, 254));
          takeTest.setForeground(Color.BLUE);
          takeTest.addActionListener(this);
          add(takeTest);
          
          setSize(800, 650);
          setLocation(350, 100);
          setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == takeTest) {
             validateButtons();
          //  setVisible(false);
            //new Quiz(email);
             
        }
        if(ae.getSource()==result)
        {
        	connect();
        	String sql="select * from testData where email_id='"+email+"';";
        	//String sql="Update users set password='"+pwdField.getText()+"' where email_id='"+email+"';";
    	       Statement stmt1;
    		try {
    		//	System.out.println("hi");
    			stmt1 = conn.createStatement();
    			ResultSet rs = stmt1.executeQuery(sql);
    			String score="";
    			while(rs.next())
    			{
    					score=rs.getString("score");
    					//System.out.println("got pwd");
    			}
    				
    			 new Score("",Integer.valueOf(score));
    			 setVisible(false);
    		}
    		catch(SQLException e1)
    		{
    			System.out.println(e1);
    			e1.printStackTrace();
    		}
        }
       
    }
    
    public static void main(String[] args) {
        new Rules("User");
    }
}
