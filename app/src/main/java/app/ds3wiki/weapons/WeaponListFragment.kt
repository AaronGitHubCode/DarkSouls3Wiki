package app.ds3wiki.weapons

import app.ds3wiki.R

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.RecyclerView

class WeaponListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.weapon_list_fragment, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ): Unit = view.findViewById<RecyclerView>(R.id.weapon_list).run {

    }
}