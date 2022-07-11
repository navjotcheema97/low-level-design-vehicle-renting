import exceptions.BookingFullException;
import models.Booking;
import models.Vehicle;
import models.VehicleType;
import services.VehicleRentingService;
import services.console.*;
import services.strategies.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BookingStrategy bookingStrategy = new LowestPriceBookingStrategy();
        PricingStrategy pricingStrategy = new DefaultPricingStrategy();
        //PricingStrategy pricingStrategy = new DynamicPricingStrategy();

        CommandHandler commandHandler = new CommandHandler(bookingStrategy, pricingStrategy);
        commandHandler.register(new AddBranchCommand());
        commandHandler.register(new AddVehicleCommand());
        commandHandler.register(new BookVehicleCommand());
        commandHandler.register(new DisplayVehicleCommand());

        String filePath = args.length > 0 ? args[0] : "sample_input/input1.txt";

        FileInputStream fstream = new FileInputStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        while ((strLine = br.readLine()) != null) {
            List<String> input = Arrays.asList(strLine.split(" "));
            try{
                commandHandler.execute(input);
            } catch (Exception e){
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }

}
