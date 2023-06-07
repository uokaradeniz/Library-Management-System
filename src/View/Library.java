package View;

import Helper.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Library extends JFrame{
    public JPanel libraryPanel;
    private JButton adminLogin;
    private JButton userLogin;
    private JButton userRegistration;
    private JLabel librarypng;

    public Library(){
        add(libraryPanel);
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Library Menu");
        setVisible(true);


        adminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin adminLogin = new AdminLogin();
                Config.CenterWindow(adminLogin);
                dispose();
            }
        });
        userLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin userLogin = new UserLogin();
                Config.CenterWindow(userLogin);
                dispose();
            }
        });
        userRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegistration userRegistration = new UserRegistration();
                Config.CenterWindow(userRegistration);
                dispose();
            }
        });
    }
}
