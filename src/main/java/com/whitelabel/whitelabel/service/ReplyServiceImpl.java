package com.whitelabel.whitelabel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.whitelabel.whitelabel.dto.ReplyDTO;
import com.whitelabel.whitelabel.entity.Board;
import com.whitelabel.whitelabel.entity.Reply;
import com.whitelabel.whitelabel.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        return reply.getPno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result =  replyRepository
                .getRepByBoardOrderByPno(Board.builder().bno(bno).build());

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

    }

    @Override
    public void remove(Long pno) {

        replyRepository.deleteById(pno);
    }


}
