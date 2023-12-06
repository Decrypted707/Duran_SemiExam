package com.duran.semiexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.duran.semiexam.constants.Consts
import com.duran.semiexam.databinding.TweetPostBinding
import com.duran.semiexam.models.Model
import com.duran.semiexam.network.retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetPost : AppCompatActivity() {
    private lateinit var binding: TweetPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TweetPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val Id = intent.getStringExtra(Consts.PARAM_ID)
        val tweetUser = intent.getStringExtra(Consts.PARAM_TWEET_USER)
        val tweetDescription = intent.getStringExtra(Consts.PARAM_TWEET_DESCRIPTION)
        binding.user.text =  tweetUser
        binding.description.text =  tweetDescription
        binding.delete.setOnClickListener {
            if(Id != null){
                deletePostById(Id)
                finish()
            }
        }
        binding.update.setOnClickListener {
            val intent = Intent(this, Update::class.java)
            intent.putExtra(Consts.PARAM_ID, Id)
            intent.putExtra(Consts.PARAM_TWEET_USER, tweetUser)
            intent.putExtra(Consts.PARAM_TWEET_DESCRIPTION, tweetDescription)
            this.startActivity(intent)
        }
    }
    private fun deletePostById(Id:String){
        retro.apiService.deletePostById(Id).enqueue(object: Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                if (response.isSuccessful) {
                    showSuccess()
                    finish()
                } else {
                    showError()
                }
            }
            override fun onFailure(call: Call<Model>, t: Throwable) {
                showError()
            }
        })
    }
    private fun showSuccess() {
        Toast.makeText(this, "Delete Successful.", Toast.LENGTH_SHORT).show()
    }
    private fun showError() {
        Toast.makeText(this, "Delete Failed.", Toast.LENGTH_SHORT).show()
    }

}