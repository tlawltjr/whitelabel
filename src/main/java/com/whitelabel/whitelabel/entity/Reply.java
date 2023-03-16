package com.whitelabel.whitelabel.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * 댓글을 관리하는 엔티티입니다. 필드로는 
 * 댓글 번호pno(PK), 댓글 내용, 작성자(비회원도 가능)
 * 따라서 이 테이블은 Board 와 M : 1관계를 맺게되는데
 * 식별이 아닌 비식별관계로 설정됩니다(이 말은 존재할 수도 아닐 수도 있습니다) 
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	private String text;
	private String replyer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
}
