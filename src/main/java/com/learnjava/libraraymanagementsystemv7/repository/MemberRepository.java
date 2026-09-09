package com.learnjava.libraraymanagementsystemv7.repository;

import com.learnjava.libraraymanagementsystemv7.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>
{

}
