package services.console;

import exceptions.CommandNotFound;
import services.VehicleRentingService;
import services.strategies.BookingStrategy;
import services.strategies.PricingStrategy;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    private List<ICommand> commands = new ArrayList<>();
    private final VehicleRentingService vehicleRentingService;

    public CommandHandler(BookingStrategy bookingStrategy, PricingStrategy pricingStrategy) {
        this.vehicleRentingService = VehicleRentingService.getInstance(bookingStrategy, pricingStrategy);
    }

    public void register(ICommand command){
        commands.add(command);
    }

    private ICommand getCommand(List<String> input) throws CommandNotFound {
        for(ICommand command: commands){
            if(command.matches(input)){
                return command;
            }
        }
        throw new CommandNotFound("Invalid input");
    }

    public void execute(List<String> input) throws CommandNotFound {
        this.getCommand(input).execute(input, vehicleRentingService);
    }

}
