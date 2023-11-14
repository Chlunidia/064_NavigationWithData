package com.example.navigation

import androidx.lifecycle.ViewModel
import com.example.navigation.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val PRICE_PER_CUP = 3000
class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI : StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setContact(listData: MutableList<String>) {
        _stateUI.update { currentState ->
            currentState.copy(
                name = listData[0],
                address = listData[1],
                tlp = listData[2]
            )
        }
    }

    fun setQuantity(iceQuantity : Int){
        _stateUI.update { currentState ->
            currentState.copy(
                quantity = iceQuantity,
                price = calculatePrice(quantity = iceQuantity)
            )
        }
    }

    fun setFlavor (selectedFlavor : String) {
        _stateUI.update { currentState ->
            currentState.copy(
                flavor = selectedFlavor
            )
        }
    }

    fun resetOrder () {
        _stateUI.value = OrderUIState()
    }

    private fun calculatePrice (quantity : Int = _stateUI.value.quantity) : String {
        val priceCalculation = quantity * PRICE_PER_CUP
        return NumberFormat.getNumberInstance().format(priceCalculation)
    }
}