package primitives;

public class Material {
    public Double3 KD=new Double3(0,0,0); //diffusive
    public Double3 KS=new Double3(0,0,0); //specular
    public Double3 KT=new Double3(0,0,0); //Promotes transparency
    public Double3 KR=new Double3(0,0,0); //Coefficient of reflection

    public int nShininess=0;

    /**
     * setter
     * @param KD
     * @return
     */
    public Material setKD(Double3 KD) {
        this.KD = KD;
        return this;
    }

    /**
     * setter
     * @param KS
     * @return
     */
    public Material setKS(Double3 KS) {
        this.KS = KS;
        return this;
    }

    /**
     * setter
     * @param KD
     * @return
     */
    public Material setKD(double KD) {
        this.KD = new Double3(KD);
        return this;
    }

    /**
     * setter
     * @param KS
     * @return
     */
    public Material setKS(double KS) {
        this.KS = new Double3(KS);
        return this;
    }

    /**
     * setter
     * @param nShininess
     * @return
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * setter
     * @param KT
     * @return
     */
    public Material setKT(Double3 KT) {
        this.KT = KT;
        return this;
    }

    /**
     * setter
     * @param KR
     * @return
     */
    public Material setKR(Double3 KR) {
        this.KR = KR;
        return this;
    }

    /**
     * setter
     * @param Kt
     * @return
     */
    public Material setKT(double Kt) {
        this.KT = new Double3(Kt);
        return this;
    }

    /**
     * setter
     * @param Kr
     * @return
     */
    public Material setKR(double Kr) {
        this.KR = new Double3(Kr);
        return this;
    }


}
