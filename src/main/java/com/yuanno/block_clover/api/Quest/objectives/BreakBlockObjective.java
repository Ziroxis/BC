package com.yuanno.block_clover.api.Quest.objectives;

import com.yuanno.block_clover.api.Quest.Objective;
import com.yuanno.block_clover.api.Quest.interfaces.IBreakBlocksObjective;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class BreakBlockObjective extends Objective implements IBreakBlocksObjective {
    private Predicate<Block> check = (block) -> false;

    public BreakBlockObjective(String title, int count, Block blockTarget, IBreakBlock check)
    {
        super(title);
        this.setMaxProgress(count);
        this.check = (block) -> block.getBlock() == blockTarget.getBlock();
    }

    @Override
    public boolean checkBreakBlock(PlayerEntity player, Block block) {
        return this.check.test(block);
    }

    @FunctionalInterface
    public interface IBreakBlock
    {
        boolean test(PlayerEntity player, int count, Block block);

        default IBreakBlock and(IBreakBlock check)
        {
            return (player, count, block) -> {
                return this.test(player, count, block) && check.test(player, count, block);
            };
        }

        default IBreakBlock or(IBreakBlock check)
        {
            return (player, count, block) -> {
              return this.test(player, count, block) || check.test(player, count, block);
            };
        }
    }
}
