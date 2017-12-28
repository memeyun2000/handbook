package com.sec;

public class User {
    public String username ;
    public String realname ;
    public int age;

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }
    /**
     * @return the realname
     */
    public String getRealname() {
        return realname;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * @param realname the realname to set
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
}