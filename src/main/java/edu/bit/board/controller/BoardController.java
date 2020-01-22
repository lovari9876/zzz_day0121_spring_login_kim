package edu.bit.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.bit.board.page.Criteria;
import edu.bit.board.page.PageMaker;
import edu.bit.board.service.BoardService;
import edu.bit.board.vo.BoardVO;
import edu.bit.board.vo.UserVO;

@Controller
public class BoardController {
	
	@Inject
	BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		model.addAttribute("list", boardService.selectBoardList());
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO) {
		System.out.println("write()");
		
		boardService.insertBoard(boardVO);		
		return "redirect:list2";
	}
	
//	@RequestMapping("/content_view")
//	public String content_view(BoardVO boardVO, Model model) {
//		System.out.println("content_view()");
//		BoardVO content = boardService.selectBoardOne(Integer.toString(boardVO.getbId()));
//		model.addAttribute("content_view", content);
//		
//		return "content_view";
//	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest req, Model model) {
		System.out.println("content_view()");
		
		String bId = req.getParameter("bId");
		model.addAttribute("content_view", boardService.selectBoardOne(bId));		
		return "content_view";
	}
	
	@RequestMapping("/modify")
	public String modify(BoardVO boardVO) {
		System.out.println("update()");
		
		boardService.updateBoard(boardVO);
		return "redirect:list2";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest req, Model model) {
		System.out.println("reply_view()");
		
		String bId = req.getParameter("bId");
		model.addAttribute("reply_view", boardService.selectBoardOne(bId));
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(BoardVO boardVO) {
		System.out.println("reply()");
		
		boardService.insertReply(boardVO);
		return "redirect:list2";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req) {
		System.out.println("delete()");
		
		String bId = req.getParameter("bId");
		boardService.deleteBoard(bId);
		return "redirect:list2";
	}

	@RequestMapping("/list2")
	// Criteria 클래스의 기본 생성자와  list2.jsp의 forEach문에서 값을 가져옴
	public String list2(HttpSession session, Criteria criteria, Model model) {
		System.out.println("list2()");
		
		UserVO member = (UserVO) session.getAttribute("member");
		
		if(member==null) {
			System.out.println("멤버세션 없음");
			return "redirect:/";
		}
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		int totalCount = boardService.selectCountBoard();
		pageMaker.setTotalCount(totalCount);
		
		List<BoardVO> boardList = boardService.selectBoardListPage(criteria);
		
		model.addAttribute("list", boardList);
		model.addAttribute("pageMaker", pageMaker);

		return "list2";
	}
}
