package me.arcademadness.simplecommands.crafting;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RepairAnvil implements Listener {

    @EventHandler
    private void onHealAnvil(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (!p.getInventory().getItemInMainHand().isSimilar(new ItemStack(Material.IRON_BLOCK))) return;
        if (!p.isSneaking()) return;
        if (event.getClickedBlock().getType() != Material.DAMAGED_ANVIL && event.getClickedBlock().getType() != Material.CHIPPED_ANVIL) return;
        event.setCancelled(true);

        if (p.getGameMode() != GameMode.CREATIVE) {
            if (p.getInventory().getItemInMainHand().getAmount() - 1 <= 0) {
                p.getInventory().remove(p.getInventory().getItemInMainHand());
            } else {
                p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
            }
        }
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1f);
        }

        if (event.getClickedBlock().getType() == Material.DAMAGED_ANVIL) {
            Directional before = (Directional) event.getClickedBlock().getBlockData();
            BlockFace bf = before.getFacing();

            event.getClickedBlock().setType(Material.CHIPPED_ANVIL);

            Directional after = (Directional) event.getClickedBlock().getBlockData();
            after.setFacing(bf);
            event.getClickedBlock().setBlockData(after);
            return;
        }
        if (event.getClickedBlock().getType() == Material.CHIPPED_ANVIL) {
            Directional before = (Directional) event.getClickedBlock().getBlockData();
            BlockFace bf = before.getFacing();

            event.getClickedBlock().setType(Material.ANVIL);

            Directional after = (Directional) event.getClickedBlock().getBlockData();
            after.setFacing(bf);
            event.getClickedBlock().setBlockData(after);
        }
    }
}
