package es.npatarino.android.gotchallenge.common

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.npatarino.android.gotchallenge.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.getString(id: Int): String = context.getString(id)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            val difference = SystemClock.elapsedRealtime() - lastClickTime
            when {
                difference < debounceTime -> return
                else -> action()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.getCharacterImage(name: String): Int = when (name) {
    CharactersName.CHARACTER_OLLY -> R.mipmap.olly_image
    CharactersName.CHARACTER_ALISSER -> R.mipmap.alisser_image
    CharactersName.CHARACTER_BERIC -> R.mipmap.beric_image
    CharactersName.CHARACTER_XARO -> R.mipmap.xaro_image
    CharactersName.CHARACTER_LORAS -> R.mipmap.loras_image
    CharactersName.CHARACTER_GREY -> R.mipmap.grey_image
    CharactersName.CHARACTER_OBARA -> R.mipmap.obara_image
    CharactersName.CHARACTER_DORAN -> R.mipmap.doran_image
    CharactersName.CHARACTER_GREGOR -> R.mipmap.gregor_image
    CharactersName.CHARACTER_LANCEL -> R.mipmap.lancel_image
    CharactersName.CHARACTER_YARA -> R.mipmap.yara_image
    CharactersName.CHARACTER_WALDER -> R.mipmap.walder_image
    CharactersName.CHARACTER_RENLY -> R.mipmap.renly_image
    CharactersName.CHARACTER_MYRCELLA -> R.mipmap.myrcella_image
    CharactersName.CHARACTER_LYSA -> R.mipmap.lysa_image
    CharactersName.CHARACTER_ROBIN -> R.mipmap.robin_image
    CharactersName.CHARACTER_BROHN -> R.mipmap.bronn_image
    CharactersName.CHARACTER_CERSEI -> R.mipmap.cersei_image
    CharactersName.CHARACTER_OBERYN -> R.mipmap.oberyn_image
    CharactersName.CHARACTER_HODOR -> R.mipmap.hodor_image
    CharactersName.CHARACTER_JORAH -> R.mipmap.jon_image
    CharactersName.CHARACTER_LORD -> R.mipmap.lord_image
    CharactersName.CHARACTER_MARGAERY -> R.mipmap.margaery_image
    CharactersName.CHARACTER_SANSA -> R.mipmap.sansa_image
    CharactersName.CHARACTER_PETYR -> R.mipmap.petyr_image
    CharactersName.CHARACTER_EDDARD -> R.mipmap.eddard_image
    CharactersName.CHARACTER_KHAL -> R.mipmap.khal_image
    CharactersName.CHARACTER_THEON -> R.mipmap.theon_image
    CharactersName.CHARACTER_STANNIS -> R.mipmap.stannis_image
    CharactersName.CHARACTER_JON -> R.mipmap.jon_image
    CharactersName.CHARACTER_JAIME -> R.mipmap.jaime_image
    CharactersName.CHARACTER_ARYA -> R.mipmap.arya_image
    CharactersName.CHARACTER_DAENERYS -> R.mipmap.daenerys_image
    CharactersName.CHARACTER_TYRION -> R.mipmap.tyrion_image
    else -> R.drawable.ic_image_placeholder
}

fun View.getHouseImage(name: String): Int = when (name) {
    HouseName.HOUSE_ARRYN -> R.mipmap.house_arryn
    HouseName.HOUSE_BARATHEON -> R.mipmap.house_baratheon
    HouseName.HOUSE_FREY -> R.mipmap.house_frey
    HouseName.HOUSE_GREYJOY -> R.mipmap.house_greyjoy
    HouseName.HOUSE_LANNISTER -> R.mipmap.lannister_house
    HouseName.HOUSE_MARTELL -> R.mipmap.house_martell
    HouseName.HOUSE_MORMONT -> R.mipmap.house_mormont
    HouseName.HOUSE_STARK -> R.mipmap.house_stark
    HouseName.HOUSE_TARGARYEN -> R.mipmap.house_targaryen
    HouseName.HOUSE_TYRELL -> R.mipmap.house_tyrell
    else -> R.drawable.ic_image_placeholder
}