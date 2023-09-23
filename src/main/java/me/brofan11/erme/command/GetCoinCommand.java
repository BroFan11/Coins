package me.brofan11.erme.command;

import me.brofan11.erme.Permissions;
import me.brofan11.erme.api.CoinsAPI;
import me.brofan11.erme.config.MessageConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GetCoinCommand extends Command {
    public GetCoinCommand() {
        super("ermeinfo", "Megtudod vele nezni hogy az adott jatekosnak mennyi ermeje van", "/ermeinfo [jatekos]", Collections.emptyList());
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

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return super.tabComplete(sender, alias, args);
        } else {
            return Collections.emptyList();
        }
    }
}
