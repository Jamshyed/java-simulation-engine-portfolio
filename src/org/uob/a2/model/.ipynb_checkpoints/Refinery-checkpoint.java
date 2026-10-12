package org.uob.a2.engine;
import org.uob.a2.model.ResourceType;

public class Refinery extends Converter
{
    public Refinery (String name)
    {
        super (name, ResourceType.ORE, 10, ResourceType.METAL, 1); //input/output
    }

    public void convert (Context ctx)
    {
        SimulationState state = ctx.state();

        if (state.getResourceAmount(ResourceType.ORE) < 10) //check ore
        {
            return;
        }
        if (state.getResourceAmount(ResourceType.COAL) < 10) //check coal
        {
            return;
        }

        state.removeResource(ResourceType.ORE, 10); //remove if enough
        state.removeResource(ResourceType.COAL, 10); //remove if enough

        state.addResource(ResourceType.METAL, 1); //convert to metal 
    }

    public void tick (Context ctx)
    {
        convert(ctx);
    }

    public String toCSV()
    {
        return "refinery," + name;
    }
}