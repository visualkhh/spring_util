package com.lgu.cbc.core.ha;

import lombok.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HaChecker {
    String ip;
    int port;

//    public void connection() {
//        EventLoopGroup group = new NioEventLoopGroup();
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(group);
//        bootstrap.channel(NioSocketChannel.class)
//        .handler(new ChannelInitializer<SocketChannel>() {
//            @Override
//            protected void initChannel(SocketChannel ch) throws Exception {
//
//            }
//        });
//        bootstrap.connect(this.ip, this.port);
//    }

    public ServerSocket isActive() {
        ServerSocket s = null;
        try {
            s = new ServerSocket();
            s.bind(new InetSocketAddress(this.ip, this.port));
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
