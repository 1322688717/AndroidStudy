package com.example.kotlin.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.R
import com.example.kotlin.bean.Vertical
import com.example.kotlin.ui.home.WallPaperActivity

class AdapterLike(private var vertical: List<Vertical>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<AdapterLike.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImage: ImageView = itemView.findViewById(R.id.img_picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.example.kotlin.R.layout.item_wall_paper, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.w("TAG", "img==${vertical.get(0).img}")
        val options = RequestOptions.bitmapTransform(RoundedCorners(50)) // 图片圆角为30
        Glide.with(activity)
            .load(vertical[position].img)
            .apply(options)
            .into(holder.mImage)

        holder.mImage.setOnClickListener {
            val intent = Intent(activity, WallPaperActivity::class.java)
            intent.putExtra("img", vertical[position].img)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return vertical.size
    }
}
