package dg.com.proyecto.Splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import dg.com.proyecto.MainActivity;
import dg.com.proyecto.R;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 4000;

    private ImageView imgvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imgvw = (ImageView) findViewById(R.id.ivSplash);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.transition);
        imgvw.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
