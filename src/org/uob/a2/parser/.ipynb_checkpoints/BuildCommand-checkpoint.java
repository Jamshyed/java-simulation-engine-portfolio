package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class BuildCommand extends Command
{

    public BuildCommand(List<String> words)
    {
        super(words);
    }

    public String execute(Context ctx)
    {
        if (words.size() < 2)
        {
            return "Please specify what to build.";
        }

        String type = words.get(1).toLowerCase();
        return ctx.engine().buildEntity(type);
    }
}