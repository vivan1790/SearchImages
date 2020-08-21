package com.sample.loadimages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.sample.loadimages.model.Photo;
import com.sample.loadimages.viewmodel.PhotosViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private PhotosViewModel photosViewModel;
    private EditText searchField;
    private Button searchButton;
    private RecyclerView listPhotos;
    private PhotosListAdapter photosListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photosViewModel = new ViewModelProvider(this).get(PhotosViewModel.class);
        searchField = findViewById(R.id.search_field);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(searchField.getText())) {
                    updateViewModel(searchField.getText().toString());
                    try {
                        InputMethodManager imm =
                                (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        assert imm != null;
                        imm.hideSoftInputFromWindow(
                                Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        });
        photosListAdapter = new PhotosListAdapter(new DiffUtil.ItemCallback<Photo>() {
            @Override
            public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
                return oldItem.equals(newItem);
            }
        });
        listPhotos = findViewById(R.id.list_photos);
        listPhotos.setAdapter(photosListAdapter);
        listPhotos.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void updateViewModel(String queryText) {
        photosViewModel.getPhotosPagedList(queryText)
                .observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(PagedList<Photo> photos) {
                photosListAdapter.submitList(photos);
            }
        });
    }
}