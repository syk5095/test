package com.care.board_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.care.board_dto.BoardDTO;

public class BoardDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.47:1521:xe";
	private String user = "java";
	private String pwd = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private JdbcTemplate template;
	
	public BoardDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<BoardDTO> list() {
//		String sql = "select * from mvc_board";
		String sql = "select * from mvc_board order by idgroup desc,step asc";

		ArrayList<BoardDTO> lists = null;
			lists = (ArrayList<BoardDTO>)template.query(sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

		return lists;
	}

	public void write_save(String name, String title, String content) {
		// TODO Auto-generated method stub

		String sql = "insert into mvc_board(id,name,title,content,hit,idgroup,step,indent)values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public BoardDTO contentView(String id) {
		upHit(id);
		// TODO Auto-generated method stub
		String sql = "select * from mvc_board where id=?";
		BoardDTO dto = new BoardDTO();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}

	public void upHit(String id) {
		String sql = "update mvc_board set hit=hit+1 where id=?";

		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void modify(String id, String name, String title, String content) {
		// TODO Auto-generated method stub
		String sql = "update mvc_board set name=?,title=?,content=? where id=?";
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setString(4, id);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from mvc_board where id=?";
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public BoardDTO reply_view(String id) {
		String sql = "select * from mvc_board where id=?";
		BoardDTO dto = new BoardDTO();

		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return dto;
	}

	public void reply(BoardDTO dto) {
		// TODO Auto-generated method stub
		replyShape(dto);
		String sql = "insert into mvc_board (id,name,title,content,idgroup,step,indent)values(mvc_board_seq.nextval,?,?,?,?,?,?)";
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getIdgroup());
			ps.setInt(5, dto.getStep() + 1);
			ps.setInt(6, dto.getIndent() + 1);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
	}

	public void replyShape(BoardDTO dto) {
		String sql = "update mvc_board set step=step+1 where idgroup=? and step>?";
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getIdgroup());
			ps.setInt(2, dto.getStep());
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

}
