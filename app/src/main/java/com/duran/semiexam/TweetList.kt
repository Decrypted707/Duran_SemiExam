package com.duran.semiexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.duran.semiexam.adapters.Adapter
import com.duran.semiexam.databinding.ActivityTweetListBinding
import com.duran.semiexam.models.Model
import com.duran.semiexam.network.retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetList : AppCompatActivity() {
    private lateinit var binding: ActivityTweetListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)
        binding = ActivityTweetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.add.setOnClickListener {
            startActivity(Intent(this, Create::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getTweet()
    }

    private fun getTweet() {
        val activity = this
        retro.apiService.getPostList().enqueue(object: Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                if (response.isSuccessful) {
                    val data: List<Model>? = response.body()
                    if(data != null) {
                        binding.tweetList.layoutManager = LinearLayoutManager(activity)
                        binding.tweetList.adapter = Adapter(activity, data)
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "No Tweets to load.", Toast.LENGTH_SHORT).show()
    }
}