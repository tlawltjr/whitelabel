package com.whitelabel.whitelabel.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.whitelabel.whitelabel.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	


}