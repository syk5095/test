package board_service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.board_dao.BoardDAO;
import com.care.board_dto.BoardDTO;

public class BReplyServiceImpl implements BoardService {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		BoardDTO dto = new BoardDTO();
		dto.setId((Integer.parseInt(request.getParameter("id"))));
		dto.setName(request.getParameter("name"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setIdgroup((Integer.parseInt(request.getParameter("idgroup"))));
		dto.setStep((Integer.parseInt(request.getParameter("step"))));
		dto.setIndent((Integer.parseInt(request.getParameter("indent"))));
		BoardDAO dao = new BoardDAO();
		dao.reply(dto);
		
	}

}
