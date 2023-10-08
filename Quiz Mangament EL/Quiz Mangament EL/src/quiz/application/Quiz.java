package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Quiz extends JFrame implements ActionListener {
    
    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit,back;
    private Connection conn;
    public static int timer = 600;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;
    
    String email;
    
    Quiz(String em) {
        email= em;
        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);
        
        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);
        
        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);
        
        questions[0][0] = "Number of primitive data types in Java are?";
        questions[0][1] = "6";
        questions[0][2] = "7";
        questions[0][3] = "8";
        questions[0][4] = "9";

        questions[1][0] = "What is the size of float and double in java?";
        questions[1][1] = "32 and 64";
        questions[1][2] = "32 and 32";
        questions[1][3] = "64 and 64";
        questions[1][4] = "64 and 32";

        questions[2][0] = "Automatic type conversion is possible in which of the possible cases";
        questions[2][1] = "float to int";
        questions[2][2] = "double to float";
        questions[2][3] = "short to int";
        questions[2][4] = "long to short";

        questions[3][0] = "Which of the following is not used with respect to exception handling";
        questions[3][1] = "dodge";
        questions[3][2] = "catch";
        questions[3][3] = "throw";
        questions[3][4] = "try";

        questions[4][0] = "Which of the following data structures are not dynamic";
        questions[4][1] = "ArrayList";
        questions[4][2] = "Array";
        questions[4][3] = "Vector";
        questions[4][4] = "List";

        questions[5][0] = "Which of the following is not possible with inheritance in java";
        questions[5][1] = "Single Inheritance";
        questions[5][2] = "Multiple Inheritance";
        questions[5][3] = "Multilevel Inheritance";
        questions[5][4] = "Heirarchial Inheritance";

        questions[6][0] = "Which keyword is used for accessing the features of a package?";
        questions[6][1] = "import";
        questions[6][2] = "package";
        questions[6][3] = "extends";
        questions[6][4] = "export";

        questions[7][0] = "In java, JVM stands for?";
        questions[7][1] = "Java Volatage Machine";
        questions[7][2] = "Java Vin Machine";
        questions[7][3] = "JSP Virtual Mode";
        questions[7][4] = "Java Virtual Machine";

        questions[8][0] = "Which of the following is a mutable class in java?";
        questions[8][1] = "java.lang.StringBuilder";
        questions[8][2] = "java.lang.Short";
        questions[8][3] = "java.lang.Byte";
        questions[8][4] = "java.lang.String";

        questions[9][0] = "Which of the following features doesnt exist in java?";
        questions[9][1] = "Dynamic Memory Allocation";
        questions[9][2] = "Machine Independence";
        questions[9][3] = "Use of exception handling";
        questions[9][4] = "Pointers";
        
        answers[0][1] = "8";
        answers[1][1] = "32 and 64";
        answers[2][1] = "short to int";
        answers[3][1] = "dodge";
        answers[4][1] = "Array";
        answers[5][1] = "Multiple Inheritance";
        answers[6][1] = "import";
        answers[7][1] = "Java Virtual Machine";
        answers[8][1] = "java.lang.StringBuilder";
        answers[9][1] = "Pointers";
        Color lightBlue=new Color(173,216,230);
        opt1 = new JRadioButton();
        opt1.setBounds(170, 520, 700, 30);
        opt1.setBackground(lightBlue);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);
        
        opt2 = new JRadioButton();
        opt2.setBounds(170, 560, 700, 30);
        opt2.setBackground(lightBlue);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);
        
        opt3 = new JRadioButton();
        opt3.setBounds(170, 600, 700, 30);
        opt3.setBackground(lightBlue);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);
        
        opt4 = new JRadioButton();
        opt4.setBounds(170, 640, 700, 30);
        opt4.setBackground(lightBlue);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);
        
        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);
        
        next = new JButton("Next");
        next.setBounds(1100, 550, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.GREEN);
        next.addActionListener(this);
        add(next);
        
        back = new JButton("Back");
        back.setBounds(1100, 630, 200, 40);
        back.setFont(new Font("Tahoma", Font.PLAIN, 22));
        back.setBackground(new Color(30, 144, 255));
        back.setForeground(Color.GREEN);
        back.addActionListener(this);
        add(back);
        
        submit = new JButton("Submit");
        submit.setBounds(1100, 710, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.GREEN);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);
        
        start(count);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            repaint();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
               useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            
            if (count == 8) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            
            count++;
            start(count);
        } 
        if (ae.getSource() == back) {
            repaint();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            ans_given = 1;
            
            if (groupoptions.getSelection() == null) {
               useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            
            if (count == 1) {
                back.setEnabled(false);
                //submit.setEnabled(true);
            }
            
            count--;
            start(count);
        } 
        else if (ae.getSource() == submit) {
           submit();
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        String time = "Time left - " + timer/60 + ":"+(timer-(timer/60)*60);// 15
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        if (timer > 0) { 
            g.drawString(time, 1100, 500);
        } else {
            g.drawString("Times up!!", 1100, 500);
        }
        
         timer--; // 14
        
        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (ans_given == 1) {
            ans_given = 0;
            //timer = 1;
        }
        else if(timer<0)
        {
        	submit();
        }
         
        
    }
    void submit()
    {
    	 ans_given = 1;
         if (groupoptions.getSelection() == null) {
             useranswers[count][0] = "";
         } else {
             useranswers[count][0] = groupoptions.getSelection().getActionCommand();
         }

         for (int i = 0; i < useranswers.length; i++) {
             if (useranswers[i][0].equals(answers[i][1])) {
                 score += 10;
             } else {
                 score += 0;
             }
         }
         updateScore();
         
         setVisible(false);
         new Score(email, score);
    }
    void updateScore()
    {
    	connect();
    	String sql="Update testData set score='"+Integer.toString(score)+"' where email_id='"+email+"';";
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
    
    public void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);
        
        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        
        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        
        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);
        
        groupoptions.clearSelection();
    }
    
    public static void main(String[] args) {
        new Quiz("User");
    }
}
