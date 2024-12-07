package com.example.flutter_finix

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.content.Intent
import com.example.flutter_finix.compose.addCard.PaymentSheetActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import android.util.Log

class MainActivity : FlutterActivity() {
    private val PAYMENT_CHANNEL = "fenix_payment_channel"
    private val PAYMENT_REQUEST_CODE = 1011

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, PAYMENT_CHANNEL)
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    "showPaymentSheet" -> {
                        val intent = Intent(this, PaymentSheetActivity::class.java)
                        startActivityForResult(intent, PAYMENT_REQUEST_CODE)
                        result.success(null)
                    }

                    else -> result.notImplemented()
                }
            }
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.keySet()?.forEach { key ->
            Log.d("PaymentSheetActivity", "Intent extra - $key: ${data.extras?.get(key)}")
        }
        if (requestCode == PAYMENT_REQUEST_CODE) {
            val channel = MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, PAYMENT_CHANNEL)
            if (resultCode == RESULT_OK && data != null) {

                // Use getParcelableExtra for safer retrieval
                val token = data.getStringExtra("token")
                val cardType = data.getStringExtra("cardType")
                val isSuccess = data.getBooleanExtra("isSuccess", false)

                if (token != null && cardType != null) {
                    val resultMap = mapOf(
                        "token" to token,
                        "cardType" to cardType,
                        "isSuccess" to isSuccess
                    )
                    channel.invokeMethod("onPaymentResult", resultMap)
                } else {
                    val errorMap = mapOf(
                        "isSuccess" to false,
                        "reason" to "Incomplete payment result"
                    )
                    channel.invokeMethod("onPaymentResult", errorMap)
                }
            } else {
                val errorMap = mapOf(
                    "isSuccess" to false,
                    "reason" to "Payment sheet canceled or failed"
                )
                channel.invokeMethod("onPaymentResult", errorMap)
            }
        }
    }

}
