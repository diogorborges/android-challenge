package es.npatarino.android.gotchallenge.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.npatarino.android.gotchallenge.GotApplication
import es.npatarino.android.gotchallenge.R
import es.npatarino.android.gotchallenge.presentation.characters.CharactersFragment
import es.npatarino.android.gotchallenge.presentation.houses.HousesFragment
import kotlinx.android.synthetic.main.activity_container.toolbar
import kotlinx.android.synthetic.main.activity_main.navigation

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private var title: String = CharactersFragment.TITLE
    private lateinit var fragment: Fragment

    companion object {
        const val FRAGMENT_KEY: String = "fragment"
        private const val TITLE_KEY: String = "title"
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GotApplication.instance.applicationComponent.inject(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { onBackPressed() }
        navigation.setOnNavigationItemSelectedListener(this)

        title = savedInstanceState?.getString(TITLE_KEY) ?: title

        if (supportFragmentManager.findFragmentByTag(FRAGMENT_KEY) == null) {
            changeFragment(CharactersFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_characters -> {
                title = CharactersFragment.TITLE
                fragment = CharactersFragment()
            }
            R.id.navigation_houses -> {
                title = HousesFragment.TITLE
                fragment = HousesFragment()
            }
        }

        toolbar.title = title
        changeFragment(fragment)

        return true
    }

    override fun onResume() {
        super.onResume()
        toolbar.title = title
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(
                R.id.main_container, fragment,
                FRAGMENT_KEY
            )
            .commit()
    }

    fun showBackButton(show: Boolean) = with(toolbar) {
        when (show) {
            true -> navigationIcon = resources.getDrawable(R.drawable.ic_white_back_arow)
            else -> navigationIcon = null
        }
    }
}