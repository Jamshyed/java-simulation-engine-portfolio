package org.uob.a2.engine;
import org.uob.a2.model.ResourceType;

public class SpacePort extends Consumer
{
    private int level = 1; //handling level
    
    public SpacePort (String name)
    {
        super(name, ResourceType.METAL, 10); 
        
    }

    public String launch (Context ctx) //return string for engine 
    {
        SimulationState state = ctx.state();

        int price = 10 * level;

        if (state.getResourceAmount(ResourceType.METAL) < price)
        {
            return "Not enough metal to luanch";
        }
        if (state.getResourceAmount(ResourceType.PEOPLE) < price)
        {
            return "Not enough people to launch";
        }

        state.removeResource(ResourceType.METAL, price);
        state.removeResource(ResourceType.PEOPLE, price);

        level ++; //reward system 
        int levelReward = 100;
        state.addResource(ResourceType.CREDITS, levelReward);
        return "**Spaceport launched** upgraded to level " + level + " and have recieved " + levelReward + " credits";
    }

    public void consume (Context ctx)
    {
    }

    public void tick(Context ctx)
    {
        consume(ctx);
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String toCSV()
    {
        return "spaceport," + name + "," + level;
    }
}

    