package app.ds3wiki.fragments

import app.ds3wiki.R

import app.ds3wiki.spells.SpellListFragment

import app.ds3wiki.weapons.WeaponListFragment

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

class InitFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.init_fragment_layout, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) = view.run {
        val spellSection = findViewById<View>(R.id.spell_section)
        val weaponSection = findViewById<View>(R.id.weapon_section)

        spellSection.setOnClickListener {
            parentFragmentManager.commit {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                replace(R.id.fragment_container, SpellListFragment(), "spell_list_fragment")
                addToBackStack("init_fragment")
            }
        }

        weaponSection.setOnClickListener {
            parentFragmentManager.commit {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                replace(R.id.fragment_container, WeaponListFragment(), "weapon_list_fragment")
                addToBackStack("init_fragment")
            }
        }
    }
}