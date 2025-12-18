package com.WolfChamp.timcheese.command;

import com.WolfChamp.timcheese.capabilities.ModCapabilities;
import com.WolfChamp.timcheese.network.ModPackets;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class CheseCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("chese")
                        .requires(src -> src.hasPermission(2))
                        .then(
                                Commands.literal("add")
                                        .then(
                                                Commands.literal("sight")
                                                        .then(
                                                                Commands.argument("amount", IntegerArgumentType.integer(1))
                                                                        .executes(ctx -> {
                                                                            return add(ctx, "sight");
                                                                        })
                                                        )
                                        )
                                        .then(
                                                Commands.literal("touch")
                                                        .then(
                                                                Commands.argument("amount", IntegerArgumentType.integer(1))
                                                                        .executes(ctx -> {
                                                                            return add(ctx, "touch");
                                                                        })
                                                        )
                                        )
                                        .then(
                                                Commands.literal("mind")
                                                        .then(
                                                                Commands.argument("amount", IntegerArgumentType.integer(1))
                                                                        .executes(ctx -> {
                                                                            return add(ctx, "mind");
                                                                        })
                                                        )
                                        )
                        )
        );
    }

    private static int add(
            com.mojang.brigadier.context.CommandContext<CommandSourceStack> ctx,
            String type
    ) {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int amt = IntegerArgumentType.getInteger(ctx, "amount");

        player.getCapability(ModCapabilities.CHESE).ifPresent(chese -> {
            switch (type) {
                case "sight" -> chese.addSight(amt);
                case "touch" -> chese.addTouch(amt);
                case "mind"  -> chese.addMind(amt);
            }
        });

        ModPackets.sync(player);

        ctx.getSource().sendSuccess(
                () -> net.minecraft.network.chat.Component.literal(
                        "Added " + amt + " " + type
                ),
                false
        );
        return 1;
    }
}
