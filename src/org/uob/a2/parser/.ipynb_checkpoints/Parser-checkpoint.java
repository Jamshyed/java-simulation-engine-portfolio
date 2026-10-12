package org.uob.a2.parser;

import org.uob.a2.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class Parser {
    public Command parse (String input)
    {
        if(input == null || input.trim().isEmpty()) //user error handling
        {
            List<String> x = new ArrayList<>();
            x.add("invalid");
            return new InvalidCommand(x);
        }
    

    String part [] = input.split(" "); //splitting the user input for switch case 
    String command = part[0].toLowerCase();
        // handling each command 
        switch(command)
        {
            case "build":
            case "b":
                if (part.length >= 2)
                {
                    List<String> x = new ArrayList<>();
                    x.add("build");
                    x.add(part[1]);
                    return new BuildCommand(x);
                }
                {
                    List<String> x = new ArrayList<>();
                    x.add("invalid");
                    return new InvalidCommand(x);
                }
    
            case "tick":
            case "t":
                if (part.length == 2)
                {
                    List<String> x = new ArrayList<>();
                    x.add("tick");
                    x.add(part[1]);
                    return new TickCommand(x);
                }
                {
                    List<String> x = new ArrayList<>();
                    x.add("tick");
                    return new TickCommand(x);
                }
    
            case "launch":
                if (part.length >= 2)
                {
                    List<String> x = new ArrayList<>();
                    x.add("launch");
                    x.add(part[1]);
                    return new LaunchCommand(x);
                }
                {
                    List<String> x = new ArrayList<>();
                    x.add("invalid");
                    return new InvalidCommand(x);
                }
    
            case "cheat":
                {
                    List<String> x = new ArrayList<>();
                    x.add("cheat");
                    return new CheatCommand(x);
                }
    
            case "info":
            case "i":
                if (part.length ==1)
                {
                    List<String> x = new ArrayList<>();
                    x.add("info");
                    return new InfoCommand(x);
                }
                else
                {
                    List<String> x = new ArrayList<>();
                    x.add("info");
                    x.add(part[1]);
                    return new InfoCommand(x);
                }
    
            case "quit":
                {
                    List<String> x = new ArrayList<>();
                    x.add("quit");
                    return new QuitCommand(x);
                }
                
    
            case "help":
                {
                    List<String> x = new ArrayList<>();
                    x.add("help");
                    return new HelpCommand(x);
                }
    
            case "graph":
            case "g":
            {
                List<String> x = new ArrayList<>();
                x.add("graph");
                
                if (part.length >= 2)
                {
                    x.add(part[1]);
                }
                else
                {
                
                }
                return new GraphCommand(x);
            }
    
            case "save":
            case "s":
                if (part.length >= 2)
                {
                    List<String> x = new ArrayList<>();
                    x.add("save");
                    x.add(part[1]);
                    return new SaveCommand(x);
                }
                {
                    List<String> x = new ArrayList<>();
                    x.add("invalid");
                    return new InvalidCommand(x);
                }
    
            case "load":
            case "l":
                if (part.length >= 2)
                {
                    List<String> x = new ArrayList<>();
                    x.add("load");
                    x.add(part[1]);
                    return new LoadCommand(x);
                }
                {
                    List<String> x = new ArrayList<>();
                    x.add("invalid");
                    return new InvalidCommand(x);
                }
    
            default:
                {
                    List<String> x = new ArrayList<>();
                    x.add("invalid");
                    return new InvalidCommand(x);
                }
        }
    }
}
        

    



            
