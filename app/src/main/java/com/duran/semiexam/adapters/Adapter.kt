package com.duran.semiexam.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duran.semiexam.TweetPost
import com.duran.semiexam.constants.Consts
import com.duran.semiexam.models.Model
import com.duran.semiexam.databinding.TweetBinding

class Adapter(
private val activity: Activity,
private val tweetList: List<Model>,
): RecyclerView.Adapter<Adapter.TweetViewHolder>() {


        class TweetViewHolder(
            private val activity: Activity,
            private val binding: TweetBinding,
        ): RecyclerView.ViewHolder(binding.root){
        fun bind(tweet: Model) {
            binding.user.text = tweet.name
            binding.description.text = tweet.description
            binding.root.setOnClickListener {
                val intent = Intent(activity, TweetPost::class.java)
                intent.putExtra(Consts.PARAM_ID, tweet.id)
                intent.putExtra(Consts.PARAM_TWEET_USER, tweet.name)
                intent.putExtra(Consts.PARAM_TWEET_DESCRIPTION, tweet.description)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TweetBinding.inflate(
            inflater,
            parent,
            false,
        )
        return TweetViewHolder(activity, binding)
    }

    override fun getItemCount() = tweetList.size

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(tweetList[position])
    }

}