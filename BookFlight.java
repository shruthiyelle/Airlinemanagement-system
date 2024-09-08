
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
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;



public class BookFlight extends JFrame implements ActionListener {


 
     JTextField tfaadhar;
     JLabel tfname,tfnationality,tfaddress,labelgender,labelfname,labelfcode;
    JButton fetchButton,flight,bookflight;
    Choice source,destination;
    JDateChooser dcdate;
     
    public BookFlight(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading=new JLabel("Book Flight");
        heading.setBounds(420,20,500,35);
        heading.setFont(new Font("Tahoma",Font.PLAIN,32));
        heading.setForeground(Color.BLUE);
        add(heading);
        
        JLabel lblaadhar=new JLabel("Aadhar");
        lblaadhar.setBounds(60,80,150,25);
        add(lblaadhar);
         tfaadhar=new JTextField();
        tfaadhar.setBounds(220,80,150,25);
        add(tfaadhar);
        
        fetchButton=new JButton("Fetch");
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
        
        JLabel lblnationality=new JLabel("Nationality");
        lblnationality.setBounds(60,180,150,25);
        add(lblnationality);
         tfnationality=new JLabel();
        tfnationality.setBounds(220,180,150,25);
        add(tfnationality);
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(60,230,150,25);
        add(lbladdress);
         tfaddress=new JLabel();
        tfaddress.setBounds(220,230,150,25);
        add(tfaddress);
        
        
        
        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(60,280,150,25);
         lblgender.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblgender);
        labelgender=new JLabel("Gender");
        labelgender.setBounds(220,280,150,25);
        add(labelgender);
       
         JLabel lblsource=new JLabel("Source");
        lblsource.setBounds(60,330,150,25);
         lblsource.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblsource);
        
        source=new Choice();
        source.setBounds(220,330,150,25);
        add(source);
        
         JLabel lbldest=new JLabel("Destination");
        lbldest.setBounds(60,380,150,25);
         lbldest.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldest);
        
        destination=new Choice();
        destination.setBounds(220,380,150,25);
        add(destination);
        try{
            Conn c=new Conn();
            String query="select * from flight";
            ResultSet rs=c.s.executeQuery(query);
            while(rs.next()){
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        flight=new JButton(" Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380,380,120,30);
        flight.addActionListener(this);
        add(flight);
        
        JLabel lblfname=new JLabel("Flight Name");
        lblfname.setBounds(60,430,150,25);
        add(lblfname);
         labelfname=new JLabel();
        labelfname.setBounds(220,430,150,25);
        add(labelfname);
        
         JLabel lblfcode=new JLabel("Flight code");
        lblfcode.setBounds(60,480,150,25);
        add(lblfcode);
         labelfcode=new JLabel();
        labelfcode.setBounds(220,480,150,25);
        add(labelfcode);
        
        JLabel lbldate=new JLabel("Date of Travel");
        lbldate.setBounds(60,530,150,25);
        lbldate.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbldate);
        
         dcdate=new JDateChooser();
        dcdate.setBounds(220,530,150,25);
        add(dcdate);
        
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("airlinemanagementystem/icons/icons/details.jpg"));
        Image i3=i2.getImage().getScaledInstance(450,320,Image.SCALE_DEFAULT);
        ImageIcon image=new ImageIcon(i3);
        JLabel lblimage=new JLabel(image);
        lblimage.setBounds(550,80,500,410);
        add(lblimage);
        
        bookflight=new JButton(" Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220,580,120,30);
        bookflight.addActionListener(this);
        add(bookflight);
        
        setSize(1100,700);
        setLocation(200,50);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
      if(ae.getSource()==fetchButton) {
        String aadhar=tfaadhar.getText();
       
       
        try{
            Conn conn=new Conn();
            String query1="select * from passenger where aadhar='"+aadhar+"'";
            ResultSet rs=conn.s.executeQuery(query1);
            if(rs.next()){
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                tfaddress.setText(rs.getString("address"));
                labelgender.setText(rs.getString("gender"));
                
            }else{
            JOptionPane.showMessageDialog(null,"Please enter correct aadhar");
        }
    
           
        }catch(Exception e){
            e.printStackTrace();
        } 
    }else if(ae.getSource()==flight) {
       String src= source.getSelectedItem();
       String dest= destination.getSelectedItem();
       
       
        try{
            Conn conn=new Conn();
            String query1="select * from flight where source='"+src+"' and destination='"+dest+"'";
            ResultSet rs=conn.s.executeQuery(query1);
            if(rs.next()){
                labelfname.setText(rs.getString("fname"));
                labelfcode.setText(rs.getString("f_code"));
                
                
            }else{
            JOptionPane.showMessageDialog(null,"No Flights found");
        }
    
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    else{
    Random random=new Random();
    String aadhar=tfaadhar.getText();
    String name=tfname.getText();
    String nationality=tfnationality.getText();
    String flightname=labelfname.getText();
    String flightcode=labelfcode.getText();
    
    String src=source.getSelectedItem();
    String dest=destination.getSelectedItem();
    String ddate=((JTextField)dcdate.getDateEditor().getUiComponent()).getText();
   try{
            Conn conn1=new Conn();
           
            String query="insert into reservation values('PNR-"+random.nextInt(1000000)+"','TIC-"+random.nextInt(10000)+"','"+aadhar+"','"+name+"','"+nationality+"','"+flightname+"','"+flightcode+"','"+src+"','"+dest+"','"+ddate+"')";
           
            
           conn1.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null,"Ticket Booked successfully");
        setVisible(false);
    
           
        }catch(Exception e){
            e.printStackTrace();
        } 
    
    
    }
    }
    public static void main(String[] args){
        new BookFlight();
    }

}


