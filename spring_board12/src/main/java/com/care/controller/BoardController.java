package com.care.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.template.Constant;

import board_service.BContnenViewServiceImpl;
import board_service.BDeleteServiceImpl;
import board_service.BListServiceImpl;
import board_service.BModifyServiceImpl;
import board_service.BReplyServiceImpl;
import board_service.BReplyViewServiceImpl;
import board_service.BWriteSaveServiceImpl;
import board_service.BoardService;

@Controller
public class BoardController {
	private BoardService board;
	public BoardController() {
		// TODO Auto-generated constructor stub
	
		String configLocation = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		JdbcTemplate template = ctx.getBean("template", JdbcTemplate.class);
		Constant.template = template;
	}
	
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		System.out.println("list 실행");
		board = new BListServiceImpl();
		board.execute(model);
		return "list";
	}

	@RequestMapping(value = "/write_view")
	public String write_view() {
		System.out.println("write_view 실행");
		return "write_view";

	}

	@RequestMapping(value = "/write_save")
	public String write_save(Model model, HttpServletRequest request) {
		System.out.println("write_save 실행");
		System.out.println("name : " + request.getParameter("name"));
		model.addAttribute("request", request);
		board = new BWriteSaveServiceImpl();

		board.execute(model);
		return "redirect:list";
	}

	@RequestMapping(value = "/content_view")
	public String content_view(Model model, HttpServletRequest request) {
		System.out.println("컨트롤러 진입");
		model.addAttribute("request", request);
		board = new BContnenViewServiceImpl();
		board.execute(model);
		return "content_view";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		board = new BModifyServiceImpl();
		board.execute(model);
		return "redirect:list"; // 수정이 끝나면 리스트로 돌아가라
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		board = new BDeleteServiceImpl();
		board.execute(model);
		return "redirect:list";

	}

	@RequestMapping(value = "/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		board = new BReplyViewServiceImpl();
		board.execute(model);
		return "reply_view";
	}
	
	@RequestMapping(value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		board = new BReplyServiceImpl();
		board.execute(model);
		return "redirect:list";
	}
}
