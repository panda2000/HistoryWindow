package ru.pandaprg.core_accelerometr_impl;


import ru.pandaprg.core_accelerometr_api.AccelerometrDataContract;

public class AccelerometrData extends AccelerometrDataContract {
    private long xy;
    private long xz;
    private long yz;

    public void setXy(long xy) {
        this.xy = xy;
    }

    public void setXz(long xz) {
        this.xz = xz;
    }

    public AccelerometrData(long xy, long xz, long yz) {
        this.xy = xy;
        this.xz = xz;
        this.yz = yz;
    }

    public void setYz(long yz) {
        this.yz = yz;
    }

    public long getXy() {
        return xy;
    }

    public long getXz() {
        return xz;
    }

    public long getYz() {
        return yz;
    }
}
