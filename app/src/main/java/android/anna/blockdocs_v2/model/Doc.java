package android.anna.blockdocs_v2.model;

public class Doc {
    private int id;
    private int number;
    private String graduationDate;
    private String qualification;
    private String specialization;
    private String FIO;

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

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Doc(int id, int number, String graduationDate, String qualification, String specialization, String FIO) {
        this.id = id;
        this.number = number;
        this.graduationDate = graduationDate;
        this.qualification = qualification;
        this.specialization = specialization;
        this.FIO = FIO;
    }
}
