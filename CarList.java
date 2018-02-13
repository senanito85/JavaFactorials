import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
/**
 * The CarList represents a function to store all the values about cars.
 * 
 * @author Xiaofen Pan
 * @verson 19/10/2017
 */

public class CarList 
{
    private ArrayList<Car> cars;
    private String filename = "usedcars.txt";
    private Validation validation;

    /**
     * Construct a CarList with new and empty value.
     * The new car object with all the elements can be read in the individual lines. 
     */
    public CarList() 
    {
        cars = new ArrayList<>();
        validation = new Validation();
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            lines = reader.lines().collect(Collectors.toList());
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }

        for (String line : lines) 
        {
            Car newCar = new Car();
            String[] carValues = line.split(",");
            newCar.setRegNumber(carValues[0]);
            newCar.setYear(Integer.parseInt(carValues[1]));

            if (carValues[2] != null && !carValues[2].isEmpty())
            {
                newCar.getColours().add(carValues[2]);
            }
            if (carValues[3] != null && !carValues[3].isEmpty()) 
            {
                newCar.getColours().add(carValues[3]);
            }
            if (carValues[4] != null && !carValues[4].isEmpty()) 
            {
                newCar.getColours().add(carValues[4]);
            }
            newCar.setMake(carValues[5]);
            newCar.setModel(carValues[6]);
            newCar.setPrice(Integer.parseInt(carValues[7]));
            cars.add(newCar);
        }
    }

    /**
     * Get a car by registerion number, if the registerion number equals that of the existing car, it can return this car, otherwise,
     * it cannot return the car.
     * @param registerionNumber The registration number we input to get an exsiting car.
     * @return car Return the car with the registerion number. 
     */
    private Car getByRegistrationNumber(String registrationNumber) 
    {
        for (Car car : cars) 
        {
            if (registrationNumber.trim().toUpperCase().equals(car.getRegNumber())) 
            {
                return car;
            }
        }
        return null;
    }

    /**
     * To check if the car has existed in the ArrayList.
     * @return Return the added car.
     */
    private boolean persist(Car car) 
    {
        return cars.add(car);
    }

    /**
     * Update the car by given registration number, and then, the colour and price can be edited.
     * @param car Car objects in the ArrayList.
     */
    private void update(Car car) 
    {
        Car foundCar = getByRegistrationNumber(car.getRegNumber());
        
        if (foundCar != null) 
        {
            foundCar.setColours(car.getColours());
            foundCar.setPrice(car.getPrice());
        }
    }

    /**
     * Delete the car by given registration number.
     * @param car The car object.
     */
    private void delete(Car car) 
    {
        Car foundCar = getByRegistrationNumber(car.getRegNumber());
        
        if (foundCar != null)
        {
            if (cars.remove(foundCar))
            {
                System.out.println("Car with Registration number: \"" + foundCar.getRegNumber()
                    + "\" successfully deleted!");
            }
        }
    }

    /**
     * Get all the cars in the ArrayList.
     * @return Return the car list.
     */
    private ArrayList<Car> getAll() 
    {
        return cars;
    }

    /**
     * Get the car by given car make.
     * @param make The company of the car.
     * @return Return the car by given car make.
     */
    private List<Car> getByCarMake(String make) 
    {
        List<Car> foundCars = new ArrayList<>();

        for (Car car : cars) 
        {
            if (make.toUpperCase().equals(car.getMake().toUpperCase())) 
                foundCars.add(car);
        }
        return foundCars;
    }

    /**
     * Get the car by given model and car lists.
     * @return Return the specific car by given model and car lists.
     */
    private List<Car> getByCarModel(String model, List<Car> carList) 
    {
        List<Car> foundCars = new ArrayList<>();

        if (carList != null && !carList.isEmpty()) 
        {
            for (Car car : carList) 
            {
                if (model.toUpperCase().equals(car.getModel().toUpperCase())) 
                {
                    foundCars.add(car);
                }
            }
        } 
        else 
        {
            for (Car car : carList) 
            {
                if (model.toUpperCase().equals(car.getModel().toUpperCase())) 
                {
                    foundCars.add(car);
                }
            }
        }
        return foundCars;
    }

    /**
     * Get the car by given age.
     * @return Return the specific car by age.
     */
    private List<Car> getByAge(int age) 
    {
        List<Car> foundCars = new ArrayList<>();

        for (Car car : cars) 
        {
            if (car.getYear() == (2017 - age))
                foundCars.add(car);
        }
        return foundCars;
    }

    /**
     * Get the car by given min and max price.
     * @return Return the specific car by given min and max price.
     */
    private List<Car> getByPrice(int min, int max) 
    {
        List<Car> foundCars = new ArrayList<>();

        for (Car car : cars) 
        {
            if (car.getPrice() >= min && car.getPrice() <= max) 
            {
                foundCars.add(car);
            }
        }
        return foundCars;
    }

    /**
     * Get the car by given colours.
     * @return Return the specific car by colours.
     */
    private List<Car> getByColor(String color) 
    {
        List<Car> foundCars = new ArrayList<>();

        for (Car car : cars) 
        {
            for (String c : car.getColours()) 
                if (color.toLowerCase().equals(c.toLowerCase().trim())) 
                    foundCars.add(car);
        }
        return foundCars;
    }

    /**
     * To save the values of car in the text file.
     */
    public void commit() 
    {
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) 
        {
            for (Car car : cars) 
            {
                String colourString = String.join(",", car.getColours());//
                if (car.getColours().size() == 1) 
                {
                    colourString += ",,";
                } else if (car.getColours().size() == 2) 
                {
                    colourString += ",";
                }
                String line = String.format("%s,%d,%s,%s,%s,%d",
                        car.getRegNumber(),
                        car.getYear(),
                        colourString,
                        car.getMake(),
                        car.getModel(),
                        car.getPrice());
                writer.println(line);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Add new cars by given the registration number, year, colours, company, price, model, and the car should not exist before.
     */
    public void addCar() 
    {
        System.out.println("Please, fill the values for a new car: " +
            "\n------------------------------");

        Car newCar = null;
        boolean isActive = true;
        while (isActive) 
        {
            if (newCar == null)
                newCar = new Car();

            try 
            {
                if (newCar.getRegNumber() == null) 
                {
                    System.out.println("Registration Number: ");
                    String regNumber = new Scanner(System.in).nextLine().trim().toUpperCase();
                    validation.checkRegistrationNumber(regNumber);
                    Car foundCar = getByRegistrationNumber(regNumber);
                    if (foundCar != null) 
                    {
                        isActive = false;
                        throw new Exception("Car with this registration number is already exists!");
                    }
                    newCar.setRegNumber(regNumber);
                }

                if (newCar.getYear() < 1900) 
                {
                    System.out.println("Made Year: ");
                    int year = new Scanner(System.in).nextInt();
                    validation.checkYear(year);
                    newCar.setYear(year);
                }

                if (newCar.getColours().isEmpty()) 
                {
                    System.out.println("Write max 3 colours separated with comma: ");
                    String coloursString = new Scanner(System.in).nextLine().trim();
                    String[] colours = coloursString.split(",");
                    validation.checkColours(Arrays.asList(colours));
                    for (String c : colours) 
                    {
                        newCar.getColours().add(c);
                    }
                }

                if (newCar.getMake() == null) 
                {
                    System.out.println("Car Make: ");
                    String carMake = new Scanner(System.in).nextLine().trim();
                    validation.checkCarMakeAndModel(carMake, null);
                    newCar.setMake(carMake);
                }

                if (newCar.getModel() == null) 
                {
                    System.out.println("Car Model: ");
                    String carModel = new Scanner(System.in).nextLine().trim();
                    validation.checkCarMakeAndModel(null, carModel);
                    newCar.setModel(carModel);
                }

                if (newCar.getPrice() < 1) 
                {
                    System.out.println("Car Price: ");
                    int price = new Scanner(System.in).nextInt();
                    validation.checkMinPrice(price);
                    newCar.setPrice(price);
                }

                boolean added = persist(newCar);

                if (!added)
                {
                    throw new Exception("Car with this registration number is already exists!");
                } else {
                    System.out.println("New car successfully added!");
                }
                isActive = false;
            } 
            catch (InputMismatchException ex)
            {
                System.err.println("Wrong input format!");
                continue;
            } 
            
            catch (Exception ex) 
            {
                System.err.println(ex.getMessage());
                continue;
            }
        }
    }

    /**
     * Edit the car by given registration number, and then, the colour and price can be edited.
     */
    public void editCar() {
        boolean isActive = true;

        do 
        {
            try 
            {
                String regNumber;
                String coloursString;
                int price;

                System.out.println("Write the registration number:");
                regNumber = new Scanner(System.in).nextLine().trim().toUpperCase();

                validation.checkRegistrationNumber(regNumber);
                Car foundCar = getByRegistrationNumber(regNumber);

                if (foundCar == null) 
                {
                    System.out.println("Car not found with this registration number!");
                    break;
                }

                System.out.println("Enter the new colour(s) separated with comma : ");
                coloursString = new Scanner(System.in).nextLine().trim();
                List<String> colours = Arrays.asList(coloursString.split(","));
                validation.checkColours(colours);

                System.out.println("Enter the new Car Price: ");
                price = new Scanner(System.in).nextInt();
                validation.checkMinPrice(price);

                foundCar.setColours(colours);
                foundCar.setPrice(price);
                update(foundCar);
                System.out.println("Car successfully updated!");

                isActive = false;
            } 
            catch (InputMismatchException ex) 
            {
                System.err.println("Price must be a number!");
            } 
            catch (Exception ex) 
            {
                System.err.println(ex.getMessage());
            }
        } 
        while (isActive);
    }

    /**
     * Delect the car with the given registration number.
     * @exception     Catch the input exceptions.
     */
    public void deleteCar() throws Exception 
    {
        boolean isActive = true;
        
        while (isActive) 
        {
            try
            {
                Car car = searchByRegNumber();
                
                if (car != null)
                {
                    delete(car);
                } 
                else
                {
                    System.out.println("Can't find car with given registration number!");
                }
                isActive = false;
            } 
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     * Search the car with given registration number and make sure it is validate.
     * @throws Throw the exception when the registration number is not validate.
     * @return Return the car with the given registration number.
     */
    public Car searchByRegNumber() throws Exception 
    {
        System.out.println("Print registration number: ");
        String regNumber = new Scanner(System.in).next().trim().toLowerCase();
        validation.checkRegistrationNumber(regNumber);
        return getByRegistrationNumber(regNumber);
    }

    /**
     * Search the car with given car make and model, and make sure it is validate.
     * @throws Throw the exception when the car make and model are not validate.
     * @return Return the car with the given car make and model.
     */
    public List<Car> searchByCarMakeAndModel() throws Exception 
    {
        System.out.println("Car Make: ");
        String carMake = new Scanner(System.in).nextLine().trim().toLowerCase();
        validation.checkCarMakeAndModel(carMake, null);
        List<Car> cars = getByCarMake(carMake);
        
        if (cars.isEmpty())
            return cars;

        System.out.println("Car Model: ");
        String carModel = new Scanner(System.in).nextLine().trim().toLowerCase();
        validation.checkCarMakeAndModel(null, carModel);

        if ("any".equals(carModel)) 
            return cars;
        else 
            return getByCarModel(carModel, cars);
    }

    /**
     * Search the car with given age, and make sure it is validate.
     * @throws Throw the exception when the age are not validate.
     * @return Return the car with the given age.
     */
    public List<Car> searchByAge() throws Exception 
    {
        System.out.println("Print car age: ");
        int age = new Scanner(System.in).nextInt();
        validation.checkAge(age);       
        return getByAge(age);
    }

    /**
     * Search the car with given price, and make sure it is validate.
     * @throws Throw the exception when the price are not validate.
     * @return Return the car with the given price.
     */
    public List<Car> searchByPrice() throws Exception 
    {
        System.out.println("Print the minimum price: ");
        int min = new Scanner(System.in).nextInt();
        validation.checkMinPrice(min);
        System.out.println("Print the maximum price: ");
        int max = new Scanner(System.in).nextInt();
        validation.checkMaxPrice(min, max);
        return getByPrice(min, max);
    }

    /**
     * Search the car with given colour, and make sure it is validate.
     * @throws Throw the exception when the colour are not validate.
     * @return Return the car with the given colour.
     */
    public List<Car> searchByColour(String colour) throws Exception 
    {
        System.out.println(" Write a color: ");
        String carColour = new Scanner(System.in).nextLine().trim().toLowerCase();
        validation.checkColour(carColour);
        return getByColor(carColour);
    }

    /**
     * Show the all the cars ArrayList by invoking internal method.
     * Print the car details to the terminal.
     */
    public void showAllCars() 
    {
        ArrayList<Car> cars = getAll();
        for (Car car : cars) 
            System.out.println(car);
    }
}
