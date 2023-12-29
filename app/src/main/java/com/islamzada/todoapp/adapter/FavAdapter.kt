package com.islamzada.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.CardDesignFavBinding
import com.islamzada.todoapp.databinding.CardDesignNoteBinding
import com.islamzada.todoapp.entity.Favorite
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.fragments.MainFragmentDirections
import com.islamzada.todoapp.util.go

class FavAdapter (val context: Context, private var noteList: MutableList<Favorite>, var onClick: (Favorite) -> Unit, var onDeleteClick: (Favorite) -> Unit
) : BaseAdapter() {

    fun addNewItem(newnoteList: List<Favorite>) {
        // Mevcut ürün listesini temizle ve yeni ürünleri ekleyerek güncelle
        noteList.clear()
        noteList.addAll(newnoteList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return noteList.count()
    }

    override fun getItem(position: Int): Any {
        return noteList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var newConvertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            val binding: CardDesignFavBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.card_design_fav,
                parent,
                false
            )

            newConvertView = binding.root
            holder = ViewHolder(binding, onClick, onDeleteClick)
            holder.bind(noteList[position])

            newConvertView.tag = holder
        } else {

            holder = convertView.tag as ViewHolder

            holder.bind(noteList[position])
        }

        return newConvertView!!
    }

    private class ViewHolder(
        var binding: CardDesignFavBinding,
        var onClick: (Favorite) -> Unit,
        var onDeleteClick: (Favorite) -> Unit
    ) {
        fun bind(note: Favorite) {
            binding.textTitle.text = note.title
            binding.textDesc.text = note.desc

            binding.note = note

            binding.root.setOnClickListener {
                onClick(binding.note as Favorite)
            }

            binding.imageDelete.setOnClickListener {
                onDeleteClick(binding.note as Favorite)
            }
        }
    }
}