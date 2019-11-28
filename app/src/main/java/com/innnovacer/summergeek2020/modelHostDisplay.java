package com.innnovacer.summergeek2020;

public class modelHostDisplay extends blogpostid {
    String hname;
    String hph;

    public modelHostDisplay(String hname, String hph, String hemail) {
        this.hname = hname;
        this.hph = hph;
        this.hemail = hemail;
    }
    modelHostDisplay(){}
    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHph() {
        return hph;
    }

    public void setHph(String hph) {
        this.hph = hph;
    }

    public String getHemail() {
        return hemail;
    }

    public void setHemail(String hemail) {
        this.hemail = hemail;
    }

    String hemail;
}
