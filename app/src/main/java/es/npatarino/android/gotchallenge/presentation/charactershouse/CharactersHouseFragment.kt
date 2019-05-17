package es.npatarino.android.gotchallenge.presentation.charactershouse

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.npatarino.android.gotchallenge.GotApplication
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.common.gone
import es.npatarino.android.gotchallenge.common.inflate
import es.npatarino.android.gotchallenge.common.visible
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.presentation.MainActivity
import es.npatarino.android.gotchallenge.presentation.characterdetail.CharacterDetailFragment
import es.npatarino.android.gotchallenge.presentation.houses.HousesFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import kotlinx.android.synthetic.main.fragment_characters.errorText
import kotlinx.android.synthetic.main.fragment_characters.progressBarLayout
import kotlinx.android.synthetic.main.fragment_characters.searchView
import kotlinx.android.synthetic.main.fragment_characters.sourcesList
import javax.inject.Inject

class CharactersHouseFragment : Fragment(), CharactersHouseContract.View {

    @Inject
    lateinit var presenter: CharactersHousePresenter

    private lateinit var adapter: FlexibleAdapter<AbstractFlexibleItem<*>>

    companion object {
        const val TITLE = "Characters House"
        private const val TAG = "CharactersHouseFragment"
        const val KEY = "CharactersHouse"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GotApplication.instance.applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_characters)

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        val house = arguments?.getParcelable<House>(HousesFragment.KEY)
        house?.houseId?.let { presenter.setView(this, it) }
    }

    private fun setupUI() {
        adapter = FlexibleAdapter(ArrayList<AbstractFlexibleItem<*>>())
        adapter.isAnimateChangesWithDiffUtil = true

        sourcesList.adapter = adapter
        sourcesList.layoutManager = LinearLayoutManager(context)
        sourcesList.isNestedScrollingEnabled = true

        searchView.gone()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBackButton(true)
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