package net.diego.arcticpoi.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

// Make sure this import matches your RotationalBlock class

public class RotationalNoCollideBlock extends RotationalBlock {

    // Example small decor hitbox (can be adjusted per block type)
    private static final VoxelShape HITBOX = Block.box(2, 0, 2, 14, 8, 14);

    public RotationalNoCollideBlock(Properties properties) {
        super(properties);
    }

    // Collision shape: empty so player can walk through
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    // Hitbox / selection shape: small box for breaking and interacting
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return HITBOX;
    }
}
