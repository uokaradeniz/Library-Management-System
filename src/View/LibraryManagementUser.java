package View;

import Helper.Config;
import Model.Books;
import Model.Manager.BookDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LibraryManagementUser extends JFrame {
    private JPanel LibraryManagementUserPanel;
    private JTabbedPane tabbedTablePane1;
    private JButton backButton;
    private JScrollPane tableScrollPanel;
    private JTable userJtable;
    private JTextField searchTextField;
    private JButton searchButton;
    private JPanel tablePanel;
    private JPanel searchPanel;
    private JLabel welcome;
    private JLabel userNameLabel;

    public LibraryManagementUser() {
    add(LibraryManagementUserPanel);
    setSize(1000, 590);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Library Management System | User");
    setResizable(false);
    setVisible(true);

    userNameLabel.setText(UserLogin.userSessionName);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Library library = new Library();
                Config.CenterWindow(library);
                dispose();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean bookFound = false;
                try {
                    String desiredBook = searchTextField.getText();
                    System.out.println(desiredBook);
                    for (Books book: BookDao.getAllBooks()) {
                        if (book.getBookTitle().equals(desiredBook)) {
                            JOptionPane.showMessageDialog(null, "ID of the desired book: " + book.getId());
                            bookFound = true;
                        }
                    }
                    if(!bookFound)
                        JOptionPane.showMessageDialog(null, "Couldn't find the desired book in the database.");
                    searchTextField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please type book title.");
                    ex.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DefaultTableModel model = new DefaultTableModel(0, 15);
        model.setColumnIdentifiers(Config.header);
        userJtable = new JTable(model);
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                Object[] row = {resultSet.getString("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getInt("page_count"),
                        resultSet.getInt("volume")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
