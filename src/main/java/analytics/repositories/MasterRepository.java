package analytics.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MasterRepository {

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String username;

    @Value("${master.datasource.password}")
    private String password;

    private Connection connection;

    public MasterRepository() {
    }

    @PostConstruct
    private void openConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Long>> getLinkCounts() {
        List<Map<String, Long>> links = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT visited_count_from_android, visited_count_from_chrome, visited_count_from_firefox, visited_count_from_ie, visited_count_from_ios, visited_count_from_linux, visited_count_from_osx, visited_count_from_other_browser, visited_count_from_other_os, visited_count_from_safari, visited_count_from_windows FROM `link`");

            while (rs.next()) {
                Map<String, Long> map = new HashMap<>();
                map.put("android", rs.getLong("visited_count_from_android"));
                map.put("chrome", rs.getLong("visited_count_from_chrome"));
                map.put("firefox", rs.getLong("visited_count_from_firefox"));
                map.put("ie", rs.getLong("visited_count_from_ie"));
                map.put("ios", rs.getLong("visited_count_from_ios"));
                map.put("linux", rs.getLong("visited_count_from_linux"));
                map.put("osx", rs.getLong("visited_count_from_osx"));
                map.put("otherBrowser", rs.getLong("visited_count_from_other_browser"));
                map.put("otherOs", rs.getLong("visited_count_from_other_os"));
                map.put("safari", rs.getLong("visited_count_from_safari"));
                map.put("windows", rs.getLong("visited_count_from_windows"));
                links.add(map);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return links;
    }
}