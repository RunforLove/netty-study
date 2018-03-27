# 杂说

##### Netty Server和Client必须配合使用吗？
- 这个倒是不用，如果只是客户端和服务器交互，那么服务器端只需要使用netty的server就可以了，有时候我们需要服务器与服务器通信，这个时候发起请求的一端可以使用netty的client，比较方便；

##### Netty Server如何独立于Client使用？
- 两者之间通过HTTP协议进行通讯；红色框中都是Netty框架自带的，灰色框中的三个类是自己实现的；
![Client-Server交互图](https://github.com/RunforLove/netty-study/doc/jpg/client_server.jpeg)

##### Handler的执行顺序
- Netty中的handler与Servlet中的filter很像，通过handler可以完成通讯报文的编码解码，拦截制定的报文，统一对日志错误进行处理，统一对请求进行计数，控制handler执行与否。
- Netty中的所有handler都实现自ChannelHandler接口。按照输入输出来分，ChannelInboundHandler  ChannelOutboundHandler两大类。ChannelInboundHandler对从客户端发往服务器的报文进行处理，一般用来执行解码，读取客户端数据，进行业务处理等；ChannelOutboundHandler对从服务器发往客户端的报文进行处理，一般用来编码，发送报文到客户端。
- Netty中，可以注册多个handler。ChannelInboundHanderl按照注册的先后顺序执行；ChannelOutboundHandler按照注册的先后顺序逆序执行。
![Client-Server交互图](https://github.com/RunforLove/netty-study/doc/jpg/handler.jpeg)

##### 关于Netty的好文章博客推荐
- [Netty in Action笔记](https://fangjian0423.github.io/2016/08/29/netty-in-action-note2/)
- 