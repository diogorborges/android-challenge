package es.npatarino.android.gotchallenge.presentation.characterdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.npatarino.android.gotchallenge.GotApplication
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.getCharacterImage
import es.npatarino.android.gotchallenge.common.inflate
import es.npatarino.android.gotchallenge.common.setThumbnailImage
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.presentation.MainActivity
import es.npatarino.android.gotchallenge.presentation.characters.CharactersFragment
import es.npatarino.android.gotchallenge.presentation.charactershouse.CharactersHouseFragment
import kotlinx.android.synthetic.main.fragment_character_detail.descriptionText
import kotlinx.android.synthetic.main.fragment_character_detail.nameText
import kotlinx.android.synthetic.main.fragment_character_detail.thumbnailImage

class CharacterDetailFragment : Fragment() {

    companion object {
        const val TITLE = "Character Detail"
        private const val TAG = "CharactersDetailFragment"
        const val KEY = "CharacterDetail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GotApplication.instance.applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_character_detail)

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Character>(CharactersFragment.KEY)?.let { setupUI(it) }
            ?: arguments?.getParcelable<Character>(CharactersHouseFragment.KEY)?.let { setupUI(it) }
    }

    private fun setupUI(character: Character) = with(character) {
        setCharacterImage(name)
        setCharacterName(name)
        setCharacterDescription(description)
    }

    private fun setCharacterDescription(description: String) = with(descriptionText) {
        text = description
    }

    private fun setCharacterName(name: String) = with(nameText) {
        text = name
    }

    private fun setCharacterImage(name: String) = with(thumbnailImage) {
        setThumbnailImage(this, getCharacterImage(name))
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBackButton(true)
    }
}