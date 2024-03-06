package app.ds3wiki.spells

import app.ds3wiki.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

typealias SpellViewHolder = SpellAdapter.SpellViewHolder

class SpellAdapter : Adapter<SpellViewHolder>() {
    lateinit var onSpellSelect: (Spell) -> Unit

    inner class SpellViewHolder(internal val view: View) : ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpellViewHolder = SpellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.spell_row_layout, parent, false))

    override fun getItemCount(): Int = spellsRepository.size

    override fun onBindViewHolder(
        holder: SpellViewHolder,
        position: Int
    ): Unit = holder.view.run {
        val spellName = findViewById<TextView>()
        val spellDescription = findViewById<TextView>()

        setOnClickListener {
            onSpellSelect(spellsRepository.getSpell(position))
        }
    }
}