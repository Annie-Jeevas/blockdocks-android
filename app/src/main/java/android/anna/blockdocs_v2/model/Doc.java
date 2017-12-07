package android.anna.blockdocs_v2.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Doc {
    private int number;
    private String graduationDate;
    private String qualification;
    private String specialization;
    private String FIO;

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

    public Doc() {
    }

    public Doc(int number, String graduationDate, String qualification, String specialization, String FIO) {
        this.number = number;
        this.graduationDate = graduationDate;
        this.qualification = qualification;
        this.specialization = specialization;
        this.FIO = FIO;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public static Doc valueOfJson(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonInString, Doc.class);
    }
}
