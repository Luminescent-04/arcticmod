package net.diego.arcticpoi.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RotationalBlock extends HorizontalDirectionalBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // Example hitbox — adjust to match your chair model
    private static final VoxelShape SHAPE_NORTH = box(2, 0, 2, 14, 10, 14);
    private static final VoxelShape SHAPE_SOUTH = box(2, 0, 2, 14, 10, 14);
    private static final VoxelShape SHAPE_EAST  = box(2, 0, 2, 14, 10, 14);
    private static final VoxelShape SHAPE_WEST  = box(2, 0, 2, 14, 10, 14);

    public RotationalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level,
                               BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case SOUTH: return SHAPE_SOUTH;
            case EAST:  return SHAPE_EAST;
            case WEST:  return SHAPE_WEST;
            case NORTH:
            default:    return SHAPE_NORTH;
        }
    }
}
