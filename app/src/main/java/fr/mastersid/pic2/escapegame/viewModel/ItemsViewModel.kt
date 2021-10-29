package fr.mastersid.pic2.escapegame.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.pic2.escapegame.model.ItemsRepository
import fr.mastersid.pic2.escapegame.model.NfcRepository
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val nfcRepository: NfcRepository,
    private val firebaseRepository: ItemsRepository
): ViewModel() {
    private val _itemNFC: LiveData<String> = nfcRepository.lastScan.asLiveData()
    val itemNFC get() = _itemNFC

    private val _itemDesc: LiveData<String> = firebaseRepository.itemDesc.asLiveData()
    val itemDesc get() = _itemDesc

    fun updateDesc() {
        firebaseRepository.fetchItemDesc(_itemNFC.value.toString())
        Log.d("ItemDesc", "Desc updated: "+itemDesc.value)
    }
}