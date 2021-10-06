package project.model;

public class Module {

    String moduleCode;
    String universityName;
    String term;
    String yearLevel;
    String moduleName;
    int moduleID;

    public Module(String moduleCode, String universityName, String term, String yearLevel, String moduleName, int moduleID) {
        this.moduleCode = moduleCode;
        this.universityName = universityName;
        this.term = term;
        this.yearLevel = yearLevel;
        this.moduleName = moduleName;
        this.moduleID = moduleID;
    }

    /*public Module(String moduleCode, String universityName, String term, String yearLevel, String moduleName) {
        this.moduleCode = moduleCode;
        this.universityName = universityName;
        this.term = term;
        this.yearLevel = yearLevel;
        this.moduleName = moduleName;
    }*/

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }
}
