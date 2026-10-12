package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;

import java.util.Scanner;

public class Main {
    public static void main (String[] args)
    {
        SimulationState state = new SimulationState(); //creating new state and engine for the state
        Engine engine = new Engine(state);

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        System.out.println("Welcome to the space simulation.\nType 'help' to see the list of availible commands.");
        System.out.println("Do you wish to load a saved game? <yes/no>"); //logic for loading a saved file or a new game 
        String user = scanner.nextLine().trim().toLowerCase();

        if (user.equals("yes"))
        {
            System.out.println("Enter filename of the game you wish to load > "); //loading the saved file 
            String fileName = scanner.nextLine();
            String result = engine.load(fileName);
            System.out.println(result);

        }
        else
        {
            engine.initialiseDefaults(); //otherwise create a new game 
        }
        //MAIN LOOP
        while (true)
        {   //user input 
            System.out.print("> ");
            String userInput = scanner.nextLine(); 

            Command command = parser.parse(userInput); //convert the user input to a command 
            String output = command.execute(new Context(engine, engine.getState())); //using the given state and engine, execute the command 

            System.out.println(output);

            if (output.equalsIgnoreCase("Thanks for playing")) //if the result of the quit command is found then end the game 
            {
                break;
            }
            state = engine.getState(); //update the state 
        }
        scanner.close();
        System.out.println("Simulation stopped.");
            
    
    }
   
} 