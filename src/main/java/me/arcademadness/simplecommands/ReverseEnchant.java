package me.arcademadness.simplecommands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseEnchant implements Listener {

    HashMap<ItemStack, List<ItemStack>> anvilTransaction = new HashMap<>();

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

            List<ItemStack> anvilItems = new ArrayList<>();
            anvilItems.add(event.getInventory().getFirstItem());
            anvilItems.add(event.getInventory().getSecondItem());

            anvilTransaction.put(newBook, anvilItems);
        }
    }

    @EventHandler
    private void onInventory(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (event.getSlotType() != InventoryType.SlotType.RESULT) return;

        if (event.getInventory().getType() != InventoryType.ANVIL) return;
        AnvilInventory anv = (AnvilInventory) event.getInventory();

        List<ItemStack> oldInventory = anvilTransaction.get(anv.getResult());
        if (oldInventory == null) return;

        if (oldInventory.get(0).equals(anv.getFirstItem()) && oldInventory.get(1).equals(anv.getSecondItem())) {
            ItemStack firstItem = oldInventory.get(0);
            ItemStack secondItem = oldInventory.get(1);

            ItemMeta firstMeta = firstItem.getItemMeta();

            Map<Enchantment, Integer> newEnchants = firstItem.getItemMeta().getEnchants();
            for (Enchantment e : newEnchants.keySet()) {
                firstMeta.removeEnchant(e);
            }

            firstItem.setItemMeta(firstMeta);
            secondItem.setAmount(secondItem.getAmount()-1);

            anvilTransaction.remove(anv.getResult());

            //Fuck this
            Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
                anv.setFirstItem(firstItem);
                anv.setSecondItem(secondItem);
            }, 0L);
        }
    }
}
