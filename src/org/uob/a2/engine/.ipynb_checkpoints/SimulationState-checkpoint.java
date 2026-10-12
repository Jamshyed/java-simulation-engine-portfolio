package org.uob.a2.engine;

import org.uob.a2.model.*;

import java.util.List;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.Map;

public class SimulationState {

    private List<Producer> producers;
    private List<Converter> converters;
    private List<Consumer> consumers;
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    public SimulationState()
    {
        producers = new ArrayList<>();
        converters = new ArrayList<>();
        consumers = new ArrayList<>();
        // start with 0
        ResourceType[] type = ResourceType.values();
        for (int i = 0; i < type.length; i++)
        {
            ResourceType rt = type[i];
            inventory.put(rt,0);
        }
    }
        

    public List<Producer> getProducers() {
        return producers;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void addProducer (Producer prod)
    {
        producers.add(prod);
    }
    public void addConverter (Converter conv)
    {
        converters.add(conv);
    }
    public void addConsumer (Consumer cons) 
    {
        consumers.add(cons);
    }

    //return all entities in 1 list 
    public List<Entity> getEntities()
    {
        List<Entity> all = new ArrayList<>();

        for (int i = 0; i < producers.size(); i++)
        {
            all.add(producers.get(i));
        }
        for (int i = 0; i < converters.size(); i++)
        {
            all.add(converters.get(i));
        }
        for (int i = 0; i < consumers.size(); i++)
        {
            all.add(consumers.get(i));
        }
        return all;
    }

    //adding resources 
    public void addResource(ResourceType type, int amount)
    {
        int currentAmount = inventory.get(type);
        inventory.put(type, currentAmount + amount);
    }

    //removing 
    public boolean removeResource(ResourceType type, int amount)
    {
        int currentAmount = inventory.get(type);

        if (currentAmount < amount)
        {
            return false;
        }

        inventory.put(type, currentAmount - amount);
        return true;
    }

    public boolean removeResources(Map<ResourceType, Integer> cost)
    {

        // checking if theres enough of each resource 
        ResourceType[] type = ResourceType.values();
        for (int i = 0; i < type.length; i++) 
        {
            ResourceType rt = type[i];
    
            if (cost.containsKey(rt))
            {
                // only check resources in the costing map
                int needed = cost.get(rt);
                int available = inventory.get(rt);
    
                if (available < needed) 
                {
                    return false; //if not enough then stop
                }
            }
        }
    
    
        // take away the amount 
        for (int i = 0; i < type.length; i++)
        {
            ResourceType rt = type[i];
    
            if (cost.containsKey(rt))
            {
                int needed = cost.get(rt);
                int current = inventory.get(rt);
                inventory.put(rt, current - needed);
            }
        }

    return true;
    }

    public List<Map<ResourceType, Integer>> getResourceHistory()
    {
        return resourceHistory;
    }
    public List<Map<ResourceType, Integer>> getHistory()
    {
        return resourceHistory;
    }

    public void updateResource(ResourceType type, int amount)
    {
        inventory.put(type, amount);
    }

    public int getResourceAmount(ResourceType type)
    {
        return inventory.get(type);
    }

    public Map<ResourceType, Integer> getInventory()
    {
        return inventory;
    }

    public boolean hasResource(ResourceType type, int amount)
    {
        int CurrentAmount = inventory.get(type);
        if (CurrentAmount >= amount)
        {
            return true;
        }
        return false;
    }

    public void updateHistory()
    {
        Map<ResourceType, Integer> status = new EnumMap<>(ResourceType.class); //clone inventory into a new map

        ResourceType[] type = ResourceType.values();
        for (int i = 0; i < type.length; i++)
        {
            ResourceType rt = type[i];
            status.put(rt, inventory.get(rt));
        }
        resourceHistory.add(status);
    }

    
    //printing 
    public String toResourceString()
    {
        String list = "**Resources**\n";

        ResourceType[] type = ResourceType.values();
        for(int i = 0; i < type.length; i++)
        {
            ResourceType rt = type[i];
            list += rt.name() + ": " + inventory.get(rt) + "\n";
        }
        return list;
    }

    public String toEntityString()
    {
        String list = "**Entities**\n";

        for (int i = 0; i < producers.size(); i++)
        {
            Producer prod  = producers.get(i);
            list += prod.getName() + " <producer>\n";
        }

        for (int i = 0; i < converters.size(); i++)
        {
            Converter conv = converters.get(i);
            list += conv.getName() + " <converter>\n";
        }

        for (int i = 0; i < consumers.size(); i++)
        {
            Consumer cons = consumers.get(i);
            list += cons.getName() + " <consumer>\n";
        }
        return list;
    }
    
}