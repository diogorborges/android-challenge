package es.npatarino.android.gotchallenge.presentation.characterdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.npatarino.android.gotchallenge.GotApplication
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.inflate
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.presentation.MainActivity
import es.npatarino.android.gotchallenge.presentation.characters.CharactersFragment
import es.npatarino.android.gotchallenge.presentation.charactershouse.CharactersHousePresenter
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import javax.inject.Inject

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

        val character = arguments?.getParcelable<Character>(CharactersFragment.KEY)
        character?.let { setupUI(it) }
    }

    private fun setupUI(character: Character?) {

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBackButton(true)
    }
}