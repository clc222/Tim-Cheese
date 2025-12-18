package com.WolfChamp.timcheese.command;

import com.WolfChamp.timcheese.capabilities.ModCapabilities;
import com.WolfChamp.timcheese.capabilities.RaceType;
import com.WolfChamp.timcheese.network.ModPackets;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class RaceCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("race")
                        .requires(src -> src.hasPermission(2))
                        .then(
                                Commands.literal("set")
                                        .then(
                                                Commands.argument("race", StringArgumentType.word())
                                                        .suggests((ctx, builder) -> {
                                                            for (RaceType r : RaceType.values()) {
                                                                builder.suggest(r.name().toLowerCase());
                                                            }
                                                            return builder.buildFuture();
                                                        })
                                                        .executes(ctx -> {
                                                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                                                            String input = StringArgumentType.getString(ctx, "race").toUpperCase();

                                                            RaceType race;
                                                            try {
                                                                race = RaceType.valueOf(input);
                                                            } catch (Exception e) {
                                                                ctx.getSource().sendFailure(
                                                                        net.minecraft.network.chat.Component.literal("Invalid race")
                                                                );
                                                                return 0;
                                                            }

                                                            player.getCapability(ModCapabilities.RACE).ifPresent(r -> r.setRace(race));
                                                            ModPackets.sync(player);

                                                            ctx.getSource().sendSuccess(
                                                                    () -> net.minecraft.network.chat.Component.literal(
                                                                            "Race set to " + race.name()
                                                                    ),
                                                                    false
                                                            );
                                                            return 1;
                                                        })
                                        )
                        )
        );
    }
}
