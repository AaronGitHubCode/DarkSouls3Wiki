package app.ds3wiki.fragments

import app.ds3wiki.R

import app.ds3wiki.activities.MainAppActivity

import app.ds3wiki.characters.CharacterViewModel
import app.ds3wiki.characters.CharacterAdapter

import app.ds3wiki.items.ItemListFragment

import app.ds3wiki.listeners.IAudioPlayer

import android.os.Bundle

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.ds3wiki.characters.CharacterDetailFragment

class HomeFragment : Fragment() {
    private lateinit var iAudioPlayer: IAudioPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.home_fragment_layout, container, false)

    override fun onAttach(context: Context): Unit {
        super.onAttach(context)

        iAudioPlayer = context as IAudioPlayer
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ): Unit = view.run {
        val activityBinding = activity as? MainAppActivity
        val characterViewModel: CharacterViewModel by viewModels<CharacterViewModel>()

        val itemSectionButton = findViewById<View>(R.id.item_section_button)

        characterViewModel.charactersLiveData.observe(viewLifecycleOwner) {
            val characters = it.toList()

            findViewById<RecyclerView>(R.id.characters_home_recycler_view).run {
                layoutManager = GridLayoutManager(context, 1)
                adapter = CharacterAdapter().apply {
                    onCharacterSelect = { character ->
                        parentFragmentManager.commit {
                            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            replace(R.id.fragment_container, CharacterDetailFragment(character), "character_detail_fragment")
                            addToBackStack("home_fragment")
                        }
                    }
                }
            }
        }

        itemSectionButton.setOnClickListener {
            parentFragmentManager.commit {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                replace(R.id.fragment_container, ItemListFragment(), "item_list_fragment")
                addToBackStack("home_fragment")
            }
        }
    }
}