package board_service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.care.board_dao.BoardDAO;
import com.care.board_dto.BoardDTO;

public class BListServiceImpl implements BoardService {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> lists = dao.list();
		model.addAttribute("list", lists);
		dao.list();
	}
}
