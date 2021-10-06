package project.model;

public class Session {

    String sessionStartTime;
    String sessionEndTime;
    String sessionDay;
    String sessionType;
    int tutorModID;
    int studentID;

    public Session(String sessionStartTime, String sessionEndTime, String sessionDay, String sessionType, int tutorModID, int studentID) {
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.sessionDay = sessionDay;
        this.sessionType = sessionType;
        this.tutorModID = tutorModID;
        this.studentID = studentID;
    }

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(String sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public String getSessionDay() {
        return sessionDay;
    }

    public void setSessionDay(String sessionDay) {
        this.sessionDay = sessionDay;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public int getTutorModID() {
        return tutorModID;
    }

    public void setTutorModID(int tutorModID) {
        this.tutorModID = tutorModID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
