package me.gallowsdove.foxymachines.tools;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.Bukkit;
import me.gallowsdove.foxymachines.Items;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;


public class ElectricWindStaff extends SlimefunItem implements Rechargeable {

  private static final float COST = 0.75F;

  public ElectricWindStaff() {
    super(Items.tools, Items.ELECTRIC_WIND_STAFF, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
      null, null, null,
      null, SlimefunItems.SMALL_CAPACITOR, null,
      null, null, null
    });
  }

  @Override
  public float getMaxItemCharge(ItemStack item) {
    return 100;
  }

  protected ItemUseHandler getItemUseHandler() {
    return e -> {
      Player p = e.getPlayer();
      ItemStack item = e.getItem();

      if (removeItemCharge(item, COST)) {
        p.setVelocity(p.getEyeLocation().getDirection().multiply(4));
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
        p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 1);
        p.setFallDistance(0F);
      }
    };
  }

  private ToolUseHandler getToolUseHandler() {
    return (e, tool, fortune, drops) -> {
      e.setCancelled(true);
    };
  }

  @Override
  public void preRegister() {
    super.preRegister();

    addItemHandler(getItemUseHandler());
    addItemHandler(getToolUseHandler());
  }
}