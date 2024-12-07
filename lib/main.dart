import 'package:flutter/material.dart';
import 'package:flutter_finix/payment_sheet.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue, contrastLevel: 0.5),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Finix'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String? token;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ElevatedButton(
              onPressed: () async {
                final result = await PaymentService().showPaymentSheet();
                if (result['isSuccess']) {
                  debugPrint('Token: ${result['token']}');
                  debugPrint('Card Type: ${result['cardType']}');
                  setState(() {
                    token = result['token'].toString();
                  });
                } else {
                  debugPrint('Payment failed: ${result['reason']}');
                }
              },
              child: const Text('Add Card'),
            ),
            const SizedBox(height: 50),
            token == null ? const SizedBox.shrink() : Text('Token: $token'),
          ],
        ),
      ),
    );
  }
}
