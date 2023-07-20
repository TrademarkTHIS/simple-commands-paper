package me.arcademadness.simplecommands;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class ReverseEnchant implements Listener {

    @EventHandler
    private void onAnvil(PrepareAnvilEvent event) {
        if (event.getInventory().getFirstItem() == null) return;
        if (event.getInventory().getSecondItem() == null) return;
        if (!event.getInventory().getSecondItem().isSimilar(new ItemStack(Material.BOOK))) return;

        if (event.getInventory().getFirstItem().getEnchantments().size() > 0) {

            ItemStack newBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
            if (!(newBook.getItemMeta() instanceof EnchantmentStorageMeta)) return;
            EnchantmentStorageMeta newBookMeta = (EnchantmentStorageMeta) newBook.getItemMeta();

            Map<Enchantment, Integer> newEnchants = event.getInventory().getFirstItem().getItemMeta().getEnchants();
            for (Enchantment e : newEnchants.keySet()) {
                newBookMeta.addStoredEnchant(e, newEnchants.get(e), false);
            }
            newBook.setItemMeta(newBookMeta);

            event.getInventory().setRepairCost(0);
            event.setResult(newBook);
        }
    }
}
