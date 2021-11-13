package ch07.before;

class TV {
    boolean power;
    int channel;

    void power() {power = !power;}
    void channelUp() {channel += 1;}
    void channelDown() {channel -= 1;}
}
class VCR {
    boolean power;
    int counter = 0;
    void power() {power = !power;}
    void play() {}
    void stop() {}
    void rew() {}
    void ff() {}
}
class TVCR extends TV {
    VCR vcr = new VCR();
    int counter = vcr.counter;

    void play() {
        vcr.play();
    }
    void stop() {
        vcr.stop();;
    }
    void rwe() {
        vcr.rew();
    }
    void ff() {
        vcr.ff();
    }
}

public class p7_4 {
    public static void main(String[] args) {
        TVCR tvcr = new TVCR();
    }
}
