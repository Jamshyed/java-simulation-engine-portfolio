package org.uob.a2.engine;
import org.uob.a2.model.ResourceType;

public class Forest extends Producer
{
    public Forest (String name)
    {
        super(name, ResourceType.WOOD, 10);
    }

    public void tick (Context ctx)
    {
        ctx.state().addResource(ResourceType.WOOD, amount);
    }

    public void produce (Context ctx)
    {
        ctx.state().addResource(ResourceType.WOOD, amount);
    }

    public String toCSV()
    {
        return "forest," + name;
    }
}


    