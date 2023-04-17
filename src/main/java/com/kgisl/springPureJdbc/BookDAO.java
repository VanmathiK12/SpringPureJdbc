package com.kgisl.springPureJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class BookDAO {
    @Autowired
    private DataSource dataSource;
    public void insertBooks(Book book) throws SQLException {
        String sql = "INSERT INTO book (id, title,author,price,qty) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setFloat(4, book.getPrice());
            ps.setInt(5, book.getQty());
            ps.executeUpdate();
        }
    }
    

    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE id = ?";
         
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql))
        {
        ps.setInt(1, id);
         
        ResultSet resultSet = ps.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            int qty = resultSet.getInt("qty");
            book = new Book(id, title, author, price, qty);
        }      
        resultSet.close();
        ps.close();
    }
        return book;
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?, qty=?";
        sql += " WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setFloat(3, book.getPrice());
        ps.setFloat(4, book.getQty());
        ps.setInt(5, book.getId());
         
        boolean rowUpdated = ps.executeUpdate() > 0;
        ps.close();
        return rowUpdated;     
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public List<Book> listAllBooks() throws SQLException {
        String sql = "SELECT * FROM book";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getFloat("price"), rs.getInt("qty")));
            }
            for (Book record : books) {
                System.out.print("ID : " + record.getId());
                System.out.print(", Title : " + record.getTitle());
                System.out.print(", Author : " + record.getAuthor());
                System.out.print(", Price : " + record.getPrice());
                System.out.println(", Qty : " + record.getQty());
            }
            return books;
        }
    }
}
