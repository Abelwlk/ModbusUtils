package com.cczu.modbus.common;

/**
 * 继承{@link com.serotonin.modbus4j.sero.util.queue.ByteQueue}的自定义封装队列
 * 主要是进行方法增强
 *
 * @author weiliangkang
 * @date 2020/9/23 10:40
 */
public class SimpleByteQueue extends com.serotonin.modbus4j.sero.util.queue.ByteQueue {

    /**
     * 默认构造，初始化size=1024
     */
    public SimpleByteQueue() {
        super(1024);
    }

    /**
     * 自定义初始化大小
     *
     * @param initialLength 初始化长度
     */
    public SimpleByteQueue(int initialLength) {
        super(initialLength);
    }

    /**
     * 判断{@link com.serotonin.modbus4j.sero.util.queue.ByteQueue}是否为空<br>
     * {@link com.serotonin.modbus4j.sero.util.queue.ByteQueue}中存储的每个元素都是16进制，其中每个值占1个byte，一个寄存器占2个byte，相邻的2个byte组成1个寄存器的值
     *
     * @return 是否为空
     */
    public boolean hasNext() {
        return super.size() / 2 > 0;
    }

    /**
     * 获取寄存器值<br>
     * 前2个字节的值为16进制，经过逻辑运算得到int类型的10进制
     *
     * @return 寄存器值
     */
    public int next() {
        return super.popU2B();
    }

}
