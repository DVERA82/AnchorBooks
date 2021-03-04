package com.example.anchorbooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anchorbooks.databinding.ItemListBookBinding
import com.example.anchorbooks.databinding.ItemListDetailBinding

class AdapterDetail : RecyclerView.Adapter<AdapterDetail.DetailVH>() {

    private var listbooksDetail = listOf<BooksDetail>()

    private var selectedItemDetail = MutableLiveData<BooksDetail>()

    fun selectedItemDetail() = selectedItemDetail

    fun update(list: List<BooksDetail>) {

        listbooksDetail= list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVH {
        return DetailVH(ItemListDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterDetail.DetailVH, position: Int) {
        val BooksDataClass = listbooksDetail[position]
        holder.bind(BooksDataClass)
    }

    override fun getItemCount(): Int = listbooksDetail.size


    inner class DetailVH(private val binding: ItemListDetailBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {

        fun bind(booksDetail: BooksDetail) {
            Glide.with(binding.ivLink)
                .load(booksDetail.imageLink)
                .into(binding.ivLink)
            binding.delivery.isClickable= false
            binding.delivery.isChecked= booksDetail.delivery
            binding.tvTitle2.text= booksDetail.title
            binding.tvAuthor2.text= booksDetail.author
            binding.tvPrice.text= booksDetail.price.toString()
            binding.tvPages.text= booksDetail.pages.toString()
            binding.webView.loadUrl(booksDetail.link)
            binding.webView.webViewClient = object : WebViewClient(){}
            val setting: WebSettings = binding.webView.settings
            setting.javaScriptEnabled = true
            binding.webView.loadUrl(booksDetail.link)
            itemView.setOnLongClickListener(this)


        }

        override fun onLongClick(v: View?): Boolean {
            selectedItemDetail.value = listbooksDetail[adapterPosition]
            return true
        }
    }
}
