package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class LaunchCommand extends Command
{

    public LaunchCommand(List<String> words)
    {
        super(words);
    }

    public String execute(Context ctx)
    {
        if (words.size() < 2)
        {
            return "Please give which consumer to launch.";
        }

        String name = words.get(1);
        return ctx.engine().LaunchConsumer(name);
    }
}