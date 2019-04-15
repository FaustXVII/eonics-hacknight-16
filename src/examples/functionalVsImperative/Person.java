package examples.functionalVsImperative;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private boolean unemployed;
    private BigDecimal annualSalary;
    private int hoursPerWeek;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isUnemployed() {
        return unemployed;
    }

    public void setUnemployed(boolean unemployed) {
        this.unemployed = unemployed;
    }

    public BigDecimal getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public BigDecimal getHourlyWage(){
        return unemployed? BigDecimal.ZERO : getAnnualSalary()
            .divide(BigDecimal.valueOf(52),3, RoundingMode.HALF_UP)
            .divide(BigDecimal.valueOf(getHoursPerWeek()), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            ", gender=" + gender +
            ", unemployed=" + unemployed +
            ", annualSalary=" + annualSalary +
            ", hoursPerWeek=" + hoursPerWeek +
            '}';
    }
}

enum Gender{
    MALE, FEMALE, OTHER
}
