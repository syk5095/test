package board_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.board_dao.BoardDAO;

public class BModifyServiceImpl implements BoardService{
	@Override
public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		dao.modify(id,name,title,content);
	}
}
