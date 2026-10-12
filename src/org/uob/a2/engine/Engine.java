package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Engine {
    private int CurrentTick = 0;
    private SimulationState state;

    public Engine (SimulationState state)
    {
        this.state = state;
        state.addResource(ResourceType.CREDITS, 1000); //giving player 1000 intial credits 
    }
    //getters 
    public int getCurrentTick()
    {
        return CurrentTick; 
    }

    public SimulationState getState()
    {
        return state;
    }

    public String nextTick()  //ticking each entity 
    {
        CurrentTick++;
        Context ctx = new Context(this, state);

        List<Producer> producers = state.getProducers();
        for (int i = 0; i < producers.size(); i++)
        {
            Producer prod = producers.get(i);
            prod.tick(ctx);
        }

        List<Converter> converters = state.getConverters();
        for (int i = 0; i < converters.size(); i++)
        {
            Converter conv = converters.get(i);
            conv.tick(ctx);
        }

        List<Consumer> consumers = state.getConsumers();
        for (int i = 0; i < consumers.size(); i++)
        {
            Consumer cons = consumers.get(i);
            cons.tick(ctx);
        }
        state.updateHistory(); //saving 
        return "Tick completed " + "(" + CurrentTick + ")"; //output 
    }

    public void ResourcesCheat() //cheat for increasing resources 
    {
        ResourceType[] type = ResourceType.values(); //looping through each resources and adding 1000 to each one 
        for (int i = 0; i < type.length; i++)
        {
            state.addResource(type[i], 1000);
        }
    }

    public String buildEntity(String type)
    {
        type = type.toLowerCase();
        int cost = 0;
        
        if (type.equals("mine")) cost = 100;
        else if (type.equals("forest")) cost = 100;
        else if (type.equals("lake")) cost = 100;
        else if (type.equals("house")) cost = 130;
        else if (type.equals("furnace")) cost = 200;
        else if (type.equals("refinery")) cost = 300;
        else if (type.equals("spaceport")) cost =500;
    
        // handling invalid entity
        if (cost == 0)
        {
            return "Invalid entity > " + type;
        }
    
        // check if enough credits 
        if (state.getResourceAmount(ResourceType.CREDITS) < cost)
        {
            return "Not enough credits to build " + type + " (Cost: " + cost + ")";
        }
    
        // update new balance 
        state.removeResource(ResourceType.CREDITS, cost);
        
        int count = 0;
        // counting how many pre existig producers/converters/consumers there are (for naming)
        List<Producer> producers = state.getProducers();
        for (int i = 0; i < producers.size(); i++)
        {
            Producer prod = producers.get(i);
            String EntityName = prod.getClass().getSimpleName().toLowerCase();

            if (EntityName.equals(type))
            {
                count ++;
            }
        }

        List<Converter> converters = state.getConverters();
        for (int i = 0; i < converters.size(); i++)
        {
            Converter conv = converters.get(i);
            String EntityName = conv.getClass().getSimpleName().toLowerCase();

            if (EntityName.equals(type))
            {
                count ++;
            }
        }

        List<Consumer> consumers = state.getConsumers();
        for (int i = 0; i < consumers.size(); i++)
        {
            Consumer cons = consumers.get(i);
            String EntityName = cons.getClass().getSimpleName().toLowerCase();

            if (EntityName.equals(type))
            {
                count ++;
            }
        }

        String name = type + "_" + (count + 1);

        if (type.equals("mine")) //producers 
        {
            state.addProducer(new Mine(name));
            return "Built " + name;
        }
         if (type.equals("forest"))
        {
            state.addProducer(new Forest(name));
            return "Built " + name;
        }
         if (type.equals("lake"))
        {
            state.addProducer(new Lake(name));
            return "Built " + name;
        }
         if (type.equals("house"))
        {
            state.addProducer(new House(name));
            return "Built " + name;
        }

         if (type.equals("furnace")) //converters 
        {
            state.addConverter(new Furnace(name));
            return "Built " + name;
        }
        if (type.equals("refinery"))
        {
            state.addConverter(new Refinery(name));
            return "Built " + name;
        }

        if (type.equals("spaceport"))
        {
            state.addConsumer(new SpacePort(name));
            return "Built " + name;
        }

        return "Invalid entity > " + type;
    }

    public String LaunchConsumer(String name) 
    {
        List<Consumer> consumers = state.getConsumers();
        for (int i = 0; i < consumers.size(); i++)
        {
            Consumer cons = consumers.get(i);

            if (cons.getName().equalsIgnoreCase(name))
            {
                if(!cons.getClass().getSimpleName().equals("SpacePort")) 
                {
                    return "This consumer cannot be launched";
                }
                SpacePort spaceport = (SpacePort)cons; //casting 
                return spaceport.launch(new Context(this,state));
            }
        }

        return "invalid consumer > " + name;
    }

    public String ResourceGraph(String ResourceName) //get resource for graph
    {
        ResourceType type = null;
        String nameUpper = ResourceName.toUpperCase();

        ResourceType[] types = ResourceType.values();
        for (int i = 0; i < types.length; i++)
        {
            if (types[i].name().equals(nameUpper))
            {
                type = types[i];
                break;
            }
        }

        if(type == null)
        {
            return "Invalid resource";
        }
        //displaying graph
        List<Map<ResourceType, Integer>> history = state.getHistory();

        String output = "Graph showing " + type.name() + ">\n";

        for (int i = 0; i < history.size(); i++)
        {
            Map<ResourceType, Integer> pastTicks = history.get(i);
            int amount = pastTicks.get(type);
            int scaledVisual = amount / 10; 

            String visual = "";
            for (int x = 0; x < scaledVisual; x++)
            {
                visual = visual + "/";
            }

            output = output + "Tick " + i + ": " + visual + "\n";
        }
        return output;
    }
        
        
    public String save (String filename) 
    {
        try
        {
            if (!filename.contains("/") && !filename.contains("\\")) //if no filename specified then save to data/ by default 
            {
                filename = "data/" + filename;
            }
            
            PrintWriter output = new PrintWriter(new FileWriter(filename));
            output.println("CurrentTick," + CurrentTick); 
            output.println("METAL, " + ResourceType.METAL);
            output.println("ORE, " + ResourceType.ORE);
            output.println("PEOPLE, " + ResourceType.PEOPLE);
            output.println("WATER, " + ResourceType.WATER);
            output.println("WOOD, " + ResourceType.WOOD);
            output.println("CREDITS, " + ResourceType.CREDITS);


            ResourceType[] type = ResourceType.values(); //saving resources
            for (int i = 0; i < type.length; i++)
            {
                ResourceType rt = type[i];
                int resAmount = state.getResourceAmount(rt);
                output.println("RESOURCE," + rt.name() + "," + resAmount);
            }

            List<Entity> allEnt = state.getEntities(); //saving entities 
            for (int i = 0; i < allEnt.size(); i++)
            {
                Entity ent = allEnt.get(i);
                output.println(ent.toCSV());
            }
            output.close();
            return "Save to " + filename;
        }

        catch (Exception e)
        {
            return "Unable to save";
        }
    }

    public String load(String filename)
    {
        try
        {
            
            BufferedReader reader = new BufferedReader(new FileReader(filename)); //open file 
            CurrentTick = 0; //reset 
            SimulationState NewState = new SimulationState();

            String hold; //store lines of text 
            while ((hold = reader.readLine()) !=null) //keep reading until empty 
            {
                String [] part = hold.split(",");


                if (part[0].equals("TICK")) //load tick
                {
                    CurrentTick = Integer.parseInt(part[1]);
                }

                else if (part[0].equals("RESOURCE")) //load resource 
                {
                    ResourceType rt = ResourceType.valueOf(part[1]);
                    int amount = Integer.parseInt(part[2]);
                    NewState.updateResource(rt, amount); //load state of resources saved
            
                }
                else
                { //load entities and unique names 
                    String type = part[0].toLowerCase();
                    String name = part[1];
                    
                    if (type.equals("mine"))
                    NewState.addProducer(new Mine(name));
                    else if (type.equals("forest"))
                    NewState.addProducer(new Forest(name));

                    else if (type.equals("lake"))
                        NewState.addProducer(new Lake(name));
    
                    else if (type.equals("house"))
                        NewState.addProducer(new House(name));
    
                    else if (type.equals("furnace"))
                        NewState.addConverter(new Furnace(name));
    
                    else if (type.equals("refinery"))
                        NewState.addConverter(new Refinery(name));
    
                    else if (type.equals("spaceport")) //restoring spaceport level 
                    {
                        SpacePort spaceport = new SpacePort(name);
                        if (part.length >= 3)
                        {
                            int level = Integer.parseInt(part[2]);
                            spaceport.setLevel(level);
                        }
                        NewState.addConsumer(spaceport);
                    }
                }
            }
            reader.close();
            state = NewState;
            return "Loaded " + filename;
        }
        catch (Exception e)
        {
            return "Failed to load file";
        }
                    
        
    }

    
    public void initialiseDefaults() {
        state.addProducer(new Mine ("Mine_1"));
        state.addProducer(new Lake ("Lake_1"));
        state.addProducer(new Forest ("Forest_1"));
        state.addProducer(new House ("House_1"));
        state.addConverter(new Furnace ("Furnace_1"));
        state.addConverter(new Refinery ("Refinery_1"));
        state.addConsumer(new SpacePort ("SpacePort_1"));
        
    }

}