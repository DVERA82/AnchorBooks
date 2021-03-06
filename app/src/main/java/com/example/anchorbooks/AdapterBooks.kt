package com.example.anchorbooks

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anchorbooks.databinding.ItemListBookBinding
import kotlin.math.log

    class AdapterBooks : RecyclerView.Adapter<AdapterBooks.BooksVH> () {

        private var listAdapterBooks = listOf<AnchorBooks>()

        private var selectedItem = MutableLiveData<AnchorBooks>()

    fun selectedItem():LiveData<AnchorBooks> = selectedItem

    fun update(list: List<AnchorBooks>) {

        listAdapterBooks= list
        notifyDataSetChanged()
    }

    inner class BooksVH(private val binding: ItemListBookBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
           fun bind(anchorBooks: AnchorBooks) {
            Glide.with(binding.imageLinkBooks)
                .load(anchorBooks.imageLink)
                .into(binding.imageLinkBooks)
             if (anchorBooks.favourite){
                 binding.ivFavourite.setColorFilter(Color.YELLOW)
               }else {
                 binding.ivFavourite.setColorFilter(Color.BLACK)
             }
               binding.tvTitle.text= anchorBooks.title
               binding.tvLanguage.text = anchorBooks.language

               Log.d("list books","${anchorBooks.id}" )
                       itemView.setOnClickListener(this)

           }


        override fun onClick(v: View?) {
          selectedItem.value = listAdapterBooks[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBooks.BooksVH {
       return BooksVH(ItemListBookBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterBooks.BooksVH, position: Int) {
       val booksDataClass = listAdapterBooks[position]
        holder.bind(booksDataClass)
    }

    override fun getItemCount(): Int = listAdapterBooks.size

}