package airlinemanagementystem;


import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.*;
import java.sql.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;



public class  Cancel extends JFrame implements ActionListener {


 
     JTextField tfpnr;
     JLabel tfname,cancellationno,tfcode,lbldate;
    JButton fetchButton,flight;
   
     
    public Cancel(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        Random random=new Random();
        
        JLabel heading=new JLabel("CANCELLATION");
        heading.setBounds(300,20,500,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblpnr=new JLabel("PNR Number");
        lblpnr.setBounds(60,80,150,25);
        add(lblpnr);
         tfpnr=new JTextField();
        tfpnr.setBounds(220,80,150,25);
        add(tfpnr);
        
        fetchButton=new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380,80,120,25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(60,130,150,25);
        add(lblname);
        tfname=new JLabel();
        tfname.setBounds(220,130,150,25);
        add(tfname);
        
        JLabel lblnationality=new JLabel("Cancellation No");
        lblnationality.setBounds(60,180,150,25);
        add(lblnationality);
         cancellationno=new JLabel(""+random.nextInt(1000000));
        cancellationno.setBounds(220,180,150,25);
        add(cancellationno);
        
        JLabel lbladdress=new JLabel("Flight Code");
        lbladdress.setBounds(60,230,150,25);
        add(lbladdress);
         tfcode=new JLabel();
        tfcode.setBounds(220,230,150,25);
        add(tfcode);
        
        
        
        JLabel lblgender=new JLabel("Date");
        lblgender.setBounds(60,280,150,25);
         lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender);
        lbldate=new JLabel();
        lbldate.setBounds(220,280,150,25);
        add(lbldate);
       

        flight=new JButton(" Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220,330,120,30);
        flight.addActionListener(this);
        add(flight);
        
      
        
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("airlinemanagementystem/icons/icons/cancel.jpg"));
        Image i3=i2.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon image=new ImageIcon(i3);
        JLabel lblimage=new JLabel(image);
        lblimage.setBounds(500,100,270,270);
        add(lblimage);
        
        
        
        setSize(800,500);
        setLocation(400,200);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
      if(ae.getSource()==fetchButton) {
        String pnr=tfpnr.getText();
       
       
        try{
            Conn conn=new Conn();
            String query1="select * from reservation where PNR='"+pnr+"'";
            ResultSet rs=conn.s.executeQuery(query1);
            if(rs.next()){
                tfname.setText(rs.getString("name"));
                tfcode.setText(rs.getString("flightcode"));
                lbldate.setText(rs.getString("ddate"));
                
                
            }else{
            JOptionPane.showMessageDialog(null,"Please enter correct PNR");
        }
    
           
        }catch(Exception e){
            e.printStackTrace();
        } 
    }else if(ae.getSource()==flight) {
       String name= tfname.getText();
       String pnr= tfpnr.getText();
       String cancelno=cancellationno.getText();
       String Flightcode=tfcode.getText();
       String date=lbldate.getText();
       
        try{
            Conn conn=new Conn();
            String query1="insert into cancel values('"+pnr+"','"+name+"','"+cancelno+"','"+Flightcode+"','"+date+"')";
            conn.s.executeUpdate(query1);
            conn.s.executeUpdate("delete from reservation where PNR='"+pnr+"'");
            
            JOptionPane.showMessageDialog(null,"Ticket Cancelled");
            setVisible(false);
    
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    }
    public static void main(String[] args){
        new Cancel();
    }

}



