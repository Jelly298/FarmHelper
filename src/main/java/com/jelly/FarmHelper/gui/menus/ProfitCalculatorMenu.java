package com.jelly.FarmHelper.gui.menus;

import com.jelly.FarmHelper.gui.components.Toggle;
import gg.essential.elementa.components.UIContainer;

public class ProfitCalculatorMenu extends UIContainer {
    public ProfitCalculatorMenu() {
        new Toggle("Enabled", "profitCalculator").setChildOf(this);
        new Toggle("Total Profit", "totalProfit").setChildOf(this);
        new Toggle("Profit per Hour", "profitHour").setChildOf(this);
        new Toggle("Item Count", "itemCount").setChildOf(this);
        new Toggle("Mushroom Count", "mushroomCount").setChildOf(this);
        new Toggle("Counter", "counter").setChildOf(this);
        new Toggle("Runtime", "runtime").setChildOf(this);
    }
}
