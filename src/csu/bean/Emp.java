package csu.bean;

import java.sql.Timestamp;

public class Emp {
    private int id;
    private String account;
    private String pasWord;
    private String name;
    private int deptno;//≤ø√≈±‡∫≈
    private String dept;
    private int basSal;
    private int heatMoney;
    private int offDays;
    private int offDiscount;
    private int overDays;
    private int ovePays;
    private int PersonalIncomeTax;
    private int pensionInsurance;
    private String timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasWord() {
        return pasWord;
    }

    public void setPasWord(String pasWord) {
        this.pasWord = pasWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getBasSal() {
        return basSal;
    }

    public void setBasSal(int basSal) {
        this.basSal = basSal;
    }

    public int getHeatMoney() {
        return heatMoney;
    }

    public void setHeatMoney(int heatMoney) {
        this.heatMoney = heatMoney;
    }

    public int getOffDays() {
        return offDays;
    }

    public void setOffDays(int offDays) {
        this.offDays = offDays;
    }

    public int getOffDiscount() {
        return offDiscount;
    }

    public void setOffDiscount(int offDiscount) {
        this.offDiscount = offDiscount;
    }

    public int getOverDays() {
        return overDays;
    }

    public void setOverDays(int overDays) {
        this.overDays = overDays;
    }

    public int getOvePays() {
        return ovePays;
    }

    public void setOvePays(int ovePays) {
        this.ovePays = ovePays;
    }

    public int getPersonalIncomeTax() {
        return PersonalIncomeTax;
    }

    public void setPersonalIncomeTax(int personalIncomeTax) {
        PersonalIncomeTax = personalIncomeTax;
    }

    public int getPensionInsurance() {
        return pensionInsurance;
    }

    public void setPensionInsurance(int pensionInsurance) {
        this.pensionInsurance = pensionInsurance;
    }

    public String getTimeStamp() {
        return String.valueOf(timeStamp);
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = String.valueOf(timeStamp);
    }
}
