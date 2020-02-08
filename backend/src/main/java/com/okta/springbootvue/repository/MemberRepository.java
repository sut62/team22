package com.okta.springbootvue.repository;

import java.util.Optional;

import com.okta.springbootvue.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(long id);

    @Query(value = "SELECT * FROM MEMBER WHERE MEMBER_TEL = :tel",nativeQuery = true)
    Optional<Member> findByTel(@Param("tel") String tel);
}
