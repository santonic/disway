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
	
	double DEFAULT_X = 265425.3;
	double DEFAULT_Y = 6248108;

	public MemberController() {
		list = new HashSet<Member>();

		list.add(new Member(nextId.getAndIncrement(), DEFAULT_X, DEFAULT_Y, "j'ai faim"));
		list.add(new Member(nextId.getAndIncrement(), DEFAULT_X + 45, DEFAULT_Y + 60, "j'ai froid"));
		list.add(new Member(nextId.getAndIncrement(), DEFAULT_X - 60, DEFAULT_Y + 90, "j'ai mal aux dents"));
		list.add(new Member(nextId.getAndIncrement(), DEFAULT_X + 40, DEFAULT_Y - 80, "j'ai froid"));
	}

	@RequestMapping("/")
    String index() {
        return "test";
    }
	
	@RequestMapping("/members")
	public Collection<Member> listMembers() {
		return list;
	}

	@RequestMapping("/members/_clear")
	public void clearMembers() {
		list.clear();
	}
	
	@RequestMapping("/members/_join")
	public Member addMember(
			@RequestParam(required=false) Double x, 
			@RequestParam(required=false) Double y) {
		int id = nextId.getAndIncrement();
		if(x == null) {
			x = randomize(DEFAULT_X);
			y = randomize(DEFAULT_Y);
		}
		Member member = new Member(id, x, y, null);
		list.add(member);
		return member;
	}
	
	private double randomize(double z) {
		if(Math.random()<0.5) {
			return z + Math.random()* 90;
		} else {
			return z - Math.random()* 90;
		}
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
