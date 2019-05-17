package es.npatarino.android.gotchallenge.presentation.houses

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
import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.presentation.MainActivity
import es.npatarino.android.gotchallenge.presentation.charactershouse.CharactersHouseFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import kotlinx.android.synthetic.main.fragment_characters.errorText
import kotlinx.android.synthetic.main.fragment_characters.progressBarLayout
import kotlinx.android.synthetic.main.fragment_houses.housesList
import javax.inject.Inject

class HousesFragment : Fragment(), HousesContract.View {

    @Inject
    lateinit var presenter: HousesPresenter

    private lateinit var adapter: FlexibleAdapter<AbstractFlexibleItem<*>>

    companion object {
        const val TITLE = "Houses"
        private const val TAG = "HousesFragment"
        const val KEY = "House"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GotApplication.instance.applicationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_houses)

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        presenter.setView(this)
    }

    private fun setupUI() {
        adapter = FlexibleAdapter(ArrayList<AbstractFlexibleItem<*>>())
        adapter.isAnimateChangesWithDiffUtil = true

        housesList.adapter = adapter
        housesList.layoutManager = LinearLayoutManager(context)
        housesList.isNestedScrollingEnabled = true
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBackButton(false)
    }

    private fun showErrorMessage() {
        housesList.gone()
        errorText.visible()
    }

    override fun onHouseClicked(house: House) {
        val bundle = Bundle()
        bundle.putParcelable(KEY, house)

        val fragment = CharactersHouseFragment()
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

    override fun showHouses(sourcesList: List<AbstractFlexibleItem<*>>) {
        adapter.clear()
        adapter.updateDataSet(sourcesList)
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