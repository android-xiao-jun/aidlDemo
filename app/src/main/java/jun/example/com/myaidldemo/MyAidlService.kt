package jun.example.com.myaidldemo

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.util.Log
import jun.example.com.myaidldemo.bean.Person

/**
 *     author        : Jun
 *     time          : 2019年5月14日
 *     description   : MyAIDLDemo
 */
class MyAidlService : Service() {

    val TAG: String = "MyAidlService";

    lateinit var mPersons: ArrayList<Person>;

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */

    private val mIBinder: IBinder = object : IMyAidl.Stub() {
        override fun getPersonList(): MutableList<Person> {
            return mPersons;
        }

        override fun addPerson(person: Person) {
            Log.e(TAG, "服务收到一条消息：" + person.toString());
            mPersons.add(person)
        }

        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            // 验证权限 返回false代表权限未验证通过
            val check = checkCallingOrSelfPermission("jun.example.com.myaidldemo.permission.ACCESS_OFFER_SERVICE")
            if (check == PackageManager.PERMISSION_DENIED) {
                return false
            }

            val packages = packageManager.getPackagesForUid(Binder.getCallingUid())
            if (packages != null && packages.size > 0) {
                if (!packages[0].endsWith("myaidldemo")) {
                    return false
                }
            }
            return super.onTransact(code, data, reply, flags)
        }
    }

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     * @param intent
     * @return
     */
    override fun onBind(intent: Intent?): IBinder? {
        mPersons = ArrayList<Person>();
        return mIBinder;
    }


}