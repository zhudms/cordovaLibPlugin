package cordova.ryl.cordovalib;

/**
 * Created by rongyile on 2017/11/22.
 */

public class FileDataBean {

    private String name;
    private byte[] data;

    public FileDataBean() {
    }

    public FileDataBean(String name, byte[] data) {

        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
