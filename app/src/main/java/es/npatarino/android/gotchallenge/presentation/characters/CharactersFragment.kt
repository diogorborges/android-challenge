package es.npatarino.android.gotchallenge.presentation.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.npatarino.android.gotchallenge.GotApplication
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.gone
import es.npatarino.android.gotchallenge.common.inflate
import es.npatarino.android.gotchallenge.common.visible
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.presentation.MainActivity
import es.npatarino.android.gotchallenge.presentation.characterdetail.CharacterDetailFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import kotlinx.android.synthetic.main.fragment_characters.errorText
import kotlinx.android.synthetic.main.fragment_characters.progressBarLayout
import kotlinx.android.synthetic.main.fragment_characters.searchView
import kotlinx.android.synthetic.main.fragment_characters.sourcesList
import javax.inject.Inject

class CharactersFragment : Fragment(), CharactersContract.View, SearchView.OnQueryTextListener {

    @Inject
    lateinit var presenter: CharactersPresenter

    private lateinit var adapter: FlexibleAdapter<AbstractFlexibleItem<*>>

    companion object {
        const val TITLE = "Characters"
        private const val TAG = "CharactersFragment"
        const val KEY = "Characters"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GotApplication.instance.applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(es.npatarino.android.gotchallenge.R.layout.fragment_characters)

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        presenter.setView(this)
    }

    private fun setupUI() {
        (activity as MainActivity).showBackButton(false)

        adapter = FlexibleAdapter(ArrayList<AbstractFlexibleItem<*>>())
        adapter.isAnimateChangesWithDiffUtil = true

        sourcesList.adapter = adapter
        sourcesList.layoutManager = LinearLayoutManager(context)
        sourcesList.isNestedScrollingEnabled = true

        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.getCharacterByQuery("%${newText!!}%")
        return false
    }

    private fun showErrorMessage() {
        sourcesList.gone()
        errorText.visible()
    }

    override fun onCharacterClicked(character: Character) {
        val bundle = Bundle()
        bundle.putParcelable(KEY, character)

        val fragment = CharacterDetailFragment()
        fragment.arguments = bundle

        (context as MainActivity)
            .supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .add(R.id.main_container, fragment, MainActivity.FRAGMENT_KEY)
            .addToBackStack(null)
            .commit()
    }

    override fun showLoader(show: Boolean) = with(progressBarLayout) {
        when (show) {
            true -> visible()
            else -> gone()
        }
    }

    override fun showCharacters(charactersList: List<AbstractFlexibleItem<*>>) {
        adapter.clear()
        adapter.updateDataSet(charactersList)
    }

    override fun showError(message: String?) {
        message?.let {
            Log.e(TAG, "Error: $it")
            showErrorMessage()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}