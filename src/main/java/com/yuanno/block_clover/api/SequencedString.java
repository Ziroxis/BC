package com.yuanno.block_clover.api;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.StringTextComponent;

import java.awt.*;
import java.util.List;

public class SequencedString
{
	public String string;
	public int maxLength;
	public int color = Color.WHITE.getRGB();
	public char[] chars;
	public int maxTicks;
	public int tickCount;
	public Minecraft mc;
	public int delayTicks = this.maxTicks;

	public SequencedString(String str, int maxLength, int maxTicks)
	{
		this(str, maxLength, maxTicks, maxTicks + 5 * 20);
	}
	
	public SequencedString(String str, int maxLength, int maxTicks, int delay)
	{
		this.mc = Minecraft.getInstance();
		this.string = str;
		this.maxLength = maxLength;
		this.chars = new char[this.string.length()];
		for (int i = 0; i < this.string.length(); i++)
		{
			this.chars[i] = this.string.charAt(i);
		}
		this.maxTicks = maxTicks;
		this.tickCount = 0;
		this.delayTicks = delay;
	}

	public void render(MatrixStack matrixStack, int posX, int posY)
	{
		String tempStr = "";
		for (int i = 0; i < this.chars.length; i++)
		{
			if (this.tickCount >= this.calculateTicksNeeded(i) && this.tickCount < this.delayTicks)
			{
				tempStr = tempStr + this.chars[i];
			}
		}
		List<IReorderingProcessor> strings = this.mc.font.split(new StringTextComponent(tempStr), this.maxLength);
		//List<String> strings = WyHelper.splitString(this.mc.font, tempStr, posX, this.maxLength);
		for (int b = 0; b < strings.size(); b++)
		{
			Beapi.drawStringWithBorder(this.mc.font, matrixStack, strings.get(b), posX, posY + 10 * b, this.color);
		}

		this.tickCount++;
	}

	public int calculateTicksNeeded(int index)
	{
		int oldRange = this.string.length();
		int newRange = this.maxTicks;
		int newValue = (((index) * newRange) / oldRange);

		return newValue;
	}
}
