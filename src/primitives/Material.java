package primitives;

public class Material {
    public Double3 KD=new Double3(0,0,0);
    public Double3 KS=new Double3(0,0,0);
    public int nShininess=0;

    public Material setKD(Double3 KD) {
        this.KD = KD;
        return this;
    }

    public Material setKS(Double3 KS) {
        this.KS = KS;
        return this;
    }
    public Material setKD(double KD) {
        this.KD = new Double3(KD);
        return this;
    }

    public Material setKS(double KS) {
        this.KS = new Double3(KS);
        return this;
    }

    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
