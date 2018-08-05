package retrofitsample.example.org.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import retrofitsample.example.org.R;
import retrofitsample.example.org.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);

        if(mainFragment == null){
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_root, mainFragment, MainFragment.TAG)
                    .commit();
        }

    }
}
