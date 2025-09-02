package org.example.vpcpractice;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public ResponseEntity<Void> create(@RequestBody MemberCreateDto request) {
        Member member = new Member(request.name());
        Member saved = memberRepository.save(member);

        return ResponseEntity.created(URI.create("/members/" + saved.getId())).build();
    }

    @GetMapping("/members")
    public ResponseEntity<List<MembersDto>>getAll(){
        List<Member> members = memberRepository.findAll();
        List<MembersDto> result = members.stream()
                .map(member -> new MembersDto(member.getId(), member.getName()))
                .toList();

        return ResponseEntity.ok(result);
    }
}

