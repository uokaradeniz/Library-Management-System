
package View;

import Helper.Config;
import Model.Books;
import Model.Manager.BookDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LibraryManagement extends JFrame {
    private JPanel LibraryManagementPanel;
    private JTabbedPane tabbedTablePane1;
    private JButton backButton;
    private JPanel adminFormPanel;
    private JLabel bookNameLabel;
    private JTextField bookNametextField;
    private JLabel authorLabel;
    private JTextField authorTextField1;
    private JLabel pagesLabel;
    private JTextField pagesTextField1;
    private JButton addBookButton;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private JPanel searchPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTable adminJtable;
    private JLabel Volume;
    private JTextField volumeTextField;
    private JTextField idTextField;
    private JScrollPane scrollPane;



    public LibraryManagement(){
        add(LibraryManagementPanel);
        setSize(1000,590);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Library Management System | Admin");
        setResizable(false);
        setVisible(true);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Books book = new Books(bookNametextField.getText(), authorTextField1.getText(),
                            Integer.parseInt(pagesTextField1.getText()), Integer.parseInt(volumeTextField.getText()));
                    BookDao.add(book);
                    Config.RefreshTableModel(adminJtable);
                    JOptionPane.showMessageDialog(null, "Book Added to Database Successfully!");
                    ClearTextBoxes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error! Fill book details.");
                    ex.printStackTrace();
                }
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int delBookId = Integer.parseInt(idTextField.getText());
                    for (Books book: BookDao.getAllBooks()) {
                        if(book.getId() == delBookId)
                            BookDao.delete(book.getId());
                    }
                    Config.RefreshTableModel(adminJtable);
                    JOptionPane.showMessageDialog(null, "Book Deleted from Database Successfully!");
                    ClearTextBoxes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error! Fill book details.");
                    ex.printStackTrace();
                }
            }
        });
        editBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateBookId = Integer.parseInt(idTextField.getText());
                    Books updatedBook = new Books(bookNametextField.getText(), authorTextField1.getText(),
                            Integer.parseInt(pagesTextField1.getText()), Integer.parseInt(volumeTextField.getText()));
                    updatedBook.setId(updateBookId);
                    for (Books book: BookDao.getAllBooks()) {
                        if(book.getId() == updateBookId)
                            BookDao.update(updatedBook,book.getId());
                    }
                    Config.RefreshTableModel(adminJtable);
                    JOptionPane.showMessageDialog(null, "Book Information Updated Successfully!");
                    ClearTextBoxes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error! Fill book details.");
                    ex.printStackTrace();
                }
            }
        });

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
                    ClearTextBoxes();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please type book title.");
                    ex.printStackTrace();
                }
            }
        });
    }
    private void ClearTextBoxes() {
        bookNametextField.setText("");
        authorTextField1.setText("");
        pagesTextField1.setText("");
        volumeTextField.setText("");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DefaultTableModel model = new DefaultTableModel(0, 15);
        model.setColumnIdentifiers(Config.header);
        adminJtable = new JTable(model);
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