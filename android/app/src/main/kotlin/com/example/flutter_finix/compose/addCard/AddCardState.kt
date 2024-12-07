package com.example.flutter_finix.compose.addCard


import com.finix.finixpaymentsheet.domain.model.PaymentSheetResources
import com.finix.finixpaymentsheet.domain.model.PaymentSheetColors
import com.finix.finixpaymentsheet.domain.model.tokenize.TokenizedResponse


data class AddCardState(

    val tokenResponseString: String = "",
    val tokenResponse: TokenizedResponse? = null,

    val showFinixPaymentSheetSelection: Boolean = false,

    val showCompletePaymentSheetOutlined: Boolean = false,
    val showPartialPaymentSheetOutlined: Boolean = false,
    val showBasicPaymentSheetOutlined: Boolean = false,
    val showMinimalPaymentSheetOutlined: Boolean = false,

    val showCompletePaymentSheet: Boolean = false,
    val showPartialPaymentSheet: Boolean = false,
    val showBasicPaymentSheet: Boolean = false,
    val showMinimalPaymentSheet: Boolean = false,


    /**
     * PaymentSheetResources
     * Customize resources
     *
     * DEFAULTS:
        @DrawableRes val logoDrawable: Int = R.drawable.ic_default_logo,
        @StringRes val logoText: Int = R.string.default_logo_text,
        @StringRes val tokenizeButtonText: Int = R.string.btn_tokenize,
        @StringRes val cancelButtonText: Int = R.string.btn_cancel,
     *
     */
    val paymentSheetResources: PaymentSheetResources = PaymentSheetResources(),


    /**
     * paymentSheetColors
     * Add custom colors to your paymentsheet
     *
     * DEFAULTS:
        val surface: Color = Color.White, -> Represents the color used for the background payment sheet
        val textColor: Color = Color.Black, -> Represents the color used for the input text of this text field
        val containerColor: Color = FinixGray, -> Represents the color used as the surface of the textfield
        val errorContainerColor: Color = FinixErrorTextSurface, -> Represents the color used as the surface of the textfield when in error state
        val errorBorderColor: Color = FinixErrorRed, -> Represents the color used for the border indicator of this text field when in error state
        val focusedIndicatorColor: Color = FinixBlue, -> Represents the color used for the border indicator of this text field when in focus
        val unfocusedIndicatorColor: Color = Color.Transparent, -> Represents the color used for the border indicator of this text field when out of focus
        val focusedLabelColor: Color = FinixBlue, -> Represents the color used for the label of this text field when focused
        val unfocusedLabelColor: Color = Color.Black, -> Represents the color used for the label of this text field when out of focus
        val errorLabelColor: Color = FinixErrorRed, -> Represents the color used for the label of this text field when in error state
        val placeholderColor: Color = FinixPlaceHolderTextGray, -> Represents the color used for the placeholder of this text field
        val tokenizeButtonColor: Color = FinixBlue, -> Represents the surface color used for tokenize button
        val tokenizeButtonTextColor: Color = Color.White, -> Represents the text color used for tokenize button
        val cancelButtonColor: Color = FinixRed, -> negative Represents the surface color used for cancel button
        val cancelButtonTextColor: Color = Color.White, -> Represents the text color used for cancel button
     */
    val paymentSheetColors: PaymentSheetColors = PaymentSheetColors(),
)

