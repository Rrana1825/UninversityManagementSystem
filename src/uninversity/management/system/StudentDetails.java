package uninversity.management.system;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class StudentDetails extends JFrame implements ActionListener{
	
	Choice crollno;
	JTable table;
	JButton search , print , update , add , cancel;
	StudentDetails(){
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		JLabel heading = new JLabel("Search by Roll number");
		heading.setBounds(20,20,150,20);
		add(heading);
		
		crollno = new Choice();
		crollno.setBounds(180 , 20 , 150 , 20);
		add(crollno);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from Student");
			while(rs.next()) {
				crollno.add(rs.getString("rollno"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		table = new JTable();
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from Student");
 			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(0,100,900,700);
		add(jsp);
		
		search = new JButton("Search");
		search.setBounds(20,70,80,20);
		search.addActionListener(this);
		add(search);
		
		print = new JButton("Print");
		print.setBounds(120,70,80,20);
		print.addActionListener(this);
		add(print);
		
		add = new JButton("Add");
		add.setBounds(220,70,80,20);
		add.addActionListener(this);
		add(add);
		
		update = new JButton("Update");
		update.setBounds(320,70,80,20);
		update.addActionListener(this);
		add(update);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(420,70,80,20);
		cancel.addActionListener(this);
		add(cancel);
		
		setSize(900,700);
		setLocation(300, 100);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == search) {
            String query = "select * from student where rollno = '"+crollno.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddStudent();
        } else if (ae.getSource() == update) {
            setVisible(false);
            //new UpdateStudent();
        } else {
            setVisible(false);
        }

	}
	
	public void main(String[] args){
		new StudentDetails();
	}
}
