package dat3.car.service;

import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {

        List<Member> members = memberRepository.findAll();
        List<MemberResponse> response = new ArrayList<>();

        for (Member member : members) {
            MemberResponse mr = new MemberResponse(member, includeAll);
            response.add(mr);
        }
        return response;
    }
}
