package org.uob.a2.engine;
import org.uob.a2.model.ResourceType;

public class House extends Producer
{
    public House (String name)
    {
        super (name, ResourceType.PEOPLE, 1);
    }

    public void tick (Context ctx)
    {
        ctx.state().addResource(ResourceType.PEOPLE, amount);
    }

    public void produce (Context ctx)
    {
        ctx.state().addResource(ResourceType.PEOPLE, amount);
    }

    public String toCSV()
    {
        return "house," + name;
    }
}
        