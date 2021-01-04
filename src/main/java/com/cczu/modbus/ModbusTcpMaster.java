package com.cczu.modbus;

import com.cczu.modbus.common.PropertiesUtil;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author weiliangkang
 * @date 2020/9/23 9:58
 */
public class ModbusTcpMaster extends com.cczu.modbus.ModbusMaster {

    private static final ModbusFactory MODBUS_FACTORY;

    /**
     * ip
     */
    private static final String HOST;
    /**
     * 端口
     */
    private static final String PORT;

    static {
        MODBUS_FACTORY = new ModbusFactory();
        HOST = PropertiesUtil.getValue("modbusTcp.host");
        PORT = PropertiesUtil.getValue("modbusTcp.port");
    }

    /**
     * 初始化ModbusTcpMaster
     *
     * @return tcpMaster
     */
    @Override
    public com.serotonin.modbus4j.ModbusMaster getMaster() {
        IpParameters params = new IpParameters();
        params.setHost(HOST);
        params.setPort(Integer.parseInt(PORT));
        ModbusMaster tcpMaster = MODBUS_FACTORY.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            return tcpMaster;
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return null;
    }

}
