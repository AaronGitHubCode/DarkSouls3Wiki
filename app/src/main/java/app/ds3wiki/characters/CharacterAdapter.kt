package app.ds3wiki.characters

import app.ds3wiki.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

typealias CharacterViewHolder = CharacterAdapter.CharacterViewHolder

class CharacterAdapter : Adapter<CharacterViewHolder>() {
    lateinit var onCharacterSelect: (Character) -> Unit

    private val characterRepository = CharacterRepository.getInstance()

    inner class CharacterViewHolder(internal val view: View) : ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder = CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_row_layout, parent, false))

    override fun getItemCount(): Int = characterRepository.repositorySize

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) = holder.view.run {
        setOnClickListener {
            onCharacterSelect(characterRepository.getCharacter(position))
        }
    }
}