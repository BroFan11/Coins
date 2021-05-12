package me.hsgamer.coins.command;

import me.hsgamer.coins.Permissions;
import me.hsgamer.coins.api.CoinsAPI;
import me.hsgamer.coins.config.MessageConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.UUID;

public class GetCoinCommand extends Command {
    public GetCoinCommand() {
        super("getcoin", "Get the coins you or other player has", "/getcoin [player]", Collections.emptyList());
        setPermission(Permissions.GET_COIN.getName());
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (args.length <= 0) {
            if (!(sender instanceof Player)) {
                MessageUtils.sendMessage(sender, MessageConfig.PLAYER_ONLY.getValue());
                return false;
            } else {
                long coin = CoinsAPI.getCoin(((Player) sender).getUniqueId());
                MessageUtils.sendMessage(sender, MessageConfig.GET_COIN_MESSAGE.getValue().replace("{coin}", Long.toString(coin)));
                return true;
            }
        } else {
            // noinspection deprecation
            UUID uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
            long coin = CoinsAPI.getCoin(uuid);
            MessageUtils.sendMessage(sender, MessageConfig.GET_COIN_MESSAGE.getValue().replace("{coin}", Long.toString(coin)));
            return true;
        }
    }
}
