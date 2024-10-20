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

    // Deklarasi elemen UI
    LinearLayout personLinearLayout;
    FloatingActionButton favorite;
    CardView cactusCardView;
    CardView responPengajuanCardView; // CardView untuk Respon Pengajuan
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
        responPengajuanCardView = findViewById(R.id.respon_pengajuan_card_view); // CardView untuk Respon Pengajuan

        // Fungsi ketika personLinearLayout diklik, pindah ke Main3Activity
        personLinearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
            startActivity(intent);
        });

        // Fungsi untuk menampilkan PopupMenu ketika tombol favorite ditekan
        favorite.setOnClickListener(this::showPopupMenu);

        // Fungsi ketika cactusCardView diklik, pindah ke Main4Activity
        cactusCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
            startActivity(intent);
        });

        // Fungsi ketika proposalButton diklik, pindah ke Main5Activity (Respon Pengajuan)
        proposalButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });

        // Fungsi ketika responPengajuanCardView diklik, pindah ke Main6Activity
        responPengajuanCardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main6Activity.class);
            startActivity(intent); // Berpindah ke Main6Activity saat Respon Pengajuan ditekan
        });

        // Fungsi ketika laporanButton diklik, pindah ke Main5Activity
        laporanButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });

        // Fungsi ketika berkasButton diklik, pindah ke Main5Activity
        berkasButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
            startActivity(intent);
        });
    }

    // Menampilkan PopupMenu saat tombol favorite ditekan
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.favorite_menu, popupMenu.getMenu());

        // Set menu item click listener
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.option_one:
                    startActivity(new Intent(Main2Activity.this, OptionOneActivity.class));
                    return true;
                case R.id.option_two:
                    startActivity(new Intent(Main2Activity.this, OptionTwoActivity.class));
                    return true;
                case R.id.option_three:
                    startActivity(new Intent(Main2Activity.this, OptionThreeActivity.class));
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }
}
