package springsecurity.demo.user;

public class Skladniki {


    private int maka;


    private int woda;
    private int drozdze;
    private int sol;

    public int getMaka() {
        return maka;
    }

    public void setMaka(int maka) {
        this.maka = (int) (maka * 0.59);
    }

    public int getWoda() {
        return woda;
    }

    public void setWoda(int woda) {
        this.woda = (int) (woda * 0.39);
    }

    public int getDrozdze() {
        return drozdze;
    }

    public void setDrozdze(int drozdze) {
        this.drozdze = (int) (drozdze * 0.01);
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = (int) (sol * 0.018);
    }

    @Override
    public String toString() {
        return "Skladniki [maka=" + maka + ", woda=" + woda + ", drozdze=" + drozdze + ", sol=" + sol + "]";
    }

}
