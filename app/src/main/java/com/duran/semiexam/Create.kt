package com.duran.semiexam

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.duran.semiexam.models.Model
import com.duran.semiexam.network.retro
import com.duran.semiexam.databinding.AddBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Create : AppCompatActivity() {
    private lateinit var binding: AddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.save.setOnClickListener {
            if(!binding.description.text.isNullOrBlank()) {
                createPost()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createPost() {
        val tweet = Model(
            id = "",
            name = binding.user.text.toString(),
            description = binding.description.text.toString()
        )
        retro.apiService.createPost(tweet).enqueue(object: Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                if (response.isSuccessful) {
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

    private fun showError() {
        Toast.makeText(this, "Failed to save data.", Toast.LENGTH_SHORT).show()
    }
}