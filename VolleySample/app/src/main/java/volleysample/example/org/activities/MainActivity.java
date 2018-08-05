package volleysample.example.org.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import volleysample.example.org.R;
import volleysample.example.org.model.requests.SendMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://randomuser.me/api/?results=15&exc=location,login,id,email,registered,cell,nat";

        SendMessage sendMessage = new SendMessage(getApplicationContext(), url, "request");
        sendMessage.sendMessageVolley();
    }
}
