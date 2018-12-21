package board_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.board_dao.BoardDAO;

public class BDeleteServiceImpl implements BoardService{
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		BoardDAO dao = new BoardDAO();
		dao.delete(id);
	}

}
