package net.beetle.naturestreasures.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class InsectEncyclopediaScreen extends HandledScreen<InsectEncyclopediaScreenHandler> {

    private static final int BOOK_W = 234;
    private static final int BOOK_H = 184;

    private static final int COVER_BORDER = 5;
    private static final int SPINE_W      = 8;
    private static final int PAGE_W       = (BOOK_W - COVER_BORDER * 2 - SPINE_W) / 2; // ~108
    private static final int PAGE_H       = BOOK_H - COVER_BORDER * 2;                  // ~174
    private static final int LEFT_PAGE_X  = COVER_BORDER;
    private static final int LEFT_PAGE_Y  = COVER_BORDER;
    private static final int RIGHT_PAGE_X = COVER_BORDER + PAGE_W + SPINE_W;


    private static final int MARGIN    = 8;
    private static final int CX_L      = LEFT_PAGE_X  + MARGIN;   // left-page content start X
    private static final int CX_R      = RIGHT_PAGE_X + MARGIN;   // right-page content start X
    private static final int CY        = LEFT_PAGE_Y  + MARGIN;   // content start Y (both pages)
    private static final int CW        = PAGE_W - MARGIN * 2;     // content width (~92px)


    private static final int ENTRY_H      = 11;
    private static final int MAX_ENTRIES  = 7;
    private static final int LIST_TOP_OFF = 62;



    private static final int COL_COVER        = 0xFF3B2512;
    private static final int COL_COVER_EDGE   = 0xFF2A1A0C;
    private static final int COL_COVER_SHINE  = 0x22FFFFFF;
    private static final int COL_SPINE        = 0xFF1E1208;
    private static final int COL_PAGE         = 0xFFEDE0C8;
    private static final int COL_PAGE_SHADOW  = 0xFFD9CDB8;
    private static final int COL_SHADOW       = 0x55000000;


    private static final int C_TITLE    = 0xFF2C1810;
    private static final int C_BODY     = 0xFF3D2B1F;
    private static final int C_SOFT     = 0xFF6B5A3E;
    private static final int C_ACCENT   = 0xFF8B6914;
    private static final int C_DIVIDER  = 0xFF8B7340;
    private static final int C_SEL_BG   = 0x33C8A850;
    private static final int C_HOV_BG   = 0x1AC8A850;
    private static final int C_BAR_BG   = 0x44000000;
    private static final int C_BAR_FILL = 0xCC8B6914;


    private final List<InsectEntry> entries = new ArrayList<>();
    private int selectedIndex = -1;
    private int scrollOffset  = 0;


    public InsectEncyclopediaScreen(InsectEncyclopediaScreenHandler handler,
                                    PlayerInventory inventory,
                                    Text title) {
        super(handler, inventory, title);
        this.backgroundWidth       = BOOK_W;
        this.backgroundHeight      = BOOK_H;
        this.playerInventoryTitleY = 10000;
    }


    private void loadEntries() {
        entries.clear();
        if (this.client == null || this.client.player == null) return;

        ItemStack stack = this.client.player.getMainHandStack();
        if (!(stack.getItem() instanceof net.beetle.naturestreasures.item.custom.InsectEncyclopediaItem)) {
            stack = this.client.player.getOffHandStack();
        }
        if (!(stack.getItem() instanceof net.beetle.naturestreasures.item.custom.InsectEncyclopediaItem)) return;

        NbtCompound tag = stack.getNbt();
        if (tag != null && tag.contains("Entries", NbtList.LIST_TYPE)) {
            NbtList list = tag.getList("Entries", NbtList.COMPOUND_TYPE);
            for (int i = 0; i < list.size(); i++) {
                NbtCompound e = list.getCompound(i);
                entries.add(new InsectEntry(
                        e.getString("id"),
                        e.getString("name"),
                        e.getInt("difficulty"),
                        e.getFloat("chance"),
                        e.getString("lore")
                ));
            }
        }
    }


    @Override
    protected void init() {
        super.init();
        loadEntries();
    }


    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        drawBookChrome(context);
        drawLeftPage(context, mouseX, mouseY);
        drawRightPage(context);
        drawScrollArrows(context);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) { }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) { }

    private void drawBookChrome(DrawContext context) {
        int bx = this.x;
        int by = this.y;


        context.fill(bx + 4, by + 4, bx + BOOK_W + 4, by + BOOK_H + 4, COL_SHADOW);


        context.fill(bx, by, bx + BOOK_W, by + BOOK_H, COL_COVER);


        context.fill(bx + 1, by + 1, bx + BOOK_W - 1, by + 2, COL_COVER_EDGE);           // top
        context.fill(bx + 1, by + BOOK_H - 2, bx + BOOK_W - 1, by + BOOK_H - 1, COL_COVER_EDGE); // bottom
        context.fill(bx + 1, by + 1, bx + 2, by + BOOK_H - 1, COL_COVER_EDGE);           // left
        context.fill(bx + BOOK_W - 2, by + 1, bx + BOOK_W - 1, by + BOOK_H - 1, COL_COVER_EDGE); // right


        context.fill(bx + 2, by + 2, bx + BOOK_W - 2, by + 3, COL_COVER_SHINE);
        context.fill(bx + 2, by + 2, bx + 3, by + BOOK_H - 2, COL_COVER_SHINE);


        int lpx = bx + LEFT_PAGE_X;
        int lpy = by + LEFT_PAGE_Y;
        context.fill(lpx, lpy, lpx + PAGE_W, lpy + PAGE_H, COL_PAGE);

        context.fill(lpx + PAGE_W - 3, lpy, lpx + PAGE_W, lpy + PAGE_H, COL_PAGE_SHADOW);


        int spx = bx + LEFT_PAGE_X + PAGE_W;
        context.fill(spx, by + 2, spx + SPINE_W, by + BOOK_H - 2, COL_SPINE);
        context.fill(spx, by + 2, spx + 1, by + BOOK_H - 2, 0x22FFFFFF); // spine shine


        int rpx = bx + RIGHT_PAGE_X;
        context.fill(rpx, lpy, rpx + PAGE_W, lpy + PAGE_H, COL_PAGE);

        context.fill(rpx, lpy, rpx + 3, lpy + PAGE_H, COL_PAGE_SHADOW);

        for (int i = 0; i < PAGE_H; i += 12) {
            int dy = lpy + i + 4;
            context.fill(spx - 1, dy, spx,          dy + 2, 0x55C8A870);
            context.fill(spx + SPINE_W, dy, spx + SPINE_W + 1, dy + 2, 0x55C8A870);
        }
    }


    private void drawLeftPage(DrawContext context, int mouseX, int mouseY) {
        int px = this.x + CX_L;
        int py = this.y + CY;


        context.drawText(this.textRenderer, this.title, px, py, C_TITLE, false);
        context.fill(px, py + 10, px + CW, py + 11, C_DIVIDER);


        context.drawTextWrapped(
                this.textRenderer,
                Text.literal("Catch insects with a Bug Net to record them here."),
                px, py + 14, CW, C_SOFT
        );


        context.drawText(this.textRenderer, entries.size() + " discovered", px, py + 44, C_ACCENT, false);
        context.fill(px, py + 54, px + CW, py + 55, C_DIVIDER);


        int listY = this.y + CY + LIST_TOP_OFF;

        for (int i = 0; i < MAX_ENTRIES && (scrollOffset + i) < entries.size(); i++) {
            InsectEntry entry = entries.get(scrollOffset + i);
            int globalIdx     = scrollOffset + i;
            int entryY        = listY + i * ENTRY_H;

            boolean hovered  = mouseX >= px && mouseX < px + CW
                    && mouseY >= entryY && mouseY < entryY + ENTRY_H;
            boolean selected = selectedIndex == globalIdx;

            if (selected) {
                context.fill(px - 2, entryY - 1, px + CW + 2, entryY + ENTRY_H - 1, C_SEL_BG);
            } else if (hovered) {
                context.fill(px - 2, entryY - 1, px + CW + 2, entryY + ENTRY_H - 1, C_HOV_BG);
            }

            String prefix = selected ? "\u2022 " : "  "; // bullet vs spaces
            int textColor = selected ? C_TITLE : C_BODY;

            String label = prefix + entry.name;
            while (this.textRenderer.getWidth(label) > CW && label.length() > 3) {
                label = label.substring(0, label.length() - 4) + "\u2026";
            }
            context.drawText(this.textRenderer, label, px, entryY, textColor, false);
        }
    }


    private void drawRightPage(DrawContext context) {
        int px = this.x + CX_R;
        int py = this.y + CY;


        if (selectedIndex < 0 || selectedIndex >= entries.size()) {
            context.drawTextWrapped(
                    this.textRenderer,
                    Text.literal("Select an entry on the left to read about it."),
                    px, py + 50, CW, C_SOFT
            );
            return;
        }

        InsectEntry entry = entries.get(selectedIndex);


        context.drawText(this.textRenderer, entry.name, px, py, C_TITLE, false);
        context.fill(px, py + 10, px + CW, py + 11, C_DIVIDER);


        int sy = py + 17;
        context.drawText(this.textRenderer, "Difficulty", px, sy, C_SOFT, false);
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stars.append(i < entry.difficulty ? "\u2605" : "\u2606"); // ★ ☆
        }
        context.drawText(this.textRenderer, stars.toString(), px, sy + 10, C_ACCENT, false);


        int cy = sy + 27;
        context.drawText(this.textRenderer, "Catch Chance", px, cy, C_SOFT, false);
        int barFill = Math.max(2, (int)(CW * entry.baseChance));
        context.fill(px, cy + 10, px + CW,      cy + 15, C_BAR_BG);
        context.fill(px, cy + 10, px + barFill, cy + 15, C_BAR_FILL);
        context.fill(px, cy + 10, px + barFill, cy + 11, 0x55FFD080); // bar shine
        context.drawText(this.textRenderer,
                (int)(entry.baseChance * 100) + "%", px, cy + 17, C_ACCENT, false);


        int ly = cy + 31;
        context.fill(px, ly, px + CW, ly + 1, C_DIVIDER);
        context.drawTextWrapped(
                this.textRenderer,
                Text.literal(entry.lore),
                px, ly + 6, CW, C_BODY
        );
    }


    private void drawScrollArrows(DrawContext context) {
        if (entries.size() <= MAX_ENTRIES) return;

        int arrowX    = this.x + CX_L + CW / 2 - 3;
        int listBottom = this.y + CY + LIST_TOP_OFF + MAX_ENTRIES * ENTRY_H + 2;

        if (scrollOffset > 0) {
            context.drawText(this.textRenderer, "\u25b2", arrowX, listBottom - 12, C_ACCENT, false);
        }
        if (scrollOffset + MAX_ENTRIES < entries.size()) {
            context.drawText(this.textRenderer, "\u25bc", arrowX, listBottom, C_ACCENT, false);
        }
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int px    = this.x + CX_L;
        int listY = this.y + CY + LIST_TOP_OFF;

        for (int i = 0; i < MAX_ENTRIES && (scrollOffset + i) < entries.size(); i++) {
            int entryY = listY + i * ENTRY_H;
            if (mouseX >= px && mouseX < px + CW
                    && mouseY >= entryY && mouseY < entryY + ENTRY_H) {
                selectedIndex = scrollOffset + i;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        int maxScroll = Math.max(0, entries.size() - MAX_ENTRIES);
        scrollOffset  = (int) Math.max(0, Math.min(scrollOffset - amount, maxScroll));
        return true;
    }


    private static class InsectEntry {
        final String id, name, lore;
        final int    difficulty;
        final float  baseChance;

        InsectEntry(String id, String name, int difficulty, float baseChance, String lore) {
            this.id         = id;
            this.name       = name;
            this.difficulty = difficulty;
            this.baseChance = baseChance;
            this.lore       = lore;
        }
    }
}