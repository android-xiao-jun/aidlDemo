package jun.example.com.myaidldemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author        : Jun
 * time          : 2019年5月14日
 * description   : MyAIDLDemo
 */
public class Person implements Parcelable {
    private String mName;
    private int age;
    private int test;

    public Person(String name,int age) {
        mName = name;
        this.age = age;
    }

    protected Person(Parcel in) {
        mName = in.readString();
        age = in.readInt();
        test = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {

            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(age);
        dest.writeInt(3);
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                "age='" + age + '\'' +
                "test='" + test + '\'' +
                '}';
    }
}

