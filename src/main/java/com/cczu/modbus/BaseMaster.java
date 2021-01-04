package com.cczu.modbus;

import com.cczu.modbus.common.SimpleByteQueue;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;

import java.util.Objects;

/**
 * @author weiliangkang
 * @date 2020/12/3 14:28
 */
public abstract class BaseMaster {

    /**
     * 获取相应的Master，声明为抽象方法，让子类必须实现
     *
     * @return ModbusMaster
     */
    abstract com.serotonin.modbus4j.ModbusMaster getMaster();

    /**
     * 读取125个保持寄存器的值
     *
     * @param slaveId 从机id
     * @param start   起始地址
     * @return 去掉了前3个字节（从机id,功能码，字节数，3个报文信息）的ByteQueue
     */
    public SimpleByteQueue readHoldingRegister(int slaveId, int start) {
        return getHoldingRegisterByteQueue(slaveId, start, 125);
    }

    /**
     * 读取readLength个保持寄存器的值
     *
     * @param slaveId    从机id
     * @param start      起始地址
     * @param readLength 读取寄存器个数
     * @return 去掉了前3个字节（[从机id,功能码，字节数]3个报文信息）的ByteQueue
     */
    public SimpleByteQueue readHoldingRegister(int slaveId, int start, int readLength) {
        return getHoldingRegisterByteQueue(slaveId, start, readLength);
    }

    /**
     * 读取寄存器公共代码部分
     * 读取{@param readLength}个保持寄存器的值
     *
     * @param slaveId    从机id
     * @param start      起始地址
     * @param readLength 读取寄存器个数
     * @return 去掉了前3个字节（从机id,功能码，字节数，3个报文信息）的ByteQueue
     */
    private SimpleByteQueue getHoldingRegisterByteQueue(int slaveId, int start, int readLength) {
        ModbusRequest request;
        ModbusResponse response;
        SimpleByteQueue byteQueue = new SimpleByteQueue(readLength * 2 + 3);
        try {
            request = new ReadHoldingRegistersRequest(slaveId, start, readLength);
            response = Objects.requireNonNull(getMaster()).send(request);
            response.write(byteQueue);
            byteQueue.popU3B();
            return byteQueue;
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        return byteQueue;
    }
}
