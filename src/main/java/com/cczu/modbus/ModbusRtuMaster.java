package com.cczu.modbus;

import com.cczu.modbus.common.PropertiesUtil;
import com.cczu.modbus.third.SerialPortWrapperImpl;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.serial.SerialPortWrapper;


/**
 * @author weiliangkang
 * @date 2020/12/3 14:27
 */
public class ModbusRtuMaster extends com.cczu.modbus.ModbusMaster {

    private static final ModbusFactory MODBUS_FACTORY;

    /**
     * 串口号
     */
    private static final String COMM_PORT_ID;
    /**
     * 波特率
     */
    private static final int BAUD_RATE;
    /**
     * 数据大小
     */
    private static final int DATA_BITS;
    /**
     * 停止位
     */
    private static final int STOP_BITS;
    /**
     * 奇偶校验
     * PARITY_NONE = 0;PARITY_ODD = 1；PARITY_EVEN = 2
     */
    private static final int PARITY;

    static {
        MODBUS_FACTORY = new ModbusFactory();
        COMM_PORT_ID = PropertiesUtil.getValue("modbusRtu.serialPort");
        BAUD_RATE = Integer.parseInt(PropertiesUtil.getValue("modbusRtu.baudRate"));
        DATA_BITS = Integer.parseInt(PropertiesUtil.getValue("modbusRtu.dataBits"));
        STOP_BITS = Integer.parseInt(PropertiesUtil.getValue("modbusRtu.stopBits"));
        PARITY = Integer.parseInt(PropertiesUtil.getValue("modbusRtu.parity"));
    }

    /**
     * 创建并初始化ModbusRtuMaster
     *
     * @return rtuMaster
     */
    @Override
    public com.serotonin.modbus4j.ModbusMaster getMaster() {
        SerialPortWrapper serialPortWrapper = new SerialPortWrapperImpl(COMM_PORT_ID, BAUD_RATE, DATA_BITS, STOP_BITS, PARITY, 0, 0);
        com.serotonin.modbus4j.ModbusMaster rtuMaster = MODBUS_FACTORY.createRtuMaster(serialPortWrapper);
        try {
            rtuMaster.init();
            return rtuMaster;
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return null;
    }


}
