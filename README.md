
This project uses [LMAX Disruptor with Ring Bugger](https://www.baeldung.com/lmax-disruptor-concurrency#:~:text=What%20Is%20a%20Disruptor%3F,the%20complexities%20of%20concurrent%20code).)
to achieve near zero GC for low latency applications.

1. There are two publishers publishing Bid-Ask and LastClose for set of tickers. 
2. There are 4 receiver threads receving all the updates. 
3. Two receiver subscibe to all events i.e. ReciverEventHandler i.e ReciverEventHandler, and remaining two only to Bid-Ask  and LastClose i.e LastCloseEventEventHandler respectively
4. lmax.disruptor:3.4.2 has dependency on java 8. 
