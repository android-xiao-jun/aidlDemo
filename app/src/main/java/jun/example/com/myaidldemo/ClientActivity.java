package jun.example.com.myaidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import jun.example.com.myaidldemo.bean.Person;

import java.util.List;
import java.util.Random;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
        bindService(intent1, mConnection, BIND_AUTO_CREATE);
    }
    public void addPerson(View view) {
        Random random = new Random();
        int anInt = random.nextInt(10);
        Person person = new Person("shixin" + anInt,anInt);

        try {
            mAidl.addPerson(person);
            List<Person> personList = mAidl.getPersonList();
            ((TextView) findViewById(R.id.show)).setText(personList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private IMyAidl mAidl;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };
}
