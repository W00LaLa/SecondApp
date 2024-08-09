package com.example.secondapp

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.secondapp.databinding.ActivityStorageBinding
import androidx.cursoradapter.widget.SimpleCursorAdapter

private  val fileName = "filenamecognizant"
private const val NAME = "name"
private const val PWD = "pwd"

class StorageActivity : AppCompatActivity() {

    lateinit var  binding: ActivityStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            // try {
            var a = 30/0
            /*} catch (e: Exception) {
                Log.i("MAin","alternate medicine")
            }*/
        }
    }

    override fun onPause() {
        super.onPause()
        storeData()
    }

    private fun storeData() {
        //create a file
        val preferences = getSharedPreferences(fileName, MODE_PRIVATE)
        //open the file
        val editor = preferences.edit()
        //write to the file
        editor.putString(NAME,binding.etName.text.toString())
        editor.putString(PWD,binding.etPassword.text.toString())
        //save the file
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        restoreData()

        /*val uriSms = Uri.parse("content://sms/inbox")
        val dataCursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)
        val fromColNames = arrayOf("address","body")
        val toTexviewIds = intArrayOf(android.R.id.text1,android.R.id.text2)
        var cursorAdaper = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            dataCursor,fromColNames,toTexviewIds)*/

        val cursor: Cursor? = getContentResolver().query(
            CallLog.Calls.CONTENT_URI,
            null, null, null, CallLog.Calls.DATE + " DESC"
        )
        // val uriSms = Uri.parse("content://sms/inbox")
        //val dataCursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)
        val colName = CallLog.Calls.NUMBER
        val fromColNames = arrayOf(colName)
        val toTexviewIds = intArrayOf(android.R.id.text1)
        var cursorAdaper = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,fromColNames,toTexviewIds)
        binding.listView.adapter = cursorAdaper
    }

    private fun restoreData() {
        //if file exists it'll open that file or create it
        val preferences = getSharedPreferences(fileName, MODE_PRIVATE)
        val name = preferences.getString(NAME,"")
        val password = preferences.getString(PWD,"")
        binding.etName.setText(name)
        binding.etPassword.setText(password)
    }
}