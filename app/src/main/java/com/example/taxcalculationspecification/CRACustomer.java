package com.example.taxcalculationspecification;


import android.os.Parcel;
import android.os.Parcelable;

public class CRACustomer implements Parcelable  {

    private String sinNo;
    private String firstName;
    private String lastName;
    private String Birthdate;
    private String age;
    private String Gender;
    private Double GrossIncome;
    private Double RRSP;

    protected CRACustomer(Parcel in) {
        sinNo = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        Birthdate = in.readString();
        age = in.readString();
        Gender = in.readString();
        if (in.readByte() == 0) {
            GrossIncome = null;
        } else {
            GrossIncome = in.readDouble();
        }
        if (in.readByte() == 0) {
            RRSP = null;
        } else {
            RRSP = in.readDouble();
        }
    }

    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    public CRACustomer() {
    }

    public CRACustomer(String sinNo, String firstName, String lastName, String birthdate, String age, String gender, Double grossIncome, Double RRSP) {
        this.sinNo = sinNo;
        this.firstName = firstName;
        this.lastName = lastName;
        Birthdate = birthdate;
        this.age = age;
        Gender = gender;
        GrossIncome = grossIncome;
        this.RRSP = RRSP;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sinNo);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(Birthdate);
        dest.writeString(age);
        dest.writeString(Gender);
        if (GrossIncome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(GrossIncome);
        }
        if (RRSP == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(RRSP);
        }
    }

    public String getSinNo() {
        return sinNo;
    }

    public void setSinNo(String sinNo) {
        this.sinNo = sinNo;
    }

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

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Double getGrossIncome() {
        return GrossIncome;
    }

    public void setGrossIncome(Double grossIncome) {
        GrossIncome = grossIncome;
    }

    public Double getRRSP() {
        return RRSP;
    }

    public void setRRSP(Double RRSP) {
        this.RRSP = RRSP;
    }

    public static Creator<CRACustomer> getCREATOR() {
        return CREATOR;
    }
}