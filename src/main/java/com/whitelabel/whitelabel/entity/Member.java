package com.whitelabel.whitelabel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * PK는 email로 사용, 암호, 이름정도만 컬럼으로 설계예정입니다.
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{

	@Id
	private String email;
	private String password;
	private String name;
}