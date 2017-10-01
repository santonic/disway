package com.disway.disway;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	AtomicInteger nextId = new AtomicInteger();
	private Collection<Member> list;

	public MemberController() {
		list = new HashSet<Member>();
		list.add(new Member(nextId.getAndIncrement(), 265425.3, 6248108, "j'ai faim"));
		list.add(new Member(nextId.getAndIncrement(), 265425.3 + 45, 6248108 + 60, "j'ai froid"));
		list.add(new Member(nextId.getAndIncrement(), 265425.3 - 60, 6248108 + 90, "j'ai mal aux dents"));
		list.add(new Member(nextId.getAndIncrement(), 265425.3 + 40, 6248108 - 80, "j'ai froid"));
	}

	@RequestMapping("/members")
	public Collection<Member> listMembers() {
		return list;
	}

	@RequestMapping("/members/_join")
	public Member addMember(@RequestParam double x, @RequestParam double y) {
		int id = nextId.getAndIncrement();
		Member member = new Member(id, x, y, null);
		list.add(member);
		return member;
	}
	
	@RequestMapping("/members/_addProblem")
	public Member addMember(@RequestParam long id, @RequestParam String problem) {
		for (Member member : list) {
			if(member.getId()==id) {
				member.setProblem(problem);
				return member;
			}
			
		}
		return null;
	}	

}
