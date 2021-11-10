package fr.mastersid.pic2.escapegame.model

import android.util.Log
import fr.mastersid.pic2.escapegame.utils.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val escapeGameFirebase: EGFirebase,
    escapeGameNfc: EGNFC
    ) {


    private val _lastScan: MutableStateFlow<String> = escapeGameNfc.lastScan
    val lastScan get ()= _lastScan

    private val _itemDesc: MutableStateFlow<String> = MutableStateFlow("")
    val itemDesc get ()= _itemDesc

    private var _random_enigma: MutableStateFlow<Int> = MutableStateFlow(-1)
    val randomEnigma get() = _random_enigma

    fun randomizeEnigma(){
        _random_enigma.value=((0..10).random())
    }

    fun fetchItem(itemName: String) {
        escapeGameFirebase.fetchFrom(EGFirebase.DB.ITEMS, itemName, object: FirebaseCallback<EGFirebase.ItemItem> {
            override fun onCallback(value: EGFirebase.ItemItem) {
                _itemDesc.value = value.desc
            }
        })
    }
}