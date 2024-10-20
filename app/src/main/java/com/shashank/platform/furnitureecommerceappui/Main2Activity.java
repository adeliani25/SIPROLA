package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main2Activity extends AppCompatActivity {

    LinearLayout personLinearLayout;
    FloatingActionButton favorite;
    CardView cactusCardView;
    CardView responPengajuanCardView; // Tambahkan ini untuk Respon Pengajuan
    Button laporanButton;
    Button proposalButton;
    Button berkasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Menghubungkan elemen-elemen di layout dengan kode
        personLinearLayout = findViewById(R.id.person_linear_layout);
        favorite = findViewById(R.id.favorite);
        cactusCardView = findViewById(R.id.cactus_card_view);
        laporanButton = findViewById(R.id.laporan_button);
        proposalButton = findViewById(R.id.proposal_button);
        berkasButton = findViewById(R.id.berkas_button);
        responPengajuanCardView = findViewById(R.id.respon_pengajuan_card_view); // Menghubungkan CardView Respon Pengajuan

        // Menambahkan fungsi untuk klik personLinearLayout
        personLinearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);
        });

        // Menambahkan fungsi untuk klik tombol favorite
        favorite.setOnClickListener(view -> showPopupMenu(view));

        // Menambahkan fungsi untuk klik CardView cactusCardView
        cactusCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
            startActivity(intent);
        });

        // Menambahkan fungsi untuk klik proposalButton (respon pengajuan)
        proposalButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });

        // Menambahkan fungsi untuk klik responPengajuanCardView
        responPengajuanCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main6Activity.class);
            startActivity(intent); // Berpindah ke Main6Activity saat Respon Pengajuan ditekan
        });

        laporanButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });

        berkasButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.favorite_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.option_one:
                        Intent intent = new Intent(Main2Activity.this, OptionOneActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.option_two:
                        // Handle option two click
                        return true;
                    case R.id.option_three:
                        // Handle option three click
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
}