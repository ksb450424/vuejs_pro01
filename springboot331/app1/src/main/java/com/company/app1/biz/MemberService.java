package com.company.app1.biz;

import com.company.app1.entity.Member;
import com.company.app1.per.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

    public Member updateMember(String id, Member updatedMember) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setPw(updatedMember.getPw());
                    member.setName(updatedMember.getName());
                    member.setBirth(updatedMember.getBirth());
                    member.setEmail(updatedMember.getEmail());
                    member.setTel(updatedMember.getTel());
                    member.setAddr1(updatedMember.getAddr1());
                    member.setAddr2(updatedMember.getAddr2());
                    member.setPostcode(updatedMember.getPostcode());
                    return memberRepository.save(member);
                })
                .orElseGet(() -> {
                    updatedMember.setId(id);
                    return memberRepository.save(updatedMember);
                });
    }

    public Optional<Member> login(String id, String pw) {
        return memberRepository.findById(id)
                .filter(member -> member.getPw().equals(pw));
    }

    public boolean isIdAvailable(String id) {
        return !memberRepository.existsById(id);
    }

    public Optional<Member> getMyPage(String id) {
        return memberRepository.findById(id);
    }
}