
    import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.io.*;
    import java.util.*;

    public class NewUser extends JFrame {
        JButton create;
        JPanel newUserPanel;
        JTextField txuserer;
        JTextField passer;
        JLabel username;
        JLabel password;
        JLabel userReq ;
        JLabel passReq ;
        private boolean passcheck(int s , int d , int c , int u , int l)
        {
            return (s > 0 && (u > 0 || l > 0) && d > 0 && (c >= 8 && c <= 10) );
        }
        private boolean userncheck(int c , int d , int s)
        {
            return ((c >= 6 && c <= 8) && d <= 2 && s == 0);
        }


        public NewUser(){
            super("Registration");
            username = new JLabel("User - ");
            password = new JLabel("Pass - ");
            userReq = new JLabel("the username must be with 6-8 chars and maximum 2 digits ");
            passReq = new JLabel("the password must be with 8-10 cahrs atleast 1 letter and 1 char and 1 spacial latter");
            create = new JButton("Create");
            newUserPanel = new JPanel();
            txuserer = new JTextField(15);
            passer = new JPasswordField(15);


            setSize(600,300);
            setLocation(500,280);
            newUserPanel.setLayout (null);
            
            passReq.setBounds(70,25,600,20);
            userReq.setBounds(70,5,600,20);
            txuserer.setBounds(70,45,150,20);
            passer.setBounds(70,65,150,20);
            create.setBounds(110,100,80,20);
            username.setBounds(20,45,150,20);
            password.setBounds(20,65,150,20);
            
            newUserPanel.add(userReq);
            newUserPanel.add(passReq);
            newUserPanel.add(create);
            newUserPanel.add(username);
            newUserPanel.add(txuserer);
            newUserPanel.add(password);
            newUserPanel.add(passer);

            getContentPane().add(newUserPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            Writer writer = null;
            File check = new File("userPass.txt");
            if(check.exists()){

                //Checks if the file exists. will not add anything if the file does exist.
            }else{
                try{
                    File texting = new File("userPass.txt");
                    writer = new BufferedWriter(new FileWriter(texting));
                    writer.write("message");
                }catch(IOException e){
                    e.printStackTrace();
                }
            }




            create.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        File file = new File("userPass.txt");
                        Scanner scan = new Scanner(file);;

                        FileWriter filewrite = new FileWriter(file, true);

                        String usertxter = " ";
                        String passtxter = " ";
                        String punamer = txuserer.getText();
                        String ppaswder = passer.getText();
                        int lower=0, upper=0, digits=0 , spacial = 0;
                        int lower1=0, upper1=0, digits1=0 , spacial1 = 0;
                        char [] array = ppaswder.toCharArray();
                        char [] array1 = punamer.toCharArray();
                        for ( int i = 0;  i < ppaswder.length(); i++)
                        {
                            if(array[i] == ('!') || array[i] == ('@') || array[i] == ('#') || array[i] == ('$') || array[i] == ('%') || array[i] == ('^') || array[i] == ('&') || array[i] == ('*'))
                                spacial++;
                            if(Character.isDigit(array[i]))
                                digits++;
                            if(Character.isLowerCase(array[i]))
                                lower++;
                            if(Character.isUpperCase(array[i]))
                                upper++;
                        }
                        for ( int i = 0;  i < punamer.length(); i++)
                        {
                            if(Character.isDigit(array1[i]))
                                digits1++;
                            if(Character.isLowerCase(array1[i]))
                                lower1++;
                            if(Character.isUpperCase(array1[i]))
                                upper1++;
                            if(array1[i] == ('!') || array1[i] == ('@') || array1[i] == ('#') || array1[i] == ('$') || array1[i] == ('%') || array1[i] == ('^') || array1[i] == ('&') || array1[i] == ('*'))
                                spacial1++;
                        }
                        while (scan.hasNext()) {
                            usertxter = scan.nextLine();
                            passtxter = scan.nextLine();
                        }
                        if(passcheck(spacial,digits,ppaswder.length(),upper,lower) && userncheck(punamer.length(),digits1,spacial1))
                        {
                            if(punamer.equals(usertxter) && ppaswder.equals(passtxter)) {
                                JOptionPane.showMessageDialog(null,"Username is already in use");
                                txuserer.setText("");
                                passer.setText("");
                                txuserer.requestFocus();

                            }
                            else if(punamer.equals("") && ppaswder.equals("")){
                                JOptionPane.showMessageDialog(null,"Please insert Username and Password");
                            }
                            else {
                                filewrite.write(punamer+"\r\n" +ppaswder+ "\r\n");
                                filewrite.close();
                                JOptionPane.showMessageDialog(null,"Account has been created.");
                                dispose();
                                login log = new login();

                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"password or username not as requseted");
                        }
                    } catch (IOException d) {
                        d.printStackTrace();
                    }

                }
            });
        }
    }




