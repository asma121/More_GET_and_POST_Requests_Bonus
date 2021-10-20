package com.example.moregetandpostrequestsbonus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etname: EditText
    lateinit var etlocation: EditText
    lateinit var etmob: EditText
    lateinit var etemail: EditText
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var rv:RecyclerView
    var userdata:List<UsersDetails.Datum>?=null
    var displayResponse =ArrayList<ArrayList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etname=findViewById(R.id.etname)
        etlocation=findViewById(R.id.etlocation)
        etmob=findViewById(R.id.etmob)
        etemail=findViewById(R.id.etemail)
        button=findViewById(R.id.button)
        button2=findViewById(R.id.button2)
        rv=findViewById(R.id.rv)

        button.setOnClickListener {
            var f = UsersDetails.Datum(etname.text.toString(),etlocation.text.toString(),etmob.text.toString(),etemail.text.toString())
            addUserDetails(f, onResult = {
            })
        }

        button2.setOnClickListener {
            getUserDetails(onResult = {
                userdata = it
                val datumList = userdata
                for (datum in datumList!!) {
                    displayResponse+= arrayListOf("${datum.name}","${datum.location}","${datum.mobile}","${datum.email}")
                }
            })
            rv.adapter=myAdapter(displayResponse)
            rv.layoutManager= LinearLayoutManager(this)
        }

    }

    private fun addUserDetails(f :UsersDetails.Datum ,onResult: () -> Unit){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addUserDetails(f)?.enqueue(object :
                Callback<UsersDetails.Datum?> {
                override fun onResponse(call: Call<UsersDetails.Datum?>, response: Response<UsersDetails.Datum?>) {
                    Toast.makeText(applicationContext,"user added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<UsersDetails.Datum?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun getUserDetails(onResult: (List<UsersDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getUserDetails()?.enqueue(object : Callback<List<UsersDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<UsersDetails.Datum>>,
                    response: Response<List<UsersDetails.Datum>>
                ) {
                    onResult(response.body())
                }
                override fun onFailure(call: Call<List<UsersDetails.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }
}