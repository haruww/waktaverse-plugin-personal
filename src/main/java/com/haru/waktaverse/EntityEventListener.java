package com.haru.waktaverse;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class EntityEventListener implements Listener
{
    @EventHandler
    public void blockDrop(ItemSpawnEvent e)
    {
        Item item = e.getEntity();
        item.setCustomNameVisible(true);
        item.setCustomName(item.getName());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if(e.getEntity().getName().equals("Tozi"))
        {
            Bukkit.broadcastMessage("데미지: " + e.getDamage());
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e)
    {
        Location loc = e.getEntity().getLocation();
        Entity deadEntity = e.getEntity();
        World w = deadEntity.getWorld();
        if(deadEntity.getName().equals("explod"))
        {
            w.createExplosion(loc, 4F, false, false);
            w.spawnParticle(Particle.EXPLOSION_HUGE, loc, 2);
        }
    }

    @EventHandler
    public void onEntityDamaged(EntityDamageByEntityEvent e)
    {
        Location loc = e.getEntity().getLocation();
        Entity damager = e.getDamager();
        World w = damager.getWorld();
        if(damager.getName().equals("explo"))
        {
            w.createExplosion(loc,1,false,false);
            w.spawnParticle(Particle.EXPLOSION_HUGE, loc, 1);
        }
    }


    @EventHandler
    public void onEnitityTeleport(EntityTeleportEvent e)
    {
        if(e.getEntityType().equals(EntityType.SHULKER))
        {
            Bukkit.broadcastMessage("SHULKER TPED");
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e)
    {
        //Bukkit.broadcastMessage("HIT : " + e.getEntityType().name());
        /*Location loc = e.getHitBlock().getLocation();
        loc.setY(loc.getY()+1);
        AreaEffectCloud cloud = (AreaEffectCloud) loc.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
        cloud.setRadius(3);
        cloud.setDuration(100);
        PotionEffect pe = new PotionEffect(PotionEffectType.POISON, 60, 0);
        cloud.addCustomEffect(pe, false);
        cloud.setParticle(Particle.REDSTONE, Color.GREEN);*/
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e)
    {
        Entity entity = e.getEntity();
        World world = entity.getWorld();
        Location loc=entity.getLocation();

        boolean[] entityb = new boolean[3];

        for(Entity ent : world.getNearbyEntities(loc, 1, 1, 1))
        {
            if(ent.getName().equals("poisonshoot"))
                entityb[0] = true;
            if(ent.getName().equals("spider"))
                entityb[1] = true;
        }

        if(entityb[0] && e.getEntityType().name().equals("CUSTOMNPCS_CUSTOMNPCPROJECTILE"))
        {
            //Bukkit.broadcastMessage(entity.getUniqueId().toString());
            ArmorStand as = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
            as.setInvulnerable(true);
            as.setVisible(false);
            as.setCollidable(false);
            as.setSmall(true);
            as.setRemoveWhenFarAway(false);
            entity.addPassenger(as);

            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    Location locas = as.getLocation();
                    if(as.isValid() && as.isOnGround())
                    {
                        as.remove();
                        AreaEffectCloud cloud = (AreaEffectCloud) world.spawnEntity(locas, EntityType.AREA_EFFECT_CLOUD);
                        cloud.setRadius(2);
                        cloud.setDuration(50);
                        PotionEffect pe = new PotionEffect(PotionEffectType.POISON, 60, 0);
                        cloud.addCustomEffect(pe, false);
                        cloud.setParticle(Particle.REDSTONE, new Particle.DustOptions(Color.GREEN, 1.0f));

                        this.cancel();
                    }
                }
            }.runTaskTimer(Waktaverse.getInstance(), 0, 1L);
        }

        else if(entityb[1] && e.getEntityType().name().equals("CUSTOMNPCS_CUSTOMNPCPROJECTILE"))
        {
            ArmorStand as = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
            as.setInvulnerable(true);
            as.setVisible(false);
            as.setSmall(true);
            as.setRemoveWhenFarAway(false);
            entity.addPassenger(as);

            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    Location asloc = as.getLocation();
                    for(Entity ent : world.getNearbyEntities(asloc, 0.5, 3, 0.5))
                    {
                        if(ent instanceof Player)
                        {
                            Location entloc = ent.getLocation();
                            Block b = entloc.getBlock();
                            if(b.isEmpty())
                                b.setType(Material.COBWEB);
                            as.remove();
                            this.cancel();
                        }
                    }

                    if(as.isOnGround() && !as.getLocation().getBlock().getType().equals(Material.COBWEB))
                    {
                        as.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer(Waktaverse.getInstance(), 0, 1L);
        }

        else if(entity.getName().equals("disappear"))
        {
            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    for(Entity ent : world.getNearbyEntities(loc, 10, 10, 10))
                    {
                        if(ent instanceof Player)
                        {
                            world.spawnParticle(Particle.SMOKE_NORMAL, loc, 150);
                            entity.remove();
                            this.cancel();
                        }
                    }
                }
            }.runTaskTimer(Waktaverse.getInstance(), 0, 1L);
        }
    }
}
