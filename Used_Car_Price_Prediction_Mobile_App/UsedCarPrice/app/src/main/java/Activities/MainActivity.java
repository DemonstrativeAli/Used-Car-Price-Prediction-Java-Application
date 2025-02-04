package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usedcarprice.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Görünümleri tanımla
        // ImageView ivLogo = findViewById(R.id.ivLogo);
        TextView tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);
        Button btnStartPrediction = findViewById(R.id.btnStartPrediction);
        Button btnViewVehicles = findViewById(R.id.btnViewVehicles); // Araç Markalarını Gör butonu

        // Animasyonları yükle
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        // Animasyonları uygula
        // ivLogo.startAnimation(fadeIn);
        tvWelcomeMessage.startAnimation(slideUp);
        btnStartPrediction.startAnimation(slideUp);
        btnViewVehicles.startAnimation(slideUp);

        // Fiyat Tahmini Yap butonu tıklama işlemi
        btnStartPrediction.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PredictionActivity.class);
            startActivity(intent);
        });

        // Araç Markalarını Gör butonu tıklama işlemi
        btnViewVehicles.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VehicleImagesActivity.class); // Araç markalarını gösteren activity
            startActivity(intent);
        });
    }
}