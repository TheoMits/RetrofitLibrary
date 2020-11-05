package com.j2.retrofitlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.j2.retrofitlibrary.databinding.ActivityMainBinding;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this , SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText(getString(R.string.loading_dialog));
                pDialog.setCancelable(false);
                pDialog.show();

                new Manager().getData(new ManagerCompleteListener() {
                    @Override
                    public void onComplete(Object object, int statusCode) {
                        pDialog.dismiss();

                        if (statusCode == 200 && object instanceof ApiRes) {
                            content += ("id : " + ((ApiRes) object).getData().getId() + "\n");
                            content += ("email : " + ((ApiRes) object).getData().getEmail() + "\n");
                            content += ("first_name :" + ((ApiRes) object).getData().getFirst_name() + "\n");
                            content += ("last_name :" + ((ApiRes) object).getData().getLast_name() + "\n");
                            content += ("Company :" + ((ApiRes) object).getAd().getCompany() + "\n");
                            content += ("URL :" + ((ApiRes) object).getAd().getUrl() + "\n");
                            content += ("Text :" + ((ApiRes) object).getAd().getText() + "\n");
                            binding.text.setText(content);
                            Glide.with(MainActivity.this).load(((ApiRes) object).getData().getAvatar()).into(binding.imageView);
                        } else {
                            Dialogs.errorDialog(MainActivity.this);
                        }
                    }
                });
            }
        });

    }
}