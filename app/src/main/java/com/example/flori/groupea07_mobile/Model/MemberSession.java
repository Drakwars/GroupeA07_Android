package com.example.flori.groupea07_mobile.Model;

public class MemberSession {

    private static MemberSession instance;
    private Member member;

    private MemberSession() {}

    public static MemberSession getInstance()
    {
        if(instance == null)
            instance = new MemberSession();

        return instance;
    }

    public Member getMember()
    {
        return member;
    }

    public void setUser(Member member)
    {
        this.member = member;
    }

}
