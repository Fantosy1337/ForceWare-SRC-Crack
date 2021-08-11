package me.force.ware.module.render;

import me.force.ware.module.*;
import net.minecraftforge.client.event.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class WaterMark extends Module
{
    public WaterMark() {
        super("WaterMark", "", Category.RENDER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text e) {
        GlStateManager.func_179152_a(1.0f, 1.0f, 1.0f);
        final String nurik = "ForceWare| Daimecs | " + WaterMark.mc.func_147104_D().field_78845_b.toLowerCase();
        this.drawRect(1.0, 4.5, 2 + WaterMark.mc.field_71466_p.func_78256_a(nurik) + 1, 5.5f + WaterMark.mc.field_71466_p.field_78288_b + 1.0f, -1879048192);
        WaterMark.mc.field_71466_p.func_175063_a(nurik, 2.0f, 5.5f, 16777215);
        GlStateManager.func_179152_a(1.0f, 1.0f, 1.0f);
        final double posY = 0.0;
        final double width = WaterMark.mc.field_71466_p.func_78256_a(nurik) + 2;
        this.drawGradientRect(1.0, posY + 3.0, 4.0 + width / 3.0, posY + 4.5, new Color(17, 75, 172, 255).getRGB(), new Color(17, 75, 172, 255).getRGB());
        this.drawGradientRect(1.0 + width / 3.0, posY + 3.0, 1.0 + width / 3.0 * 2.0, posY + 4.5, new Color(17, 75, 172, 255).getRGB(), new Color(187, 30, 68, 255).getRGB());
        this.drawGradientRect(1.0 + width / 3.0 * 2.0, posY + 3.0, width / 3.0 * 3.0 + 1.0, posY + 4.5, new Color(187, 30, 68, 255).getRGB(), new Color(187, 30, 68, 255).getRGB());
    }
    
    private void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            final double j = top;
            top = bottom;
            bottom = j;
        }
        final float f3 = (color >> 24 & 0xFF) / 255.0f;
        final float f4 = (color >> 16 & 0xFF) / 255.0f;
        final float f5 = (color >> 8 & 0xFF) / 255.0f;
        final float f6 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c(f4, f5, f6, f3);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferbuilder.func_181662_b(left, bottom, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(right, bottom, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(right, top, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(left, top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public void drawGradientRect(final double left, final double top, final double right, final double bottom, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j(7425);
        final Tessellator tessellator = Tessellator.func_178181_a();
        final BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(right, top, 1.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        bufferbuilder.func_181662_b(left, top, 1.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        bufferbuilder.func_181662_b(left, bottom, 1.0).func_181666_a(f2, f3, f4, f).func_181675_d();
        bufferbuilder.func_181662_b(right, bottom, 1.0).func_181666_a(f6, f7, f8, f5).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }
}
