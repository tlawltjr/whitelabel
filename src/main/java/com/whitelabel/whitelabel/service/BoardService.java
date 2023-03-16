package com.whitelabel.whitelabel.service;

import com.whitelabel.whitelabel.dto.BoardDTO;
import com.whitelabel.whitelabel.dto.PageRequestDTO;
import com.whitelabel.whitelabel.dto.PageResultDTO;
import com.whitelabel.whitelabel.entity.Board;
import com.whitelabel.whitelabel.entity.Member;

public interface BoardService {
	
	/*
	 * 사용자가 요청한 페이지 정보와, 글목록 개수를 담은 Pageable 객체를 이용해서
	 * Entity --> DTO로 변환된 목록(List)를 필드로 담고있는 PageResultDTO객체를
	 * 리턴하도록 메서드를 선언합니다
	 * 
	 * 이 메서드를 구현하는 구현체는 조인된 테이블의 쿼리를 통해 나오는 결과가 Object[]이기 때문에
	 * 아래 entityToDto를 파라미터 3개로 변경해서 하나의 DTO로 변경하도록 정의했음
	 */
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	//Entity --> DTO 로 변경하는 default 메서드 정의
	//BoardDTO 객체 하나에 Member, Board, 댓글수를 모두 넣어야 하기때문에
	//이 메서드는 파라미터 3개를 받아야합니다
	default BoardDTO entityToDto(Board board, Member member, Long repleCount) {
		BoardDTO boardDTO = BoardDTO.builder()
							.bno(board.getBno())
							.title(board.getTitle())
							.content(board.getContent())
							.regDate(board.getRegDate())
							.modDate(board.getModDate())
							.writereEmail(member.getEmail())
							.writereName(member.getName())
							.repleCount(repleCount.intValue())
							.build();
		return boardDTO;
		
	}
	

	//글등록 메서드
	/*
	 * 방명록에서와 같이 사용자의 요청을 BoardDTO에 담아서 등록하는 작업을 진행합니다
	 * 등록후에는 등록된 Board bno를 리턴해서 스크립트단에서 결과를ㄹ 출력하도록 합니다
	 */
	Long register(BoardDTO boardDTO);
	
	/*
	 * 등록자의 이메일은 Member 객체로 참조가 걸려 있으므로 Member객체를 통해 값을 설정합니다
	 * DTO --> Entity변환 default 정의
	 */
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder().email(dto.getWritereEmail()).build();
		//여기까지가 dto에서 사용자가 입력한 email설정 작업와료
		//위 멤버객체를 Board에 writer로 참조(ref)를 걸어야합니다
		Board board = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent()).writer(member).build();
		return board;
	}
	//글상세보기 메서드
	BoardDTO read(Long bno);
	
	//글삭제 메서드
	void removeWithReplies(Long bno);
	
	//글수정 메서드
	void modify(BoardDTO boardDTO);
}