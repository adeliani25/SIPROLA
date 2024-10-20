package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OptionThreeActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private TextView textFileName;
    private EditText editJudulKegiatan, editDosenPembina, editTanggalKegiatan, editDeskripsiKegiatan;
    private Button buttonUploadFile, buttonSubmit;
    private ArrayList<String> selectedFileNames = new ArrayList<>();
    private Uri photoUri; // URI untuk foto yang diambil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_three);

        textFileName = findViewById(R.id.text_file_name);
        editJudulKegiatan = findViewById(R.id.edit_judul_kegiatan);
        editDosenPembina = findViewById(R.id.edit_dosen_pembina);
        editTanggalKegiatan = findViewById(R.id.edit_tanggal_kegiatan);
        editDeskripsiKegiatan = findViewById(R.id.edit_deskripsi_kegiatan);
        buttonUploadFile = findViewById(R.id.button_upload_file);
        buttonSubmit = findViewById(R.id.button_submit);

        buttonUploadFile.setOnClickListener(v -> openFileChooser());

        buttonSubmit.setOnClickListener(v -> {
            // Kirim proposal ke server (implementasikan logika pengiriman file di sini)
        });
    }

    private void openFileChooser() {
        String[] options = {"Pilih File", "Ambil Foto"};
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Pilih Opsi")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        // Pilih File
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*"); // Mengizinkan semua jenis file
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Mengizinkan pemilihan beberapa file
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(Intent.createChooser(intent, "Pilih File"), FILE_SELECT_CODE);
                    } else if (which == 1) {
                        // Ambil Foto
                        takePhoto();
                    }
                })
                .show();
    }

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Buat file untuk menyimpan foto
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error saat membuat file
                Toast.makeText(this, "Gagal membuat file gambar", Toast.LENGTH_SHORT).show();
            }
            // Jika berhasil membuat file, ambil foto
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, "com.shashank.platform.furnitureecommerceappui.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Buat file gambar dengan nama unik
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(null);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                selectedFileNames.clear(); // Kosongkan daftar sebelumnya
                for (int i = 0; i < count; i++) {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    String fileName = getFileName(uri);
                    selectedFileNames.add(fileName);
                }
                textFileName.setText(selectedFileNames.toString()); // Tampilkan semua nama file
            } else if (data.getData() != null) {
                Uri uri = data.getData();
                String fileName = getFileName(uri);
                selectedFileNames.clear();
                selectedFileNames.add(fileName);
                textFileName.setText(fileName);
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Foto berhasil diambil, tampilkan foto di ImageView
            ImageView imagePreview = findViewById(R.id.image_preview);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                imagePreview.setImageBitmap(bitmap);
                imagePreview.setVisibility(View.VISIBLE);
            }
        }
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
