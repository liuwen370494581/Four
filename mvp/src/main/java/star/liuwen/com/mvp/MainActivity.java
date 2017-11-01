package star.liuwen.com.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import star.liuwen.com.mvp.Ui.MvcActivity;
import star.liuwen.com.mvp.Ui.MvpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mvc_activity:
                startActivity(new Intent(MainActivity.this, MvcActivity.class));
                break;

            case R.id.mvp_activity:
                startActivity(new Intent(MainActivity.this, MvpActivity.class));
                break;

            default:
                break;
        }
    }
}
