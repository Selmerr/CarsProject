package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2,memberResponses.size(), "Expect 2 members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time,"Fisk");

    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        assertEquals(2,memberResponses.size(), "Expect 2 members");
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(memberResponses.get(0).getUsername(),"Username was not null");
        assertNull(time,"Created time was null");
        assertNull(memberResponses.get(0).getRanking());
    }

    @Test
    void testFindByIdFound() {
        MemberResponse response = memberService.findById("user1");
        assertEquals("user1",response.getUsername(), "Found a user with the username");
    }

    @Test
    void testFindByIdNotFound() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.findById("i don't exist"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember savex a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        MemberRequest mr = MemberRequest.builder().
                username("user3").
                password("pass").
                email("mail3").
                firstName("first").
                lastName("last").
                build();
        MemberResponse response = memberService.addMember(mr);
        assertEquals("user3",response.getUsername());
        assertTrue(memberRepository.existsById("user3"));
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        MemberRequest mr = new MemberRequest(m1);
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.addMember(mr), "user already exists");
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest request = new MemberRequest(m1);
        request.setFirstName("first");
        request.setLastName("last");
        memberService.editMember(request,request.getUsername());
        MemberResponse response = memberService.findById("user1");
        assertEquals(response.getUsername(), "user1");
        assertEquals("first",response.getFirstName());
        assertEquals("last",response.getLastName());
        assertEquals(response.getEmail(), m1.getEmail());
        assertEquals(response.getCity(), m1.getCity());
        assertEquals(response.getZip(), m1.getZip());
        assertEquals(response.getStreet(), m1.getStreet());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        MemberRequest request = new MemberRequest();
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request,"fisk"));
        assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("user3");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMember(request,"user1"));
        assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());
    }

    @Test
    void testSetRankingForUser() {
        memberService.editMemberRanking("user1",5);
        assertEquals(5,m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.editMemberRanking("user3",5),"Member with this username does not exist");
        assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMember("user1");
        assertFalse(memberRepository.existsById("user1"));
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> memberService.deleteMember("user3"),"Member with that id does not exist");
        assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
    }
}

