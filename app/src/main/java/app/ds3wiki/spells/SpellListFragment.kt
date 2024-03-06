package app.ds3wiki.spells

import app.ds3wiki.R

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpellListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.spell_list_fragment, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) = view.findViewById<RecyclerView>(R.id.spell_list).run {
        layoutManager = GridLayoutManager(context, 1)
        adapter =
    }
}