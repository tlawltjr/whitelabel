package com.whitelabel.whitelabel.service;

import com.whitelabel.whitelabel.dto.ReplyDTO;
import com.whitelabel.whitelabel.entity.Board;
import com.whitelabel.whitelabel.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO ReplyDTO); //댓글의 등록

    List<ReplyDTO> getList(Long bno); //특정 게시물의 댓글 목록

    void modify(ReplyDTO ReplyDTO); //댓글 수정

    void remove(Long rno); //댓글 삭제

    //DTO --> Entity변환
    default Reply dtoToEntity(ReplyDTO ReplyDTO){

    	//제목글 얻어와야 하기에 Board 객체 생성
        Board board = Board.builder().bno(ReplyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .pno(ReplyDTO.getPno())
                .text(ReplyDTO.getText())
                .replyer(ReplyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    //Reply객체를 ReplyDTO로 변환 Board 객체가 필요하지 않으므로 게시물 번호만
    default ReplyDTO entityToDTO(Reply Reply){

        ReplyDTO dto = ReplyDTO.builder()
                .pno(Reply.getPno())
                .text(Reply.getText())
                .replyer(Reply.getReplyer())
                .regDate(Reply.getRegDate())
                .modDate(Reply.getModDate())
                .build();

        return dto;

    }
}