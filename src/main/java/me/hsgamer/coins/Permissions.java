package me.hsgamer.coins;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission GET_COIN = new Permission("coins.get", PermissionDefault.TRUE);
    public static final Permission ADD_COIN = new Permission("coins.add", PermissionDefault.OP);
    public static final Permission TAKE_COIN = new Permission("coins.take", PermissionDefault.OP);
    public static final Permission SET_COIN = new Permission("coins.set", PermissionDefault.OP);

    private Permissions() {
        // EMPTY
    }
}
