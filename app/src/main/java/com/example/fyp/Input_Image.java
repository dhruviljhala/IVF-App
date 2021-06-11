package com.example.fyp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Input_Image extends AppCompatActivity {

    private Button button_next_page,button_upload,button_choose_image;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_image);

        button_next_page=findViewById(R.id.next_page);
        button_upload = findViewById(R.id.button_upload);
        button_choose_image = findViewById(R.id.button_choose_image);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Embryo Images Uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Parameter Uploads");

        button_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Input_Image.this, Prediction.class));
                Input_Image.super.finish();
            }
        });


        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!=null && mUploadTask.isInProgress()){}else {
                    uploadImage();
                }
            }
        });


        button_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });


    }

    private void OpenFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData() != null){
            //IF image in Chosen and is not empty

            mImageUri = data.getData(); //Save Chosen IMAGE URI

            Picasso.with(this).load(mImageUri).into(mImageView); //Load chosen Image into mImageView
        }
    }

    private  String getFileExtension(Uri Uri){ // returns extension and type of file uploaded
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cr.getType(Uri));
    }

    private   void uploadImage(){
        if(mImageUri!=null){
            //Create unique reference fro storage of each image with IamgeUri and System time
            StorageReference fileRefereance = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            mUploadTask = fileRefereance.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //mProgressBar.setProgress(); -> Too FAST No visual feedback ,Hence we add delay fro 0.5 secinds
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress((0));
                                }
                            }, 500 );
                            Toast.makeText(Input_Image.this , "Upload Sucessful" , Toast.LENGTH_LONG).show();;
                            Input_parameters obj = new Input_parameters();
                            Upload Upload = obj.upload_all;
                            Upload.setmName(FirebaseAuth.getInstance().getUid().toString().trim());
                            Upload.setmImageUri(taskSnapshot.getUploadSessionUri().toString());

                            //Storing reference in Database for image uploaded
                            String uploadid = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadid).setValue(Upload);

                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Display Error
                            Toast.makeText(Input_Image.this,e.getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot tasksnapshot) {
                            //TRACK PROGRESS OF UPLOAD AND UPDATE ON Progressbar
                            double progress = (100.0 * tasksnapshot.getBytesTransferred() / tasksnapshot.getTotalByteCount());
                            mProgressBar.setProgress( (int) progress);
                        }
                    });
        }else
        {
            Toast.makeText(this,"No Image or File Selected",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_page:
                startActivity(new Intent(this, Prediction.class));
                break;
            case R.id.button_upload:
                Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_SHORT).show();
                uploadImage();
                break;
        }
    }
};