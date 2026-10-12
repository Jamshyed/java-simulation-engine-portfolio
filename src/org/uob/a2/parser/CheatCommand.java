package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class CheatCommand extends Command
{

    public CheatCommand(List<String> words)
    {
        super(words);
    }

    public String execute(Context ctx)
    {
        ctx.engine().ResourcesCheat();
        return "<Cheat mode activated> 1000 of each resource added ";
    }
}