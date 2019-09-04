package com.zyj.ice.servant;

import Ice.*;
import org.springframework.stereotype.Service;
import slice2java.IServerCallBackPrx;
import slice2java.IServerCallBackPrxHelper;
import slice2java._IServerDisp;

/**
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 *
 * @author by zyj
 * @version V1.0
 * @Description:
 * @Date 2018/11/15 16:49
 */
@Service
public class ServerServant extends _IServerDisp {


    @Override
    public boolean setCallBack(Identity id, Current __current) {
        IServerCallBackPrx iServerCallBackPrx = IServerCallBackPrxHelper.uncheckedCast(__current.con.createProxy(id));
        iServerCallBackPrx.ice_getConnection().setCallback(new ConnectionCallback() {

            @Override
            public void heartbeat(Connection c) {

                System.out.println("sn:" + " client heartbeat....");
            }

            @Override
            public void closed(Connection c) {

                System.out.println("sn:" + " " + "closed....");
            }
        });
        // 每30/2 s向对方做心跳
        // 客户端向服务端做心跳 服务端打印服务端的con.setCallback(new Ice.ConnectionCallback()
        iServerCallBackPrx.ice_getConnection().setACM(new IntOptional(10), new Optional<ACMClose>(ACMClose.CloseOff),
                new Optional<ACMHeartbeat>(ACMHeartbeat.HeartbeatAlways));

        return true;
    }

    @Override
    public boolean request(String msg, Current __current) {
        System.out.println("client:" + msg);
        return false;
    }


}
