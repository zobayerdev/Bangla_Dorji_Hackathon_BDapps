package com.trodev.bangladorjiadmin;

public class User {

    public String sname, semail, spass, snumber, saddress;

    public User() {
    }

    public User(String sname, String semail, String spass, String snumber, String saddress) {
        this.sname = sname;
        this.semail = semail;
        this.spass = spass;
        this.snumber = snumber;
        this.saddress = saddress;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }
}
