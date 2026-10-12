package org.uob.a2.engine;
import org.uob.a2.model.ResourceType;

public class Lake extends Producer 
{
    public Lake (String name)
    {
        super(name, ResourceType.WATER, 10);
    }

    public void tick(Context ctx)
    {
        ctx.state().addResource(ResourceType.WATER, amount);
    }

    public void produce (Context ctx)
    {
        ctx.state().addResource(ResourceType.WATER, amount);
    }

    public String toCSV()
    {
        return "lake," + name;
    }
}
        