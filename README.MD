# 杂说

##### Netty Server和Client必须配合使用吗？
- 这个倒是不用，如果只是客户端和服务器交互，那么服务器端只需要使用netty的server就可以了，有时候我们需要服务器与服务器通信，这个时候发起请求的一端可以使用netty的client，比较方便；