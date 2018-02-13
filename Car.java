import java.util.*;
/**
 * The class stores basic information of cars, including registration number,year,colours,makeYear,
 * model and price.
 * 
 * @author Xiaofen Pan
 * @version 19/10/2017
 */
public class Car {
    private String regNumber;
    private int year;
    private List<String> colours;
    private String make;
    private String model;
    private int price;

    /**
     * Construct an empty car with empty values.
     */
    public Car() 
    {
        colours = new ArrayList<>();
    }

    /**
     * Constructor for objects of Car class.
     * @param regNumber The registration number of the car.
     * @param year      The year when the car is made.
     * @param colours   The colours of the car.
     * @param make      The place where the car is made.
     * @param model     The model of the car.
     * @param price     The price of the car.
     */public Car(String regNumber, int year, ArrayList<String> colours, String make, String model, int price) 
    {
        this.regNumber = regNumber;
        this.year = year;
        this.colours = colours;
        this.make = make;
        this.model = model;
        this.price = price;
    }

    /**
     * Override the toString method of car objects that returns car attributes in the 
     * certainy format.
     * @return Return the certainy format.
     */
    @Override
    public String toString() 
    {
        String colourString = "";
        
        for (String colour : getColours()) 
        {
            colourString += colour + ",";
        }

        colourString = colourString.substring(0, colourString.length()-1);
        return String.format("\nCar info: " +
                "\nRegistration Number: %s" +
                "\nMade Year: %d" +
                "\nColour(s): %s" +
                "\nCar Make: %s" +
                "\nCar Model: %s" +
                "\nPrice: $%d\n", this.regNumber, this.year, colourString, this.make, this.model, this.price);
    }

    /**
     * To check if the car with the given registeration number have existed. 
     * @param obj The object of car
     * @return boolean.
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null)
            return false;

        if (obj instanceof Car) 
        {
            Car car = (Car) obj;
            if (this == car)
                return true;

            if (this.getRegNumber().toLowerCase().equals(car.getRegNumber().toLowerCase())) 
            {
                return true;
            }

            return false;
        } 
        else 
            return false;
    }

    /**
     * Get the given registration number.
     * @return Return registration number.
     */
    public String getRegNumber() 
    {
        return regNumber;
    }

    /**
     * Set the registration number.
     *@param regNumber registration number.
     */
    public void setRegNumber(String regNumber) 
    {
        this.regNumber = regNumber;
    }

    /**
     * Get the car year.
     *@return regNumber Return the year when the car is made. 
     */
    public int getYear() 
    {
        return year;
    }

    /**
     * Set the car year.
     *@param year The year when the car is made. 
     */
    public void setYear(int year) 
    {
        this.year = year;
    }

    /**
     * Get the car colours.
     *@return colours Return the colours when the car is made of. 
     */
    public List<String> getColours() 
    {
        return colours;
    }

    /**
     *Set the car colours.
     *@param colours The colours when the car is made of. 
     */
    public void setColours(List<String> colours) 
    {
        this.colours = colours;
    }

    /**
     * Get the car make.
     *@return colours Return the company of the car. 
     */
    public String getMake() 
    {
        return make;
    }

    /**
     *Set the company of the car. 
     *@param make The company of the car. 
     */
    public void setMake(String make) 
    {
        this.make = make;
    }

    /**
     *Get the model of the car. 
     *@return model Return the model of the car. 
     */
    public String getModel() 
    {
        return model;
    }

    /**
     *Set the model of the car. 
     *@param model The model of the car. 
     */
    public void setModel(String model) 
    {
        this.model = model;
    }

    /**
     *Get the price of the car. 
     *@return price Return the price of the car. 
     */
    public int getPrice() 
    {
        return price;
    }

    /**
     *Set the price of the car. 
     *@param pice The price of the car. 
     */
    public void setPrice(int price) 
    {
        this.price = price;
    }
    
    /**
     *Set the price of the car. 
     *@param pice The price of the car. 
     */
    public void display()
    {
        System.out.println(getRegNumber());
        System.out.println(getYear());
        System.out.println(getColours() );
        System.out.println(getMake());
        System.out.println(getModel());
        System.out.println(getPrice());
    }
}
