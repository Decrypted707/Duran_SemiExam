package com.duran.semiexam
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.duran.semiexam.constants.Consts
import com.duran.semiexam.models.Model
import com.duran.semiexam.network.retro
import com.duran.semiexam.databinding.UpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Update : AppCompatActivity() {
    private lateinit var binding: UpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.update.setOnClickListener {

            if(!binding.user.text.isNullOrBlank()||!binding.description.text.isNullOrBlank()) {
                updateTweet()
            }
        }
    }

    private fun updateTweet() {
        val tweetId = intent.getStringExtra(Consts.PARAM_ID)

        val post = Model(
            id = tweetId.toString(),
            name = binding.user.text.toString(),
            description = binding.description.text.toString()
        )
        retro.apiService.updateTweet(post, tweetId.toString()).enqueue(object:
            Callback<Model> {
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
        Toast.makeText(this, "Update Successful.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, TweetList::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    private fun showError() {
        Toast.makeText(this, "Update Failed.", Toast.LENGTH_SHORT).show()
    }
}