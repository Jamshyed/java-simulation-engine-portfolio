package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class TickCommand extends Command
{

    public TickCommand(List<String> words)
    {
        super(words);
    }

    
    public String execute(Context ctx)
    {
        return ctx.engine().nextTick();
    }
}