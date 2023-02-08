package com.example.m214_retrofit_2_one_object;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView txt_nom, txt_prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=findViewById(R.id.imageView);
        txt_nom=findViewById(R.id.textView);
        txt_prix=findViewById(R.id.textView2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        PhoneAPI phoneAPI = retrofit.create(PhoneAPI.class);

        Call<Phone> call = phoneAPI.getPhone();

        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if(response.code()==200)
                {

                    Phone ph = response.body();

                    txt_nom.setText(ph.getNom());
                    txt_prix.setText(String.valueOf(ph.getPrix()));
                    Picasso.get().load(ph.getImage()).into(img);

                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Problem contacting API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}