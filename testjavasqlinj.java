// Test case for Java SQL Injection (Order-By Parameter injection pattern)
// This vulnerability was missed by Semgrep

package com.test.vulnerabilities;

import java.sql.*;

public class SQLInjectionOrderBy {

    public void vulnerableOrderByQuery(Connection conn, String userInput) throws SQLException {
        // VULNERABILITY: Order-By parameter injection - user input directly concatenated
        String sql = "SELECT * FROM users ORDER BY " + userInput;

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("username"));
        }
    }

    public void anotherVulnerablePattern(Connection conn, String sortColumn, String sortDirection) throws SQLException {
        // VULNERABILITY: Multiple parameter injection in ORDER BY clause
        String query = "SELECT id, name FROM products ORDER BY " + sortColumn + " " + sortDirection;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
}
