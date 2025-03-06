/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;

/**
 *
 * @author MSII
 */
public class Car {
    private String Colour;
    private int EnginePower;
    private boolean Convertible;
    private boolean ParkingBrake;

    public Car() {
    }

    public Car(String colour, int EnginePower, boolean Convertible, boolean ParkingBrake) {
        this.Colour = colour;
        this.EnginePower = EnginePower;
        this.Convertible = Convertible;
        this.ParkingBrake = ParkingBrake;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        this.Colour = colour;
    }

    public int getEnginePower() {
        return EnginePower;
    }

    public void setEnginePower(int EnginePower) {
        this.EnginePower = EnginePower;
    }

    public boolean isConvertible() {
        return Convertible;
    }

    public void setConvertible(boolean Convertible) {
        this.Convertible = Convertible;
    }

    public boolean isParkingBrake() {
        return ParkingBrake;
    }

    public void setParkingBrake(boolean ParkingBrake) {
        this.ParkingBrake = ParkingBrake;
    }
    
    public void pressStartButton(){
        System.out.println("You have pressed the start button.");
}
    
    public void pressAcceleratorButton(){
        System.out.println("You have pressed the Accelerator button.");
    }
    
       public void output() {
        System.out.println("Car Details:");
        System.out.println("Colour: " + Colour);
        System.out.println("Engine Power: " + EnginePower);
        System.out.println("Convertible: " + Convertible);
        System.out.println("Parking Brake: " + ParkingBrake);
    }
}
