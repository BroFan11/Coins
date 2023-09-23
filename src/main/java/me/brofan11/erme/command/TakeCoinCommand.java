package me.brofan11.erme.command;

import me.brofan11.erme.Permissions;
import me.brofan11.erme.api.CoinsAPI;
import me.brofan11.erme.config.MessageConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TakeCoinCommand extends Command {
    public TakeCoinCommand() {
        super("ermeelvetel", "Elveszi az ermet a jatekostol", "/ermeelvetel <jatekos> <erme>", Collections.emptyList());
        setPermission(Permissions.TAKE_COIN.getName());
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (args.length < 2) {
            MessageUtils.sendMessage(sender, getUsage());
            return false;
        }
        long coin;
        try {
            coin = Long.parseLong(args[1]);
        } catch (Exception e) {
            MessageUtils.sendMessage(sender, MessageConfig.NUMBER_ONLY.getValue());
            return false;
        }
        // noinspection deprecation
        UUID uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        CoinsAPI.takeCoin(uuid, coin);
        MessageUtils.sendMessage(sender, MessageConfig.SUCCESS.getValue());
        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return super.tabComplete(sender, alias, args);
        } else if (args.length == 2) {
            return IntStream.range(1, 100).boxed().map(i -> Integer.toString(i)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
