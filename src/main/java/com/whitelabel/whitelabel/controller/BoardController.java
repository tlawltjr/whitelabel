package com.whitelabel.whitelabel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whitelabel.whitelabel.dto.BoardDTO;
import com.whitelabel.whitelabel.dto.PageRequestDTO;
import com.whitelabel.whitelabel.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board")//Root 서버 하위에(localhost:8080/{context Mapping})되어서
//board 하위에 오는 모든 요청은 이 컨트롤러가 잡습니다
public class BoardController {

	private final BoardService boardService;
	
	//이번엔 각 context 하위의 요청마다 처리해줄 요청 매핑을 선언 해야합니다
	//기본적으로 get 방식은 @Get Post --> @Post
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("리스트 페이지 요청");
		model.addAttribute("PageResObj", boardService.getList(pageRequestDTO));
	}
	//신규 글등록 요청 처리
	@GetMapping("/register")
	public void getRegister() {
		log.info("신규글등록");
	}
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		
		Long bno = boardService.register(boardDTO);
		redirectAttributes.addFlashAttribute("msg", bno);
		return "redirect:/board/list";
		
	}
	@GetMapping({"/read","/modify"})
	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model) {
		
		BoardDTO boardDTO = boardService.read(bno);
		
		model.addAttribute("dto",boardDTO);
	}
	  @PostMapping("/modify")
	    public String modify(BoardDTO dto,
	                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
	                         RedirectAttributes redirectAttributes){


	        log.info("post modify.........................................");
	        log.info("dto: " + dto);

	        boardService.modify(dto);

	        redirectAttributes.addAttribute("page",requestDTO.getPage());
	        redirectAttributes.addAttribute("type",requestDTO.getType());
	        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());

	        redirectAttributes.addAttribute("bno",dto.getBno());

	        return "redirect:/board/read";

	    }
}
