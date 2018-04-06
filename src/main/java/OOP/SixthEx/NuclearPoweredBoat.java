package OOP.SixthEx;

public class NuclearPoweredBoat {
    private String name;
    private boolean swim;
    private BoatEngine engine;

    public NuclearPoweredBoat(String name, double power) {
        this.name = name;
        engine = new BoatEngine(power);
    }

    public boolean isSwim() {
        return swim;
    }

    public void ready() {
        engine.start();
    }

    public void brake() {
        engine.stop();
    }


    private class BoatEngine{
        double power;

        BoatEngine(double power) {
            this.power = power;
        }

        public void start() {
            System.out.println("Engine is running");
            swim = true;
        }

        public void stop() {
            System.out.println("Engine stopped");
            swim = false;
        }
    }

    public static void main(String... args) {
        NuclearPoweredBoat boat = new NuclearPoweredBoat("ritt", 4.2);
        boat.ready();
        System.out.println(boat.isSwim());
        boat.brake();
    }

}
