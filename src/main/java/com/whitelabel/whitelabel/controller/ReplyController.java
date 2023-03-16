package com.whitelabel.whitelabel.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whitelabel.whitelabel.dto.ReplyDTO;
import com.whitelabel.whitelabel.service.ReplyService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController//화면제공없이 데이터만 주고 받는 형태의 컨트롤러 선언
//위처럼 선언이 되면 자동으로 기본 데이터 입출형식이 JSON으로 설정됩니다
@RequestMapping("/reply")//리플 관련 데이터는 옆에 path 하위로 요청을 처리하도록 합니다
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService replyService;
	
	//value 속성 중의 {}는 MVC 패턴에서 자주 쓰이는 패턴 표기법입니다
	//일반적으로 가변값이 들어올 때 매핑의 용도로 사용됩니다
	//현재는 board/bno(글번호)로 바로 요청이 오면 아래서 mapping을 시켜주고 
	//데이터는 JSON 객체로 생성해서 바인딩하겠다는 의미입니다
	@GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno){ 
		log.info("bno------>" + bno);
		return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
	}
	//댓글등록 메서드 정의
	/*
	 * http 프로토콜에서 정의한 데이터 전송 메서드 종류
	 * 요청된 메서드에 JSON 객체를 바인딩한 객체가 필요합니다
	 * 일반적으로 문서의 링크등을 통해서 요청되는 형태는 RequestDTO에 바인딩이 되지만 
	 * 지금처럼 REST 형태로 올 경우에 요청시 같이 보내준 컨텐트 바디를 지정을 해줘야만 
	 * DTO에 매핑이 되어집니다. 해당 어노테이션이 RequestBody 입니다.
	 */
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
		log.warn("Ajax 통신 댓글 등록 요청옴 bno---> " + replyDTO.getBno());
		Long pno = replyService.register(replyDTO);
		return new ResponseEntity<>(pno, HttpStatus.OK);
	}
	/*
	 * 댓글 삭제 메서드 getListByBoard() 처럼 요청에 바로 댓글 넘버로 오기 때문에
	 * 맵핑설정을 {}로 해줘야 하고 ajax로 삭제 요청이기에, delete 매핑을 해야합니다
	 * service를 이용해서 삭제 후 결과 메세지는 String으로 success로 주고 상태 코드는
	 * OK를 줍니다. 
	 */
	@DeleteMapping("/{pno}")
	public ResponseEntity<String> remove(@PathVariable("pno") Long pno){
		replyService.remove(pno);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	@PutMapping("/{pno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO repleDTO){
		replyService.modify(repleDTO);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
}

