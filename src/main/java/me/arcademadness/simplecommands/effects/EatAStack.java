package me.arcademadness.simplecommands.effects;

import me.arcademadness.simplecommands.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EatAStack implements Listener {

    @EventHandler
    public void onEatCarrot(PlayerItemConsumeEvent event) {
        if (event.getItem().isSimilar(new ItemStack(Material.CARROT))) {
            Player p = event.getPlayer();
            int slot = event.getPlayer().getInventory().getHeldItemSlot();
            new BukkitRunnable() {
                int index = 0;
                @Override
                public void run() {
                    index++;
                    if (index > 64) cancel();
                    if (p.getSaturation() > 29) cancel();

                    float sat = 3.6f;
                    int hun = 0;

                    if (p.getFoodLevel() < 20) {
                        hun = 3;
                    }
                    ItemStack item = p.getInventory().getItem(slot);
                    if (item.isSimilar(new ItemStack(Material.CARROT))) {
                        if (item.getAmount()-1 <= 0)
                            item = new ItemStack(Material.AIR);
                        else
                            item.setAmount(item.getAmount() - 1);
                        p.getInventory().setItem(slot, item);
                        p.setFoodLevel(p.getFoodLevel() + hun);
                        p.setSaturation(p.getSaturation() + sat);
                        if (p.getPotionEffect(PotionEffectType.HUNGER) != null) {
                            if (p.getPotionEffect(PotionEffectType.HUNGER).getDuration() - 1200 <= 0) {
                                p.removePotionEffect(PotionEffectType.HUNGER);
                            } else {
                                int dur = p.getPotionEffect(PotionEffectType.HUNGER).getDuration() - 1200;
                                int amp = p.getPotionEffect(PotionEffectType.HUNGER).getAmplifier();
                                p.removePotionEffect(PotionEffectType.HUNGER);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, dur, amp));
                                p.setSaturation(0);
                            }
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(), 0L, 1L);
        }
    }

    @EventHandler
    public void onEatFlesh(PlayerItemConsumeEvent event) {
        if (event.getItem().isSimilar(new ItemStack(Material.ROTTEN_FLESH))) {
            Player p = event.getPlayer();
            int slot = event.getPlayer().getInventory().getHeldItemSlot();
            new BukkitRunnable() {
                int index = 0;
                @Override
                public void run() {
                    if (index >= 128) cancel();
                    index++;

                    ItemStack item = p.getInventory().getItem(slot);
                    if (item.isSimilar(new ItemStack(Material.ROTTEN_FLESH))) {
                        if (item.getAmount()-1 <= 0)
                            item = new ItemStack(Material.AIR);
                        else
                            item.setAmount(item.getAmount() - 1);
                        p.getInventory().setItem(slot, item);
                        if (p.getPotionEffect(PotionEffectType.HUNGER) == null) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 0));
                        } else {
                            int amp = p.getPotionEffect(PotionEffectType.HUNGER).getAmplifier();
                            if (index % 16 == 0) amp+=1;
                            p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, (p.getPotionEffect(PotionEffectType.HUNGER).getDuration()+600), amp));
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(), 0L, 1L);
        }
    }
}
