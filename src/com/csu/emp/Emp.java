package com.csu.emp;

public class Emp {

    private int id;
    private String name;
    //部门编号
    private int number;
    //部门名
    private String job;
    private int salary;
    //采暖补贴
    private int allowance;
    private int day1;
    private int day1sal;
    private int day2;
    private int daysal2;
    //个人所得税
    private int personalsal;
    //养老保险
    private int personalsafe;

    public int getTotalSal() {
        return totalSal;
    }

    public void setTotalSal(int totalSal) {
        this.totalSal = totalSal;
    }

    private int totalSal;


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

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


    public int getDay1sal() {
        return day1sal;
    }

    public void setDay1sal(int day1sal) {
        this.day1sal = day1sal;
    }

    public int getDaysal2() {
        return daysal2;
    }

    public void setDaysal2(int daysal2) {
        this.daysal2 = daysal2;
    }

    public int getPersonalsal() {
        return personalsal;
    }

    public void setPersonalsal(int personalsal) {
        this.personalsal = personalsal;
    }

    public int getPersonalsafe() {
        return personalsafe;
    }

    public void setPersonalsafe(int personalsafe) {
        this.personalsafe = personalsafe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}