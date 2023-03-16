package com.whitelabel.whitelabel.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.whitelabel.whitelabel.dto.BoardDTO;
import com.whitelabel.whitelabel.dto.PageRequestDTO;
import com.whitelabel.whitelabel.dto.PageResultDTO;
import com.whitelabel.whitelabel.entity.Board;
import com.whitelabel.whitelabel.entity.Member;
import com.whitelabel.whitelabel.repository.BoardRepository;
import com.whitelabel.whitelabel.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepository;
	private final ReplyRepository repleRepository;
	
	@Override
	public Long register(BoardDTO boardDTO) {
		log.info("신규등록 호출");
		
		//dto를 Entity로 변환 시킨 후 변환된 entity를 save 시킵니다
		//그리고 insert된 새글의 글넘버(bno)를 view단으로 리턴시켜 
		//글등록 후 리다이렉션되는 List페이지에서 글등록 관련 메세지를 출력시키도록합니다
		Board board = dtoToEntity(boardDTO);
		boardRepository.save(board);
		return board.getBno();
	}
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {
		
		/*
		 * boardRepository의 getBoardWithReple()를 통해서 조인된 테이블을 통해
		 * 게시글의 작서자 이메일, 이름 -- 이상 Member, 댓글수, 게시글 제목, 글넘버 등을 얻어내서
		 * dto로 변환 후 PageResult객체를 생성하여 리턴시킵니다
		 * 아까 설명했던 내용입니다. 이렇게 리턴된 객체는 View단에서 PageResultDTO
		 * 객체의 필드명인 dtoList를 통해 변환된 DTO 객체를 사용할 수 있게 됩니다.
		 */
		log.info("페이지 목록 요청됨");
		
		//Entity --> DTO 변경 Function객체 생성
		Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0], (Member)en[1], (Long)en[2]));
		
		//PageResult 객체의 파라미터로 보낼 Page객체 생성합니다
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(requestDTO.getPageable(Sort.by("bno").descending()));
		
		//PageResult 객체 생성 후 리턴시킵니다
		return new PageResultDTO<>(result, fn);
	}
	@Override
	public BoardDTO read(Long bno) {
		
		Object ob = boardRepository.getBoardByBno(bno);
		Object[] arr  = (Object[]) ob;
		
		return entityToDto((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		
		repleRepository.deleteByBno(bno);
		boardRepository.deleteById(bno);
		
	}
	
	@Transactional
    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.getOne(boardDTO.getBno());

        if(board != null) {

            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            boardRepository.save(board);
        }
    }
	
	
	
}
