package com.company.app1.ctrl;

import com.company.app1.biz.MemberService;
import com.company.app1.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/list")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public Member registerMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestParam String id, @RequestParam String pw) {
        Optional<Member> member = memberService.login(id, pw);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Boolean> checkId(@PathVariable String id) {
        return ResponseEntity.ok(memberService.isIdAvailable(id));
    }

    @GetMapping("/mypage/{id}")
    public ResponseEntity<Member> getMyPage(@PathVariable String id) {
        Optional<Member> member = memberService.getMyPage(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Member member) {
        Member updatedMember = memberService.updateMember(id, member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}