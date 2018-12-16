package com.example.flori.groupea07_mobile.Model;

public class Member {

    private int idUser;
    private String emailUser;
    private String username;
    private String userPwd;
    private int userAdmin;

    public Member(int id,String email, String user, String pwd, int admin){
            this.idUser = id;
            this.emailUser = email;
            this.username = user;
            this.userPwd = pwd;
            this.userAdmin = admin;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int id) {
        this.idUser = id;
    }

    public String getEmailUser(){ return emailUser;}

    public void setEmailUser(String email){ this.emailUser = email;}

    public String getUsername(){ return username;}

    public void setUsername( String user){ this.username = user;}

    public String getUserPwd(){ return userPwd;}

    public void setUserPwd(String pwd){ this.userPwd = pwd;}

    public int getUserAdmin(){ return userAdmin;}

    public void setUserAdmin(int admin){ this.userAdmin = admin;}

    @Override
    public String toString() {
        return "Member{" +
                "idUser=" + idUser +
                ", emailUser='" + emailUser + '\'' +
                ", username='" + username + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userAdmin=" + userAdmin +
                '}';
    }
}
