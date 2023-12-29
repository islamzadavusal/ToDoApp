package com.islamzada.todoapp.adapter

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.islamzada.todoapp.R
import com.islamzada.todoapp.databinding.CardDesignNoteBinding
import com.islamzada.todoapp.entity.Notes
import com.islamzada.todoapp.fragments.MainFragmentDirections
import com.islamzada.todoapp.util.go

class MainAdapter (val context: Context, private var noteList: MutableList<Notes>, var onClick: (Notes) -> Unit, var onDeleteClick: (Notes) -> Unit
) : BaseAdapter() {

    fun addNewItem(newnoteList: List<Notes>) {
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
            val binding: CardDesignNoteBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.card_design_note,
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
        var binding: CardDesignNoteBinding,
        var onClick: (Notes) -> Unit,
        var onDeleteClick: (Notes) -> Unit
    ) {
        fun bind(note: Notes) {
            binding.textNote.text = note.note

            binding.note = note

            binding.root.setOnClickListener {
                onClick(binding.note as Notes)
            }

            binding.imageDelete.setOnClickListener {
                onDeleteClick(binding.note as Notes)
            }

            binding.imageUpdate.setOnClickListener {
                val transition = MainFragmentDirections.toUpdate()
                Navigation.go(it,transition)
            }
        }
    }
}