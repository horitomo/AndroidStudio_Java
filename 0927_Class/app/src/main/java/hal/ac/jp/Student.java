package hal.ac.jp;

import java.io.Serializable;

public class Student implements Serializable {
    private int studentNum;
    private String className;
    private String studentName;

    // setter&getter生成
    public String getClassName() {
        return className;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
