package com.example.flutter_finix.compose.addCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.finix.finixpaymentsheet.domain.model.PaymentSheetColors
import com.finix.finixpaymentsheet.domain.model.PaymentSheetResources
import com.example.flutter_finix.R
import com.example.flutter_finix.theme.*


class AddCardViewModel : ViewModel() {

    var state by mutableStateOf(AddCardState())


    init {
        setPaymentSheetResources(
            PaymentSheetResources(
                logoDrawable = R.drawable.ic_logo,
                logoText = R.string.daphneys_corner,
                tokenizeButtonText = R.string.btn_tokenize,
                cancelButtonText = R.string.btn_cancel
            )
        )

        setPaymentSheetColors(
            PaymentSheetColors(
                surface = Color.White,
                errorContainerColor = FinixErrorTextSurface,
                containerColor = FinixGray,
                focusedIndicatorColor = FinixBlue,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = FinixBlue,
                unfocusedLabelColor = Color.Black,
                errorPlaceholderColor = FinixErrorRed,
                errorLabelColor = FinixErrorRed,
                tokenizeButtonColor = FinixBlue,
                cancelButtonColor = FinixRed
            )
        )

    }

    fun setPaymentSheetResources(resources: PaymentSheetResources) {
        state = state.copy(paymentSheetResources = resources)
    }

    fun setPaymentSheetColors(paymentSheetColors: PaymentSheetColors) {
        state = state.copy(paymentSheetColors = paymentSheetColors)
    }


    fun setTokenResponse(response: String) {
        state = state.copy(tokenResponseString = response)
    }

    fun setShowMinimalPaymentSheet(show: Boolean) {
        state = state.copy(showMinimalPaymentSheet = show)
    }

}
