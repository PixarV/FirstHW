package OOP.SixthEx;

@ImportantInfoAboutShipyard("Trouble")
public class NuclearPoweredBoat {
    private boolean swim;
    private BoatEngine engine;

    public NuclearPoweredBoat(double power) {
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

    public static void printAnnotationInfo() {
        ImportantInfoAboutShipyard info = NuclearPoweredBoat.class
                .getDeclaredAnnotation(ImportantInfoAboutShipyard.class);
        System.out.printf("We started from shipyard named \"%s\" that manages by captain %s.\n",
                info.value(), info.captain());
    }

    public static void main(String... args) {
        NuclearPoweredBoat boat = new NuclearPoweredBoat(4.2);
        boat.ready();
        System.out.println(boat.isSwim());
        boat.brake();
        NuclearPoweredBoat.printAnnotationInfo();

    }

}
