package com.uteq.appmoviles.googlemapspractice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.uteq.appmoviles.googlemapspractice.Models.Country.CountryAll;
import com.uteq.appmoviles.googlemapspractice.Models.Country.CountryOne;
import com.uteq.appmoviles.googlemapspractice.Services.GeoService;
import com.uteq.appmoviles.googlemapspractice.databinding.ActivityMapsBinding;

import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String URL = "http://www.geognos.com/api/en/countries/";
    private static final String URL_FLAG = "http://www.geognos.com/api/en/countries/flag/%s.png";
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 0);
        ORIENTATIONS.append(Surface.ROTATION_90, 90);
        ORIENTATIONS.append(Surface.ROTATION_180, 180);
        ORIENTATIONS.append(Surface.ROTATION_270, 270);
    }

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ImageView imgBandera;
    private ImageView imgTest;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgBandera = findViewById(R.id.imgBandera);
        imgTest = findViewById(R.id.imgTest);
        txtResult = findViewById(R.id.txtResult);

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        //Obtener imagen de bundle
        Bitmap bitmap = scaleBitmapDown((Bitmap) bundle.get("data"), 500);

        //Procesado de imagen usando MLKit
        InputImage inputImage = null;
        inputImage = InputImage.fromBitmap(bitmap, 0);
        imgTest.setImageBitmap(bitmap);

        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        Task<Text> result =
                recognizer.process(inputImage)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {
                                String resultText = visionText.getText();
                                servicioRetrofitAll(resultText);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        txtResult.setText(e.getMessage());
                                    }
                                });
    }

    public void procesarImagen(View view) {
        //Abrir cámara
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, 101);
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private void servicioRetrofitAll(String countryName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GeoService geoService = retrofit.create(GeoService.class);

        Call<CountryAll> getAllCall = geoService.getAll();

        getAllCall.enqueue(new Callback<CountryAll>() {
            @Override
            public void onResponse(Call<CountryAll> call, Response<CountryAll> response) {
                for (Map.Entry<String, CountryOne> mapEntry: response.body().getResults().entrySet()){
                    if (mapEntry.getValue().getName().toLowerCase(Locale.ROOT).equals(countryName.toLowerCase(Locale.ROOT)))
                    {
                        servicioRetrofitOne(mapEntry.getKey());
                    }
                }
            }

            @Override
            public void onFailure(Call<CountryAll> call, Throwable t) {
                String test = "testing";
            }
        });
    }

    private void servicioRetrofitOne(String countryCode){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GeoService geoService = retrofit.create(GeoService.class);

        Call<JsonObject> getAllCall = geoService.getOne(countryCode);

        getAllCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    Gson gson = new Gson();
                    CountryOne countryOne = gson.fromJson(response.body().get("Results").toString(), CountryOne.class);
                    Glide.with(MapsActivity.this).load(String.format(URL_FLAG, countryCode)).into(imgBandera);
                    txtResult.setText(countryOne.toString());
                    reconfigurarMapa(countryOne);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                String test = "testing";
            }
        });
    }

    private void reconfigurarMapa(CountryOne country){
        mMap.clear();
        LatLng countryLatLng = new LatLng(Double.parseDouble(country.getGeoPt()[0]), Double.parseDouble(country.getGeoPt()[0]));

        PolygonOptions countryMargins = new PolygonOptions()
                .add(
                        new LatLng(country.getGeoRectangle().getSouth(), country.getGeoRectangle().getWest()),
                        new LatLng(country.getGeoRectangle().getNorth(), country.getGeoRectangle().getWest()),
                        new LatLng(country.getGeoRectangle().getNorth(), country.getGeoRectangle().getEast()),
                        new LatLng(country.getGeoRectangle().getSouth(), country.getGeoRectangle().getEast()),
                        new LatLng(country.getGeoRectangle().getSouth(), country.getGeoRectangle().getWest())
                )
                .strokeColor(Color.RED)
                .fillColor(Color.TRANSPARENT)
                .strokeWidth(10.0f);

        LatLng centerLatLng = null;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0 ; i < countryMargins.getPoints().size() ; i++)
        {
            builder.include(countryMargins.getPoints().get(i));
        }
        LatLngBounds bounds = builder.build();
        centerLatLng =  bounds.getCenter();

        mMap.addPolygon(countryMargins);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 1));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}