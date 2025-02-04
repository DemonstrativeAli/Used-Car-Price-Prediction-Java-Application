package Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.usedcarprice.R;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PredictionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        // Viewleri tanımladık
        EditText etBrand = findViewById(R.id.etBrand);
        EditText etModel = findViewById(R.id.etModel);
        EditText etMileage = findViewById(R.id.etMileage);
        EditText etEngineCapacity = findViewById(R.id.etEngineCapacity);
        EditText etAccidents = findViewById(R.id.etAccidents);
        Spinner spinnerTransmission = findViewById(R.id.spinnerTransmission);
        Spinner spinnerFuelType = findViewById(R.id.spinnerFuelType);
        Button btnPredict = findViewById(R.id.btnPredict);
        TextView tvPredictionResult = findViewById(R.id.tvPredictionResult);

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcı girişlerini al
                String brand = etBrand.getText().toString().trim();
                String model = etModel.getText().toString().trim();
                String mileage = etMileage.getText().toString().trim();
                String engineCapacity = etEngineCapacity.getText().toString().trim();
                String accidents = etAccidents.getText().toString().trim();
                String transmission = spinnerTransmission.getSelectedItem().toString();
                String fuelType = spinnerFuelType.getSelectedItem().toString();

                // Boş alan kontrolü
                if (brand.isEmpty() || model.isEmpty() || mileage.isEmpty() || engineCapacity.isEmpty() || accidents.isEmpty()) {
                    Toast.makeText(PredictionActivity.this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tahmin isteğini yap
                predictPrice(brand, model, mileage, engineCapacity, accidents, transmission, fuelType, tvPredictionResult);
            }
        });
    }

    private void predictPrice(String brand, String model, String mileage, String engineCapacity, String accidents,
                              String transmission, String fuelType, TextView tvPredictionResult) {
        try {
            // JSON isteği oluştur
            JSONObject requestData = new JSONObject();
            requestData.put("Mil", Double.parseDouble(mileage));
            requestData.put("Motor Hacmi", Double.parseDouble(engineCapacity));
            requestData.put("Kaza Sayısı", Integer.parseInt(accidents));

            // Spinner değerlerini JSON anahtarına dönüştürme
            String transmissionKey = "Vites_" + transmission.replace(" ", "_");
            String fuelTypeKey = "Benzin Türü_" + fuelType.replace(" ", "_");

            requestData.put(transmissionKey, 1);  // Vites türü
            requestData.put(fuelTypeKey, 1);      // Benzin türü

            // Marka ve model için dinamik anahtarlar
            requestData.put("Marka_" + brand, 1);
            requestData.put("Model_" + model, 1);

            // OkHttp ile API çağrısı
            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(requestData.toString(), JSON);

            // Flask API URL'si
            Request request = new Request.Builder()
                    .url("http://10.202.5.143:5001/predict") // Flask API URL'sini buraya yazın
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // Hata durumunda
                    runOnUiThread(() -> Toast.makeText(PredictionActivity.this, "Tahmin başarısız: " + e.getMessage(), Toast.LENGTH_LONG).show());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        try {
                            // Yanıtı JSON formatında işle
                            JSONObject jsonResponse = new JSONObject(responseBody);
                            double predictedPrice = jsonResponse.getDouble("predicted_price");

                            // Tahmini ekrana yazdır
                            runOnUiThread(() -> {
                                tvPredictionResult.setText("Tahmin Edilen Fiyat: " + predictedPrice + "$");
                                tvPredictionResult.setVisibility(View.VISIBLE);
                            });
                        } catch (Exception e) {
                            runOnUiThread(() -> Toast.makeText(PredictionActivity.this, "Yanıt işleme hatası: " + e.getMessage(), Toast.LENGTH_LONG).show());
                        }
                    } else {
                        runOnUiThread(() -> Toast.makeText(PredictionActivity.this, "API Yanıt Hatası! Kod: " + response.code(), Toast.LENGTH_LONG).show());
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Hata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}