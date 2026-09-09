package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.MemberRequest;
import com.learnjava.libraraymanagementsystemv7.dto.MemberResponse;
import com.learnjava.libraraymanagementsystemv7.entity.Member;
import com.learnjava.libraraymanagementsystemv7.exception.MemberNotFoundException;
import com.learnjava.libraraymanagementsystemv7.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService
{
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public MemberResponse addMember(MemberRequest memberRequest) {

        Member member = new Member();

        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setPhone(memberRequest.getPhone());

        Member savedMember = memberRepository.save(member);

        return toMemberResponse(savedMember);
    }
    public List<MemberResponse> getAllMembers() {

        List<Member> members = memberRepository.findAll();

        List<MemberResponse> responses = new ArrayList<>();

        for (Member member : members) {

            MemberResponse response = toMemberResponse(member);

            responses.add(response);
        }
        return responses;
    }
    public MemberResponse getMemberById(int id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException(
                                "Member with id " + id + " not found"
                        ));
        return toMemberResponse(member);
    }
    public MemberResponse updateMember(int id, MemberRequest memberRequest) {

        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException(
                                "Member with id " + id + " not found"
                        ));

        existingMember.setName(memberRequest.getName());
        existingMember.setEmail(memberRequest.getEmail());
        existingMember.setPhone(memberRequest.getPhone());

        Member savedMember = memberRepository.save(existingMember);

        return toMemberResponse(savedMember);
    }
    public void deleteMemberById(int id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException(
                                "Member with id " + id + " not found"
                        ));

        memberRepository.delete(member);
    }
    private MemberResponse toMemberResponse(Member member) {

        MemberResponse response = new MemberResponse();

        response.setId(member.getId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());

        return response;
    }
}
