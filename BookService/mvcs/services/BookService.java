package mvcs.services;

import mvcs.models.Book;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private String createQuery = "insert into book values(?,?,?)";
    private String findAllQuery = "select * from book";
    private String findByCodeQuery = "Select * from book where code = ?";
    private String updateQuery = "update book set title=?, dop = ? where code = ?";
    private String deleteQuery = "delete from book where code = ?";

    public void create(Book book) throws SQLException {
        try (
                Connection con = DbConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(createQuery);) {

            pstmt.setString(1, book.getCode());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getDop().toString());
            pstmt.executeUpdate();

        }
    }

    public List<Book> findAll() throws SQLException {
        List<Book> ls = new ArrayList<>();
        try (
                Connection con = DbConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(findAllQuery);) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString(1);
                String title = rs.getString(2);
                String sdop = rs.getString(3);
                LocalDate dop = LocalDate.parse(sdop);
                Book book = new Book(code, title, dop);
                ls.add(book);
            }
        }
        return ls;
    }

    public Book findByCode(String code) throws SQLException {
        try (Connection con = DbConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(findByCodeQuery);) {

            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString(2);
                String sdop = rs.getString(3);
                LocalDate dop = LocalDate.parse(sdop);
                Book book = new Book(code, title, dop);
                return book;
            }
        }
        return null;
    }

    public void update(Book book) throws SQLException {
        try (
                Connection con = DbConnect.getConnection(); 
                PreparedStatement pstmt = con.prepareStatement(updateQuery);
                ) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getDop().toString());
            pstmt.setString(3, book.getCode());
            pstmt.executeUpdate();
        }
    }
    
   
}
