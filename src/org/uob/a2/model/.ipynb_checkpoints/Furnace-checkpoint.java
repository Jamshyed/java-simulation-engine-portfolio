package org.uob.a2.engine;
import org.uob.a2.model.ResourceType;
import java.util.Map;
import java.util.EnumMap;

public class Furnace extends Converter 
{
    public Furnace (String name)
    {
        super (name, ResourceType.WOOD, 10, ResourceType.COAL, 1); //input/output
    }

        
    public void convert(Context ctx)
    {
        SimulationState state = ctx.state();

        if (state.getResourceAmount(ResourceType.WOOD) < 10) //check for wood
        {
            return;
        }
        if (state.getResourceAmount(ResourceType.WATER) < 10) //check for water
        {
            return;
        }

        state.removeResource(ResourceType.WOOD, 10); //remove if there is sufficient wood
        state.removeResource(ResourceType.WATER, 10); //remove if there is sufficient water 

        state.addResource(ResourceType.COAL, 1); 
    }

    public void tick (Context ctx) 
    {
        convert(ctx);
    }

    public String toCSV()
    {
        return "furnace," + name;
    }
}