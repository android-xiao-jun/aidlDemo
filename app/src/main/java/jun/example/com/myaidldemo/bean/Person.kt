package jun.example.com.myaidldemo.bean

import android.os.Parcel
import android.os.Parcelable

/**
 *     author        : Jun
 *     time          : 2019年5月14日
 *     description   : MyAIDLDemo
 */
class Person() : Parcelable {
    lateinit var  mName:String
    var  age:Int = 0
    var  test:Int = 0
    constructor(name:String , age:Int) : this() {
        mName = name
        this.age = age
    }
    constructor(parcel: Parcel) : this() {
        mName=parcel.readString()
        age=parcel.readInt()
        test=parcel.readInt()
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(mName)
        dest?.writeInt(age)
        dest?.writeInt(3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Person{" +
                "mName='" + mName + '\'' +
                "age='" + age + '\'' +
                "test='" + test + '\'' +
                '}'
    }

}