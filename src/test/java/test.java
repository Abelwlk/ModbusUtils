import com.cczu.modbus.ModbusMaster;
import com.cczu.modbus.ModbusRtuMaster;
import com.cczu.modbus.ModbusTcpMaster;
import com.cczu.modbus.common.PropertiesUtil;
import com.cczu.modbus.common.SimpleByteQueue;
import org.junit.Test;

/**
 * @author weiliangkang
 * @date 2020/12/3 14:10
 */
public class test {

    @Test
    public void test01() {
        String value = PropertiesUtil.getValue("modbusTcp.host");
        System.out.println(value);
    }

    @Test
    public void test02() {
        ModbusMaster master = new ModbusTcpMaster();
        SimpleByteQueue simpleByteQueue = master.readHoldingRegister(1, 1, 15);
        while (simpleByteQueue.hasNext()) {
            int next = simpleByteQueue.next();
            System.out.println(next);
        }
    }

    @Test
    public void test03() {
        ModbusMaster master = new ModbusRtuMaster();
        SimpleByteQueue simpleByteQueue = master.readHoldingRegister(1, 1);
        while (simpleByteQueue.hasNext()) {
            int next = simpleByteQueue.next();
            System.out.println(next);
        }
    }

}
