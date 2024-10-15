package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main2Activity extends AppCompatActivity {

    LinearLayout personLinearLayout;
    FloatingActionButton favorite;
    CardView cactusCardView;
    Button laporanButton;
    Button proposalButton;
    Button berkasButton;// Tombol Laporan

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
        berkasButton = findViewById(R.id.berkas_button);// Menghubungkan laporan_button

        // Menambahkan fungsi untuk klik personLinearLayout
        personLinearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);
        });

        // Menambahkan fungsi untuk klik tombol favorite
        favorite.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
            startActivity(intent);
        });

        // Menambahkan fungsi untuk klik CardView cactusCardView
        cactusCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });

        // Menambahkan fungsi untuk klik laporanButton (tombol Laporan di Arsip Dokumen)
        laporanButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent); // Berpindah ke Main5Activity saat tombol Laporan ditekan
        });

        proposalButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent); // Berpindah ke Main5Activity saat tombol Laporan ditekan
        });

        berkasButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent); // Berpindah ke Main5Activity saat tombol Laporan ditekan
        });
    }
}
