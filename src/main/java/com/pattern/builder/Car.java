package com.pattern.builder;

import com.pattern.strategy.Electric;
import com.pattern.strategy.IHybrid;
import com.pattern.strategy.Petrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Car implements ICar {

    private static enum CarColumns {
        MODEL(0, "Mercedes"),
        COLOR(1, "Black"),
        ENGINE(2, "Petrol"),
        GEAR(3, "Automat"),
        LIGHT(4, "Halogen"),
        SALON(5, "BlackSalon"),
        RIMS(6, "MetalRims");
        //
        private int index;
        private String defaultValue;

        private CarColumns(int index, String defaultValue) {
            this.index = index;
            this.defaultValue = defaultValue;
        }

        public int getIndex() {
            return index;
        }

        public String getDefaultValue() {
            return defaultValue;
        }
    }

    // --------------------------------------------------------------------------------

    public class Model {

        private Model() {}

        public Color setModel(String model) {
            Car.this.model = model;
            return new Color();
        }

        public Color setMercedes() {
            model = "Mercedes";
            return new Color();
        }

        public Color setBMWx5() {
            model = "BMWx5";
            return new Color();
        }

        public Color setHonda() {
            model = "Honda";
            return new Color();
        }

        public Color setMazdaCX() {
            model = "MazdaCX";
            return new Color();
        }

        public Color setTruck() {
            model = "Truck";
            return new Color();
        }

        public Color setPickup() {
            model = "Pickup";
            return new Color();
        }
    }

    // --------------------------------------------------------------------------------

    public class Color {

        private Color() {}

        public Engine setColor(String color) {
            Car.this.color = color;
            return new Engine();
        }

        public Engine setRed() {
            color = "Red";
            return new Engine();
        }

        public Engine setBlack() {
            color = "Black";
            return new Engine();
        }

        public Engine setWhite() {
            color = "White";
            return new Engine();
        }
    }

    // --------------------------------------------------------------------------------

    public class Engine {

        private Engine() {}

        public Gear setEngine(String engine) {
            Car.this.engine = engine;
            return new Gear();
        }

        public Gear setPetrol() {
            engine = "Petrol";
            return new Gear();
        }

        public Gear setDisel() {
            engine = "Disel";
            return new Gear();
        }

        public Gear setElectric() {
            engine = "Electric";
            return new Gear();
        }

        public Gear setHybrid() {
            engine = "Hybrid";
            return new Gear();
        }
    }

    // --------------------------------------------------------------------------------

    public class Gear {

        private Gear() {}

        public Light setGear(String gear) {
            Car.this.gear = gear;
            return new Light();
        }

        public Light setManual() {
            gear = "Manual";
            return new Light();
        }

        public Light setAutomat() {
            gear = "Automat";
            return new Light();
        }
    }

    // --------------------------------------------------------------------------------

    public class Light {

        private Light() {}

        public CarBuilder setLight(String light) {
            Car.this.light = light;
            return new CarBuilder();
        }

        public CarBuilder setHalogen() {
            light = "Halogen";
            return new CarBuilder();
        }

        public CarBuilder setXenon() {
            light = "Xenon";
            return new CarBuilder();
        }

        public CarBuilder setLed() {
            light = "Led";
            return new CarBuilder();
        }
    }

    // --------------------------------------------------------------------------------

    public class CarBuilder {

        private CarBuilder() {}

        public CarBuilder setSalon(String salon) {
            Car.this.salon = salon;
            return new CarBuilder();
        }

        public CarBuilder setBlackSalon() {
            salon = "BlackSalon";
            return this;
        }

        public CarBuilder setWhiteSalon() {
            salon = "WhiteSalon";
            return this;
        }

        public CarBuilder setRims(String rims) {
            Car.this.rims = rims;
            return new CarBuilder();
        }

        public CarBuilder setMetalRims() {
            rims = "MetalRims";
            return this;
        }

        public CarBuilder setTitanRims() {
            rims = "TitanRims";
            return this;
        }

        // 3. Builder. Inner Classes. Static Factory.
        //public Car build() {
        // 4. Dependency inversion.
        public ICar build() {
            return Car.this;
        }
    }

    // --------------------------------------------------------------------------------

    private final static String EMPTY_STRING = new String();
    private String model;
    private String color;
    private String engine;
    private String gear;
    private String light;
    private String salon; // optional
    private String rims; // optional
    private IHybrid hybrid;

    /*
    // 1. Classic Constructor
    public Car(String model, String color, String engine, String gear, String light) {
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.gear = gear;
        this.light = light;
        salon = "";
        rims = "";
    }

    // setters

    // 1. Classic Constructor
    //public void setSalon(String salon) {
    // 2. Fluent interface
    public Car setSalon(String salon) {
        this.salon = salon;
        return this;
    }

    public Car setRims(String rims) {
        this.rims = rims;
        return this;
    }
    */

    // 3. Builder. Inner Classes. Static Factory.
    private Car() {
        salon = "BlackSalon";
        rims = "TitanRims";
        setHybridStrategyPetrol();
        System.out.println("\tConstructor Car() done");
    }

    public static Model builder() {
        //return new Car();
        return new Car().new Model();
    }

    // Strategy

    public void setHybridStrategyElectric() {
        hybrid = new Electric();
    }

    public void setHybridStrategyPetrol() {
        hybrid = new Petrol();
    }

    public String workingHybrid() {
        if (engine.toLowerCase().contains("hybrid")) {
            return ", \'" + hybrid.run() + "\'";
        }
        return EMPTY_STRING;
    }

    // setters. Fluent interface.

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public Car setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public Car setGear(String gear) {
        this.gear = gear;
        return this;
    }

    public Car setLight(String light) {
        this.light = light;
        return this;
    }

    public Car setSalon(String salon) {
        this.salon = salon;
        return this;
    }

    public Car setRims(String rims) {
        this.rims = rims;
        return this;
    }

    // getters

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getEngine() {
        return engine;
    }

    public String getGear() {
        return gear;
    }

    public String getLight() {
        return light;
    }

    public String getSalon() {
        return salon;
    }

    public String getRims() {
        return rims;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", engine='" + engine + '\'' +
                workingHybrid() +
                ", gear='" + gear + '\'' +
                ", light='" + light + '\'' +
                ", salon='" + salon + '\'' +
                ", rims='" + rims + '\'' +
                '}';
    }

    // --------------------------------------------------------------------------------
    // 6. Static Factory.

    public static ICar getByList(List<String> row) {
        //logger.trace("row.size() = " + row.size() + " UserColumns.values().length = " + UserColumns.values().length);
        List<String> carData = new ArrayList<>(row);
        for (int i = carData.size(); i < CarColumns.values().length; i++) {
            carData.add(EMPTY_STRING);
        }
        return Car.builder()
                .setModel(carData.get(CarColumns.MODEL.getIndex()))
                .setColor(carData.get(CarColumns.COLOR.getIndex()))
                .setEngine(carData.get(CarColumns.ENGINE.getIndex()))
                .setGear(carData.get(CarColumns.GEAR.getIndex()))
                .setLight(carData.get(CarColumns.LIGHT.getIndex()))
                .setSalon(carData.get(CarColumns.SALON.getIndex()))
                .setRims(carData.get(CarColumns.RIMS.getIndex()))
                .build();
    }

    public static List<ICar> getByLists(List<List<String>> rows) {
        List<ICar> result = new ArrayList<>();
        // TODO Verify Test Data as Valid
        if (rows.get(0).get(CarColumns.MODEL.getIndex())
                .contains(CarColumns.MODEL.name().toLowerCase())) {
            rows.remove(0);
        }
        for (List<String> currentRow : rows) {
            result.add(getByList(currentRow));
        }
        return result;
    }

    public static ICar getByMap(Map<String, String> map) {
        List<String> carData = new ArrayList<>(CarColumns.values().length);
        for (CarColumns carColumns : CarColumns.values()) {
            carData.add(carColumns.defaultValue);
            //carData.set(carColumns.getIndex(), carColumns.defaultValue);
        }
        System.out.println("\t***carData = " + carData);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            for (CarColumns carColumns : CarColumns.values()) {
                if (entry.getKey().toLowerCase().equals(carColumns.name().toLowerCase())) {
                    carData.set(carColumns.getIndex(), entry.getValue());
                    break;
                }
            }
        }
        System.out.println("\t***carData map = " + carData);
        return Car.builder()
                .setModel(carData.get(CarColumns.MODEL.getIndex()))
                .setColor(carData.get(CarColumns.COLOR.getIndex()))
                .setEngine(carData.get(CarColumns.ENGINE.getIndex()))
                .setGear(carData.get(CarColumns.GEAR.getIndex()))
                .setLight(carData.get(CarColumns.LIGHT.getIndex()))
                .setSalon(carData.get(CarColumns.SALON.getIndex()))
                .setRims(carData.get(CarColumns.RIMS.getIndex()))
                .build();
    }

}
