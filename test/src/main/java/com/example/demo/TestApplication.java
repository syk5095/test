package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class TestApplication {
	int i = 0;

	private String url = "jdbc:oracle:thin:@192.168.0.47:1521:xe";
	private String user = "java";
	private String pw = "1234";
	private Connection con;
	private PreparedStatement ps;
	ResultSet rs;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Scheduled(cron = "*/1 * * * * *") // 매 초마다
	public void scheduelRun() {
		i++;
		System.out.println(i + " : 실행");
		String sql="select count(*) from mvc_board";
		try {
			con = DriverManager.getConnection(url, user, pw);
				System.out.println("연결객체 생성");
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			System.out.println("현재 총 게시글 : "+ rs.getInt(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(con!=null)con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
