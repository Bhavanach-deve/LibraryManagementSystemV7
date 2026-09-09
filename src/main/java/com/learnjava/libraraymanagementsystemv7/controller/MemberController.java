package com.learnjava.libraraymanagementsystemv7.controller;


import com.learnjava.libraraymanagementsystemv7.dto.MemberRequest;
import com.learnjava.libraraymanagementsystemv7.dto.MemberResponse;
import com.learnjava.libraraymanagementsystemv7.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController
{
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> addMember(
            @Valid @RequestBody MemberRequest memberRequest) {

        MemberResponse response = memberService.addMember(memberRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {

        List<MemberResponse> responses = memberService.getAllMembers();

        return ResponseEntity.ok(responses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(
            @PathVariable int id) {

        MemberResponse response = memberService.getMemberById(id);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(
            @PathVariable int id,
            @Valid @RequestBody MemberRequest memberRequest) {

        MemberResponse response =
                memberService.updateMember(id, memberRequest);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(
            @PathVariable int id) {

        memberService.deleteMemberById(id);

        return ResponseEntity.noContent().build();
    }
}
