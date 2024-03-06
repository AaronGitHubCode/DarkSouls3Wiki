package app.ds3wiki.spells

val spellsRepository = SpellRepository()

class SpellRepository {
    private val spells = mutableListOf<Spell>()

    val size = spells.size

    fun getSpell(index: Int): Spell = spells[index]

    fun addSpell(vararg spell: Spell): Unit {
        spells.addAll(spell)
    }

    fun removeSpell(vararg spell: Spell): Unit {
        spells.removeAll(spell.toSet())
    }
}