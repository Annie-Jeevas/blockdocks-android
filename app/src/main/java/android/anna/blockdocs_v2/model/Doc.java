package android.anna.blockdocs_v2.model;

public class Doc {
    private int id;
    private int number;
    private String graduationDate;
    private String qualification;
    private String specialization;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Doc(int id, int number, String graduationDate, String qualification, String specialization) {
        this.id = id;
        this.number = number;
        this.graduationDate = graduationDate;
        this.qualification = qualification;
        this.specialization = specialization;
    }
}
