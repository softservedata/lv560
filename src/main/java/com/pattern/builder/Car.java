package com.pattern.builder;

public class Car implements ICar {

    // --------------------------------------------------------------------------------

    public class Model {

        private Model() {}

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
    }

    // --------------------------------------------------------------------------------

    public class Color {

        private Color() {}

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
    }

    // --------------------------------------------------------------------------------

    public class Gear {

        private Gear() {}

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

        public CarBuilder setBlackSalon() {
            salon = "BlackSalon";
            return this;
        }

        public CarBuilder setWhiteSalon() {
            salon = "WhiteSalon";
            return this;
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

    private String model;
    private String color;
    private String engine;
    private String gear;
    private String light;
    private String salon; // optional
    private String rims; // optional

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
    private Car() {}

    public static Model builder() {
        //return new Car();
        return new Car().new Model();
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
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", engine='" + engine + '\'' +
                ", gear='" + gear + '\'' +
                ", light='" + light + '\'' +
                ", salon='" + salon + '\'' +
                ", rims='" + rims + '\'' +
                '}';
    }
}
