- To Run the Application , Run PriceFeedApp main class
- Internal Prices will get published and written on Console

Console Output:

Price Received: Price{id=106, ccyPair='EUR/USD', bid=1.1000, ask=1.2000, dateTime=2020-06-01T12:01:01.001}
Price Received: Price{id=107, ccyPair='EUR/JPY', bid=119.60, ask=119.90, dateTime=2020-06-01T12:01:02.002}
Price Received: Price{id=108, ccyPair='GBP/USD', bid=1.2500, ask=1.2560, dateTime=2020-06-01T12:01:02.002}
Price Received: Price{id=109, ccyPair='GBP/USD', bid=1.2499, ask=1.2561, dateTime=2020-06-01T12:01:02.100}
Price Received: Price{id=110, ccyPair='EUR/JPY', bid=119.61, ask=119.91, dateTime=2020-06-01T12:01:02.110}
publishing price to client: Price{id=109, ccyPair='GBP/USD', bid=1.2487, ask=1.2574, dateTime=2020-06-01T12:01:02.100}
publishing price to client: Price{id=110, ccyPair='EUR/JPY', bid=119.49, ask=120.03, dateTime=2020-06-01T12:01:02.110}
publishing price to client: Price{id=107, ccyPair='EUR/JPY', bid=119.48, ask=120.02, dateTime=2020-06-01T12:01:02.002}
publishing price to client: Price{id=108, ccyPair='GBP/USD', bid=1.2488, ask=1.2573, dateTime=2020-06-01T12:01:02.002}
publishing price to client: Price{id=106, ccyPair='EUR/USD', bid=1.0989, ask=1.2012, dateTime=2020-06-01T12:01:01.001}

- I am using disruptor ring buffer for publishing message. 