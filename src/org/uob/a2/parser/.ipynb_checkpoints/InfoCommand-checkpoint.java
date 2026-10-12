package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class InfoCommand extends Command
{

    public InfoCommand(List<String> words)
    {
        super(words);
    }

    public String execute(Context ctx)
    {
        if (words.size() < 2)
        {
            return " resources/entities info. ";
        }

        String option = words.get(1).toLowerCase();

        switch (option)
            {
            case "resources":
                return ctx.state().toResourceString();
            case "entities":
                return ctx.state().toEntityString();
            default:
                return "Unknown option.";
        }
    }
}