package jun.example.com.myaidldemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import jun.example.com.myaidldemo.bean.Person
import java.util.*

/**
 *     author        : Jun
 *     time          : 2019年5月14日
 *     description   : MyAIDLDemo
 */
class ClientActivity : AppCompatActivity() {
    lateinit var mAidl: IMyAidl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

//        val intent1: Intent = Intent(getApplicationContext(), MyAidlService::class.java)
        val intent1: Intent = Intent()
        intent1.setClassName("jun.example.com.myaidldemo","jun.example.com.myaidldemo.MyAidlService")
        bindService(intent1, mConnection, BIND_AUTO_CREATE)

        //如果是危险权限需要申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf("jun.example.com.myaidldemo.permission.ACCESS_OFFER_SERVICE"), 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }

    fun addPerson(view: View) {
        var random: Random = Random()
        var anInt: Int = random.nextInt(10)
        var person: Person = Person("shixin" + anInt, anInt)
        mAidl.addPerson(person)
        findViewById<TextView>(R.id.show).text = mAidl.personList.toString()
    }

    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
//            mAidl=null;   kotiln中不用这样操作
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidl.Stub.asInterface(service)
        }

    }

}