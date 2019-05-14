package jun.example.com.myaidldemo;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import jun.example.com.myaidldemo.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * author        : Jun
 * time          : 2019年5月14日
 * description   : MyAIDLDemo
 */
public class MyAidlService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<Person> mPersons;

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */
    private IBinder mIBinder = new IMyAidl.Stub() {


        @Override
        public void addPerson(jun.example.com.myaidldemo.bean.Person person) throws RemoteException {
            Log.e(TAG,"服务收到一条消息："+person.toString());
            mPersons.add(person);
        }

        @Override
        public List<jun.example.com.myaidldemo.bean.Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     * @param intent
     * @return
     */

    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        return mIBinder;
    }
}
