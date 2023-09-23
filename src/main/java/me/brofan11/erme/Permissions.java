package me.brofan11.erme;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission GET_COIN = new Permission("erme.leker", PermissionDefault.TRUE);
    public static final Permission ADD_COIN = new Permission("erme.ad", PermissionDefault.OP);
    public static final Permission TAKE_COIN = new Permission("erme.elvetel", PermissionDefault.OP);
    public static final Permission SET_COIN = new Permission("erme.beallit", PermissionDefault.OP);

    private Permissions() {
        // EMPTY
    }
}
