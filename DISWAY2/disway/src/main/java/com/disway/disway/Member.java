package com.disway.disway;

public class Member {

	private final long id;
	private final double x;
	private final double y;
	private String problem;
	private boolean me;

	public Member(long id, double x, double y, String problem) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.problem = problem;
	}

	public long getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem= problem;
	}

	public boolean isMe() {
		return me;
	}

	public void setMe(boolean me) {
		this.me = me;
	}
	
}