import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class PaymentService {
  static const platform = MethodChannel('fenix_payment_channel');

  Future<Map<String, dynamic>> showPaymentSheet() async {
    final completer = Completer<Map<String, dynamic>>();

    // Set a temporary handler for the result
    platform.setMethodCallHandler((call) async {
      if (call.method == 'onPaymentResult') {
        if (!completer.isCompleted) {
          try {
            final Map<String, dynamic> result = Map<String, dynamic>.from(call.arguments as Map);
            completer.complete(result);
          } catch (e) {
            debugPrint("Error completing completer: $e");
          }
        } else {
          debugPrint("completer already completed");
        }
      }
      return null;
    });

    try {
      // Trigger the payment sheet in Android
      await platform.invokeMethod('showPaymentSheet');

      // Wait for the result
      final result = await completer.future;
      return result;
    } on PlatformException catch (e) {
      debugPrint('Error showing payment sheet: ${e.message}');
      return {
        'isSuccess': false,
        'reason': e.message,
      };
    } finally {
      // Clear the handler after completing
      platform.setMethodCallHandler(null);
    }
  }
}
