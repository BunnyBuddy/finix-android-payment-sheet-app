package com.example.flutter_finix.compose.addCard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.finix.finixpaymentsheet.domain.model.tokenize.TokenizedResponse
import com.finix.finixpaymentsheet.ui.viewModel.paymentSheet.MinimalPaymentSheet


class PaymentSheetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShowMinimalPaymentSheetWrapper(
                onComplete = { tokenResponse ->
                    val resultIntent = Intent().apply {
                        putExtra("token", tokenResponse.id) // Token ID
                        putExtra("cardType", tokenResponse.type) // Card type
                        putExtra("isSuccess", true) // Success flag
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                },
                onCancel = {
                    // Return cancellation result
                    val resultIntent = Intent().apply {
                        putExtra("isSuccess", false) // Indicate failure
                        putExtra("reason", "User cancelled card addition") // Reason for failure
                    }
                    setResult(RESULT_CANCELED, resultIntent)
                    finish() // Close the activity
                }
            )
        }
    }

}

@Composable
fun ShowMinimalPaymentSheetWrapper(
    onComplete: (TokenizedResponse) -> Unit,
    onCancel: () -> Unit
) {
    val viewModel = viewModel<AddCardViewModel>()
    MinimalPaymentSheet(
        onDismiss = {
            viewModel.setShowMinimalPaymentSheet(false)
            onCancel()
        },
        onNegativeClick = {
            viewModel.setShowMinimalPaymentSheet(false)
            onCancel()
        },
        onPositiveClick = { tokenResponse ->
            viewModel.setShowMinimalPaymentSheet(false)
            viewModel.setTokenResponse(tokenResponse.id)
            onComplete(tokenResponse)
        },
        applicationId = "APjMB6owJ7542dehJ6hCojzR",
        isSandbox = true,
        paymentSheetColors = viewModel.state.paymentSheetColors,
        paymentSheetResources = viewModel.state.paymentSheetResources
    )
}
