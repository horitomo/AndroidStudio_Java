package hal.ac.jp;

public class Company {
    private String companyName;
    private String visitDate;
    private String visitTime;
    private String visitPurpose;
    private String visitPlace;

    public String companyStr() {
        return visitDate+" "+visitTime+ " "+companyName+" "+visitPurpose+" "+visitPlace;
    }

    public Company(String companyName, String visitDate, String visitTime, String visitPurpose, String visitPlace) {
        this.companyName = companyName;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visitPurpose = visitPurpose;
        this.visitPlace = visitPlace;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getVisitPlace() {
        return visitPlace;
    }

    public void setVisitPlace(String visitPlace) {
        this.visitPlace = visitPlace;
    }
}
