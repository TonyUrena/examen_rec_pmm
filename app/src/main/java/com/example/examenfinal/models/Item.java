package com.example.examenfinal.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    private String name;
    private int cost;
    private ItemCategory category;
    @SerializedName("effect_entries")
    private List<ItemEffectEntry> effectEntries;
    private ItemSprites sprites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public List<ItemEffectEntry> getEffectEntries() {
        return effectEntries;
    }

    public void setEffectEntries(List<ItemEffectEntry> effectEntries) {
        this.effectEntries = effectEntries;
    }

    public ItemSprites getSprites() {
        return sprites;
    }

    public void setSprites(ItemSprites sprites) {
        this.sprites = sprites;
    }

    // Clase interna para la categoría del ítem
    public class ItemCategory {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {}
    }

    // Clase interna para los efectos del ítem
    public class ItemEffectEntry {
        @SerializedName("short_effect")
        private String shortEffect;

        public String getShortEffect() {
            return shortEffect;
        }

        public void setShortEffect(String shortEffect) {}
    }

    // Clase interna para los sprites del ítem
    public class ItemSprites {
        @SerializedName("default")
        private String defaultSprite;

        public String getDefaultSprite() {
            return defaultSprite;
        }
    }

    public String getEffectsString() {
        StringBuilder effectsBuilder = new StringBuilder();
        if (effectEntries != null) {
            for (ItemEffectEntry entry : effectEntries) {
                effectsBuilder.append(entry.getShortEffect()).append("\n");
            }
        }
        return effectsBuilder.toString();
    }
}