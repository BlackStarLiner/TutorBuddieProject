package project.model;

public class Session {

    String sessionTime;
    int timeSlot;

    public Session(String sessionTime, int timeSlot) {
        this.sessionTime = sessionTime;
        this.timeSlot = timeSlot;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }
}
