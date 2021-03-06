package kr.co.study.firebase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ch20_firebase.util.dateToString
import com.google.firebase.storage.StorageReference
import kr.co.study.firebase.databinding.ActivityAddBinding
import java.io.File
import java.util.*

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode===10 && resultCode=== Activity.RESULT_OK){
            Glide
                .with(getApplicationContext())
                .load(data?.data)
                .apply(RequestOptions().override(250, 200))
                .centerCrop()
                .into(binding.addImageView)


            val cursor = contentResolver.query(data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null);
            cursor?.moveToFirst().let {
                filePath=cursor?.getString(0) as String
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_add_gallery){
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, 10)
        }else if(item.itemId === R.id.menu_add_save){
            if(binding.addImageView.drawable !== null && binding.addEditView.text.isNotEmpty()){
                //store ??? ?????? ???????????? ????????? document id ????????? ????????? ?????? ?????? ??????
                saveStore()
            }else {
                Toast.makeText(this, "???????????? ?????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }
    //....................
    private fun saveStore(){
        val data = mapOf(
            "email" to MyApplication.email,
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date())
        )
        MyApplication.db.collection("news")
            .add(data)
            .addOnSuccessListener {
                // ??????????????? ????????? ?????? ??? id????????? ??????????????? ????????? ?????????
                uploadImage(it.id)
            }
            .addOnFailureListener {
                Log.w("TESTTEST", "data save error", it)
            }
        
    }
    private fun uploadImage(docId: String){
        val storage = MyApplication.storage
        // ??????????????? ???????????? StorageReference ??????
        val storageRef : StorageReference = storage.reference
        // ?????? ??????????????? ????????? ???????????? StorageReference ??????
        val imgRef: StorageReference = storageRef.child("images/${docId}.jpg")
        // ?????? ?????????
        var file = Uri.fromFile(File(filePath))
        imgRef.putFile(file)
            .addOnFailureListener {
                Log.d("TESTTEST", " failure..........." + it)
            } .addOnSuccessListener {
                Toast.makeText(this, "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
                finish()
            }
        
    }
}