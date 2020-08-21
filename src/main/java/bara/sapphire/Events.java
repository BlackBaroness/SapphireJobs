package bara.sapphire;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    private final Economy eco;
    private final Coefficient coeff;

    Events(Economy eco, Coefficient coeff) {
        this.eco = eco;
        this.coeff = coeff;
    }

    @EventHandler
    private void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Material mat = e.getBlock().getType();
        switch (mat) {
            case DIAMOND_ORE: {
                give(p, 10);
                return;
            }
            case COAL_ORE: {
                give(p, 1);
                return;
            }
            case IRON_ORE: {
                give(p, 3);
                return;
            }
            case REDSTONE_ORE: {
                give(p, 2);
                return;
            }
            case QUARTZ_ORE: {
                give(p, 3);
                return;
            }
            case EMERALD_ORE: {
                give(p, 50);
                return;
            }
            case GOLD_ORE: {
                give(p, 5);
                return;
            }
            case STONE: {
                give(p, (int) 0.01);
                return;
            }
            case SAPLING: {
                take(p, 1, e);
                return;
            }
            case LOG: {
                give(p, 0.2);
                return;
            }
            case WHEAT: {
                boolean i = false;
                for (ItemStack item : e.getBlock().getDrops()) {
                    if (item.getType().equals(Material.WHEAT)) {
                        i = true;
                        break;
                    }
                }
                if (i) give(p, (int) 0.4);
            }
        }
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Material mat = e.getBlock().getType();
        switch (mat) {
            case DIAMOND_ORE: {
                take(p, 10, e);
                return;
            }
            case COAL_ORE: {
                take(p, 1, e);
                return;
            }
            case IRON_ORE: {
                take(p, 3, e);
                return;
            }
            case REDSTONE_ORE: {
                take(p, 2, e);
                return;
            }
            case QUARTZ_ORE: {
                take(p, 3, e);
                return;
            }
            case EMERALD_ORE: {
                take(p, 50, e);
                return;
            }
            case GOLD_ORE: {
                take(p, 5, e);
                return;
            }
            case SAPLING: {
                give(p, 1);
                return;
            }
            case LOG: {
                take(p, 0.2, e);
            }
        }
    }


    @EventHandler(ignoreCancelled = true)
    private void onCraft(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        String item = e.getCurrentItem().getType().toString();

        switch (item) {
            case "SAPPHIREBREAD_TIESTO": {
                give(p, 5);
                return;
            }
            case "CAKE": {
                give(p, 3);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onKill(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        Entity en = e.getEntity();
        new Thread(() -> {
            try {
                Thread.sleep(700);
                if (!en.isDead()) return;
                Player p = (Player) e.getDamager();
                EntityType type = en.getType();

                switch (type) {
                    case SKELETON: {
                        give(p, 3);
                        return;
                    }
                    case COW: {
                        give(p, 3);
                        return;
                    }
                    case PIG: {
                        give(p, 3.2);
                        return;
                    }
                    case ZOMBIE: {
                        give(p, 1.5);
                        return;
                    }
                    case SHEEP: {
                        give(p, 1);
                        return;
                    }
                    case BAT: {
                        give(p, 0.2);
                        return;
                    }
                    case BLAZE: {
                        give(p, 5);
                        return;
                    }
                    case CHICKEN: {
                        give(p, 1);
                        return;
                    }
                    case CREEPER: {
                        give(p, 2);
                    }
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }).start();

    }

    private void take(Player p, double i, BlockPlaceEvent e) {
        i = i * coeff.getCoeff();
        if (eco.getBalance(p) < i) {
            p.sendMessage(ChatColor.RED + "Вы не можете сломать этот блок из-за нехватки средств.");
            e.setCancelled(true);
        }
        eco.withdrawPlayer(p, i);
    }

    private void take(Player p, double i, BlockBreakEvent e) {
        i = i * coeff.getCoeff();
        if (eco.getBalance(p) < i) {
            p.sendMessage(ChatColor.RED + "Вы не можете поставить этот блок из-за нехватки средств.");
            e.setCancelled(true);
        }
        eco.withdrawPlayer(p, i);
    }


    private void give(Player p, double money) {
        money = money * coeff.getCoeff();
        eco.depositPlayer(p, money);
    }
}
