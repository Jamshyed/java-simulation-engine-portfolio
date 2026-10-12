package org.uob.a2.parser;

import org.uob.a2.*;
import java.util.List;
import org.uob.a2.engine.Context;

public class GraphCommand extends Command
{
    public GraphCommand(List<String> words)
    {
        super(words);
    }

    public String execute(Context ctx)
    {
        if (words.size() < 2)
        {
            return "Specify a resource";
        }

        String resource = words.get(1);
        return ctx.engine().ResourceGraph(resource);
    }
}