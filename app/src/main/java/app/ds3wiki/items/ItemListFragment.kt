package app.ds3wiki.items

import app.ds3wiki.R

import app.ds3wiki.listeners.IAudioPlayer

import android.os.Bundle

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemListFragment : Fragment() {
    private lateinit var iAudioPlayer: IAudioPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.item_list_fragment_layout, container, false)

    override fun onAttach(context: Context): Unit {
        super.onAttach(context)

        iAudioPlayer = context as IAudioPlayer
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ): Unit = view.findViewById<RecyclerView>(R.id.items_list).run {
        layoutManager = GridLayoutManager(context, 1)
        adapter = ItemAdapter().apply {
            onItemSelected = { item ->
                //iAudioPlayer.onPlayResource(R.raw.)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container, ItemDetailFragment(item), "item_detail_fragment")
                    addToBackStack("item_list_fragment")
                }
            }
        }
    }
}